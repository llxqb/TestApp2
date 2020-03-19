package com.llxqb.testapp.mvp.read;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.llxqb.testapp.MainActivity;
import com.llxqb.testapp.R;
import com.llxqb.testapp.entity.request.BookContentRequest;
import com.llxqb.testapp.entity.request.ReadingBookRequest;
import com.llxqb.testapp.entity.request.SelectionRequest;
import com.llxqb.testapp.entity.response.BookContentResponse;
import com.llxqb.testapp.entity.response.ReadingBookResponse;
import com.llxqb.testapp.entity.response.SelectionResponse;
import com.llxqb.testapp.help.RetryWithDelay;
import com.llxqb.testapp.ireader.model.bean.BookChapterBean;
import com.llxqb.testapp.ireader.model.bean.ChapterInfoBean;
import com.llxqb.testapp.ireader.model.local.BookRepository;
import com.llxqb.testapp.ireader.model.remote.RemoteRepository;
import com.llxqb.testapp.ireader.utils.MD5Utils;
import com.llxqb.testapp.ireader.utils.RxUtils;
import com.llxqb.testapp.ireader.widget.page.TxtChapter;
import com.llxqb.testapp.mvp.model.BookModel;
import com.llxqb.testapp.mvp.model.ResponseData;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterReadImpl
 */

public class ReadBookPresenterImpl implements ReadBookControl.PresenterReadBook {

    private ReadBookControl.ReadBookView mReadBookView;
    private final BookModel mBookModel;
    private final Context mContext;
    private Subscription mChapterSub;

    @Inject
    public ReadBookPresenterImpl(Context context, BookModel model, ReadBookControl.ReadBookView view) {
        mContext = context;
        mBookModel = model;
        mReadBookView = view;
    }


    /**
     * 小说阅读
     */
    @Override
    public void onRequestBookInfo(ReadingBookRequest readingBookRequest) {
        Disposable disposable = mBookModel.onRequestBookInfo(readingBookRequest).compose(mReadBookView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestLoginSuccess, throwable -> mReadBookView.showErrMessage(throwable),
                        () -> mReadBookView.dismissLoading());
        mReadBookView.addSubscription(disposable);
    }

    /**
     * 小说阅读 成功
     */
    private void requestLoginSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(ReadingBookResponse.class);
            if (responseData.parsedData != null) {
                ReadingBookResponse response = (ReadingBookResponse) responseData.parsedData;
                mReadBookView.getReadingBookInfoSuccess(response);
            }
        } else {
            mReadBookView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 请求漫画选集信息
     */
    @Override
    public void onRequestSelectionInfo(SelectionRequest selectionRequest) {
        Disposable disposable = mBookModel.onRequestSelectionInfo(selectionRequest).compose(mReadBookView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestSelectionInfoSuccess, throwable -> mReadBookView.showErrMessage(throwable),
                        () -> mReadBookView.dismissLoading());
        mReadBookView.addSubscription(disposable);
    }

    /**
     * 请求漫画选集信息 成功
     */
    private void requestSelectionInfoSuccess(ResponseData responseData) {
        mReadBookView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(SelectionResponse.class);
            if (responseData.parsedData != null) {
                SelectionResponse response = (SelectionResponse) responseData.parsedData;
                mReadBookView.getSelectionInfoSuccess(response);
            }
        } else {
            mReadBookView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void loadCategory(String bookId) {
        Disposable disposable = RemoteRepository.getInstance()
                .getBookChapters(bookId)
                .doOnSuccess(new Consumer<List<BookChapterBean>>() {
                    @Override
                    public void accept(List<BookChapterBean> bookChapterBeen) throws Exception {
                        //进行设定BookChapter所属的书的id。
                        for (BookChapterBean bookChapter : bookChapterBeen) {
                            bookChapter.setId(MD5Utils.strToMd5By16(bookChapter.getLink()));
                            bookChapter.setBookId(bookId);
                            bookChapter.setLink("https://img.pulaukomik.com/novel/20200220/6aa3ff745de607c578d25a6b4f5fb818.txt");
                        }
                    }
                })
                .compose(RxUtils::toSimpleSingle)
                .subscribe(
                        beans -> {
                            mReadBookView.showCategory(beans);
                        }
                        ,
                        e -> {
                            //TODO: Haven't grate conversation method.
                        }
                );
        mReadBookView.addSubscription(disposable);
    }




    @Override
    public void loadChapter(String bookId, List<TxtChapter> bookChapters) {
        int size = bookChapters.size();

        //取消上次的任务，防止多次加载
        if (mChapterSub != null) {
            mChapterSub.cancel();
        }

        List<Single<ChapterInfoBean>> chapterInfos = new ArrayList<>(bookChapters.size());
        ArrayDeque<String> titles = new ArrayDeque<>(bookChapters.size());

        // 将要下载章节，转换成网络请求。
        for (int i = 0; i < size; ++i) {
            TxtChapter bookChapter = bookChapters.get(i);
            Log.e("ddd", "bookChapter:" + new Gson().toJson(bookChapter));
            // 网络中获取数据 bookChapter.getLink()
            BookContentRequest bookContentRequest = new BookContentRequest();
            bookContentRequest.token = "32a85c49806810b0a141fe8236f97a2f";
            bookContentRequest.catalogue_id = bookChapter.getChapterId();
//            Single<ChapterInfoBean> chapterInfoSingle = RemoteRepository.getInstance()
//                    .getChapterInfo(bookContentRequest);
            onRequestChapterInfo(bookContentRequest,bookId,bookChapter.getTitle());
//            chapterInfos.add(chapterInfoSingle);

            titles.add(bookChapter.getTitle());
        }
//        Single.concat(chapterInfos)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        new Subscriber<ChapterInfoBean>() {
//                            String title = titles.poll();
//
//                            @Override
//                            public void onSubscribe(Subscription s) {
//                                s.request(Integer.MAX_VALUE);
//                                mChapterSub = s;
//                            }
//
//                            @Override
//                            public void onNext(ChapterInfoBean chapterInfoBean) {
//                                Log.e("ddd", "chapterInfoBean:" + new Gson().toJson(chapterInfoBean));
//                                //存储数据
//                                BookRepository.getInstance().saveChapterInfo(
//                                        bookId, title, chapterInfoBean.getContent()
//                                );
//                                mReadBookView.finishChapter();
//                                //将获取到的数据进行存储
//                                title = titles.poll();
//                            }
//
//                            @Override
//                            public void onError(Throwable t) {
//                                //只有第一个加载失败才会调用errorChapter
//                                if (bookChapters.get(0).getTitle().equals(title)) {
//                                    mReadBookView.errorChapter();
//                                }
//                            }
//
//                            @Override
//                            public void onComplete() {
//                            }
//                        }
//                );
    }
    /**
     * 根据章节id获取章节内容
     */
    public void onRequestChapterInfo(BookContentRequest bookContentRequest,String bookId,String title) {
        mBookModel.onRequestChapterInfo(bookContentRequest).compose(mReadBookView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(new Observer<ResponseData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseData responseData) {
                        requestChapterInfoSuccess(responseData,bookId,title);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mReadBookView.dismissLoading();
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }


    /**
     * 根据章节id获取章节内容 成功
     */
    private void requestChapterInfoSuccess(ResponseData responseData,String bookId,String title) {
        mReadBookView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(BookContentResponse.class);
            if (responseData.parsedData != null) {
                BookContentResponse response = (BookContentResponse) responseData.parsedData;
//                mReadBookView.getSelectionInfoSuccess(response);
                //将获取到的数据进行存储
                //存储数据
                BookRepository.getInstance().saveChapterInfo(
                        bookId, title, response.getContent()
                );
                mReadBookView.finishChapter();
            }
        } else {
            mReadBookView.showToast(responseData.errorMsg);
        }

    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        if (mChapterSub != null) {
            mChapterSub.cancel();
        }
    }


//    private String loadFromSDFile() {
//        String xml = null;
//        try {
//            StringBuffer buffer = new StringBuffer();
//            String FilePath = Environment.getExternalStorageDirectory() + "/hs_user.txt";
//            BufferedReader bf = new BufferedReader(new FileReader(FilePath));
//            String s = null;
//            while ((s = bf.readLine()) != null) {//使用readLine方法，一次读一行
//                buffer.append(s.trim());
//            }
//
//            xml = buffer.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.e("ddd", "xml:" + xml);
//        return xml;
//    }


}

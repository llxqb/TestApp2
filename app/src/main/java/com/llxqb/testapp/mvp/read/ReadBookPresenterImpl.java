package com.llxqb.testapp.mvp.read;

import android.content.Context;


import com.llxqb.testapp.R;
import com.llxqb.testapp.entity.request.ReadingBookRequest;
import com.llxqb.testapp.entity.request.SelectionRequest;
import com.llxqb.testapp.entity.response.ReadingBookResponse;
import com.llxqb.testapp.entity.response.SelectionResponse;
import com.llxqb.testapp.help.RetryWithDelay;
import com.llxqb.testapp.mvp.model.BookModel;
import com.llxqb.testapp.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;



/**
 * Created by li.liu on 2017/4/27.
 * PresenterReadImpl
 */

public class ReadBookPresenterImpl implements ReadBookControl.PresenterReadBook {

    private ReadBookControl.ReadBookView mReadBookView;
    private final BookModel mBookModel;
    private final Context mContext;

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
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}

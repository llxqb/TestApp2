package com.llxqb.testapp.mvp.read;


import com.llxqb.testapp.entity.request.ReadingBookRequest;
import com.llxqb.testapp.entity.request.SelectionRequest;
import com.llxqb.testapp.entity.response.ReadingBookResponse;
import com.llxqb.testapp.entity.response.SelectionResponse;
import com.llxqb.testapp.mvp.presenter.LoadDataView;
import com.llxqb.testapp.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/11/14.
 */

public class ReadBookControl {
    public interface ReadBookView extends LoadDataView {
        void getReadingBookInfoSuccess(ReadingBookResponse readingBookResponse);

        void getSelectionInfoSuccess(SelectionResponse selectionResponse);
    }

    public interface PresenterReadBook extends Presenter<ReadBookView> {

        /**
         * 小说阅读
         */
        void onRequestBookInfo(ReadingBookRequest readingBookRequest);


        /**
         * 请求书籍选集信息
         */
        void onRequestSelectionInfo(SelectionRequest selectionRequest);

    }

}

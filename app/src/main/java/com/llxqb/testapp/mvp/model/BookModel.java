package com.llxqb.testapp.mvp.model;

import com.google.gson.Gson;
import com.llxqb.testapp.entity.request.ReadingBookRequest;
import com.llxqb.testapp.entity.request.SelectionRequest;
import com.llxqb.testapp.network.networkapi.BookApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by li.liu on 2019/9/28.
 * MainModel
 */

public class BookModel {
    private final BookApi mBookApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public BookModel(BookApi api, Gson gson, ModelTransform transform) {
        mBookApi = api;
        mGson = gson;
        mTransform = transform;
    }


    /**
     * 小说阅读
     */
    public Observable<ResponseData> onRequestBookInfo(ReadingBookRequest request) {
        return mBookApi.onRequestBookInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }


    /**
     * 请求漫画选集信息
     */
    public Observable<ResponseData> onRequestSelectionInfo(SelectionRequest request) {
        return mBookApi.onRequestSelectionInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }

}

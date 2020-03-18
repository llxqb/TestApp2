package com.llxqb.testapp.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.llxqb.testapp.BuildConfig;
import com.llxqb.testapp.di.scopes.PerActivity;
import com.llxqb.testapp.mvp.model.BookModel;
import com.llxqb.testapp.mvp.model.ModelTransform;
import com.llxqb.testapp.mvp.read.ReadBookControl;
import com.llxqb.testapp.mvp.read.ReadBookPresenterImpl;
import com.llxqb.testapp.network.RetrofitUtil;
import com.llxqb.testapp.network.networkapi.BookApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class ReadBookModule {
    private final AppCompatActivity activity;
    private ReadBookControl.ReadBookView view;

    public ReadBookModule(AppCompatActivity activity, ReadBookControl.ReadBookView view) {
        this.activity = activity;
        this.view = view;
    }

    public ReadBookModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    ReadBookControl.ReadBookView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    ReadBookControl.PresenterReadBook providePresenterReadBook(ReadBookPresenterImpl readBookPresenter) {
        return readBookPresenter;
    }

    @Provides
    @PerActivity
    BookModel provideBookModel(Gson gson, ModelTransform modelTransform) {
        return new BookModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl("https://www.pulaukomik.com")
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(BookApi.class), gson, modelTransform);
    }




}

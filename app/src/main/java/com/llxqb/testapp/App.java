package com.llxqb.testapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.llxqb.testapp.di.components.AppComponent;
import com.llxqb.testapp.di.components.DaggerAppComponent;
import com.llxqb.testapp.di.modules.AppModule;
import com.llxqb.testapp.ireader.service.DownloadService;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by newbiechen on 17-4-15.
 */

public class App extends Application {
    private static Context sInstance;
    private AppComponent mAppComponent;


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        startService(new Intent(getContext(), DownloadService.class));

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);//必须有

        // 初始化内存分析工具
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    public static Context getContext() {
        return sInstance;
    }
}
package com.llxqb.testapp.di.components;

import android.app.Activity;


import com.llxqb.testapp.di.modules.ActivityModule;
import com.llxqb.testapp.di.scopes.PerActivity;

import dagger.Component;

/**
 *
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}

package com.llxqb.testapp.di.components;


import android.support.v7.app.AppCompatActivity;


import com.llxqb.testapp.di.modules.ActivityModule;
import com.llxqb.testapp.di.modules.ReadBookModule;
import com.llxqb.testapp.di.scopes.PerActivity;
import com.llxqb.testapp.ireader.ui.activity.ReadActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ReadBookModule.class, ActivityModule.class})
public interface ReadBookComponent extends ActivityComponent {
    //对LoginActivity进行依赖注入
    void inject(ReadActivity activity);

    AppCompatActivity activity();


}

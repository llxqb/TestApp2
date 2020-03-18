package com.llxqb.testapp.di;


import com.llxqb.testapp.App;
import com.llxqb.testapp.mvp.base.BaseActivity;

/**
 * Created by wxl on 16/3/30.
 */
public interface ComponetGraph {

    void inject(App application);

    void inject(BaseActivity baseActivity);
//
//    void inject(BaseFragment baseFragment);

}

package com.llxqb.testapp.ireader.ui.adapter;


import com.llxqb.testapp.ireader.model.bean.CollBookBean;
import com.llxqb.testapp.ireader.ui.adapter.view.CollBookHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;
import com.llxqb.testapp.ireader.widget.adapter.WholeAdapter;

/**
 * Created by newbiechen on 17-5-8.
 */

public class CollBookAdapter extends WholeAdapter<CollBookBean> {

    @Override
    protected IViewHolder<CollBookBean> createViewHolder(int viewType) {
        return new CollBookHolder();
    }

}

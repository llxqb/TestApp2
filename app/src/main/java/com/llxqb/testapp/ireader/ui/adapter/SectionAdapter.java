package com.llxqb.testapp.ireader.ui.adapter;


import com.llxqb.testapp.ireader.model.bean.SectionBean;
import com.llxqb.testapp.ireader.ui.adapter.view.SectionHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseListAdapter;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;

/**
 * Created by newbiechen on 17-4-16.
 */

public class SectionAdapter extends BaseListAdapter<SectionBean> {
    @Override
    protected IViewHolder<SectionBean> createViewHolder(int viewType) {
        return new SectionHolder();
    }
}

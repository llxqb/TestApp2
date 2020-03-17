package com.llxqb.testapp.ireader.ui.adapter;


import com.llxqb.testapp.ireader.model.bean.BillBookBean;
import com.llxqb.testapp.ireader.ui.adapter.view.BillBookHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseListAdapter;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;

/**
 * Created by newbiechen on 17-5-3.
 */

public class BillBookAdapter extends BaseListAdapter<BillBookBean> {
    @Override
    protected IViewHolder<BillBookBean> createViewHolder(int viewType) {
        return new BillBookHolder();
    }
}

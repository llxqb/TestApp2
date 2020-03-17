package com.llxqb.testapp.ireader.ui.adapter;


import com.llxqb.testapp.ireader.model.bean.DownloadTaskBean;
import com.llxqb.testapp.ireader.ui.adapter.view.DownloadHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseListAdapter;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;

/**
 * Created by newbiechen on 17-5-12.
 */

public class DownLoadAdapter extends BaseListAdapter<DownloadTaskBean> {

    @Override
    protected IViewHolder<DownloadTaskBean> createViewHolder(int viewType) {
        return new DownloadHolder();
    }
}

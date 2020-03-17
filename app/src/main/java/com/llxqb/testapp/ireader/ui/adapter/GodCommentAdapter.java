package com.llxqb.testapp.ireader.ui.adapter;


import com.llxqb.testapp.ireader.model.bean.CommentBean;
import com.llxqb.testapp.ireader.ui.adapter.view.CommentHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseListAdapter;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;

/**
 * Created by newbiechen on 17-4-29.
 */

public class GodCommentAdapter extends BaseListAdapter<CommentBean> {
    @Override
    protected IViewHolder<CommentBean> createViewHolder(int viewType) {
        return new CommentHolder(true);
    }
}

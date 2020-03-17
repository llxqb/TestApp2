package com.llxqb.testapp.ireader.ui.adapter;

import android.content.Context;

import com.llxqb.testapp.ireader.model.bean.BookCommentBean;
import com.llxqb.testapp.ireader.ui.adapter.view.DiscCommentHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;
import com.llxqb.testapp.ireader.widget.adapter.WholeAdapter;


/**
 * Created by newbiechen on 17-4-20.
 */

public class DiscCommentAdapter extends WholeAdapter<BookCommentBean> {

    public DiscCommentAdapter(Context context, Options options) {
        super(context, options);
    }

    @Override
    protected IViewHolder<BookCommentBean> createViewHolder(int viewType) {
        return new DiscCommentHolder();
    }
}

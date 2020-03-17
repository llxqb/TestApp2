package com.llxqb.testapp.ireader.ui.adapter;

import android.content.Context;

import com.llxqb.testapp.ireader.model.bean.BookListBean;
import com.llxqb.testapp.ireader.ui.adapter.view.BookListHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;
import com.llxqb.testapp.ireader.widget.adapter.WholeAdapter;


/**
 * Created by newbiechen on 17-5-1.
 */

public class BookListAdapter extends WholeAdapter<BookListBean> {
    public BookListAdapter() {
    }

    public BookListAdapter(Context context, Options options) {
        super(context, options);
    }

    @Override
    protected IViewHolder<BookListBean> createViewHolder(int viewType) {
        return new BookListHolder();
    }
}

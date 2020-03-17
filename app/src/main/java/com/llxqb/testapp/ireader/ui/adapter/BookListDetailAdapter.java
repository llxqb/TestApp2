package com.llxqb.testapp.ireader.ui.adapter;

import android.content.Context;

import com.llxqb.testapp.ireader.model.bean.BookListDetailBean;
import com.llxqb.testapp.ireader.ui.adapter.view.BookListInfoHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;
import com.llxqb.testapp.ireader.widget.adapter.WholeAdapter;


/**
 * Created by newbiechen on 17-5-2.
 */

public class BookListDetailAdapter extends WholeAdapter<BookListDetailBean.BooksBean.BookBean> {
    public BookListDetailAdapter(Context context, Options options) {
        super(context, options);
    }

    @Override
    protected IViewHolder<BookListDetailBean.BooksBean.BookBean> createViewHolder(int viewType) {
        return new BookListInfoHolder();
    }
}

package com.llxqb.testapp.ireader.ui.adapter;


import com.llxqb.testapp.ireader.model.bean.BookSortBean;
import com.llxqb.testapp.ireader.ui.adapter.view.BookSortHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseListAdapter;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;

/**
 * Created by newbiechen on 17-4-23.
 */

public class BookSortAdapter extends BaseListAdapter<BookSortBean> {

    @Override
    protected IViewHolder<BookSortBean> createViewHolder(int viewType) {
        return new BookSortHolder();
    }
}

package com.llxqb.testapp.ireader.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.llxqb.testapp.ireader.ui.adapter.view.PageStyleHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseListAdapter;
import com.llxqb.testapp.ireader.ui.base.adapter.BaseViewHolder;
import com.llxqb.testapp.ireader.ui.base.adapter.IViewHolder;
import com.llxqb.testapp.ireader.widget.page.PageStyle;


/**
 * Created by newbiechen on 17-5-19.
 */

public class PageStyleAdapter extends BaseListAdapter<Drawable> {
    private int currentChecked;

    @Override
    protected IViewHolder<Drawable> createViewHolder(int viewType) {
        return new PageStyleHolder();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        IViewHolder iHolder = ((BaseViewHolder) holder).holder;
        PageStyleHolder pageStyleHolder = (PageStyleHolder) iHolder;
        if (currentChecked == position){
            pageStyleHolder.setChecked();
        }
    }

    public void setPageStyleChecked(PageStyle pageStyle){
        currentChecked = pageStyle.ordinal();
    }

    @Override
    protected void onItemClick(View v, int pos) {
        super.onItemClick(v, pos);
        currentChecked = pos;
        notifyDataSetChanged();
    }
}

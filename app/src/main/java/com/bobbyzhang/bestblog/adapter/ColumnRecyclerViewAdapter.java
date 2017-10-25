package com.bobbyzhang.bestblog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bobbyzhang.bestblog.ColumnFragment.OnListFragmentInteractionListener;
import com.bobbyzhang.bestblog.R;
import com.bobbyzhang.bestblog.bean.ColumnBean;

import java.util.List;

/**
 * 专栏列表适配器
 */
public class ColumnRecyclerViewAdapter extends RecyclerView.Adapter<ColumnRecyclerViewAdapter.ViewHolder> {

    private final List<ColumnBean.ColumnsBean> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ColumnRecyclerViewAdapter(List<ColumnBean.ColumnsBean> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_column, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_remark.setText(mValues.get(position).getRemark());
        holder.tv_url.setText(mValues.get(position).getUrl());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(mValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tv_remark;
        public final TextView tv_url;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_remark =  view.findViewById(R.id.tv_remark);
            tv_url = view.findViewById(R.id.tv_url);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}

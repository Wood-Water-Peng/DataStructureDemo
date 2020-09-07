package com.example.myapplication.card;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.card.viewholder.TBaseViewHolder;

import java.util.List;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL;

public class FlowCardManager {

    RecyclerView mRecyclerView;
    FlowAdapter mAdapter;
    Context mContext;

    public FlowCardManager bindRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        return this;
    }

    public void flow() {
        if (mRecyclerView == null) throw new IllegalArgumentException("you must bind recyclerView");
        bindAdapter();
    }

    public interface onLoadMoreCallBack {

    }

    private void bindAdapter() {
        if (mAdapter == null) {
            mAdapter = new FlowAdapter();
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        }
    }

    class FlowAdapter extends RecyclerView.Adapter<TBaseViewHolder> {

        @NonNull
        @Override
        public TBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull TBaseViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public void onLoadMoreSuccess(List<BaseFlowItem> list, int hasMore) {

    }
}

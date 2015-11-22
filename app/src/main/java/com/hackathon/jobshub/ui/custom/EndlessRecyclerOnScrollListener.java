package com.hackathon.jobshub.ui.custom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Nguyen on 11/21/2015.
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    public static final String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0; // The total number of items in the dataset after the last loadEdit
    private boolean loading = true; // True if we are still waiting for the last set of data to loadEdit.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            current_page++;

            onLoadMore(current_page);

            loading = true;
        }
    }

    public abstract void onLoadMore(int current_page);

    public void reset() {
        previousTotal = 0;
        loading = true;
        visibleThreshold = 5;
        current_page = 1;
    }
}
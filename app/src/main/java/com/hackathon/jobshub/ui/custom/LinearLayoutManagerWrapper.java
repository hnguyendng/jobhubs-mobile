package com.hackathon.jobshub.ui.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.hackathon.jobshub.utils.LogUtils;

/**
 * Created by Nguyen on 11/22/2015.
 */
public class LinearLayoutManagerWrapper extends LinearLayoutManager {

    private static final String TAG = LinearLayoutManagerWrapper.class.getSimpleName();

    public LinearLayoutManagerWrapper(Context context) {
        super(context);
    }

    public LinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public LinearLayoutManagerWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            LogUtils.d(TAG, "onLayoutChildren", "meet a IOOBE in RecyclerView");
        }
    }
}

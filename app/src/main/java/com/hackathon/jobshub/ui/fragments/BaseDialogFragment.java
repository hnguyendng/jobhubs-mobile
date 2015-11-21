package com.hackathon.jobshub.ui.fragments;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.Display;

/**
 * Created by Nguyen on 11/21/2015.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private void changeDialogWidth() {
        // change dialog width
        if (getDialog() != null) {

            int fullWidth = getDialog().getWindow().getAttributes().width;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                fullWidth = size.x;
            } else {
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                fullWidth = display.getWidth();
            }

            final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                    .getDisplayMetrics());

            int w = fullWidth - padding;
            int h = getDialog().getWindow().getAttributes().height;

            getDialog().getWindow().setLayout(w, h);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeDialogWidth();
    }

    @Override
    public void onStart() {
        super.onStart();
        changeDialogWidth();
    }
}
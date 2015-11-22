package com.hackathon.jobshub.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hackathon.jobshub.R;
import com.hackathon.jobshub.ui.callbacks.FilterListenner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class FilterDialogFragment extends BaseDialogFragment implements SeekBar.OnSeekBarChangeListener {

    @Bind(R.id.sbDistance)
    SeekBar seekBarDistance;

    @Bind(R.id.tvDistance)
    TextView tvDistance;

    @Bind(R.id.radioSortType)
    RadioGroup radioSortType;

    FilterListenner mCallback;

    public static FilterDialogFragment newInstance(FilterListenner callback) {
        FilterDialogFragment fragment = new FilterDialogFragment();
        fragment.mCallback = callback;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setCanceledOnTouchOutside(false);

        if (getDialog() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        View rootView = inflater.inflate(R.layout.fragment_dialog_filter, container, false);
        ButterKnife.bind(this, rootView);
        seekBarDistance.setOnSeekBarChangeListener(this);

        return rootView;
    }

    @OnClick(R.id.btnUpdate)
    public void onUpdateClick(View v) {
        int radioButtonID = radioSortType.getCheckedRadioButtonId();
        View radioButton = radioSortType.findViewById(radioButtonID);
        int index = radioSortType.indexOfChild(radioButton);
        if(index == 0) {
            mCallback.sortBy("RELEVANCE");
        } else {
            mCallback.sortBy("TITLE");
        }
    }

    final int stepSize = 5;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = ((int) Math.round(progress / stepSize)) * stepSize;
        seekBar.setProgress(progress);
        if (progress == 0) {
            tvDistance.setText("Exact location only");
        } else {
            tvDistance.setText("Within " + progress + " kilometers");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @OnClick(R.id.btnUpdate)
    public void onUpdateClicked(View v) {
        this.dismiss();
    }
}

package com.hackathon.jobshub.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.hackathon.jobshub.R;

/**
 * Created by Nguyen on 11/22/2015.
 */
public final class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    private static ProgressDialog progressDialog;

    public static synchronized void showProgressDialog(Context context) {
        try {
            if (progressDialog == null || progressDialog.isShowing() == false) {
                progressDialog = new ProgressDialog(context);

                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_progress);
            }
        } catch (Exception ex) {
            LogUtils.e(TAG, "showProgressDialog()", ex);
        }
    }

    public static synchronized void hideProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception ex) {
            LogUtils.e(TAG, "hideProgressDialog()", ex);
        }
    }
}

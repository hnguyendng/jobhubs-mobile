package com.hackathon.jobshub.utils;

import com.bluelinelabs.logansquare.LoganSquare;
import com.hackathon.jobshub.BuildConfig;

import java.io.IOException;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class LogUtils {

    private static final boolean isPrintLog = BuildConfig.DEBUG;

    public static void o(String className, String methodName, Object src) {
        if (isPrintLog) {
            try {
                android.util.Log.i("JobsHub " + className, methodName + ": " + new LoganSquare().serialize(src));
            } catch (IOException ex) {
                LogUtils.e("LogUtils", "o", ex);
            }
        }
    }

    public static void i(String className, String methodName, String message) {
        if (isPrintLog) {
            android.util.Log.i("JobsHub " + className, methodName + ": " + message);
        }
    }

    public static void e(String className, String methodName, Exception ex) {
        if (isPrintLog) {
            android.util.Log.e("JobsHub " + className, methodName, ex);
        }
    }

    public static void w(String className, String methodName, String message) {
        if (isPrintLog) {
            android.util.Log.w("JobsHub " + className, methodName + ": " + message);
        }
    }

    public static void d(String className, String methodName, String message) {
        if (isPrintLog) {
            android.util.Log.d("JobsHub " + className, methodName + ": " + message);
        }
    }
}
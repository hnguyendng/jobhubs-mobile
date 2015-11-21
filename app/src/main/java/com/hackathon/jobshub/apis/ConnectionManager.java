package com.hackathon.jobshub.apis;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class ConnectionManager {

    private static ConnectionManager mInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private ConnectionManager(Context context) {
        mContext = context;
    }

    public static synchronized ConnectionManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ConnectionManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}

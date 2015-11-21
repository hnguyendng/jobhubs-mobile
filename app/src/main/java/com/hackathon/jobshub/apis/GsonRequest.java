package com.hackathon.jobshub.apis;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hackathon.jobshub.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {

    private final String TAG = GsonRequest.class.getSimpleName();

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Object json;
    private final Listener<T> listener;

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Object json, Listener<T> listener, ErrorListener errorListener) {
        this(method, url, clazz, headers, json, listener, errorListener, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);    // Timeout 2500
    }

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Object json, Listener<T> listener, ErrorListener errorListener, int socketTimeout) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.json = json;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(policy);

        LogUtils.d(TAG, "getUrl", url);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (json != null) {
            LogUtils.o(TAG, "getBody", json);
            return gson.toJson(json).getBytes();
        }
        return null;
    }

    @Override
    public Map<String, String> getHeaders() {
        if (headers == null) {
            return new HashMap();
        }
        LogUtils.o(TAG, "getHeaders", headers);
        return headers;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            LogUtils.d(TAG, "parseNetworkResponse", json);
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(TAG, "parseNetworkResponse", e);
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            LogUtils.e(TAG, "parseNetworkResponse", e);
            return Response.error(new ParseError(e));
        }
    }
}
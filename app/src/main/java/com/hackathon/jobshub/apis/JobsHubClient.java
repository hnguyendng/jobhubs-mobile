package com.hackathon.jobshub.apis;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hackathon.jobshub.models.SearchResponse;

/**
 * Created by Nguyen on 11/22/2015.
 */
public final class JobsHubClient {

    public static final String TAG = JobsHubClient.class.getSimpleName();

    public static void getJobs(final Context context, String searchTerm, String city, String sort, Integer page, Integer pageSize, final IResponse<SearchResponse> callback) {

        Uri.Builder builder = Uri.parse(APIContract.SEARCH).buildUpon();

        if (searchTerm != null) {
            builder.appendQueryParameter("searchTerm", searchTerm);
        }
        if (city != null) {
            builder.appendQueryParameter("city", city);
        }
        if (sort != null) {
            builder.appendQueryParameter("sort", sort);
        }
        if (page != null) {
            builder.appendQueryParameter("page", Integer.toString(page));
        }
        if (pageSize != null) {
            builder.appendQueryParameter("pageSize", Integer.toString(pageSize));
        }

        GsonRequest request = new GsonRequest(
                Request.Method.GET,
                builder.build().toString(),
                SearchResponse.class,
                null,
                null,
                new Response.Listener<SearchResponse>() {
                    @Override
                    public void onResponse(SearchResponse response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure();
                    }
                }, 30000);

        ConnectionManager.getInstance(context).addToRequestQueue(request);
    }
}

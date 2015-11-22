package com.hackathon.jobshub.apis;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hackathon.jobshub.models.Place;
import com.hackathon.jobshub.models.Places;
import com.hackathon.jobshub.utils.LogUtils;
import com.hackathon.jobshub.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen on 11/21/2015.
 */
public final class MapsClient {

    public static final String TAG = MapsClient.class.getSimpleName();

    public static void getAddresses(final Context context, double lat, double lng, final IResponse<List<String>> callback) {

        GsonRequest request = new GsonRequest(
                Request.Method.GET,
                String.format(APIContract.NEARBY_PLACES, lat, lng),
                Places.class,
                null,
                null,
                new Response.Listener<Places>() {
                    @Override
                    public void onResponse(Places response) {
                        if (response == null || response.placeList == null) {
                            callback.onResponse(null);
                        } else {
                            List<String> places = new ArrayList<>();
                            for (Place place : response.placeList) {
                                if (place != null) {
                                    LogUtils.d(TAG, "getAddresses", place.vicinity);
                                    places.add(StringUtils.deAccent(place.vicinity));
                                }
                            }
                            callback.onResponse(places);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure();
                    }
                });

        ConnectionManager.getInstance(context).addToRequestQueue(request);
    }
}

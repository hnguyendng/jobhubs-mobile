package com.hackathon.jobshub.apis;

import com.hackathon.jobshub.BuildConfig;

/**
 * Created by Nguyen on 11/21/2015.
 */
public interface APIContract {

    String NEARBY_PLACES = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=500&key=AIzaSyATPA_E54DSMXSz0pzeUAQDrYW89xZJ-H8";

    String SEARCH = BuildConfig.BASE_URL + "/api/search";

}

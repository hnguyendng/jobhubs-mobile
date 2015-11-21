package com.hackathon.jobshub.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class Places {

    @SerializedName("results")
    public List<Place> placeList;

}

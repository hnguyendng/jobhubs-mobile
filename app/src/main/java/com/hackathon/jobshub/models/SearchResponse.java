package com.hackathon.jobshub.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen on 11/22/2015.
 */
public class SearchResponse {

    @SerializedName("content")
    public List<Job> content;

    @SerializedName("totalElements")
    public int totalElements;

    @SerializedName("totalPages")
    public int totalPages;

    @SerializedName("size")
    public int size;

    @SerializedName("number")
    public int number;

    @SerializedName("numberOfElements")
    public int numberOfElements;

    @SerializedName("first")
    public boolean first;

    @SerializedName("last")
    public boolean last;

}

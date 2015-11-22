package com.hackathon.jobshub.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class Job {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("requirement")
    public String requirement;

    @SerializedName("companyName")
    public String companyName;

    @SerializedName("position")
    public String position;

    @SerializedName("vacancy")
    public String vacancy;

    @SerializedName("category")
    public String category;

    @SerializedName("minSalary")
    public String minSalary;

    @SerializedName("maxSalary")
    public String maxSalary;

    @SerializedName("city")
    public String city;

    @SerializedName("address")
    public String address;

    @SerializedName("image")
    public String image;

    @SerializedName("salaryInfo")
    public String salaryInfo;

    @SerializedName("expireDate")
    public String expireDate;

    @SerializedName("link")
    public String link;

    @SerializedName("source")
    public String source;

    @SerializedName("companyDescription")
    public String companyDescription;

    @SerializedName("benefit")
    public String benefit;

}

package com.hackathon.jobshub.apis;

/**
 * Created by Nguyen on 11/21/2015.
 */
public interface IResponse<T> {

    void onResponse(T response);

    void onFailure();

}
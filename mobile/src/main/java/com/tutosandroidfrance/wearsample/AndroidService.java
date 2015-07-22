package com.tutosandroidfrance.wearsample;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

// http://pastebin.com/raw.php?i=PHPXBsEf

public interface AndroidService {

    String ENDPOINT = "http://pastebin.com";

    @GET("/raw.php?i=PHPXBsEf")
    void getElements(Callback<List<AndroidVersion>> callback);
}
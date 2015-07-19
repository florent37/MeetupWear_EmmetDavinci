package com.tutosandroidfrance.wearsample;

import com.tutosandroidfrance.wearprotocol.AndroidVersion;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface AndroidService {

    String ENDPOINT = "http://pastebin.com";

    @GET("/raw.php?i=PHPXBsEf")
    void getElements(Callback<List<AndroidVersion>> callback);
}
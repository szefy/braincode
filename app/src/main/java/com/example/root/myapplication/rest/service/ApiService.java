package com.example.root.myapplication.rest.service;

import com.example.root.myapplication.rest.model.ChannelResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by jbis on 2015-03-13.
 */
public interface ApiService {

    @GET("/channels/{channel}")
    void getChannel(@Path("channel") String channel, Callback<ChannelResponse> channelResponseCallback);
}

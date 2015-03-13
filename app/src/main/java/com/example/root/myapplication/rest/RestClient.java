package com.example.root.myapplication.rest;

import com.example.root.myapplication.rest.service.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by jbis on 2015-03-13.
 */
public class RestClient {

    private static ApiService apiService;
    private static final String BASE_URL= "https://api.twitch.tv/kraken";

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static ApiService getApiService() {
        return apiService;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        apiService = restAdapter.create(ApiService.class);
    }
}

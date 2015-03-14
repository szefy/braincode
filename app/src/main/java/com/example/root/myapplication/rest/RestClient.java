package com.example.root.myapplication.rest;

import com.example.root.myapplication.rest.service.ApiService;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

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
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/vnd.github.v3.full+json");
            }
        };

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        apiService = restAdapter.create(ApiService.class);
    }
}

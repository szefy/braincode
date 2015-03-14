package com.example.root.myapplication.rest;

import android.util.Log;

import com.example.root.myapplication.rest.service.ApiService;
import com.squareup.okhttp.OkHttpClient;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
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
                .setErrorHandler(new ErrorHandler() {

                    @Override
                    public Throwable handleError(RetrofitError cause) {

                        if (cause.isNetworkError()) {
                            if (cause.getCause() instanceof SocketTimeoutException) {
                                //Socket Timeout
                                return new SocketTimeoutException("Connection Timeout. " +
                                        "Please verify your internet connection.");
                            } else {
                                //No Connection
                                return new ConnectException("No Connection. " +
                                        "Please verify your internet connection.");
                            }
                        } else {

                            return cause;
                        }
                    }


/*
        @Override
                    public Throwable handleError(RetrofitError cause) {
                        switch (cause.getResponse().getStatus()) {
                            case 400:
                                Handle the expected body format
                                cause.getBody();
                                throw new RuntimeException("Bad Request");
                            default:
                                Things and stuff
                                Log.e("ERR_CODE: ", String.valueOf(cause.getResponse().getStatus()));
                                throw new RuntimeException("");
                        }
                    }

                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        String errorDescription;

                        if (cause.isNetworkError()) {
                            errorDescription = "Network error";
                        } else {
                            if (cause.getResponse() == null) {
                                errorDescription = "Response error";
                            } else {

                                // Error message handling - return a simple error to Retrofit handlers..
                                try {
                                    ErrorResponse errorResponse = (ErrorResponse) cause.getBodyAs(ErrorResponse.class);
                                    errorDescription = errorResponse.error.data.message;
                                } catch (Exception ex) {
                                    try {
                                        errorDescription = "No network";
                                        Log.e("errorDescription", cause.getResponse().toString());
                                    } catch (Exception ex2) {
                                        Log.e("ERROR", "handleError: " + ex2.getLocalizedMessage());
                                        errorDescription = "Unknown error";
                                    }
                                }
                            }
                        }

                        return new Exception(errorDescription);
                    }
                    */
                })
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        apiService = restAdapter.create(ApiService.class);
    }
}

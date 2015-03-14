package com.example.root.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;
import android.os.Handler;
import android.view.View;

import com.example.root.myapplication.rest.RestClient;
import com.example.root.myapplication.rest.model.StreamResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 14.03.15.
 */
public class StreamStatusService extends Service{
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    List<String> activeStreamers = new ArrayList<String>();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Log.i("Service", "Service Started");
        startTimer();
    }

    @Override
    public void onDestroy(){
        Log.i("Service", "Service stopped");
    }

    public void startTimer(){
        timer = new Timer();
        initializeTimerTask();

        timer.schedule(timerTask, 0, 20000);
    }

    public void initializeTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        getActiveStreamers();
                        if(activeStreamers.size()>0)
                            for(int i=0;i<activeStreamers.size();i++)
                                createNotification(activeStreamers.get(i).toString(), i);
                    }
                });
            }
        };
    }

    public void createNotification(String channelTitle, int id){
        Notification noti = new Notification.Builder(getApplicationContext())
                .setContentTitle(channelTitle + " started streaming")
                .setContentText(channelTitle + " started streaming")
                .setSmallIcon(R.drawable.icon_search)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(id, noti);
    }

    void getActiveStreamers(){
        String[] favouriteStreamers;

        SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
        Map<String,?> sourceMap = settings.getAll();
        Collection<String> values = sourceMap.keySet();

        favouriteStreamers = values.toArray(new String[values.size()]);
        for(int i=0;i<favouriteStreamers.length; i++)
            checkIfStreamStarted(favouriteStreamers[i]);
    }

    void checkIfStreamStarted(final String channelName){
        RestClient.getApiService().getStream(channelName, new Callback<StreamResponse>() {
            @Override
            public void success(StreamResponse streamResponse, Response response) {
                if (streamResponse.getStream() == null) {
                    Log.i("activee", channelName + " false");

                } else {
                    Date date5 = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
                    Log.i("Date", date5.toString());
                    if(streamResponse.getStream().getCreated_at().after(date5))
                        activeStreamers.add(channelName);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("ERROR", error.getMessage());
            }
        });
}}

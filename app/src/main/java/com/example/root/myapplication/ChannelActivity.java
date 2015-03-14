package com.example.root.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.root.myapplication.rest.RestClient;
import com.example.root.myapplication.rest.model.ChannelResponse;
import com.example.root.myapplication.rest.model.StreamResponse;
import com.example.root.myapplication.rest.utils.DateTimeUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ChannelActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        //get extra channel name
        Intent intent = getIntent();
        String channelName = intent.getStringExtra("channelName");

        final TextView textChannelName = (TextView)findViewById(R.id.c_textChannelName);
        final TextView textGame = (TextView)findViewById(R.id.c_textGame);
        final TextView textStreamStatus = (TextView)findViewById(R.id.c_textStreamStatus);
        final TextView textStreamGame = (TextView)findViewById(R.id.c_textStreamGame);
        final TextView textStreamCreate = (TextView)findViewById(R.id.c_textStreamCreate);


        RestClient.getApiService().getChannel(channelName, new Callback<ChannelResponse>() {
            @Override
            public void success(ChannelResponse channelResponse, Response response) {
                textChannelName.setText(channelResponse.getName());
                textGame.setText(channelResponse.getGame());
                System.out.println("Long: "+DateTimeUtils.getLong(channelResponse.getCreated_at()));
                System.out.println("SHORT: "+DateTimeUtils.getShort(channelResponse.getCreated_at()));
            }
            @Override
            public void failure(RetrofitError error) {
                textChannelName.setText("Error getting channel info");
                Log.i("ERROR", error.getMessage());
            }

        });

        RestClient.getApiService().getStream(channelName, new Callback<StreamResponse>() {
            @Override
            public void success(StreamResponse streamResponse, Response response) {
                if (streamResponse.getStream() == null) {
                    textStreamStatus.setText("Stream offline.");
                    textStreamStatus.setTextColor(Color.RED);
                } else {
                    textStreamStatus.setText("Stream online.");
                    textStreamStatus.setTextColor(Color.GREEN);
                    textStreamGame.setText(streamResponse.getStream().getGame());
                    textStreamCreate.setText(streamResponse.getStream().getCreated_at().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("ERROR", error.getMessage());
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

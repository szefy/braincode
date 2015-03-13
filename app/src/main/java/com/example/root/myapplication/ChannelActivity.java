package com.example.root.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.root.myapplication.rest.RestClient;
import com.example.root.myapplication.rest.model.ChannelResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ChannelActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        final TextView textChannelName = (TextView)findViewById(R.id.c_textChannelName);
        final TextView textGame = (TextView)findViewById(R.id.c_textGame);
        final TextView textStartDate = (TextView)findViewById(R.id.c_textStartDate);


        RestClient.getApiService().getChannel("test_channel", new Callback<ChannelResponse>() {
            @Override
            public void success(ChannelResponse channelResponse, Response response) {
                textChannelName.setText(channelResponse.getName());
                textGame.setText(channelResponse.getGame());
                //textStartDate.setText(channelResponse.get);
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

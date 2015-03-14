package com.example.root.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.myapplication.rest.RestClient;
import com.example.root.myapplication.rest.model.ChannelResponse;
import com.example.root.myapplication.rest.model.StreamResponse;
import com.example.root.myapplication.rest.utils.DateTimeUtils;
import com.example.root.myapplication.rest.utils.DownloadImage;

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
        final String channelName = intent.getStringExtra("channelName");

        setTitle(channelName);

        final TextView textGame = (TextView)findViewById(R.id.c_textViewGameValue);
        final TextView textStreamStatus = (TextView)findViewById(R.id.c_textStreamStatus);
        final TextView textJoined = (TextView)findViewById(R.id.c_textViewOnTwitchSinceValue);
        final TextView textLanguage = (TextView) findViewById(R.id.c_textViewLanguageValue);
        final TextView textFollowers = (TextView) findViewById(R.id.c_textViewFollowersValue);
        final TextView textChannelUrl = (TextView) findViewById(R.id.c_textViewUrlValue);
        final ImageView logo = (ImageView) findViewById(R.id.c_imageViewUserLogo);
        final TextView textViews = (TextView) findViewById(R.id.s_textViewViewsValue);

        textChannelUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(textChannelUrl.getText().toString()));
                startActivity(browserIntent);
            }
        });

        Button backButton = (Button) findViewById(R.id.c_buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button removeButton = (Button)findViewById(R.id.c_buttonRemove);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove(channelName);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        RestClient.getApiService().getChannel(channelName, new Callback<ChannelResponse>() {
            @Override
            public void success(ChannelResponse channelResponse, Response response) {
                textGame.setText(channelResponse.getGame());
                textLanguage.setText(channelResponse.getBroadcaster_language());
                textFollowers.setText(Integer.toString(channelResponse.getFollowers()));
                textChannelUrl.setLines(2);
                textChannelUrl.setText(channelResponse.getUrl());
                textChannelUrl.setAutoLinkMask(Linkify.ALL);
                textChannelUrl.setMovementMethod(LinkMovementMethod.getInstance());
                new DownloadImage(logo).execute(channelResponse.getLogo());
                textJoined.setText(DateTimeUtils.getLong(channelResponse.getCreated_at()));
                textViews.setText(Integer.toString(channelResponse.getViews()));
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ChannelActivity.this, error.getCause().getMessage(), Toast.LENGTH_LONG).show();
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
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ChannelActivity.this, error.getCause().getMessage(), Toast.LENGTH_LONG).show();
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

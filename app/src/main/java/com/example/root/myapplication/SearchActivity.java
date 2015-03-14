package com.example.root.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.myapplication.rest.RestClient;
import com.example.root.myapplication.rest.model.ChannelResponse;
import com.example.root.myapplication.rest.utils.DownloadImage;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SearchActivity extends ActionBarActivity {
    private EditText channelToSearch;
    private ImageView logo;
    private TextView username;
    private TextView statusTitle;
    private TextView statusValue;
    private TextView gameTitle;
    private TextView gameValue;
    private Button addButton;
    private TextView userNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button okButton = (Button) findViewById(R.id.s_buttonSubmitSearching);
        Button addButton = (Button) findViewById(R.id.s_buttonAddUser);
        channelToSearch = (EditText) findViewById(R.id.s_editTextEnterSearchValue);
        logo = (ImageView) findViewById(R.id.s_imageViewUserLogo);
        username = (TextView) findViewById(R.id.s_textViewUsername);
        statusTitle = (TextView) findViewById(R.id.s_textViewStatusTitle);
        statusValue = (TextView) findViewById(R.id.s_textViewStatusValue);
        gameTitle = (TextView) findViewById(R.id.s_textViewGameTitle);
        gameValue = (TextView) findViewById(R.id.s_textViewGameValue);

        okButton.setOnClickListener(onOkClickListener);
        addButton.setOnClickListener(onAddClickListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

    private View.OnClickListener onOkClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            RestClient.getApiService().getChannel(channelToSearch.getText().toString(), new Callback<ChannelResponse>() {
                @Override
                public void success(ChannelResponse channelResponse, Response response) {
                    username.setText(channelToSearch.getText());
                    username.setVisibility(View.VISIBLE);
                    statusTitle.setVisibility(View.VISIBLE);
                    statusValue.setText(channelResponse.getStatus());
                    statusValue.setVisibility(View.VISIBLE);
                    gameTitle.setVisibility(View.VISIBLE);
                    gameValue.setText(channelResponse.getGame());
                    gameValue.setVisibility(View.VISIBLE);
                    addButton = (Button) findViewById(R.id.s_buttonAddUser);
                    addButton.setOnClickListener(onAddClickListener);
                    addButton.setVisibility(View.VISIBLE);
                    new DownloadImage(logo).execute(channelResponse.getLogo());
                    channelToSearch.setOnClickListener(onSearchFieldClickListener);
                }

                @Override
                public void failure(RetrofitError error) {
                    userNotFound = (TextView) findViewById(R.id.s_textViewNoItemFound);
                    userNotFound.setVisibility(View.VISIBLE);
                    channelToSearch.setOnClickListener(onSearchFieldClickListener);
                }

            });
        }
    };

    private View.OnClickListener onAddClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            String name = username.getText().toString();
            SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(name, 1);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    };

    private View.OnClickListener onSearchFieldClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            logo.setVisibility(View.INVISIBLE);
            username.setVisibility(View.INVISIBLE);
            statusTitle.setVisibility(View.INVISIBLE);
            statusValue.setVisibility(View.INVISIBLE);
            gameTitle.setVisibility(View.INVISIBLE);
            gameValue.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.INVISIBLE);
            userNotFound.setVisibility(View.INVISIBLE);
        }
    };
}

package com.example.root.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    String[] favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
        Map<String,?> sourceMap = settings.getAll();
        Collection<String> values = sourceMap.keySet();
        favourites = values.toArray(new String[values.size()]);

        mainListView = (ListView) findViewById( R.id.m_listView );
        listAdapter = new MyArrayAdapter(this, favourites);
        mainListView.setAdapter( listAdapter );

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChannelActivity.class);
                intent.putExtra("channelName", ((TextView)view.findViewById(R.id.rowTextView)).getText().toString());
                startActivity(intent);
                finish();
            }
        });

        //Service
        startService(new Intent(this, StreamStatusService.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

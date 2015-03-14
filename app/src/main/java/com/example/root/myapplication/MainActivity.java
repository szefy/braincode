package com.example.root.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
        //favourites = new ArrayList<String>(sourceMap.keySet());
        Collection<String> values = sourceMap.keySet();
        favourites = values.toArray(new String[values.size()]);

        mainListView = (ListView) findViewById( R.id.m_listView );

        ArrayList<String> favouriteList = new ArrayList<String>();
        favouriteList.addAll( Arrays.asList(favourites) );

        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, favouriteList);

        mainListView.setAdapter( listAdapter );

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChannelActivity.class);
                intent.putExtra("channelName", ((TextView)view).getText().toString());
                startActivity(intent);
            }
        });
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
//                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                startActivity(new Intent(getApplicationContext(), ChannelActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void updateFavourites(){
        SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
        Map<String,?> sourceMap = settings.getAll();
        //favourites = new ArrayList<String>(sourceMap.keySet());
        Collection<String> values = sourceMap.keySet();
        favourites = values.toArray(new String[values.size()]);

        for(int i=0;i<favourites.length;i++){
            Log.i("AAA", favourites[i]);
        }
    }
}

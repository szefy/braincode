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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    ArrayAdapter<String> adapter;
    //List<String> favourites;
    String[] favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.m_listView);

        updateFavourites();

        adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.textView2, favourites);
        list.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_main);

        //updateFavourites();
        //adapter.notifyDataSetChanged();
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
//                startActivity(new Intent(getApplicationContext(), ChannelActivity.class));
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

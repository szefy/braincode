package com.example.root.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.myapplication.rest.RestClient;
import com.example.root.myapplication.rest.model.ChannelResponse;
import com.example.root.myapplication.rest.model.StreamResponse;
import com.example.root.myapplication.rest.utils.DateTimeUtils;
import com.example.root.myapplication.rest.utils.DownloadImage;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 14.03.15.
 */
public class MyArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MyArrayAdapter(Context context, String[] values){
        super(context, R.layout.list_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);
        final TextView text_channelName = (TextView)rowView.findViewById(R.id.rowTextView);
        final TextView streamStatus = (TextView)rowView.findViewById(R.id.streamStatus);
        final ImageView channelLogo = (ImageView)rowView.findViewById(R.id.icon);
        String channelName = values[position];

        //Setting channelName view
        text_channelName.setText(channelName);

        //Setting stream status
        RestClient.getApiService().getStream(channelName, new Callback<StreamResponse>() {
            @Override
            public void success(StreamResponse streamResponse, Response response) {
                if (streamResponse.getStream() == null) {
                    streamStatus.setText("Offline");
                    streamStatus.setTextColor(Color.RED);
                } else {
                    streamStatus.setText("Online");
                    streamStatus.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("ERROR", error.getMessage());
            }

        });

        //Setting channel logo
        RestClient.getApiService().getChannel(channelName, new Callback<ChannelResponse>() {
            @Override
            public void success(ChannelResponse channelResponse, Response response) {
                new DownloadImage(channelLogo).execute(channelResponse.getLogo());
            }
            @Override
            public void failure(RetrofitError error) {
                Log.i("ERROR", error.getMessage());
            }

        });

        return rowView;
    }
}

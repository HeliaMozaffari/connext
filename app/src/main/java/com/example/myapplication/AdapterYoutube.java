package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterYoutube extends BaseAdapter {
    Activity activity;
    ArrayList<ModelYoutube> videoDetailsArrayList;
    LayoutInflater inflater;

    public AdapterYoutube(Activity activity, ArrayList<ModelYoutube> videoDetailsArrayList){


        this.activity = activity;
        this.videoDetailsArrayList = videoDetailsArrayList;
    }

    @Override
    public int getCount() {
        return this.videoDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.videoDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = this.activity.getLayoutInflater();
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.single_youtube, null);
        }

        ImageView imageView = convertView.findViewById(R.id.ytImageView);
        TextView textView = convertView.findViewById(R.id.ytTitle);
        LinearLayout linearLayout = convertView.findViewById(R.id.root);
        ModelYoutube modelYoutube = this.videoDetailsArrayList.get(position);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,YTVideoDetails.class);
                intent.putExtra("videoId",modelYoutube.getVideoId());
                activity.startActivity(intent);


            }
        });


        Picasso.get().load(modelYoutube.getUrl()).into(imageView);
        textView.setText(modelYoutube.getTitle());
        return convertView;
    }
}

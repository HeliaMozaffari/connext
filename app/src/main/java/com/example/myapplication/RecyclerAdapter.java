package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Users> usersList;

    public RecyclerAdapter(Context mContext, ArrayList<Users> usersList) {
        this.mContext = mContext;
        this.usersList = usersList;
    }

    @NonNull

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_match, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerAdapter.ViewHolder holder, int position) {

        holder.textView.setText(usersList.get(position).getName());
        holder.textView2.setText(usersList.get(position).getDescription());
        Glide.with(mContext).load(usersList.get(position).getPicture()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

       ImageView imageView;
       TextView textView, textView2;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.smImageView);
            textView = itemView.findViewById(R.id.smTextView);
            textView2 = itemView.findViewById(R.id.smTextView2);
        }
    }
}

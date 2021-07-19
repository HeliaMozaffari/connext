package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Twitch extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_twitch, container, false);
        SocialsActivity activity = (SocialsActivity) getActivity();
        String getData = activity.twitchData();

        Toast.makeText(getActivity(),getData,Toast.LENGTH_SHORT).show();





        return view;
    }
}
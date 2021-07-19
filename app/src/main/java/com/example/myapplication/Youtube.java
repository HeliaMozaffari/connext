package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Youtube extends Fragment {


    public String getData;
    ListView listView;
    ArrayList<ModelYoutube> videoDetailsArrayList;
    AdapterYoutube adapterYoutube;

    String APIKey = "AIzaSyD3BVAmN3Ft7KZJBgQJ3LSLFcKmQje3VYM";
    private String url;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_youtube, container, false);
        SocialsActivity activity = (SocialsActivity) getActivity();
        getData = activity.youtubeData();
        Toast.makeText(getActivity(),getData,Toast.LENGTH_SHORT).show();
        url =String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCty4mfuiq07cMUtSmVxrE4A&maxResults=30&order=date&key=AIzaSyD3BVAmN3Ft7KZJBgQJ3LSLFcKmQje3VYM", getData);
        listView = view.findViewById(R.id.lvYoutube);
        videoDetailsArrayList = new ArrayList<>();
        adapterYoutube = new AdapterYoutube(activity, videoDetailsArrayList);

        displayVideos();








        return view;
    }

    private void displayVideos() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId = jsonObject1.getJSONObject("id");
                        JSONObject jsonObjectSnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonObjectDefault = jsonObjectSnippet.getJSONObject("thumbnails").getJSONObject("medium");
                        ModelYoutube vd = new ModelYoutube();
                        vd.setTitle(jsonObjectSnippet.getString("title"));
                        vd.setDescription(jsonObjectSnippet.getString("description"));
                        vd.setUrl(jsonObjectDefault.getString("url"));

                        videoDetailsArrayList.add(vd);


                    }

                    listView.setAdapter(adapterYoutube);
                    adapterYoutube.notifyDataSetChanged();

                } catch (JSONException e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }
}
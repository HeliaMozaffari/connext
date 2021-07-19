package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SocialsActivity extends AppCompatActivity {

    Users users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);


        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof Users){
            users = (Users) object;
        }


        ViewPager viewPager = findViewById(R.id.viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        if(users.youtube != null && users.youtube !="") {
            vpAdapter.addFragments(new Youtube(), users.youtube.toString());
        }
        if(users.instagram != null && users.instagram !="") {
            vpAdapter.addFragments(new Instagram(), users.instagram.toString());
        }
        if(users.facebook != null && users.facebook !="") {
            vpAdapter.addFragments(new Facebook(), users.facebook.toString());
        }
        if(users.twitter!= null && users.twitter !="") {
            vpAdapter.addFragments(new Twitter(), users.twitter.toString());
        }
        if(users.tiktok!= null && users.tiktok !="") {
            vpAdapter.addFragments(new TikTok(), users.tiktok.toString());
        }
        if(users.twitch != null && users.twitch !="") {
            vpAdapter.addFragments(new Twitch(), users.twitch.toString());
        }
        viewPager.setAdapter(vpAdapter);

    }

    public String youtubeData() {
        return users.youtube.toString();
    }
    public String instagramData() {
        return users.instagram.toString();
    }
    public String facebookData() {
        return users.facebook.toString();
    }
    public String twitterData() {
        return users.twitter.toString();
    }
    public String tiktokData() {
        return users.tiktok.toString();
    }
    public String twitchData() {
        return users.twitch.toString();
    }

}
package com.example.myapplication;

import java.io.Serializable;

public class Users implements Serializable {
    public String name,description,picture,facebook, instagram,tiktok,twitch,twitter,youtube;

    public Users() {

    }

    public Users(String name, String description, String picture, String facebook, String instagram, String tiktok, String twitch, String youtube,String twitter) {

        this.name = name;
        this.description = description;
        this.picture = picture;
        this.facebook = facebook;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.twitch = twitch;
        this.twitter = twitter;
        this.youtube = youtube;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public String getTwitch() {
        return twitch;
    }

    public void setTwitch(String twitch) {
        this.twitch = twitch;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

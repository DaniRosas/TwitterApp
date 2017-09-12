package com.examples.twitterapp.timeline.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class Post {
    @SerializedName("post_id")
    private String id;

    @SerializedName("user")
     private String user;

    @SerializedName("time")
     private String time;

    @SerializedName("post")
     private String post;

    @SerializedName("is_3fav")
     private boolean isFav;

    @SerializedName("hashtags")
    private List<String> hashtags;

    private final static String BASE_TWEET_URL = "https://twitter.com/null/status/";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getTweetURL() {
        return BASE_TWEET_URL + this.id;
    }

    public boolean getFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}

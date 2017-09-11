package com.examples.twitterapp.timeline.events;

import com.examples.twitterapp.timeline.entities.Post;

import java.util.List;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class TimelineEvent {

    private int type;
    private String error;
    private List<Post> hashtags;
    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;
    public final static int DELETE_EVENT = 2;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Post> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Post> hashtags) {
        this.hashtags = hashtags;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

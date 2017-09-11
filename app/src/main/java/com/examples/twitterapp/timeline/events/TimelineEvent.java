package com.examples.twitterapp.timeline.events;

import com.examples.twitterapp.timeline.entities.Post;

import java.util.List;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class TimelineEvent {
    private String error;
    private List<Post> hashtags;

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
}

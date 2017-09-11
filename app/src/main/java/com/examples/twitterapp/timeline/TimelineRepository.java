package com.examples.twitterapp.timeline;

import com.examples.twitterapp.timeline.entities.Post;

/**
 * Created by DaniRosas on 10/9/17.
 */

public interface TimelineRepository {
    void getPosts();
    void updateTweet(Post tweet);
    void deleteTweet(Post tweet);
    void getSavedFavorites();
}

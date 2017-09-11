package com.examples.twitterapp;

import com.examples.twitterapp.timeline.entities.Post;

/**
 * Created by DaniRosas on 11/9/17.
 */



public interface StoredFavoritesInteractor {
    void executeUpdate(Post tweet);
    void executeDelete(Post tweet);
}

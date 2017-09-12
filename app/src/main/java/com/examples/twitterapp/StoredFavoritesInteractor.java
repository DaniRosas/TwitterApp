package com.examples.twitterapp;

import android.content.Context;

import com.examples.twitterapp.timeline.entities.Post;

/**
 * Created by DaniRosas on 11/9/17.
 */



public interface StoredFavoritesInteractor {
    void executeUpdate(Post tweet, Context context);
    void executeDelete(Post tweet);
}

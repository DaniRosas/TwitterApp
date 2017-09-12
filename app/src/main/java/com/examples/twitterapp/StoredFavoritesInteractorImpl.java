package com.examples.twitterapp;

import android.content.Context;

import com.examples.twitterapp.timeline.TimelineRepository;
import com.examples.twitterapp.timeline.TimelineRepositoryImpl;
import com.examples.twitterapp.timeline.entities.Post;

/**
 * Created by DaniRosas on 11/9/17.
 */

public class StoredFavoritesInteractorImpl implements StoredFavoritesInteractor {
    private TimelineRepository repository;

    public StoredFavoritesInteractorImpl(TimelineRepository repository) {
        this.repository = repository;
    }

    public StoredFavoritesInteractorImpl() {
        repository = new TimelineRepositoryImpl();
    }


    @Override
    public void executeUpdate(Post tweet, Context context) {
        repository.updateTweet(tweet, context);
    }

    @Override
    public void executeDelete(Post tweet) {
        repository.deleteTweet(tweet);
    }
}

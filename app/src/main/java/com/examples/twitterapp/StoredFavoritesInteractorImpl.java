package com.examples.twitterapp;

import com.examples.twitterapp.timeline.TimelineRepository;
import com.examples.twitterapp.timeline.entities.Post;

/**
 * Created by DaniRosas on 11/9/17.
 */

public class StoredFavoritesInteractorImpl implements StoredFavoritesInteractor {
    private TimelineRepository repository;

    public StoredFavoritesInteractorImpl(TimelineRepository repository) {
        this.repository = repository;
    }


    @Override
    public void executeUpdate(Post tweet) {
        repository.updateTweet(tweet);
    }

    @Override
    public void executeDelete(Post tweet) {
        repository.deleteTweet(tweet);
    }
}

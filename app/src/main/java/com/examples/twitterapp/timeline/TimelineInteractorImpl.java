package com.examples.twitterapp.timeline;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class TimelineInteractorImpl implements TimelineInteractor {

    private TimelineRepository repository;

    public TimelineInteractorImpl(TimelineRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getTimelineItemsList() {
        repository.getPosts();
    }
}

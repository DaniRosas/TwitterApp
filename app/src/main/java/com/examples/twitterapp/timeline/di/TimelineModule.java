package com.examples.twitterapp.timeline.di;

import android.content.Context;

import com.examples.twitterapp.api.CustomTwitterApiClient;
import com.examples.twitterapp.libs.base.Eventbus;
import com.examples.twitterapp.timeline.TimelineInteractor;
import com.examples.twitterapp.timeline.TimelineInteractorImpl;
import com.examples.twitterapp.timeline.TimelinePresenter;
import com.examples.twitterapp.timeline.TimelinePresenterImpl;
import com.examples.twitterapp.timeline.TimelineRepository;
import com.examples.twitterapp.timeline.TimelineRepositoryImpl;
import com.examples.twitterapp.timeline.adapters.TimelineAdapter;
import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.ui.OnItemClickListener;
import com.examples.twitterapp.timeline.ui.TimelineView;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DaniRosas on 10/9/17.
 */

@Module
public class TimelineModule {
    private TimelineView view;
    private OnItemClickListener clickListener;

    public TimelineModule(TimelineView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;

    }

    @Provides
    @Singleton
    List<Post> provideItems() {
        return new ArrayList<Post>();
    }


    @Provides
    @Singleton
    OnItemClickListener provideClickListener() {
        return this.clickListener;
    }

    @Provides
    TimelineAdapter provideAdapter(Context context, List<Post> items, OnItemClickListener clickListener) {
        return new TimelineAdapter(context, items, clickListener);
    }

    @Provides
    @Singleton
    TimelineView provideTimelineView() {
        return this.view;
    }

    @Provides
    @Singleton
    TimelinePresenter provideTimelinePresenter(TimelineView view, TimelineInteractor interactor, Eventbus eventBus) {
        return new TimelinePresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    TimelineInteractor provideTimelineInteractor(TimelineRepository repository) {
        return new TimelineInteractorImpl(repository);
    }

    @Provides
    @Singleton
    TimelineRepository provideTimelineRepository(CustomTwitterApiClient client, Eventbus eventBus) {
        return new TimelineRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient provideTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession provideTwitterSession() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }
}

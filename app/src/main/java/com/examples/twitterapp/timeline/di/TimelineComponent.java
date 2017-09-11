package com.examples.twitterapp.timeline.di;

import com.examples.twitterapp.TwitterAppModule;
import com.examples.twitterapp.libs.di.LibsModule;
import com.examples.twitterapp.timeline.ui.TimelineFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DaniRosas on 10/9/17.
 */

@Component
        (modules = {
                TimelineModule.class,
                LibsModule.class,
                TwitterAppModule.class
        })

@Singleton
public interface TimelineComponent {
    void inject(TimelineFragment fragment);
}

package com.examples.twitterapp.libs.di;

import android.support.v4.app.Fragment;

import com.examples.twitterapp.libs.GreenRobotEventBus;
import com.examples.twitterapp.libs.base.Eventbus;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DaniRosas on 9/9/17.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    Eventbus provideEventBus() {
        return new GreenRobotEventBus();
    }

}

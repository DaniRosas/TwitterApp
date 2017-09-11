package com.examples.twitterapp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DaniRosas on 11/9/17.
 */

@Singleton
@Component(modules = {TwitterAppModule.class})

public interface TwitterAppComponent {
}

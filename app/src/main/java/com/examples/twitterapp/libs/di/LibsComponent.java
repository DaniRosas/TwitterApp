package com.examples.twitterapp.libs.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DaniRosas on 9/9/17.
 */


@Singleton
@Component(modules = {LibsModule.class})
public interface LibsComponent {

}

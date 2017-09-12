package com.examples.twitterapp;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

/**
 * Created by DaniRosas on 9/9/17.
 */

public class TwitterApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);

    }

}

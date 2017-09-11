package com.examples.twitterapp.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by DaniRosas on 9/9/17.
 */

public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public TimeLineService getTimelineService(){
        return getService(TimeLineService.class);
    }
}

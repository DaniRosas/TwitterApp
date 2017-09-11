package com.examples.twitterapp.timeline;

import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.events.TimelineEvent;

/**
 * Created by DaniRosas on 10/9/17.
 */

public interface TimelinePresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getPostTweets();
    void onEventMainThread(TimelineEvent event);

    void toggleFavorite(Post tweet);
}

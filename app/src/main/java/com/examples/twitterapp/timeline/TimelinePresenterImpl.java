package com.examples.twitterapp.timeline;

import com.examples.twitterapp.StoredFavoritesInteractor;
import com.examples.twitterapp.libs.base.Eventbus;
import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.events.TimelineEvent;
import com.examples.twitterapp.timeline.ui.TimelineView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class TimelinePresenterImpl implements TimelinePresenter {

    private Eventbus eventBus;
    private TimelineView timelineView;
    private TimelineInteractor timelineInteractor;
    private StoredFavoritesInteractor storedInteractor;

    public TimelinePresenterImpl(Eventbus eventBus, TimelineView timelineView, TimelineInteractor timelineInteractor, StoredFavoritesInteractor storedFavoritesInteractor) {
        this.eventBus = eventBus;
        this.timelineView = timelineView;
        this.timelineInteractor = timelineInteractor;
        this.storedInteractor = storedFavoritesInteractor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);

    }

    @Override
    public void onPause() {
        eventBus.unregister(this);

    }

    @Override
    public void onDestroy() {
        this.timelineView = null;
    }

    @Override
    public void getPostTweets() {
        if (this.timelineView != null){
            timelineView.hideList();
            timelineView.showProgress();
        }
        this.timelineInteractor.getTimelineItemsList();
    }

    @Override
    @Subscribe
    public void onEventMainThread(TimelineEvent event) {
        String errorMsg = event.getError();
        if (this.timelineView != null) {
            timelineView.showList();
            timelineView.hideProgress();
            if (errorMsg != null) {
                this.timelineView.onHashtagsError(errorMsg);
            } else {
                List<Post> items = event.getHashtags();
                if (items != null && !items.isEmpty()) {
                    this.timelineView.setPosts(items);
                }
            }

            switch (event.getType()){
                case TimelineEvent.READ_EVENT:
                    timelineView.setPosts(event.getHashtags());
                    break;
                case TimelineEvent.UPDATE_EVENT:
                    timelineView.postUpdated();
                    break;
                case TimelineEvent.DELETE_EVENT:
                    Post post = event.getHashtags().get(0);
                    timelineView.postDeleted(post);
                    break;

            }
        }
    }

    @Override
    public void toggleFavorite(Post tweet) {
        boolean fav = tweet.getFav();
        tweet.setFav(!fav);
        storedInteractor.executeUpdate(tweet);
    }
}

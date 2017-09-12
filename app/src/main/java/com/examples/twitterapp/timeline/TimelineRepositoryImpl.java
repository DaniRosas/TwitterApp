package com.examples.twitterapp.timeline;

import android.content.Context;

import com.examples.twitterapp.api.CustomTwitterApiClient;
import com.examples.twitterapp.favorites.db.Favorite;
import com.examples.twitterapp.favorites.db.FavoritesDbHelper;
import com.examples.twitterapp.libs.GreenRobotEventBus;
import com.examples.twitterapp.libs.base.Eventbus;
import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.events.TimelineEvent;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class TimelineRepositoryImpl implements TimelineRepository {

    private Eventbus eventBus;
    private CustomTwitterApiClient client;
    private FavoritesDbHelper favoritesDbHelper;
    private final static int TWEET_COUNT = 100;

    public TimelineRepositoryImpl(Eventbus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    public TimelineRepositoryImpl() {
        this.client = new CustomTwitterApiClient(TwitterCore.getInstance().getSessionManager().getActiveSession());

    }

    public void getPosts() {
        Call<List<Tweet>> call = client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true);
        call.enqueue(new retrofit2.Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                List<Post> items = new ArrayList<>();
                for (Tweet tweet : response.body()) {
                    Post tweetModel = new Post();
                    tweetModel.setId(tweet.idStr);
                    tweetModel.setPost(tweet.text);
                    tweetModel.setTime(tweet.createdAt);
                    tweetModel.setUser(tweet.user.name);
                    //tweetModel.setFavoriteCount(tweet.favoriteCount);

                    List<String> hashtags = new ArrayList<>();
                    for (HashtagEntity hashtag : tweet.entities.hashtags) {
                        hashtags.add(hashtag.text);
                    }
                    tweetModel.setHashtags(hashtags);

                    items.add(tweetModel);

                }
                postEvent(items);
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {

            }
        });


    }

    @Override
    public void updateTweet(Post tweet, Context context) {
        favoritesDbHelper = new FavoritesDbHelper(context);
        Favorite fav = new Favorite(tweet.getId(),tweet.getPost(),tweet.getTime());

        if(!tweet.getFav()){
            favoritesDbHelper.deleteFavorite(fav.getId());
        }else {
            favoritesDbHelper.saveFavorite(fav);
        }
    }

    @Override
    public void deleteTweet(Post tweet) {
    }

    @Override
    public void getSavedFavorites() {
        //FlowCursorList<Post> storedRecipes = new FlowCursorList<>(true, Post.class);
        //post(TimelineEvent.READ_EVENT, storedRecipes.getAll());
    }

    private boolean checkIfTweetHasHashtags(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error) {
        TimelineEvent event = new TimelineEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<Post> items) {
        TimelineEvent event = new TimelineEvent();
        event.setHashtags(items);
        Eventbus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(event);
    }

    private void post(int type, List<Post> posts) {
        TimelineEvent event = new TimelineEvent();
        event.setHashtags(posts);
        event.setType(type);
        eventBus.post(event);
    }

    private void post(int type) {
        post(type, null);
    }
    private void post(){
        post(TimelineEvent.UPDATE_EVENT, null);
    }
}

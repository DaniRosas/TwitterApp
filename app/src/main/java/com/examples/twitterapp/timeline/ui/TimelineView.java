package com.examples.twitterapp.timeline.ui;

import com.examples.twitterapp.timeline.entities.Post;

import java.util.List;

/**
 * Created by DaniRosas on 10/9/17.
 */

public interface TimelineView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void postUpdated();
    void postDeleted(Post post);

    void onHashtagsError(String error);
    void setPosts(List<Post> items);
}

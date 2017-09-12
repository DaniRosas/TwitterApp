package com.examples.twitterapp.timeline.ui;

import android.content.Context;

import com.examples.twitterapp.timeline.entities.Post;

/**
 * Created by DaniRosas on 11/9/17.
 */

public interface OnItemClickListener {
    void onItemClick(Post tweet);

    void onFavClick(Post tweet, Context context);
}

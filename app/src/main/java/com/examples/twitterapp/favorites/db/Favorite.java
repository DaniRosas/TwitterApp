package com.examples.twitterapp.favorites.db;

import android.content.ContentValues;

import static com.examples.twitterapp.favorites.db.FavoritesContract.FavoritesEntry;

/**
 * Created by DaniRosas on 12/9/17.
 */

public class Favorite {
    private String id;
    private String post;
    private String time;

    public Favorite(String id, String post, String time) {
        this.id = id;
        this.post = post;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FavoritesEntry.ID, id);
        values.put(FavoritesEntry.POST, post);
        values.put(FavoritesEntry.TIME, time);
        return values;
    }


}

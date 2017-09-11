package com.examples.twitterapp.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by DaniRosas on 11/9/17.
 */

@Database(name = PostsDatabase.NAME, version = PostsDatabase.VERSION)
public class PostsDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Posts";
}

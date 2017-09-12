package com.examples.twitterapp.favorites.db;

import android.provider.BaseColumns;

/**
 * Created by DaniRosas on 12/9/17.
 */

public class FavoritesContract {

    public static abstract class FavoritesEntry implements BaseColumns{
        public static final String TABLE_NAME ="favorites";
        public static final String ID = "id";
        public static final String POST = "post";
        public static final String TIME = "time";
    }
}

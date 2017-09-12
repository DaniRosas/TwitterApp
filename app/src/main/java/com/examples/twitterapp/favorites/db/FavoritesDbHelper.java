package com.examples.twitterapp.favorites.db;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.examples.twitterapp.favorites.db.FavoritesContract.FavoritesEntry;

/**
 * Created by DaniRosas on 12/9/17.
 */

public class FavoritesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Favorites.db";

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public FavoritesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public FavoritesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FavoritesEntry.TABLE_NAME + " ("
                    + FavoritesEntry.ID + " TEXT PRIMARY KEY, "
                    + FavoritesEntry.POST + " TEXT NOT NULL, "
                    + FavoritesEntry.TIME + " TEXT NOT NULL)"
                    );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Favorite> getAllFavorites() {
        List<Favorite> favorites = new ArrayList<>();
        Cursor c = getReadableDatabase()
                .query(
                        FavoritesEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
        while(c.moveToNext()){
            Favorite fav = new Favorite(c.getString(0), c.getString(1), c.getString(2));
            favorites.add(fav);
        }
        if(c.isLast()){
            Favorite fav = new Favorite(c.getString(0), c.getString(1), c.getString(2));
            favorites.add(fav);
        }
        return favorites;
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockFavorite(sqLiteDatabase, new Favorite("1233423", "Abogado penalista lalalalal",
                "300 200 1111"));
        mockFavorite(sqLiteDatabase, new Favorite("12343112", "En enero gener un parrafo limpio y sincero en febrero",
                "Martes 13 de enero"));
        mockFavorite(sqLiteDatabase, new Favorite("124322", "Afilando acero me espero",
                "20 de enero 13:33"));
    }
    public long mockFavorite(SQLiteDatabase db, Favorite favorite) {
        return db.insert(
                FavoritesEntry.TABLE_NAME,
                null,
                favorite.toContentValues());
    }

    public long saveFavorite(Favorite favorite) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                FavoritesEntry.TABLE_NAME,
                null,
                favorite.toContentValues());

    }

    public int deleteFavorite(String favoriteId) {
        return getWritableDatabase().delete(
                FavoritesEntry.TABLE_NAME,
                FavoritesEntry.ID + " LIKE ?",
                new String[]{favoriteId});
    }

}

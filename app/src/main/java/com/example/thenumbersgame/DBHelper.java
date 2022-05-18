package com.example.thenumbersgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "theNumbersGame";
    private static final int DB_VERSION = 2;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBHelper", "OnCreate Called");
        updateDatabase(db, 0, DB_VERSION);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBHelper", "OnUpgrade Called");
        updateDatabase(db, oldVersion, newVersion);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateDatabase(db, oldVersion, newVersion);
    }


    public void addGame(int totalGames, int gamesWon, int gamesLost, int gamesStreak){ {
        Log.i("DBHelper", "addGame Called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gamesValues = new ContentValues();
        gamesValues.put("TOTAL_GAMES", totalGames);
        gamesValues.put("GAMES_WON", gamesWon);
        gamesValues.put("GAMES_LOST", gamesLost);
        gamesValues.put("GAME_STREAK" , gamesStreak);
        Log.i("DBHelper", "Adding to DB: Games played: " + totalGames + " | Games Won: " + gamesWon + " | Games Lost: " + gamesLost + " | Winning Streak: " + gamesStreak);
        db.insert("GAMES", null, gamesValues);
        }
    }
    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBHelper", "updateDatabase Called");
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE GAMES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TOTAL_GAMES INTEGER, "
                    + "GAMES_WON INTEGER, "
                    + "GAMES_LOST INTEGER, "
                    + "GAME_STREAK INTEGER);");
        }
    }
}




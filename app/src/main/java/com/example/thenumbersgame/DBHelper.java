package com.example.thenumbersgame;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "The_Numbers_Game_DB";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBHelper", "OnCreate Called");
        db.execSQL("CREATE TABLE GAMES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TOTAL_GAMES INTEGER, "
                + "GAMES_WON INTEGER, "
                + "GAMES_LOST INTEGER, "
                + "GAME_STREAK INTEGER);");

        db.execSQL("CREATE TABLE SETTINGS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "MIN INTEGER, "
                + "MAX INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBHelper", "OnUpgrade Called");
        updateDatabase(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    //TODO review better ways to do this
    //Check if data is in table if not put in default
    public void populateTable() {
        Log.i("checkDB", "checkDB Called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gamesValues = new ContentValues();
        String query = "SELECT count(*) FROM GAMES";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int countInt = cursor.getInt(0);
        if (countInt > 0) {
            Log.i("checkDB", "Items found: " + countInt);
        } else {
            Log.i("checkDB", "No items found, entering default data");
            gamesValues.put("TOTAL_GAMES", 0);
            gamesValues.put("GAMES_WON", 0);
            gamesValues.put("GAMES_LOST", 0);
            gamesValues.put("GAME_STREAK", 0);
            db.insert("GAMES", null, gamesValues);
            db.close();
            cursor.close();
        }
    }

    //TODO combine this with above
    public void populateSettingsTable(){
        Log.i("checkDB", "checkDB Called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gamesValues = new ContentValues();
        String query = "SELECT count(*) FROM SETTINGS";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int countInt = cursor.getInt(0);
        if (countInt > 0) {
            Log.i("checkDB", "Items found: " + countInt);
        } else {
            Log.i("checkDB", "No items found, entering default data");
            gamesValues.put("MIN", 101);
            gamesValues.put("MAX", 999);
            db.insert("SETTINGS", null, gamesValues);
            db.close();
            cursor.close();
        }

    }


    public void addGame(int totalGames, int gamesWon, int gamesLost, int gamesStreak) {
        {
            Log.i("DBHelper", "addGame Called");

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues gamesValues = new ContentValues();

            Log.i("DBHelper", "Adding to DB: Games played: " + totalGames + " | Games Won: " + gamesWon + " | Games Lost: " + gamesLost + " | Winning Streak: " + gamesStreak);

            gamesValues.put("TOTAL_GAMES", totalGames);
            gamesValues.put("GAMES_WON", gamesWon);
            gamesValues.put("GAMES_LOST", gamesLost);
            gamesValues.put("GAME_STREAK", gamesStreak);

            db.update("GAMES", gamesValues, "_id = 1", null);
            db.close();
        }
    }


    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBHelper", "updateDatabase Called");
        //TODO fix logic
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE GAMES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TOTAL_GAMES INTEGER, "
                    + "GAMES_WON INTEGER, "
                    + "GAMES_LOST INTEGER, "
                    + "GAME_STREAK INTEGER);");

            db.execSQL("CREATE TABLE SETTINGS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "MIN INTEGER, "
                    + "MAX INTEGER);");
        }
    }


    public List<Integer> getScores() {
        Log.i("DBHelper", "getScores Called");
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM GAMES WHERE _id = 1;", null);

        List<Integer> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            result.add(id);
            int games = cursor.getInt(1);
            result.add(games);
            int win = cursor.getInt(2);
            result.add(win);
            int lost = cursor.getInt(3);
            result.add(lost);
            int streak = cursor.getInt(4);
            result.add(streak);
        }
        cursor.close();
        db.close();
        return result;
    }

    public void saveSettings(int min, int max) {
        Log.i("DBHelper", "saveSettings Called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues settingsValues = new ContentValues();
        Log.i("DBHelper", "Adding settings | Min:" + min + " | Max: " + max);
        settingsValues.put("MIN", min);
        settingsValues.put("MAX", max);
        db.update("SETTINGS", settingsValues, "_id = 1", null);
        db.close();
    }

    public List<Integer> loadSettings() {
        Log.i("DBHelper", "loadSettings Called");
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE _id = 1;", null);

        List<Integer> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            result.add(id);
            int min = cursor.getInt(1);
            result.add(min);
            int max = cursor.getInt(2);
            result.add(max);
        }
        return result;
    }
}





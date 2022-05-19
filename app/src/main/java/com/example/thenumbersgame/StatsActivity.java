package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class StatsActivity extends AppCompatActivity {

    boolean win;

    private DBHelper dbHelper;

    private TextView games_played;
    private TextView games_won;
    private TextView games_lost;
    private TextView games_streak;

    private int total;
    private int wins;
    private int lost;
    private int run;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Log.i("StatsActivity", "Created");

        dbHelper = new DBHelper(StatsActivity.this);

        games_played = findViewById(R.id.games_played);
        games_won = findViewById(R.id.games_won);
        games_lost = findViewById(R.id.games_lost);
        games_streak = findViewById(R.id.streak);

        //Enter default data
        dbHelper.populateTable();
        getScores();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            win = extras.getBoolean("win");
        }

        if(win){
            total = total + 1;
            run = run + 1;
            wins = wins + 1;
        }

        //TODO this runs when clicking on stats page
        else {
            Toast.makeText(this, "Better Look next time!", Toast.LENGTH_SHORT).show();
            System.out.println("total: " + total);
            total = total + 1;
            System.out.println("New total: " + total);
            run = 0;
            lost = lost + 1;
        }

        Log.i("StatsActivity", "Adding scores: Total:" + total + " | win: " + wins + " | Loss: " + lost + " | Run: " + run);

        //add latest game to DB
        dbHelper.addGame(total,wins,lost,run);

        //get the latest results from DB
        getScores();

        //set score on screen
        setScore();

    }

    //Get score from DB
    private void getScores() {
        Log.i("StatsActivity", "Reading data from DB");
        ArrayList scores = (ArrayList) dbHelper.getScores();
        total = (int) scores.get(1);
        wins = (int) scores.get(2);
        lost = (int) scores.get(3);
        run = (int) scores.get(4);
        Log.i("StatsActivity", "Reading data from DB | Total: " + total + " | wins: " + wins + "| lost: " + lost + " | run: " + run);
   }

   //Set scores on screen
    private void setScore() {
        Log.i("StatsActivity", "setScore called.");
        games_played.setText(String.valueOf(total));
        games_won.setText(String.valueOf(wins));
        games_lost.setText(String.valueOf(lost));
        games_streak.setText(String.valueOf(run));
    }


    public void homeClicked(View view) {
        Log.i("StatsActivity", "Clicked button home");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //TODO is this needed?
    }

    public void statsClicked(View view) {
        Log.i("StatsActivity", "Clicked button stats");
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        Log.i("StatsActivity", "Clicked button settings");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
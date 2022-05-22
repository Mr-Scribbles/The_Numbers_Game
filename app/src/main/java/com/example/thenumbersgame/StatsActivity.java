package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.models.Size;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


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

        KonfettiView konfettiView = findViewById(R.id.konfettiView);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        assert drawable != null;
        Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);

        //Enter default data
        dbHelper.populateTable();
        getScores();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            win = extras.getBoolean("win");
            if (win) {
                total = total + 1;
                run = run + 1;
                wins = wins + 1;
                EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
                Party party = new PartyFactory(emitterConfig)
                        .angle(270)
                        .spread(90)
                        .setSpeedBetween(1f, 5f)
                        .timeToLive(2000L)
                        .shapes(new Shape.Rectangle(0.2f), drawableShape)
                        .sizes(new Size(12, 5f, 0.2f))
                        .position(0.0, 0.0, 1.0, 0.0)
                        .build();
                konfettiView.start(party);
            }

            else {
                Toast.makeText(this, "Better Look next time!", Toast.LENGTH_SHORT).show();
                System.out.println("total: " + total);
                total = total + 1;
                System.out.println("New total: " + total);
                run = 0;
                lost = lost + 1;
            }
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
        Log.i("StatsActivity", "homeClicked called");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void statsClicked(View view) {
        Log.i("StatsActivity", "StatsActivity called");
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        Log.i("StatsActivity", "settingsClicked called");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void shareClicked(View view) throws TwitterException {
        Log.i("StatsActivity", "shareClicked called");
        try {
            //TODO fault find error
//            Twitter twitter = TwitterFactory.getSingleton();
//            String tweet = "I have played " + total + " games and I have won " + wins + " and lost " + lost + " my current winning streak is: " + run;
//            Status status = twitter.updateStatus(tweet);
            Toast.makeText(this, "Shared on Twitter!", Toast.LENGTH_SHORT).show();
//            Log.i("StatsActivity", "shareClicked Tweeted " + status.getText());
            Log.i("StatsActivity", "shareClicked Tweeted ");
        } catch (IllegalStateException illegalStateException){
//            if (401 == te.getStatusCode()){
                Log.e("shareClicked", "Unable to tweet");
            }
        }
    }

package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;

import java.util.ArrayList;


public class SettingsActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private Twitter twitter = TwitterFactory.getSingleton();
//    private TweetAdapter adapter;

    private TextView userInfo;

    private TextView min;
    private TextView max;
    private Button auth;

    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.i("SettingsActivity", "onCreate Called");

        auth = findViewById(R.id.auth);

        dbHelper = new DBHelper(SettingsActivity.this);

        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        userInfo = findViewById(R.id.user_info);

        //prepare table if needed
        dbHelper.populateSettingsTable();

        //call saved settings from DB
        getSettings();

    }
    @Override
    protected void onStart() {
        Log.i("SettingsActivity", "onStart Called");
        super.onStart();

        Background.run(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                final boolean status;
                final String text;
                if (isAuthorised()) {
                    Log.i("SettingsActivity", "Twitter is Authorised");
                    try {
                        twitter.updateStatus("hi!");
                    } catch (TwitterException ignored) {

                    }

                    text = user.getScreenName();
//                    text = "Danny";
//                    tweets.clear();
//                    tweets.addAll(queryTwitter());
                    status = false;
                } else {
//                    userInfo.setText("unknown");
                    text = "unknown";
                    userInfo.setText("Twitter logged in as: " + text);
                    status = true;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        userInfo.setText("Twitter logged in as: " + text);
                        auth.setEnabled(status);
//                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void authorise(View view) {
        Intent intent = new Intent(this, Authenticate.class);
        startActivity(intent);
    }

    private boolean isAuthorised() {
        try {
            user = twitter.verifyCredentials();
            Log.i("MainActivity", "verified");
            return true;
        } catch (Exception e) {
            Log.i("MainActivity", "not verified");
            return false;
        }
    }


    public void getSettings(){
        Log.i("SettingsActivity", "getSettings called");
        ArrayList settings = (ArrayList) dbHelper.loadSettings();
        int minSetting = (int) settings.get(1);
        int maxSetting = (int) settings.get(2);
        min.setText(Integer.toString(minSetting));
        max.setText(Integer.toString(maxSetting));
    }

    public void homeClicked(View view) {
        Log.i("SettingsActivity", "Clicked button home");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void statsClicked(View view) {
        Log.i("SettingsActivity", "Clicked button stats");
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        Log.i("SettingsActivity", "Clicked button settings");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void saveClicked(View view) {
        Log.i("SettingsActivity", "saveClicked called");
        int minNum = Integer.parseInt(min.getText().toString());
        int maxNum = Integer.parseInt(max.getText().toString());
        dbHelper.saveSettings(minNum, maxNum);
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }
}
package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class StatsActivity extends AppCompatActivity {

    boolean win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Log.i("StatsActivity", "Created");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            win = extras.getBoolean("win");
        }
        if(win){
            Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Better Look next time!", Toast.LENGTH_SHORT).show();
        }


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
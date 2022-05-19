package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SettingsActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private TextView min;
    private TextView max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.i("SettingsActivity", "onCreate Called");

        dbHelper = new DBHelper(SettingsActivity.this);

        min = findViewById(R.id.min);
        max = findViewById(R.id.max);

        //prepare table if needed
        dbHelper.populateSettingsTable();

        //call saved settings from DB
        getSettings();

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
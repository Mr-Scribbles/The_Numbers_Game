package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> bigNumbers = new ArrayList<>();
    private ArrayList<Integer> smallNumbers = new ArrayList<>();
    private ArrayList<Integer> numbers = new ArrayList<>();

    private TextView displayNumbers;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate called");

        displayNumbers = findViewById(R.id.numbersView);

        //TODO Add to arrays from database

        //prepare small numbers
        for (int i = 1; i < 11; i++) {
            Log.i("MainActivity", "Small number added to array: " + i);
            smallNumbers.add(i);
        }

        //prepare big numbers
        for (int i = 25; i < 101; i++) {
            if (i % 25 == 0) {
                Log.i("MainActivity", "Big number added to array: " + i);
                bigNumbers.add(i);
            }
        }
    }

    public void homeClicked(View view) {
        Log.i("MainActivity", "Clicked button home");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void statsClicked(View view) {
        Log.i("MainActivity", "Clicked button stats");
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        Log.i("MainActivity", "Clicked button settings");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void quickPickClick(View view) {
        Log.i("MainActivity", "Clicked button QuickPick");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

    //Add up to 4 big numbers to game
    public void largeClicked(View view) {
        Log.i("MainActivity", "Clicked button large");
        if (bigNumbers.isEmpty()) {
            Toast.makeText(this, "You can only choose up to 4 large numbers", Toast.LENGTH_SHORT).show();
        } else {
            int i = (int) (Math.random() * bigNumbers.size());
            int randomBig = bigNumbers.get(i);
            bigNumbers.remove(i);
            addNumbers(randomBig);
        }
    }

    public void smallClicked(View view) {
        Log.i("MainActivity", "Clicked button small");
        int i = (int) (Math.random() * smallNumbers.size());
        int randomSmall = smallNumbers.get(i);
        smallNumbers.remove(i);
        addNumbers(randomSmall);
    }

    private void addNumbers(int numberPicked) {
        if(numbers.size() == 5) {
            numbers.add(numberPicked);
            displayNumbers.setText(numbers.toString());
            System.out.println("starting delay");
            Toast.makeText(this, "You have picked 6 numbers, Get Ready!", Toast.LENGTH_SHORT).show();
            //slight delay fpr user
            Handler handler = new Handler();
                handler.postDelayed(() -> {
                    //run Game with chosen numbers
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("numbers", numbers);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                }, 1000);
            } else if (numbers.size() < 5){
                numbers.add(numberPicked);
                displayNumbers.setText(numbers.toString());
                System.out.println("numbers is " + numbers);
            }
    }
}


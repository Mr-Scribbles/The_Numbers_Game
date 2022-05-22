package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;

    private ArrayList<Integer> bigNumbers = new ArrayList<>();
    public ArrayList<Integer> smallNumbers = new ArrayList<>();
    private ArrayList<Integer> numbers = new ArrayList<>();



    private TextView displayNumbers;

    private long lastUpdate;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate called");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        displayNumbers = findViewById(R.id.numbersView);

        lastUpdate = System.currentTimeMillis();

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

    @Override
    public void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume called");
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause called");
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

//            Log.i("MainActivity", "getAccelerometer values" + values[0] + " | " + values[1] + " | " + values[2]);

            float acceleration = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            long actualTime = sensorEvent.timestamp;
            if(acceleration >= 2){
                if(actualTime - lastUpdate < 200){
                    Log.i("MainActivity", "actualTime - lastUpdate < 200: " + actualTime + lastUpdate);
                    return;
                }
                lastUpdate = actualTime;
                Toast.makeText(this, "Quick Pick Selected", Toast.LENGTH_SHORT).show();
                pickRandom();
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.i("MainActivity", "onAccuracyChanged called");

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
        Log.i("MainActivity", "quickPickClick Called");
        pickRandom();
//        Intent intent = new Intent(this, GameActivity.class);
//        startActivity(intent);

    }

    public void pickLarge() {
        Log.i("MainActivity", "Clicked button large");
        if (bigNumbers.isEmpty()) {
            pickSmall();
//            Toast.makeText(this, "You can only choose up to 4 large numbers", Toast.LENGTH_SHORT).show();
        } else {
            int i = (int) (Math.random() * bigNumbers.size());
            int randomBig = bigNumbers.get(i);
            bigNumbers.remove(i);
            addNumbers(randomBig);
        }
    }

    public void largeClicked(View view) {
        Log.i("MainActivity", "Clicked button large");
        pickLarge();
    }

    public void smallClicked(View view) {
        Log.i("MainActivity", "Clicked button small");
        pickSmall();
    }

    public void pickSmall() {
        Log.i("MainActivity", "pickSmall Called");
        int i = (int) (Math.random() * smallNumbers.size());
        int randomSmall = smallNumbers.get(i);
        smallNumbers.remove(i);
        addNumbers(randomSmall);
    }

    private void addNumbers(int numberPicked) {
        if (numbers.size() == 5) {
            numbers.add(numberPicked);
            displayNumbers.setText(numbers.toString().replace(",", " ").replace("[", " "). replace("]", " ").trim());
            Toast.makeText(this, "Get Ready!", Toast.LENGTH_SHORT).show();
            //slight delay fpr ux
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                //run Game with chosen numbers
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("numbers", numbers);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }, 1000);
        } else if (numbers.size() < 5) {
            numbers.add(numberPicked);
            displayNumbers.setText(numbers.toString().replace(",", " ").replace("[", " "). replace("]", " ").trim());
            System.out.println("numbers is " + numbers);
        }
    }

    public void pickRandom() {
        Log.i("MainActivity", "pickRandom Called");
        int bigCount = 0;
        int min = 0;
        int max = 2;
        for (int i = 0; i < 6; ++i) {
            Random random = new Random();
            int randomNumber = random.nextInt(max - min) + min;
            if (randomNumber == 1) {
                pickLarge();
                bigCount = bigCount + bigCount;
                if(bigCount > 4){
                    pickSmall();
                }
            } else if (randomNumber == 0) {
                pickSmall();
            }

        }
    }

}


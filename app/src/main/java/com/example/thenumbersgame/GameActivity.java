package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {


    private static int max;
    private static int min;
    private DBHelper dbHelper;

    private ArrayList<Integer> numbers = new ArrayList<>();


    private final int time = 60000;
//    private int time = 60; //set for debugging

    private boolean win;

    private MediaPlayer lose_sound;
    private MediaPlayer tick_sound;
    private MediaPlayer win_sound;


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button add;
    private Button subtract;
    private Button divide;
    private Button multiply;


    private TextView num1;
    private TextView operator;
    private TextView num2;
    private TextView equal;
    private TextView total;
    private TextView random_num;
    private TextView timer_view;

    private CountDownTimer timer = null;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.i("GameActivity", "onCreate Called");

        dbHelper = new DBHelper(GameActivity.this);

        lose_sound = MediaPlayer.create(this, R.raw.lose_sound);
        win_sound = MediaPlayer.create(this, R.raw.win_sound);
        tick_sound = MediaPlayer.create(this, R.raw.tick_sound);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        Button clear = findViewById(R.id.clear);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        clear.setOnClickListener(this);

        num1 = findViewById(R.id.num1);
        operator = findViewById(R.id.operator);
        num2 = findViewById(R.id.num2);
        equal = findViewById(R.id.equal);
        total = findViewById(R.id.total);
        random_num = findViewById(R.id.random_num);
        timer_view = findViewById(R.id.count_down);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            numbers = extras.getIntegerArrayList("numbers");
            setNumbers();
        }
        dbHelper.populateSettingsTable();
        getSettings();
        random_num.setText(Integer.toString(setRandomNum()));
        countDown();

    }

    @Override
    public void onStop() {
        Log.i("GameActivity", "onStop Called");
        cancelTimer();
        super.onStop();
    }

    public void homeClicked(View view) {
        Log.i("GameActivity", "homeClicked Called");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void statsClicked(View view) {
        Log.i("GameActivity", "statsClicked Called");
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        Log.i("GameActivity", "settingsClicked Called");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void getSettings(){
        Log.i("SettingsActivity", "getSettings called");
        ArrayList settings = (ArrayList) dbHelper.loadSettings();
        min = (int) settings.get(1);
        max = (int) settings.get(2);
    }


    @SuppressLint("SetTextI18n")
    private void setNumbers() {
        Log.i("GameActivity", "setNumbers Called");
        button1.setText(numbers.get(0).toString());
        button2.setText(numbers.get(1).toString());
        button3.setText(numbers.get(2).toString());
        button4.setText(numbers.get(3).toString());
        button5.setText(numbers.get(4).toString());
        button6.setText(numbers.get(5).toString());

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (button1.getText().equals("")) {
                    break;
                } else if (num1.getText().equals("")) {
                    System.out.println("num1 not used setting text");
                    num1.setText(button1.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button1.getText());
                }
                button1.setText("");
                System.out.println("num1: " + num1.getText() + " | num2: " + num2.getText() + " | operator: " + operator.getText());
                //check if ready to do math
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.button2:
                if (button2.getText().equals("")) {
                    break;
                } else if (num1.getText().equals("")) {
                    System.out.println("num1 not used setting text");
                    num1.setText(button2.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button2.getText());
                }
                button2.setText("");
                System.out.println("num1: " + num1.getText() + " | num2: " + num2.getText() + " | operator: " + operator.getText());
                //check if ready to do math
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.button3:
                if (button3.getText().equals("")) {
                    break;
                } else if (num1.getText().equals("")) {
                    System.out.println("num1 not used setting text");
                    num1.setText(button3.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button3.getText());
                }
                button3.setText("");
                System.out.println("num1: " + num1.getText() + " | num2: " + num2.getText() + " | operator: " + operator.getText());
                //check if ready to do math
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.button4:
                if (button4.getText().equals("")) {
                    break;
                } else if (num1.getText().equals("")) {
                    System.out.println("num1 not used setting text");
                    num1.setText(button4.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button4.getText());
                }
                button4.setText("");
                System.out.println("num1: " + num1.getText() + " | num2: " + num2.getText() + " | operator: " + operator.getText());
                //check if ready to do math
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.button5:
                if (button5.getText().equals("")) {
                    break;
                } else if (num1.getText().equals("")) {
                    System.out.println("num1 not used setting text");
                    num1.setText(button5.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button5.getText());
                }
                button5.setText("");
                System.out.println("num1: " + num1.getText() + " | num2: " + num2.getText() + " | operator: " + operator.getText());
                //check if ready to do math
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.button6:
                if (button6.getText().equals("")) {
                    break;
                } else if (num1.getText().equals("")) {
                    System.out.println("num1 not used setting text");
                    num1.setText(button6.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button6.getText());
                }
                button6.setText("");
                System.out.println("num1: " + num1.getText() + " | num2: " + num2.getText() + " | operator: " + operator.getText());
                //check if ready to do math
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.add:
                operator.setText(add.getText());
                if (num1.getText().equals("")) {
                    break;
                } else if (num2.getText().equals("")) {
                    break;
                } else if (operator.getText().equals("")) {
                    break;
                } else {
                    doMath();
                }
                break;

            case R.id.subtract:
                operator.setText(subtract.getText());
                break;

            case R.id.divide:
                //TODO set only if it can be divided
                operator.setText(divide.getText());
                break;

            case R.id.multiply:
                operator.setText(multiply.getText());
                break;

            case R.id.clear:
                setNumbers();
                num1.setText("");
                num2.setText("");
                operator.setText("");
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void doMath() {
        Log.i("GameActivity", "doMath called");
        int number1 = Integer.parseInt(num1.getText().toString());
        int number2 = Integer.parseInt(num2.getText().toString());
        int number3 = 0;
        int randomNumber = Integer.parseInt(random_num.getText().toString());
        if (operator.getText().equals("+")) {
            number3 = number1 + number2;
        } else if (operator.getText().equals("-")) {
            number3 = number1 - number2;
        } else if (operator.getText().equals("*")) {
            number3 = number1 * number2;
        } else if (operator.getText().equals("/")) {
            number3 = number1 / number2;
        }
        total.setText(Integer.toString(number3));
        equal.setText("=");
        if (randomNumber == number3) {
            Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show();
            win_sound.start();
            win = true;
            Handler handler = new Handler();
                handler.postDelayed(() -> {
                    //slight delay before calling stats page
                    Intent intent = new Intent(GameActivity.this, StatsActivity.class);
                    intent.putExtra("win", win);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                }, 1000);
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            //slight delay before clearing
            equal.setText("");
            total.setText("");
            num1.setText("");
            num2.setText("");
            operator.setText("");
        }, 500);
        setNewNumber();
    }

    // put new number in empty button
    private void setNewNumber() {
        if (button1.getText().equals("")) {
            button1.setText(total.getText());
        } else if (button2.getText().equals("")) {
            button2.setText(total.getText());
        } else if (button3.getText().equals("")) {
            button3.setText(total.getText());
        } else if (button4.getText().equals("")) {
            button4.setText(total.getText());
        } else if (button5.getText().equals("")) {
            button5.setText(total.getText());
        } else if (button6.getText().equals("")) {
            button6.setText(total.getText());
        }
    }
    @SuppressLint("SetTextI18n")
    public static int setRandomNum() {
        Random random = new Random();
        //        random_num.setText(Integer.toString(randomNumber));
        return random.nextInt(max - min) + min;
    }


    private void countDown() {
        Log.i("GameActivity", "CountDown called");
        timer = new CountDownTimer(time, 1000) {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onTick(long l) {
                tick_sound.start();
                int seconds = (int) (l /1000);
                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);
                timer_view.setText(String.format("%02d", hours)
                        + ":" + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                Log.i("GameActivity", "onFinish called");
                timer_view.setText("00:00:00");
                lose_sound.start();
                Intent intent = new Intent(GameActivity.this, StatsActivity.class);
                intent.putExtra("win", win);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        };
        timer.start();
    }

    private void cancelTimer() {
        Log.i("GameActivity", "cancelTimer called");
        if(timer != null){
            timer.cancel();
        }
    }


}





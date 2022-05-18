package com.example.thenumbersgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();

    private int min = 101;
    private int max = 999;
    private int time = 60000;

    private boolean win;


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
    private Button clear;


    private TextView num1;
    private TextView operator;
    private TextView num2;
    private TextView equal;
    private TextView total;
    private TextView random_num;
    private TextView timer_view;


    private CountDownTimer timer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.i("GameActivity", "Created");

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
        clear = findViewById(R.id.clear);

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
        setRandomNum();
        countDown();

    }

    public void homeClicked(View view) {
        Log.i("GameActivity", "Clicked button home");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //TODO is this needed?
    }

    public void statsClicked(View view) {
        Log.i("GameActivity", "Clicked button stats");
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void settingsClicked(View view) {
        Log.i("GameActivity", "Clicked button settings");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    private void setNumbers() {
        Log.i("GameActivity", "setNumbers Called");
        //TODO Research better way to add numbers use an array with buttons?
        button1.setText(numbers.get(0).toString());
        button2.setText(numbers.get(1).toString());
        button3.setText(numbers.get(2).toString());
        button4.setText(numbers.get(3).toString());
        button5.setText(numbers.get(4).toString());
        button6.setText(numbers.get(5).toString());

    }

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
            win = true;
            Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //slight delay before calling stats page
                        Intent intent = new Intent(GameActivity.this, StatsActivity.class);
                        intent.putExtra("win", win);
                        setResult(RESULT_OK, intent);
                        startActivity(intent);
                    }
                }, 1000);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //slight delay before clearing
                equal.setText("");
                total.setText("");
                num1.setText("");
                num2.setText("");
                operator.setText("");
            }
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
    private void setRandomNum() {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min) + min;
        random_num.setText(Integer.toString(randomNumber));
    }
    private void countDown() {
        Log.i("GameActivity", "CountDown called");
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                int seconds = (int) (l /1000);
                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);
                timer_view.setText(String.format("%02d", hours)
                        + ":" + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }
            @Override
            public void onFinish() {
                timer_view.setText("00:00:00");
                Intent intent = new Intent(GameActivity.this, StatsActivity.class);
                intent.putExtra("win", win);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        };
        timer.start();
    }

    private void cancelTimer() {
        if(timer != null){
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}





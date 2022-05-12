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
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button operators;

    private TextView num1;
    private TextView operator;
    private TextView num2;
    private TextView equal;
    private TextView total;






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
        operators = findViewById(R.id.operators);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        operators.setOnClickListener(this);


        num1 = findViewById(R.id.num1);
        operator = findViewById(R.id.operator);
        num2 = findViewById(R.id.num2);
        equal = findViewById(R.id.equal);
        total = findViewById(R.id.total);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            numbers = extras.getIntegerArrayList("numbers");
            setNumbers();
        }

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

    private void setNumbers(){
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
        Log.i("GameActivity", "onClick Called");
        switch (view.getId()) {
            case R.id.button1:
                if(num1.getText().equals("")){
                    System.out.println("num1 not used setting text");
                    num1.setText(button1.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button1.getText());
                    if(operator.getText().equals("")){
                        System.out.println("operator is empty");
                    }  else {
                        System.out.println("ready to do math");
                    }
                }
                button1.setText("");
                break;

            case R.id.button2:
                if(num1.getText().equals("")){
                    System.out.println("num1 not used setting text");
                    num1.setText(button2.getText());
                } else {
                    System.out.println("num 1 used setting num 2");
                    num2.setText(button2.getText());
                    if(operator.getText().equals("")){
                        System.out.println("operator is empty");
                    }  else {
                        System.out.println("ready to do math");
                        doMath();
                    }
                }
                button2.setText("");
                break;




            case R.id.operators:
                if (operator.getText().equals("")){
                    operator.setText(operators.getText());
                }
                break;

            case R.id.clear:
                setNumbers();
                break;
                    }


                }

    private void doMath() {
        Log.i("GameActivity", "doMath called");
    }


    //Check if math equation is ready to run
    private void doCheck(){

    }
}



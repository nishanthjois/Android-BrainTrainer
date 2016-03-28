package com.braintrainer.nj.braintrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    Button button0,button1,button2,button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21); //Random number between 0 and 20
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        answers.clear();
        locationOfCorrectAnswer = rand.nextInt(4);
        int inCorrectAnswer;

        for (int i=0;i<4;i++){

            if (i==locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                inCorrectAnswer=rand.nextInt(41);

                while (inCorrectAnswer==locationOfCorrectAnswer){
                    inCorrectAnswer=rand.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");

            Log.i("Tag", "Correct");
        }

        else {

            resultTextView.setText("Wrong!!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
        Log.i("Tag", (String)view.getTag());
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.answerButton0);
        button1 = (Button) findViewById(R.id.answerButton1);
        button2 = (Button) findViewById(R.id.answerButton2);
        button3 = (Button) findViewById(R.id.answerButton3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        generateQuestion();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package muskan.myappcompany.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    int locationOfCorrectAns;
    TextView answerTextView;
    TextView questionTextView;
    ArrayList<String> options = new ArrayList<String>();
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout main;

    int score = 0;
    int numberOfQuestions = 0;
    public void selectOption(View view){

        if(Integer.toString(locationOfCorrectAns+1).equals(view.getTag().toString())){
            answerTextView.setText("Correct!");
            score++;
        }else{
            answerTextView.setText("Incorrect!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        answerTextView.setVisibility(View.VISIBLE);
        newQuestion();
    }
    public void start(View view){
        button = findViewById(R.id.button);
        button.setVisibility(View.GONE);
        main.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.answerTextView));


    }
    public  void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        answerTextView.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        timerTextView.setText("00:30");
        newQuestion();
        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText("00:" + String.format("%02d", millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    public void newQuestion(){

        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        int correctAnswer = a + b;
        int wrongAnswer;
        locationOfCorrectAns = random.nextInt(4);

        options.clear();
        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAns){
                options.add(Integer.toString(correctAnswer));
            }else{
                wrongAnswer = random.nextInt(41);
                while(wrongAnswer == correctAnswer){
                    wrongAnswer = random.nextInt(41);
                }
                options.add(Integer.toString(wrongAnswer));
            }

        }

        button0.setText(options.get(0));
        button1.setText(options.get(1));
        button2.setText(options.get(2));
        button3.setText(options.get(3));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        button0 = (Button) findViewById(R.id.button1);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button4);
        answerTextView = findViewById(R.id.answerTextView);
        answerTextView.setVisibility(View.INVISIBLE);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        answerTextView = findViewById(R.id.answerTextView);
        answerTextView.setVisibility(View.INVISIBLE);
        main = (ConstraintLayout) findViewById(R.id.main);
        main.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.button);



    }
}

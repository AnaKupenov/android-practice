package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
//subclass of Activity;
// provides compatibility support for older Android versions
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
// contains the remaining needed classes like onStart

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private QuizQuestion[] mQuizQuestionsBank = new QuizQuestion[]{
            new QuizQuestion(R.string.question_africa, false),
            new QuizQuestion(R.string.question_americas, true),
            new QuizQuestion(R.string.question_asia, true),
            new QuizQuestion(R.string.question_ocean, true),
            new QuizQuestion(R.string.question_suez, false),
    };

    public void updateQuestion() {
        mCurrentIndex++;
        if(mCurrentIndex >= mQuizQuestionsBank.length) {
            mCurrentIndex = 0;
        }
        mQuestionTextView.setText(mQuizQuestionsBank[mCurrentIndex].getResourceId());
    }

    public void validateAnswer(boolean userAnswer) {
        int msgId = 0;
        //user answer matches "True"
        if(userAnswer == mQuizQuestionsBank[mCurrentIndex].isAnswerTrue()){
            msgId = R.string.correct_toast;
        } else {
            msgId = R.string.incorrect_toast;
        }
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    //OnCreate(Bundle) is called when an instance of the Activity class
    //is created. Creating an Activity requires a UI to manage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //the resource ID for the layout is an
        // integer constant within the layout innerclass or R
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mQuizQuestionsBank[mCurrentIndex].getResourceId());
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });

        //To wire up a widget:
        //1) Create a reference;
        mTrueButton = (Button) findViewById(R.id.true_button);
        // 2) set a listener to the object to respond to actions
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick() required to implement
//                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                validateAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                validateAnswer(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

}

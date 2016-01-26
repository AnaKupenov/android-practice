package com.bignerdranch.android.geoquiz;

import android.app.Activity;
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
import android.util.Log;
import android.content.Intent;

public class QuizActivity extends AppCompatActivity {
// contains the remaining needed classes like onStart
    private static final String TAG = "QuizActivity";
    private static final String CHEATER = "userCheated";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    private QuizQuestion[] mQuizQuestionsBank = new QuizQuestion[]{
            new QuizQuestion(R.string.question_africa, false),
            new QuizQuestion(R.string.question_americas, true),
            new QuizQuestion(R.string.question_asia, true),
            new QuizQuestion(R.string.question_ocean, true),
            new QuizQuestion(R.string.question_suez, false),
    };

    public void updateQuestion() {
//        //logs the entire stack trace
//        Log.d(TAG, "Updating question text for question #" + mCurrentIndex,
//                new Exception());
        mCurrentIndex++;
        if(mCurrentIndex >= mQuizQuestionsBank.length) {
            mCurrentIndex = 0;
        }
        mQuestionTextView.setText(mQuizQuestionsBank[mCurrentIndex].getResourceId());
    }

    public void validateAnswer(boolean userAnswer) {
        int msgId = 0;

        if(mIsCheater){
            msgId = R.string.cheat_toast;
        } else {
            if(userAnswer == mQuizQuestionsBank[mCurrentIndex].isAnswerTrue()){
                msgId = R.string.correct_toast;
            } else {
                msgId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    //OnCreate(Bundle) is called when an instance of the Activity class
    //is created. Creating an Activity requires a UI to manage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        //the resource ID for the layout is an
        // integer constant within the layout innerclass or R
        setContentView(R.layout.activity_quiz);

        //kude triabva da se namira?
        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(CHEATER, false);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mQuizQuestionsBank[mCurrentIndex].getResourceId());

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

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void  onClick(View v) {
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.id_cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start CheatActivity
                boolean answerIsTrue = mQuizQuestionsBank[mCurrentIndex].isAnswerTrue();
                Intent iLoadCheatView = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(iLoadCheatView, REQUEST_CODE_CHEAT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if(data != null) {
                mIsCheater = CheatActivity.wasAnswerShown(data);
            }
        }
    }

    //typically called by the onPause() and onStop() methods
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(CHEATER, mIsCheater);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
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

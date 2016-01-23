package com.bignerdranch.android.geoquiz;

public class QuizQuestion {
    private int mTextId;    //the questions text
    private boolean mAnswerTrue;

    public QuizQuestion(int resourceId, boolean answerTrue) {
        mTextId = resourceId;
        mAnswerTrue = answerTrue;
    }

    public void setResourceId(int resourceId) { mTextId = resourceId; }
    
    public int getResourceId() {
        return mTextId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
    
}

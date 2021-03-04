package com.hsys.ham.common.utils.sms;


public class HamSmsIntent {
    String intent;	// intent 명
    float score;	// intent 매칭도


    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}

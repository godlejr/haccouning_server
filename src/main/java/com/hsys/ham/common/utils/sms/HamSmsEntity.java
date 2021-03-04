package com.hsys.ham.common.utils.sms;


public class HamSmsEntity {
    String entity;                    // sms entity 값
    String type;                      // sms entity 속성 = table column명
    int startIndex;                 // sms body 내부 entity 시작 위치
    int endIndex;                   // sms body 내부 entity 끝 위치
    float score;                   // simple entity 매칭율  ( list entity 의 경우 null )


    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

  
}

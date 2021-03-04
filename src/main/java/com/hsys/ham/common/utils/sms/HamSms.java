package com.hsys.ham.common.utils.sms;

public class HamSms {

    private String query;						// SMS 본문
    private HamSmsIntent topScoringIntent;		// 매칭 인텐트
    private HamSmsIntent[] intents;						// 정의된 인텐트 별 매칭도
    private HamSmsEntity[] entities;						// 엔티티요소

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public HamSmsIntent getTopScoringIntent() {
        return topScoringIntent;
    }

    public void setTopScoringIntent(HamSmsIntent topScoringIntent) {
        this.topScoringIntent = topScoringIntent;
    }

    public HamSmsIntent[] getIntents() {
        return intents;
    }

    public void setIntents(HamSmsIntent[] intents) {
        this.intents = intents;
    }

    public HamSmsEntity[] getEntities() {
        return entities;
    }

    public void setEntities(HamSmsEntity[] entities) {
        this.entities = entities;
    }
}

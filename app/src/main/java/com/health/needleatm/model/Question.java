package com.health.needleatm.model;

import java.util.ArrayList;

public class Question {

    int quesNumber;
    String quesEng;
    String answer; //[CSV]
    int quesType;   //0-MC 1-Single Option 2-Slider
    String quesOptions;     //If quesType is 1 or 2 then used  [CSV]
    String ansOptions;

    public Question(int quesNumber, String quesEng, String answer, int quesType, String quesOptions, String ansOptions) {
        this.quesNumber = quesNumber;
        this.quesEng = quesEng;
        this.answer = answer;
        this.quesType = quesType;
        this.quesOptions = quesOptions;
        this.ansOptions = ansOptions;
    }

    public int getQuesNumber() {
        return quesNumber;
    }

    public void setQuesNumber(int quesNumber) {
        this.quesNumber = quesNumber;
    }

    public String getQuesEng() {
        return quesEng;
    }

    public void setQuesEng(String quesEng) {
        this.quesEng = quesEng;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuesType() {
        return quesType;
    }

    public void setQuesType(int quesType) {
        this.quesType = quesType;
    }

    public String getQuesOptions() {
        return quesOptions;
    }

    public void setQuesOptions(String quesOptions) {
        this.quesOptions = quesOptions;
    }

    public String getAnsOptions() {
        return ansOptions;
    }

    public void setAnsOptions(String ansOptions) {
        this.ansOptions = ansOptions;
    }
}

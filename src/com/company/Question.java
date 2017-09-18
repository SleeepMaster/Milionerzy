package com.company;

public class Question {

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int rightAnswer;
    private String audienceHelpA;
    private String audienceHelpB;
    private String audienceHelpC;
    private String audienceHelpD;

    public Question() {
    }

    public Question(String question, String answerA, String answerB, String answerC, String answerD, int rightAnswer, String audienceHelpA, String audienceHelpB, String audienceHelpC, String audienceHelpD) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.rightAnswer = rightAnswer;
        this.audienceHelpA = audienceHelpA;
        this.audienceHelpB = audienceHelpB;
        this.audienceHelpC = audienceHelpC;
        this.audienceHelpD = audienceHelpD;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public String getAudienceHelpA() {
        return audienceHelpA;
    }

    public String getAudienceHelpB() {
        return audienceHelpB;
    }

    public String getAudienceHelpC() {
        return audienceHelpC;
    }

    public String getAudienceHelpD() {
        return audienceHelpD;
    }
}

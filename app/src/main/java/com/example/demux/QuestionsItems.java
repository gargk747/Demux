package com.example.demux;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionsItems {

    public QuestionsItems(){

    }

    private String title, difficulty;
    private boolean trending;
    private  String jobNature, interviewType, fullQuestion;
    private int frequency;
    private HashMap<String,String> topicTag, company, college;

    public QuestionsItems(String title, String difficulty, boolean trending,
                          String jobNature, String interviewType, String fullQuestion,
                          int frequency, HashMap<String,String> topicTag,
                          HashMap<String,String> company, HashMap<String,String> college) {
        this.title = title;
        this.difficulty = difficulty;
        this.trending = trending;
        this.jobNature = jobNature;
        this.interviewType = interviewType;
        this.fullQuestion = fullQuestion;
        this.frequency = frequency;
        this.topicTag = topicTag;
        this.company = company;
        this.college = college;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isTrending() {
        return trending;
    }

    public void setTrending(boolean trending) {
        this.trending = trending;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public String getFullQuestion() {
        return fullQuestion;
    }

    public void setFullQuestion(String fullQuestion) {
        this.fullQuestion = fullQuestion;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HashMap<String,String> getTopicTag() {
        return topicTag;
    }

    public void setTopicTag(HashMap<String,String> topicTag) {
        this.topicTag = topicTag;
    }

    public HashMap<String,String> getCompany() {
        return company;
    }

    public void setCompany(HashMap<String,String> company) {
        this.company = company;
    }

    public HashMap<String,String> getCollege() {
        return college;
    }

    public void setCollege(HashMap<String,String> college) {
        this.college = college;
    }
}

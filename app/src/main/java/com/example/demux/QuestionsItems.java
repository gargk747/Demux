package com.example.demux;

import java.util.ArrayList;

public class QuestionsItems {

    private String Title, Difficulty;
    private boolean Trending;
    private  String JobNature, InterviewType, FullQuestion;
    private int Frequency;
    private ArrayList<String> TopicTags, Company, College;

    public QuestionsItems(String title, String difficulty, boolean trending,
                          String jobNature, String interviewType, String fullQuestion,
                          int frequency, ArrayList<String> topicTags,
                          ArrayList<String> company, ArrayList<String> college) {
        Title = title;
        Difficulty = difficulty;
        Trending = trending;
        JobNature = jobNature;
        InterviewType = interviewType;
        FullQuestion = fullQuestion;
        Frequency = frequency;
        TopicTags = topicTags;
        Company = company;
        College = college;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public boolean isTrending() {
        return Trending;
    }

    public void setTrending(boolean trending) {
        Trending = trending;
    }

    public String getJobNature() {
        return JobNature;
    }

    public void setJobNature(String jobNature) {
        JobNature = jobNature;
    }

    public String getInterviewType() {
        return InterviewType;
    }

    public void setInterviewType(String interviewType) {
        InterviewType = interviewType;
    }

    public String getFullQuestion() {
        return FullQuestion;
    }

    public void setFullQuestion(String fullQuestion) {
        FullQuestion = fullQuestion;
    }

    public int getFrequency() {
        return Frequency;
    }

    public void setFrequency(int frequency) {
        Frequency = frequency;
    }

    public ArrayList<String> getTopicTags() {
        return TopicTags;
    }

    public void setTopicTags(ArrayList<String> topicTags) {
        TopicTags = topicTags;
    }

    public ArrayList<String> getCompany() {
        return Company;
    }

    public void setCompany(ArrayList<String> company) {
        Company = company;
    }

    public ArrayList<String> getCollege() {
        return College;
    }

    public void setCollege(ArrayList<String> college) {
        College = college;
    }
}

package com.mystory;

/**
 * Created by slavik on 18.08.15.
 */
public class Question {

    int id;
    private String question;
    private Story story;

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", story=" + story +
                '}';
    }
}
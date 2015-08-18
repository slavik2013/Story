package com.mystory;

import java.util.Map;

/**
 * Created by slavik on 18.08.15.
 */
public class Story {

    private int id;
    private String storyPart;
    private Map<Integer, Question> questions;
    private IDisplay display;

    public Story() {
    }

    public class Builder {
        public Builder id(int id) {
            Story.this.setId(id);
            return this;
        }

        public Builder storyPart(String storyPart) {
            Story.this.setStoryPart(storyPart);
            return this;
        }

        public Builder questions(Map<Integer, Question> questions) {
            Story.this.setQuestions(questions);
            return this;
        }

        public Builder display(IDisplay display) {
            Story.this.setDisplay(display);
            return this;
        }

        public Story build() {
            return Story.this;
        }
    }

    public static Builder getBuilder() {
        return new Story().new Builder();
    }

    public Question getQuestion(int number) {
        return questions.get(number);
    }

    public void displayStory() {
        display.displayStory(this);
    }

    public Story getStoryByQuestionNumber(int questionNumber) throws Exception {
        if (questionNumber > questions.size())
            throw new Exception("choose number between 1 and " + questions.size());

        return getQuestion(questionNumber).getStory();
    }

    /**
     *
     * @param number question position in list of possible options
     * @param question question

     */
    public void addQuestion(int number, Question question) {
        questions.put(number, question);
    }

    public IDisplay getDisplay() {
        return display;
    }

    public void setDisplay(IDisplay display) {
        this.display = display;
    }

    public String getStoryPart() {
        return storyPart;
    }

    public void setStoryPart(String storyPart) {
        this.storyPart = storyPart;
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, Question> questionStory) {
        this.questions = questionStory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Story{" +
                "storyPart='" + storyPart + '\'' +
                ", questionStory=" + questions +
                '}';
    }

}

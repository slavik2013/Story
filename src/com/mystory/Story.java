package com.mystory;

import java.util.Map;

/**
 * Created by slavik on 18.08.15.
 */
public class Story {

    private String storyPart;
    private Map<Integer, Question> questions;
    private IDisplay display;

    public Story() {
    }

    public class Builder {
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

    /*
        find question in story hierarchy by id and add story to this question
        For example if we have hierarchy like below

        Story part 1:
            1) Question 1 (id = 1)
                Story part 2:
                    1) Question 1 (id = 3)
                    2) Question 2 (id = 4)

            2) Question 2 (id = 2)
                Story part 3:
                    1) Question 1 (id = 5)
                    2) Question 2 (id = 6)

        and we have new story part like

        Story part 4:
            1) Question 1 (id = 7)
            2) Question 2 (id = 8)

        which we need to add to question with id 3 if it is chosen

        then after calling addStoryByQuestionId(3, Story part 4) the hierarchy will be

        Story part 1:
            1) Question 1 (id = 1)
                Story part 2:
                    1) Question 1 (id = 3)
                            Story part 4:
                                1) Question 1 (id = 7)
                                2) Question 2 (id = 8)
                    2) Question 2 (id = 4)

            2) Question 2 (id = 2)
                Story part 3:
                    1) Question 1 (id = 5)
                    2) Question 2 (id = 6)
    */
    public void addStoryByQuestionId(int id, Story story) {
        Question question = findQuestionById(id, this);
        question.setStory(story);
    }

    private Question findQuestionById(int id, Story story) {
        Map<Integer, Question> questions = null;
        if (story != null)
            questions = story.getQuestions();
        Question question = null;
        if (questions != null) {
            for (Map.Entry<Integer, Question> questionEntry : questions.entrySet()) {
                if (questionEntry.getValue().getId() == id) {
                    question = questionEntry.getValue();
                    break;
                } else {
                    question = findQuestionById(id, questionEntry.getValue().getStory());
                    if (question != null && question.getId() == id)
                        break;
                }
            }
        }
        return question;
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

    @Override
    public String toString() {
        return "Story{" +
                "storyPart='" + storyPart + '\'' +
                ", questionStory=" + questions +
                '}';
    }

}

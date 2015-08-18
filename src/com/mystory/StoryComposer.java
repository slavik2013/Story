package com.mystory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by slavik on 18.08.15.
 */
public class StoryComposer {

    private Story story;
    private IDisplay display;

    private List<Story> stories = new ArrayList<Story>();
    private List<Question> questions = new ArrayList<Question>();

    public void addStoryPart(int questionId, int storyId, String storyPart){
        Story nextStory = Story.getBuilder()
                .id(storyId)
                .storyPart(storyPart)
                .questions(new LinkedHashMap<Integer, Question>())
                .display(display)
                .build();

        if (story != null){
            for (Question question : questions){
                if (question.getId() == questionId){
                    question.setStory(nextStory);
                    break;
                }
            }
        } else {
            story = nextStory;
        }
        stories.add(nextStory);
    }

    public void addStoryPart(int questionId, Story storyPart){
        if (story != null){
            for (Question question : questions){
                if (question.getId() == questionId){
                    question.setStory(storyPart);
                    break;
                }
            }
        } else {
            story = storyPart;
        }
        stories.add(storyPart);
    }


    public void addQuestion(int storyId, int questionId, int questionOrder, String guestion){
        Question question = new Question(questionId, guestion);
        questions.add(question);
        for (Story story : stories){
            if (story.getId() == storyId){
                story.addQuestion(questionOrder, question);
                break;
            }
        }
    }

    public void addQuestion(int storyId, Question question){
        questions.add(question);
        for (Story story : stories){
            if (story.getId() == storyId){
                story.addQuestion(question.getOrderInList(), question);
                break;
            }
        }
    }

    public Story getStory() {
        return story;
    }

    public IDisplay getDisplay() {
        return display;
    }

    public void setDisplay(IDisplay display) {
        this.display = display;
    }
}

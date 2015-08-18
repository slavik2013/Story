package com.mystory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public void saveStory(String storyName){
        try {
            FileOutputStream fileOut = new FileOutputStream(storyName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(story);
            out.close();
            fileOut.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadStory(String storyName){
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            FileInputStream fileIn = new FileInputStream(storyName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            story = (Story) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

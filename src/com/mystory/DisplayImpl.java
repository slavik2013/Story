package com.mystory;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by slavik on 18.08.15.
 */
public class DisplayImpl implements IDisplay {

    /**
     display story with questions to choose on the console
     */
    @Override
    public void displayStory(Story story) {
        Scanner in = new Scanner(System.in);
        int num = 0;
        Story currentStory = story;
        boolean errorPresent = false;
        while (num != -1) {
            if (!errorPresent)
                printQuestions(currentStory);
            if (currentStory.getQuestions() == null || currentStory.getQuestions().isEmpty())
               break;
            num = in.nextInt();
            if (num != -1) {
                try {
                    currentStory = currentStory.getStoryByQuestionNumber(num);
                    errorPresent = false;
                } catch (Exception e) {
                    errorPresent = true;
                    System.out.println("error = " + e);
                }
            }
        }
    }


    private void printQuestions(Story story) {
        Map<Integer, Question> questions = story.getQuestions();
        System.out.println(story.getStoryPart());
        if (questions != null) {
            for (Map.Entry<Integer, Question> questionEntry : questions.entrySet()) {
                int number = questionEntry.getKey();
                Question question = questionEntry.getValue();
                System.out.println(number + ") " + question.getQuestion());
            }
        }
    }
}

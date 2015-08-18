package test;

import com.mystory.*;
import org.junit.Before;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by slavik on 18.08.15.
 */
public class Test {

    private Story story;

    @Before
    public void generateStory() {
        StoryComposer storyComposer = new StoryComposer();
        IDisplay display = new DisplayImpl();
        storyComposer.setDisplay(display);

        storyComposer.addStoryPart(0, 1, "You spend your summer holidays in Karpaty mountains in Ukraine and there are many places you want to visit. \n You:");
        storyComposer.addQuestion(1, new Question(1, 1, "Visit the highest mountain Hoverla"));
        storyComposer.addQuestion(1, new Question(2, 2, "Visit old city"));

        storyComposer.addStoryPart(1, 2, "You decided to visit mountain Hoverla but you don't have a car and the distance to mountain is 25 kilometers. What will you do? \n You:");
        storyComposer.addQuestion(2, new Question(3, 1, "Take a taxi for 40$"));
        storyComposer.addQuestion(2, new Question(4, 2, "Rent a bicycle for 5$"));

        storyComposer.addStoryPart(2, 3, "You take a bus and go to city where are many different interesting places. You have half a day to see the city. Where will you go? \n You");
        storyComposer.addQuestion(3, new Question(5, 1, "Go to the museums"));
        storyComposer.addQuestion(3, new Question(6, 2, "Walk on the streets in the city"));

        storyComposer.addStoryPart(3, 4, "You take a taxi, climb a mountain, take a photos, walk near mountain, have a rest and you are happy but feel that a taxi is expensive and you wanted to spend less money");
        storyComposer.addStoryPart(4, 5, "Yoy rent a bicycle, climb to the middle of the mountain, take a photos, walk near mountain, have a rest and you are happy but feel tired");
        storyComposer.addStoryPart(5, 6, "You see different museums and learn something about history and culture on your country");
        storyComposer.addStoryPart(6, 7, "You see different old buildings, roads, modern shops, eat a tasty food");


        story = storyComposer.getStory();

    }

    @org.junit.Test
    public void TestGetStoryByQuestionNumber() throws Exception {
        Story story2 = story.getStoryByQuestionNumber(1);
        assertTrue(story2.getStoryPart().equals("You decided to visit mountain Hoverla but you don't have a car and the distance to mountain is 25 kilometers. What will you do? \n You:"));
    }

    @org.junit.Test
    public void TestGetQuestions() {
        Map<Integer, Question> questionMap = story.getQuestions();
        assertTrue(questionMap.size() == 2);
    }

    @org.junit.Test
    public void TestStoryHierarchyForQuestion1() {
        Map<Integer, Question> questionMap = story.getQuestions();
        Question question = questionMap.get(2);
        Story story1 = question.getStory();
        Map<Integer, Question> questionMap2 = story1.getQuestions();
        Question question2 = questionMap2.get(2);

        assertTrue(question2.getQuestion().equals("Walk on the streets in the city"));
    }

    @org.junit.Test
    public void TestStoryHierarchyForQuestion2() {
        Map<Integer, Question> questionMap = story.getQuestions();
        Question question = questionMap.get(1);
        Story story1 = question.getStory();
        Map<Integer, Question> questionMap2 = story1.getQuestions();
        Question question2 = questionMap2.get(2);

        assertTrue(question2.getQuestion().equals("Rent a bicycle for 5$"));
    }

    @org.junit.Test
    public void TestStoryHierarchyForStoryPart() {
        Map<Integer, Question> questionMap = story.getQuestions();
        Question question = questionMap.get(1);
        Story story1 = question.getStory();
        Map<Integer, Question> questionMap2 = story1.getQuestions();
        Question question2 = questionMap2.get(2);
        Story story3 = question2.getStory();

        assertTrue(story3.getStoryPart().equals("Yoy rent a bicycle, climb to the middle of the mountain, take a photos, walk near mountain, have a rest and you are happy but feel tired"));
    }

}

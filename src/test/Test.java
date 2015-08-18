package test;

import com.mystory.DisplayImpl;
import com.mystory.IDisplay;
import com.mystory.Question;
import com.mystory.Story;
import org.junit.Before;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by slavik on 18.08.15.
 */
public class Test {

    private Story story;

    @Before
    public void generateStory() {
        IDisplay display = new DisplayImpl();

        Story storyPartTop = Story.getBuilder()
                .storyPart("You spend your summer holidays in Karpaty mountains in Ukraine and there are many places you want to visit. \n You:")
                .questions(new LinkedHashMap<Integer, Question>())
                .display(display)
                .build();
        storyPartTop.addQuestion(1, new Question(1, "Visit the highest mountain Hoverla"));
        storyPartTop.addQuestion(2, new Question(2, "Visit old city"));


        Story storyPart2 = Story.getBuilder()
                .storyPart("You decided to visit mountain Hoverla but you don't have a car and the distance to mountain is 25 kilometers. What will you do? \n You:")
                .questions(new LinkedHashMap<Integer, Question>())
                .display(display)
                .build();
        storyPart2.addQuestion(1, new Question(3, "Take a taxi for 40$"));
        storyPart2.addQuestion(2, new Question(4, "Rent a bicycle for 5$"));
        storyPartTop.addStoryByQuestionId(1, storyPart2);


        Story storyPart3 = Story.getBuilder()
                .storyPart("You take a bus and go to city where are many different interesting places. You have half a day to see the city. Where will you go? \n You")
                .questions(new LinkedHashMap<Integer, Question>())
                .display(display)
                .build();
        storyPart3.addQuestion(1, new Question(5, "Go to the museums"));
        storyPart3.addQuestion(2, new Question(6, "Walk on the streets in the city"));
        storyPartTop.addStoryByQuestionId(2, storyPart3);


        Story storyPart4 = Story.getBuilder()
                .storyPart("You take a taxi, climb a mountain, take a photos, walk near mountain, have a rest and you are happy but feel that a taxi is expensive and you wanted to spend less money")
                .display(display)
                .build();
        storyPartTop.addStoryByQuestionId(3, storyPart4);


        Story storyPart5 = Story.getBuilder()
                .storyPart("Yoy rent a bicycle, climb to the middle of the mountain, take a photos, walk near mountain, have a rest and you are happy but feel tired")
                .display(display)
                .build();
        storyPartTop.addStoryByQuestionId(4, storyPart5);


        Story storyPart6 = Story.getBuilder()
                .storyPart("You see different museums and learn something about history and culture on your country")
                .display(display)
                .build();
        storyPartTop.addStoryByQuestionId(5, storyPart6);


        Story storyPart7 = Story.getBuilder()
                .storyPart("You see different old buildings, roads, modern shops, eat a tasty food")
                .display(display)
                .build();
        storyPartTop.addStoryByQuestionId(6, storyPart7);

        story = storyPartTop;

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

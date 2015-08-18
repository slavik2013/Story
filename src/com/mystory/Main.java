package com.mystory;

import java.util.LinkedHashMap;

/**
 * Created by slavik on 18.08.15.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("type -1 to exit");
        Story story = generateStory();
        story.displayStory();

    }


    public static Story generateStory(){
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

        return storyPartTop;
    }
}


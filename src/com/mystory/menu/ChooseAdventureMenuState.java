package com.mystory.menu;

import com.mystory.DisplayImpl;
import com.mystory.Question;
import com.mystory.Story;
import com.mystory.StoryComposer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by slavik on 18.08.15.
 */
public class ChooseAdventureMenuState implements MenuState{

    @Override
    public void display(Menu menu) {
        Scanner inString = new Scanner(System.in);
        System.out.println("type -1 to go to main menu");
        System.out.println("type story name (type default for default story)");
        String storyName = inString.nextLine();
        if ("-1".equals(storyName)){
            menu.setMenuState(new MainMenuState());
            menu.display();
        } else if ("default".equals(storyName)) {
            Story story = generateStory();
            story.displayStory(new DisplayImpl());
            menu.setMenuState(new MainMenuState());
            menu.display();
        } else {
            try {
                StoryComposer storyComposer = new StoryComposer();
                storyComposer.loadStory(storyName.replaceAll("\\s+", ""));
                storyComposer.getStory().displayStory(new DisplayImpl());
            } catch (IOException e) {
                System.out.println("adventure was not found");
                this.display(menu);
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            menu.setMenuState(new MainMenuState());
            menu.display();
        }

    }

    public static Story generateStory(){
        StoryComposer storyComposer = new StoryComposer();

        storyComposer.addStoryPart(0, 1, "You spend your summer holidays in Karpaty mountains in Ukraine and there are many places you want to visit. \n You:");
        storyComposer.addQuestion(1, new Question(1, 1, "Visit the highest mountain Hoverla"));
        storyComposer.addQuestion(1, new Question(2, 2, "Visit old city"));

        storyComposer.addStoryPart(1, 2, "You decided to visit mountain Hoverla but you don't have a car and the distance to mountain is 25 kilometers. What will you do? \n You:");
        storyComposer.addQuestion(2, new Question(3, 1, "Take a taxi for 40$"));
        storyComposer.addQuestion(2, new Question(4, 2, "Rent a bicycle for 5$"));

        storyComposer.addStoryPart(2, 3, "You take a bus and go to city where are many different interesting places. You have half a day to see the city. Where will you go? \n You");
        storyComposer.addQuestion(3, new Question(5, 1, "Go to the museums"));
        storyComposer.addQuestion(3, new Question(6, 2, "Walk on the streets in the city"));

        storyComposer.addStoryPart(3, 4, "You take a taxi, climb a mountain, take a photos, walk near mountain, have a rest and you are happy but feel that a taxi is expensive and you wanted to spend less money \n End");
        storyComposer.addStoryPart(4, 5, "Yoy rent a bicycle, climb to the middle of the mountain, take a photos, walk near mountain, have a rest and you are happy but feel tired \n End");
        storyComposer.addStoryPart(5, 6, "You see different museums and learn something about history and culture on your country \n End");
        storyComposer.addStoryPart(6, 7, "You see different old buildings, roads, modern shops, eat a tasty food \n End");


        return storyComposer.getStory();
    }
}

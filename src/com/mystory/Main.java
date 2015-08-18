package com.mystory;

import java.util.Scanner;

/**
 * Created by slavik on 18.08.15.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("type -1 to exit");


        System.out.println("1) choose adventure");
        System.out.println("2) write custom adventure");

        Scanner in = new Scanner(System.in);
        int menuItem = in.nextInt();

        if (menuItem == 1){
            Scanner inString = new Scanner(System.in);
            System.out.println("type story name (type default for default story)");
            String storyName = inString.nextLine();
            if ("default".equals(storyName)) {
                Story story = generateStory();
                story.displayStory(new DisplayImpl());
            } else {
                StoryComposer storyComposer = new StoryComposer();
                IDisplay display = new DisplayImpl();
                storyComposer.setDisplay(display);
                storyComposer.loadStory(storyName.replaceAll("\\s+",""));
                storyComposer.getStory().displayStory(new DisplayImpl());
            }
        } else if (menuItem == 2){
            writeStory();
        }

    }

    public static void writeStory(){
        System.out.println("type 0 to finish");

        StoryComposer storyComposer = new StoryComposer();
        IDisplay display = new DisplayImpl();
        storyComposer.setDisplay(display);

        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);

        while (true){
            System.out.println("1) add new story part");
            System.out.println("2) add new item to choose to story part");
            int storyAndItemToChoose = in.nextInt();
            if (storyAndItemToChoose == 0)
                break;
            if (storyAndItemToChoose == 1){
                System.out.print("story part answer for chosen question with id(type any number if it is story beginning) : ");
                int questionId = in.nextInt();

                System.out.print("story part id : ");
                int storyId = in.nextInt();

                System.out.print("story part string : ");
                String storyPart = in2.nextLine();

                storyComposer.addStoryPart(questionId, storyId, storyPart);

            } else if (storyAndItemToChoose == 2){
                System.out.print("question for story part with id : ");
                int storyId = in.nextInt();

                System.out.print("question  id : ");
                int questionId = in.nextInt();

                System.out.print("question order in list of items to choose : ");
                int questionOrder = in.nextInt();

                System.out.print("question string : ");
                String question = in2.nextLine();

                storyComposer.addQuestion(storyId, new Question(questionId, questionOrder, question));
            }
        }

        System.out.println("1) choose adventure");
        System.out.println("2) choose created adventure ");
        System.out.println("3) save created adventure ");
        System.out.println("4) exit");

        int menuItem = in.nextInt();
        if (menuItem == 1){
            generateStory().displayStory(new DisplayImpl());
        } else if (menuItem == 2){
            storyComposer.getStory().displayStory(new DisplayImpl());
        } else if (menuItem == 3){
            System.out.println("type your story name");
            String storyName = in2.nextLine();
            storyName = storyName.replaceAll("\\s+","");
            storyComposer.saveStory(storyName);

        } else if (menuItem == 4){
            System.exit(0);
        }

    }

    public static Story generateStory(){
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


        return storyComposer.getStory();
    }

}


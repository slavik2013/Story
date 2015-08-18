package com.mystory;

import java.util.Scanner;

/**
 * Created by slavik on 18.08.15.
 */
public class WriteAdventureMenu implements MenuState{

    @Override
    public void display(Menu menu) {
        System.out.println("type 0 to finish");

        StoryComposer storyComposer = new StoryComposer();

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

        if (storyComposer.getStory() == null){
            menu.setMenuState(new MainMenu());
            menu.display();
        }

        System.out.println("1) Main menu");
        System.out.println("2) save created adventure and go to main menu");
        System.out.println("3) exit");

        int menuItem = in.nextInt();

        if (menuItem == 1){
            menu.setMenuState(new MainMenu());
            menu.display();
        } else if (menuItem == 2){
            System.out.println("type your story name");
            String storyName = in2.nextLine();
            storyName = storyName.replaceAll("\\s+","");
            storyComposer.saveStory(storyName);

            menu.setMenuState(new MainMenu());
            menu.display();
        } else if (menuItem == 3){
            System.exit(0);
        }
    }
}

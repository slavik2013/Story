package com.mystory.menu;

import java.util.Scanner;

/**
 * Created by slavik on 18.08.15.
 */
public class MainMenuState implements MenuState {
    @Override
    public void display(Menu menu) {
        System.out.println("type -1 to exit");
        System.out.println("1) choose adventure");
        System.out.println("2) write custom adventure");

        Scanner in = new Scanner(System.in);
        int menuItem = in.nextInt();

        if (menuItem == 1){
            menu.setMenuState(new ChooseAdventureMenuState());
            menu.display();
        } else if (menuItem == 2) {
            menu.setMenuState(new WriteAdventureMenuState());
            menu.display();
        } else if (menuItem == -1) {
            System.exit(0);
        }
    }
}

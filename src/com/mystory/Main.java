package com.mystory;

/**
 * Created by slavik on 18.08.15.
 */
public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.setMenuState(new MainMenu());
        menu.display();

    }

}


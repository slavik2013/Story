package com.mystory;

import com.mystory.menu.MainMenuState;
import com.mystory.menu.Menu;

/**
 * Created by slavik on 18.08.15.
 */
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setMenuState(new MainMenuState());
        menu.display();
    }
}


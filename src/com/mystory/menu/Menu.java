package com.mystory.menu;

/**
 * Created by slavik on 18.08.15.
 */
public class Menu {

    MenuState menuState;

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public void display(){
        menuState.display(this);
    }

}

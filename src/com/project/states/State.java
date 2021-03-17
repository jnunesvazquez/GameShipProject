package com.project.states;

import java.awt.*;

public abstract class State {

    private static State currentState = null;                    //Estado actual de nuestro juego

    public static State getCurrentState() {
        return currentState;
    }

    public static void changeState(State newState) {
        currentState = newState;
    }

    public abstract void update();
    public abstract void draw(Graphics g);
}

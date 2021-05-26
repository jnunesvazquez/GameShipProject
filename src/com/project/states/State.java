package com.project.states;

import java.awt.*;

/**
 * Clase abstracta para definir un estado
 */
public abstract class State {

    private static State currentState = null;                    //Estado actual de nuestro juego

    /**
     * Getter
     * @return Estado actual de nuestro juego
     */
    public static State getCurrentState() {
        return currentState;
    }

    /**
     * Metodo para cambiar entre estados
     * @param newState  Nuevo estado
     */
    public static void changeState(State newState) {
        currentState = newState;
    }

    /**
     * Metodo para actualizar un estado
     */
    public abstract void update();

    /**
     * Metodo para dibujar sobre un estado
     */
    public abstract void draw(Graphics g);
}

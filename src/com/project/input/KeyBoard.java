package com.project.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard extends KeyAdapter {
    //Le asociamos este tamaño porque equivale a la cantidad de elementos que posee el teclado
    private boolean [] keys = new boolean[256];

    public static boolean UP, LEFT, RIGHT, SHOOT;

    public KeyBoard(){
        UP = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;
    }

    public void update(){
        UP = keys [KeyEvent.VK_UP];
        LEFT = keys [KeyEvent.VK_LEFT];
        RIGHT = keys [KeyEvent.VK_RIGHT];
        SHOOT = keys [KeyEvent.VK_Z];
    }

    /**
     * Metodo que activa un evento cuando una tecla es presionada
     * @param keyEvent Guarda la informacion de una tecla
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys [keyEvent.getKeyCode()] = true;
    }

    /**
     * Metodo que activa un evento cuando una tecla es soltada
     * @param keyEvent Guarda la informacion de una tecla
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys [keyEvent.getKeyCode()] = false;
    }
}

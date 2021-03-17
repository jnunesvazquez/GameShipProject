package com.project.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    public static int X, Y;
    public static boolean MOUSELEFTBUTTON;

    /**
     * Metodo que activa un evento cuando una tecla es presionada
     * @param mouseEvent Guarda la informacion de una tecla
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
                                                    //Boton izquierdo del raton
        if (mouseEvent.getButton() == MouseEvent.BUTTON1){
            MOUSELEFTBUTTON = true;
        }
    }

    /**
     * Metodo que activa un evento cuando una tecla es presionada
     * @param mouseEvent Guarda la informacion de una tecla
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1){
            MOUSELEFTBUTTON = false;
        }
    }

    /**
     * Metodo que activa un evento cuando una tecla es presionada
     * @param mouseEvent Guarda la informacion de una tecla
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        X = mouseEvent.getX();
        Y = mouseEvent.getY();
    }

    /**
     * Metodo que activa un evento cuando una tecla es presionada
     * @param mouseEvent Guarda la informacion de una tecla
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        X = mouseEvent.getX();
        Y = mouseEvent.getY();
    }
}

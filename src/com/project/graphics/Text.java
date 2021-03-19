package com.project.graphics;

import com.project.math.Vector2D;

import java.awt.*;

/**
 * Clase para implementar texto en la ventana de juego
 */
public class Text {

    /**
     * Metodo para dibujar en la ventana nuestro texto
     * @param g
     * @param text texto a dibujar
     * @param pos   posicion en la que aparecera
     * @param center si aparecera centrado o en una esquina
     * @param color color del texto
     * @param font la fuente de texto a usar
     */
    public static void drawText(Graphics g, String text, Vector2D pos, boolean center,Color color, Font font){
       g.setColor(color);
       g.setFont(font);
       Vector2D position = new Vector2D(pos.getX(),pos.getY());
       if(center){
           FontMetrics fm=g.getFontMetrics();//g.getFontMetrics() como las dimensiones del texto
           position.setX(position.getX()-fm.stringWidth(text)/2); //posicion x-la posiciontexto entre 2
           position.setY(position.getY()-fm.getHeight()/2); //posicion y- la alturatexto entre 2
       }
       g.drawString(text,(int)position.getX(),(int)position.getY());
    }
}

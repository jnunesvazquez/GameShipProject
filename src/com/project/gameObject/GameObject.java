package com.project.gameObject;

import com.project.math.Vector2D;
import frames.IFrame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Clase padre de cualquier objeto que aparezca en el juego
 */
public abstract class GameObject implements IFrame {
    protected BufferedImage texture;                //Imagen que define la apariencia del objeto
    protected Vector2D position;                    //Vector que define la posicion inicial del objeto

    /**
     * Constructor
     * @param position  Posicion del objeto
     * @param texture   Apariencia del objeto
     */
    public GameObject( Vector2D position, BufferedImage texture) {
        this.texture = texture;
        this.position = position;
    }

    /**
     * Metodo que nos permitira actualizar el objeto
     */
    public abstract void update();

    /**
     * Metodo que nos permitira dibujar el objeto sobre nuestra ventana
     * @param g Objeto a dibujar
     */
    public abstract void draw(Graphics g);

}

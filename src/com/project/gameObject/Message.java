package com.project.gameObject;

import com.project.graphics.Text;
import com.project.math.Vector2D;
import com.project.states.GameState;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Clase para mostrar mensajes en el juego
 */
public class Message {
    private GameState gameState;
    private float alpha;        //Rango de valores que indica la transparencia
    private String text;        //Texto a mostrar en pantalla
    private Vector2D position;  //Posicion del mensaje
    private Color color;        //Color del mensaje
    private boolean center;     //Para indicar si es en el centro o en una esquina true=center
    private boolean fade;       //Para indicar si aparece o desaparece true=desaparece false=aparece
    private Font font;          //Nuestra fueste de texto
    private final float deltaAlpha = 0.01f; //Valor que indica la rapidesz con la que parece y desaparece
    private boolean dead;       //Define si el jugador esta muerto o no

    /**
     * Constructor
     * @param position  //Posicion del mensaje
     * @param fade  //Para indicar si aparece o desaparece true=desaparece false=aparece
     * @param text  //Texto a mostrar en pantalla
     * @param color //Color del mensaje
     * @param center    //Para indicar si es en el centro o en una esquina true=center
     * @param font  //Nuestra fueste de texto
     */
    public Message(Vector2D position, boolean fade, String text, Color color,
                   boolean center, Font font) {
        this.gameState = gameState;
        this.text = text;
        this.position = position;
        this.color = color;
        this.center = center;
        this.fade = fade;
        this.font = font;
        this.dead = false;

        if (fade) {             //fade=true
            alpha = 1;
        } else {                //fade=false
            alpha = 0;
        }
    }

    /**
     * Metodo que dibuja
     * @param g2d parametro Graphics2D para que haga a transparencia
     */
    public void draw(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        Text.drawText(g2d, text, position, center, color, font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        position.setY(position.getY() - 1);
        if (fade == true) {
            alpha -= deltaAlpha;            //para desaparecer le resta a alpha deltaAlpha
        } else {
            alpha += deltaAlpha;            //para aparecer le suma a alpha deltaAlpha
        }
        //si desaparece y alpha es menor que 0 borramos el mensaje, si aparece y alpha es mayor quw 1 borramos el mensaje
        if (fade && alpha < 0) {
            dead = true;
        }

        if (!fade && alpha > 1) {
            fade = true;
            alpha = 1;
        }
    }

    /**
     * Metodo para saber si el jugador ha muerto o no
     * @return
     */
    public boolean isDead() {
        return dead;
    }
}

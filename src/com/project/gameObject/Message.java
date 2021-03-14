package com.project.gameObject;

import com.project.graphics.Text;
import com.project.math.Vector2D;
import com.project.states.GameState;

import java.awt.*;

public class Message {
    private GameState gameState;
    private float alpha; //rango de valores que indica la transparencia
    private String text;
    private Vector2D position; //posicion del mensaje
    private Color color;
    private boolean center; //para indicar si es en el centro o en una esquina true=center
    private boolean fade; //para indicar si aparece o desaparece true=desaparece false=aparece
    private Font font; //nuestra fueste de texto
    private final float deltaAlpha=0.01f; //valor que indica la rapidesz con la que parece y desaparece

    public Message(Vector2D position, boolean fade, String text, Color color,
                   boolean center, Font font, GameState gameState) {
        this.gameState = gameState;
        this.text = text;
        this.position = position;
        this.color = color;
        this.center = center;
        this.fade = fade;
        this.font = font;

        if(fade){ //fade=true
            alpha=1;
        }else{ //fade=false
            alpha=0;
        }
    }

    /**
     * metodo que dibuja
     * @param g2d parametro Graphics2D para que haga a transparencia
     *
     */
    public void draw(Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        Text.drawText(g2d,text,position,center,color,font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
        position.setY(position.getY()-1);
        if(fade){
            alpha-=deltaAlpha; //para desaparecer le resta a alpha deltaAlpha
        }else{
            alpha+=deltaAlpha; //para aparecer le suma a alpha deltaAlpha
        }
        //si desaparece y alpha es menor que 0 borramos el mensaje, si aparece y alpha es mayor quw 1 borramos el mensaje
        if(fade && alpha<0 || !fade && alpha>1){
            gameState.getMessages().remove(this);
        }

    }
}

package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;
import constants.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Subclase de MovingObject que nos permite crear los lasers de nuestra nave
 */
public class Laser extends MovingObject{

    /**
     * Constructor
     * @param position  Posicion del laser
     * @param velocity  Velocidad del laser
     * @param maxVelocity   Velocidad del laser
     * @param angle Angulo del laser
     * @param texture   Apariencia del laser
     * @param gameState Estado del laser
     */
    public Laser(Vector2D position, Vector2D velocity, double maxVelocity, double angle,
                 BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVelocity);        //Hacemos que la velocidad del laser equivalga a la velocidad maxima
    }

    /**
     * Metodo para actualizar el laser durante el estado de juego
     */
    @Override
    public void update() {
        position = position.add(velocity);                                          //Añadimos velocidad al vector de posicion del laser
        if(position.getX() < 0 || position.getX() > Constants.WIDTH ||
                position.getY() < 0 || position.getY() > Constants.HEIGHT){
            destroy();                                                              //Si el laser sale de pantalla se destruye
        }
        collidesWith();                                                             //Permitimos que el laser colisione
    }

    /**
     * Metodo para dibujar el laser
     * @param g Objeto a dibujar
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(position.getX() - width / 2, position.getY());
        at.rotate(angle, width / 2 , 0);
        g2d.drawImage(texture, at, null);
    }

    /**
     * Metodo para recoger el centro del laser
     * @return  Centro del laser
     */
    @Override
    public Vector2D getCenter(){
        return new Vector2D(position.getX() + width / 2, position.getY() + width / 2);
    }
}

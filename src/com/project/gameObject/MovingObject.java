package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class MovingObject extends GameObject{

    protected GameState gameState;
    protected Vector2D velocity;
    protected AffineTransform at;                                                         //Clase que nos permite crear un mapeado linear 2D sobre nuestro objeto
    protected double angle;                                                               //Angulo que nos permitira hacer rotar nuestra nave
    protected double maxVelocity;
    protected int width;
    protected int height;

    public MovingObject(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
    }
}

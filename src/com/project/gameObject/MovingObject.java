package com.project.gameObject;

import com.project.math.Vector2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class MovingObject extends GameObject{

    protected Vector2D velocity;
    protected AffineTransform at;                                                         //Clase que nos permite crear un mapeado linear 2D sobre nuestro objeto
    protected double angle;                                                               //Angulo que nos permitira hacer rotar nuestra nave
    protected double maxVelocity;
    public MovingObject(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        angle = 0;
    }
}

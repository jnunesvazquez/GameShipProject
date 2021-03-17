package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;
import constants.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends MovingObject{
    public Laser(Vector2D position, Vector2D velocity, double maxVelocity, double angle,
                 BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVelocity);
    }

    @Override
    public void update() {
        position = position.add(velocity);
        if(position.getX() < 0 || position.getX() > Constants.WIDTH ||
                position.getY() < 0 || position.getY() > Constants.HEIGHT){
            destroy();
        }
        collidesWith();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(position.getX() - width / 2, position.getY());
        at.rotate(angle, width / 2 , 0);
        g2d.drawImage(texture, at, null);
    }

    @Override
    public Vector2D getCenter(){
        return new Vector2D(position.getX() + width / 2, position.getY() + width / 2);
    }
}

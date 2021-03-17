package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;
import constants.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Meteor extends MovingObject{
    public Meteor(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        this.velocity = velocity.scale(maxVelocity);                    //Para que los meteoritos vayan a diferentes velocidades
    }

    @Override
    public void update() {
        position = position.add(velocity);
        if(position.getX() > Constants.WIDTH){
            position.setX(-width);
        }
        if(position.getY() > Constants.HEIGHT){
            position.setY(-height);
        }
        if(position.getX() < -width){
            position.setX(Constants.WIDTH);
        }
        if(position.getY() < -height){
            position.setY(Constants.HEIGHT);
        }
        angle += Constants.DELTAANGLE / 2;
    }

    @Override
    protected void destroy() {
        gameState.addScore(Constants.METEOR_SCORE,position);
        super.destroy();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width / 2, height / 2);
        g2d.drawImage(texture, at, null);
    }
}

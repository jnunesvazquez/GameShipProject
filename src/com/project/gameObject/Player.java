package com.project.gameObject;

import com.project.graphics.Assets;
import com.project.input.KeyBoard;
import com.project.math.Vector2D;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject{

    private Vector2D heading;                                                               //Variable que nos dira a que posicion esta mirando la nave
    private Vector2D acceleration;
    private final double ACC = 0.2;
    private final double DELTAANGLE = 0.1;

    public Player(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture) {
        super(position, velocity, maxVelocity, texture);
        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
    }

    @Override
    public void update() {
        if (KeyBoard.RIGHT){
            angle += DELTAANGLE;
        }
        if (KeyBoard.LEFT){
            angle -= DELTAANGLE;
        }
        if (KeyBoard.UP){
            acceleration = heading.scale(ACC);
        }
        else {
            if (velocity.getMagnitude() != 0){
                acceleration = velocity.scale(-1).normalize().scale(ACC / 2);
            }
        }

        velocity = velocity.add(acceleration);
        heading = heading.setDirection(angle - Math.PI / 2);

        position = position.add(velocity);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, Assets.player.getWidth() / 2, Assets.player.getHeight() / 2);
        g2d.drawImage(Assets.player, at, null);
    }
}

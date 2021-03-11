package com.project.gameObject;

import com.project.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
    public Player(Vector2D position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture,(int)position.getX(),(int)position.getY(),null);
    }
}

package com.project.gameObject;

import com.project.graphics.Assets;
import com.project.input.KeyBoard;
import com.project.math.Vector2D;
import com.project.states.GameState;
import constants.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject{

    private Vector2D heading;                                                               //Variable que nos dira a que posicion esta mirando la nave
    private Vector2D acceleration;
    private Timer fireRate;

    public Player(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
        fireRate = new Timer();
    }

    @Override
    public void update() {
        if (KeyBoard.SHOOT && !fireRate.isRunning()){
            gameState.getMovingObjects().add(0, new Laser(
                    getCenter().add(heading.scale(width)),          //Generamos el laser en la cabeza de la nave
                    heading,                                            //Indicamos el vector de velocidad que queremos que recorra el laser
                    Constants.LASER_VEL,                                       //Indicamos la velocidad maxima del laser
                    angle,                                              //Le pasamos el angulo de la nave
                    Assets.blueLaser,                                    //Le pasamos el sprite del laser
                    gameState
                    ));
            fireRate.run(Constants.FIRERATE);
        }
        if (KeyBoard.RIGHT){
            angle += Constants.DELTAANGLE;
        }
        if (KeyBoard.LEFT){
            angle -= Constants.DELTAANGLE;
        }
        if (KeyBoard.UP){
            acceleration = heading.scale(Constants.ACC);
        }
        else {
            if (velocity.getMagnitude() != 0){
                acceleration = velocity.scale(-1).normalize().scale(Constants.ACC / 2);
            }
        }
        velocity = velocity.add(acceleration);
        velocity.limit(maxVelocity);
        heading = heading.setDirection(angle - Math.PI/2);
        position = position.add(velocity);

        if(position.getX() > Constants.WIDTH)
            position.setX(0);
        if(position.getY() > Constants.HEIGHT)
            position.setY(0);

        if(position.getX() < 0)
            position.setX(Constants.WIDTH);
        if(position.getY() < 0)
            position.setY(Constants.HEIGHT);

        fireRate.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, Assets.player.getWidth()/2, Assets.player.getHeight()/2);
        g2d.drawImage(texture, at, null);
    }

    public Vector2D getCenter(){
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }
}

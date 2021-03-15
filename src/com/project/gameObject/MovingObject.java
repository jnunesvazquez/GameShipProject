package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    /**
     * Metodo de colision de los objetos
     */
    protected void collidesWith(){
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();           //Creamos un ArrayList para saber si un objeto colisiona o no
        for (int i = 0; i < movingObjects.size(); i++){                                 //Recorremos el array
            MovingObject m = movingObjects.get(i);
            if (m.equals(this)){                                                        //Comprobamos que cada objeto no colisione consigo mismo
                continue;
            }

            double distance = m.getCenter().subtract(getCenter()).getMagnitude();       //Calculamos la distancia entre objetos
                            //radio      //radio del objeto seleccionado    //metodo para que cuando dos meteoros colisionen entre si no se destruyan
            if (distance < m.width / 2 + width / 2 && movingObjects.contains(this)){
                objectCollision(m, this);
            }
        }
    }

    private void objectCollision(MovingObject a, MovingObject b){
                //comprueba la instancia del objeto
        if(a instanceof Player && ((Player)a).isSpawning()) {
            return;
        }
        if(b instanceof Player && ((Player)b).isSpawning()) {
            return;
        }
        if (!(a instanceof Meteor && b instanceof Meteor)){
            a.destroy();
            b.destroy();
        }
    }

    protected void destroy(){
        gameState.getMovingObjects().remove(this);
    }

    protected Vector2D getCenter(){
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }
}

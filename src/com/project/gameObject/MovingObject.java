package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Subclase de GameObject y clase padre de cualquier objeto movible
 */
public abstract class MovingObject extends GameObject{

    protected GameState gameState;
    protected Vector2D velocity;
    protected AffineTransform at;               //Clase que nos permite crear un mapeado linear 2D sobre nuestro objeto
    protected double angle;                     //Angulo que nos permitira hacer rotar nuestra nave
    protected double maxVelocity;               //Velocidad maxima de la nave
    protected int width;                        //Anchura del objeto
    protected int height;                       //Altura del objeto
    protected boolean dead;                     //Estado del jugador si esta muerto o no

    /**
     * Constructor
     * @param position  Posicion del objeto
     * @param velocity  Velocidad del objeto
     * @param maxVelocity   Velocidad maxima del objeto
     * @param texture   Apariencia del objeto
     * @param gameState Estado del objeto en el juego
     */
    public MovingObject(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
        dead = false;
    }

    /**
     * Metodo para comprobar si los objetos pueden colisionar
     */
    protected void collidesWith(){
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();           //Creamos un ArrayList para saber si un objeto colisiona o no
        for (int i = 0; i < movingObjects.size(); i++){
            MovingObject m = movingObjects.get(i);
            if (m.equals(this)){                                                        //Comprobamos que cada objeto no colisione consigo mismo
                continue;
            }

            double distance = m.getCenter().subtract(getCenter()).getMagnitude();       //Calculamos la distancia entre objetos
                            //radio      //radio del objeto seleccionado    //metodo para que cuando dos meteoros colisionen entre si no se destruyan
            if (distance < m.width / 2 + width / 2 && movingObjects.contains(this)){    //Si el objeto colisiona
                objectCollision(m, this);                                            //Le decimos que compruebe el tipo de colision
            }
        }
    }

    /**
     * Metodo para comprobar el tipo de colision
     * @param a Primer objeto
     * @param b Segundo objeto
     */
    private void objectCollision(MovingObject a, MovingObject b){
                //comprueba la instancia del objeto
        if(a instanceof Player && ((Player)a).isSpawning()) {       //Comprobamos si uno de los objetos es el jugador y esta apareciendo para que no colisione con nada
            return;
        }
        if(b instanceof Player && ((Player)b).isSpawning()) {
            return;
        }
        if (!(a instanceof Meteor && b instanceof Meteor)){         //Comprobamos si los objetos son dos meteoritos para que no colisionen
            a.destroy();                                            //Si no, hacemos que los dos objetos se destruyan
            b.destroy();
        }
    }

    /**
     * Metodo para destruir un objeto
     */
    protected void destroy(){
        dead = true;
    }

    /**
     * Metodo para recoger el centro de un objeto dentro de un vector
     * @return Vector del centro del objeto
     */
    protected Vector2D getCenter(){
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }

    /**
     * Metodo que devuelve si un objeto esta muerto o no
     * @return  estado del objeto
     */
    public boolean isDead() {
        return dead;
    }
}

package com.project.gameObject;

import com.project.math.Vector2D;
import com.project.states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class MovingObject extends GameObject{

    private GameState gameState;
    private Vector2D velocity;
    private AffineTransform at;                                                         //Clase que nos permite crear un mapeado linear 2D sobre nuestro objeto
    private double angle;                                                               //Angulo que nos permitira hacer rotar nuestra nave
    private double maxVelocity;
    private int width;
    private int height;

    private MovingObject(GameState gameState, Vector2D velocity, AffineTransform at, double angle, double maxVelocity, int width, int height) {
        this.gameState = gameState;
        this.velocity = velocity;
        this.at = at;
        this.angle = angle;
        this.maxVelocity = maxVelocity;
        this.width = width;
        this.height = height;
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

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public AffineTransform getAt() {
        return at;
    }

    public void setAt(AffineTransform at) {
        this.at = at;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static final class MovingObjectBuilder {
        private GameState gameState;
        private Vector2D velocity;
        private AffineTransform at;
        private double angle;
        private double maxVelocity;
        private int width;
        private int height;

        private MovingObjectBuilder(GameState gameState, Vector2D velocity, AffineTransform at, double angle, double maxVelocity, int width, int height) {
            this.gameState = gameState;
            this.velocity = velocity;
            this.at = at;
            this.angle = angle;
            this.maxVelocity = maxVelocity;
            this.width = width;
            this.height = height;
        }

        public static MovingObjectBuilder aMovingObject(GameState gameState, Vector2D velocity, AffineTransform at, double angle, double maxVelocity, int width, int height) {
            return new MovingObjectBuilder(gameState, velocity, at, angle, maxVelocity, width, height);
        }

        public MovingObject build() {
            MovingObject movingObject = new MovingObject(gameState, velocity, at, angle, maxVelocity, width, height);
            return movingObject;
        }
    }
}

package com.project.gameObject;

import com.project.input.KeyBoard;
import com.project.math.Vector2D;
import com.project.states.ChoosePlayerState;
import com.project.states.GameState;
import constants.Constants;
import constants.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Subclase de MovingObject y clase que nos permite crear nuestra nave
 */
public class Player extends MovingObject{
    private boolean spawning, visible;                          //Variable para saber si la nave es visible o ha aparecido
    private final Timer spawnTime, flickerTime, fireRate;       //Definir el tiempo de aparicion, el tiempo de parpadeo y el ratio de disparo
    private Vector2D heading;                                   //Variable que nos dira a que posicion esta mirando la nave
    private Vector2D acceleration;                              //Aceleracion de la nave

    /**
     * Constructor
     * @param position  Posicion de la nave
     * @param velocity  Velocidad de la nave
     * @param maxVelocity   Velocidad maxima de la nave
     * @param texture   Apariencia de la nave
     * @param gameState Estado de la nave
     */
    public Player(Vector2D position, Vector2D velocity, double maxVelocity, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVelocity, texture, gameState);
        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
        fireRate = new Timer();
        spawnTime = new Timer();
        flickerTime = new Timer();
    }

    /**
     * Metodo que nos permite actualizar nuestra nave durante el proceso
     */
    @Override
    public void update() {
        if(!spawnTime.isRunning()) {    //Mientras la nave no reaparezca
            spawning = false;
            visible = true;
        }
        if(spawning) {                              //Cuando la nave reaparezca, creamos un pequeño titileo para darle efecto
            if(!flickerTime.isRunning()) {
                flickerTime.run(Constants.FLICKER_TIME);
                visible = !visible;
            }
        }
        if (KeyBoard.SHOOT && !fireRate.isRunning() && !spawning){      //Si disparamos sin haber superado el tiempo entre disparos establecido
            gameState.getMovingObjects().add(0, new Laser(           //Añadimos al estado del juego un laser
                    getCenter().add(heading.scale(width)),              //Generamos el laser en la cabeza de la nave
                    heading,                                            //Indicamos el vector de velocidad que queremos que recorra el laser
                    Constants.LASER_VEL,                                //Indicamos la velocidad maxima del laser
                    angle,                                              //Le pasamos el angulo de la nave
                    ChoosePlayerState.getPlayerLaser(),                 //Le pasamos el sprite del laser
                    gameState
                    ));
            fireRate.run(Constants.FIRERATE);                           //Decimos que el proceso de disparar se produzca
        }
        if (KeyBoard.RIGHT){                                            //Cuando nos movemos a la derecha o a la izquierda le decimos que cambie el angulo para que rote
            angle += Constants.DELTAANGLE;
        }
        if (KeyBoard.LEFT){
            angle -= Constants.DELTAANGLE;
        }
        if (KeyBoard.UP){                                               //Cuando nos movemos arriba, hacemos que la nave acelere y coja velocidad
            acceleration = heading.scale(Constants.ACC);
        }
        else {
            if (velocity.getMagnitude() != 0){                          //Cuando no pulsamos ninguna tecla, hacemos que la nave desacelere hasta pararse
                acceleration = velocity.scale(-1).normalize().scale(Constants.ACC / 2);
            }
        }
        velocity = velocity.add(acceleration);              //Le añadimos aceleracion al vector velocidad
        velocity = velocity.limit(maxVelocity);             //Establecemos la velocidad maxima
        heading = heading.setDirection(angle - Math.PI/2);  //Definimos la direccion del vector velocidad segun el angulo de la nave
        position = position.add(velocity);                  //Le añadimos al vector posicion el vector velocidad

        /**
         * Cuando la nave se sale de un margen de la ventana, hacemos que se restablezca al otro lado del margen
         */
        if(position.getX() > Constants.WIDTH)
            position.setX(0);
        if(position.getY() > Constants.HEIGHT)
            position.setY(0);

        if(position.getX() < 0)
            position.setX(Constants.WIDTH);
        if(position.getY() < 0)
            position.setY(Constants.HEIGHT);

        /**
         * Actualizamos todos nuestros parametros
         */
        fireRate.update();
        spawnTime.update();
        flickerTime.update();
        collidesWith(); //Establecemos que la nave pueda colisionar
    }

    /**
     * Metodo para cuando la nave colisione, se destruya y reaparezca
     */
    @Override
    public void destroy() {
        spawning = true;
        spawnTime.run(Constants.SPAWNING_TIME);
        resetValues();                              //Restablecemos los parametros de la nave
        if(!gameState.subtractLife()) {             //Si nos quedamos sin vidas despues de morir
            gameState.gameOver();                   //Se activa el game over
            super.destroy();                        //y destruimos la nave
        }
    }

    /**
     * Metodo para restaurar valores
     */
    private void resetValues() {
        angle = 0;                                      //Restablecemos la posicion de la nave
        velocity = new Vector2D();
        position = GameState.PLAYER_START_POSITION;
    }

    /**
     * Metodo para dibujar la nave
     * @param g Objeto a dibujar
     */
    @Override
    public void draw(Graphics g) {
        if(!visible)        //Comprobamos si es o no visible la nave
            return;
        Graphics2D g2d = (Graphics2D) g;        //Creamos un objeto 2D para dibujar
        //AffineTransform nos permite hacer el metodo matematico de Transformacion Afin para poder cambiar la posicion y el tamaño de nuestros objetos sin cambiar la proporcion de sus dimensiones
        AffineTransform at = AffineTransform.getTranslateInstance(position.getX(), position.getY());    //Creamos un objeto
        at.rotate(angle, width / 2, height / 2);
        g2d.drawImage(ChoosePlayerState.getPlayerSkin(), at, null);
    }

    /**
     * Metodo para saber si la nave esta reapareciendo o no
     * @return spawning
     */
    public boolean isSpawning() {return spawning;}
}

package com.project.states;

import com.project.gameObject.MovingObject;
import com.project.gameObject.Player;
import com.project.graphics.Assets;
import com.project.math.Vector2D;
import constants.Constants;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList();
    private int score=0; //Puntaje del jugador que al inicio empieza con 09
    public GameState() {
        player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
                Constants.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
        movingObjects.add(player);
    }

    /**
     * Metodo que vaya sumando las puntuaciones de destruir los meteoritos
     * @param value valor a sumar en la puntuación
     */
    public void addScore(int value){
        score+=value; //la puntuacion anterior mas value(nueva puntuacion añadida);
    }
    public void update(){
        for (int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
    }
    public void draw(Graphics g){

        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    /**
     * Metodo para dibujar en la ventana la puntiacion de nuestro juego
     * @param g
     */
    private void drawScore(Graphics g){
        Vector2D pos=new Vector2D(850,25); //Posicion donde se visualizara la puntuación
        String scoreToString=Integer.toString(score);

        for(int i=0;i<scoreToString.length();i++){

        }
    }
}

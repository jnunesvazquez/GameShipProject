package com.project.states;

import com.project.gameObject.Message;
import com.project.gameObject.Meteor;
import com.project.gameObject.MovingObject;
import com.project.gameObject.Player;
import com.project.graphics.Assets;
import com.project.graphics.Text;
import com.project.math.Vector2D;
import constants.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState {

    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList();
    private int meteors;                                                //Variable para indicar cuantos meteoritos habra en cada oleada
    private ArrayList<Message> messages = new ArrayList<Message>();
    private int score = 0; //Puntaje del jugador que al inicio empieza con 09
    private int waves = 1; //oleadas de la partida iniciadas en 1

    public GameState() {
        player = new Player(new Vector2D(Constants.WIDTH / 2 - Assets.player.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.player.getHeight() / 2), new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
        movingObjects.add(player);

        meteors = 1;

        startWave();
    }

    /**
     * Metodo que vaya sumando las puntuaciones de destruir los meteoritos
     *
     * @param value valor a sumar en la puntuación
     */
    public void addScore(int value, Vector2D position) {
        score += value; //la puntuacion anterior mas value(nueva puntuacion añadida);
        messages.add(new Message(position,true,"+"+value+" score",Color.WHITE,false,Assets.fontMed,this));
    }

    public void startWave() {
        messages.add(new Message(new Vector2D(Constants.WIDTH/2,Constants.HEIGHT/2),true,"Wawe "+waves,Color.WHITE,true,Assets.fontBig,this));
        double x, y;
        for (int i = 0; i < meteors; i++) {                          //Usamos este bucle para generar unas coordenadas logicas aleatorias para nuestro meteorito
            x = i % 2;
            y = i % 2;
            if (x == 0) {
                x = Math.random() * Constants.WIDTH;
            } else {
                x = 0;
            }
            if (y == 0) {
                y = 0;
            } else {
                y = Math.random() * Constants.HEIGHT;
            }
            BufferedImage texture1 = Assets.bigs[(int) Math.random() * Assets.bigs.length];
            BufferedImage texture2 = Assets.meds[(int) Math.random() * Assets.bigs.length];
            BufferedImage texture3 = Assets.smalls[(int) Math.random() * Assets.bigs.length];

            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),       //Generamos un vector aleatorio entre 0º y 360º
                    2 * Math.random() + 1,
                    texture1,
                    this
            ));
            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),       //Generamos un vector aleatorio entre 0º y 360º
                    2 * Math.random() + 1,
                    texture2,
                    this
            ));
            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),       //Generamos un vector aleatorio entre 0º y 360º
                    2 * Math.random() + 1,
                    texture3,
                    this
            ));
        }
        meteors++;
    }

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
        for (int i = 0; i < movingObjects.size(); i++) {
            if (movingObjects.get(i) instanceof Meteor) {
                return;
            }
        }
        startWave();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
        for (int i = 0; i < messages.size(); i++) {
            movingObjects.get(i).draw(g);
        }
        Text.drawText(g, "Wave " + waves, new Vector2D(Constants.WIDTH / 2,
                Constants.HEIGHT / 2), true, Color.CYAN, Assets.fontBig); //dibujamos el texto de oleada

    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    /**
     * Metodo para dibujar en la ventana la puntiacion de nuestro juego
     *
     * @param g
     */
    private void drawScore(Graphics g) {
        Vector2D pos = new Vector2D(850, 25); //Posicion donde se visualizara la puntuación
        String scoreToString = Integer.toString(score);

        for (int i = 0; i < scoreToString.length(); i++) {

        }
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}

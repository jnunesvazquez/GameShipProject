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
    private int waves = 0; //Contadoor iniciado en 0
    private int lives = 3;
    public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
            Constants.HEIGHT/2 - Assets.player.getHeight()/2);

    public GameState() {
        player = new Player(PLAYER_START_POSITION, new Vector2D(),
                Constants.PLAYER_MAX_VEL, Assets.player, this);
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
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture1,
                    this
            ));
            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),       //Generamos un vector aleatorio entre 0º y 360º
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture2,
                    this
            ));
            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),       //Generamos un vector aleatorio entre 0º y 360º
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture3,
                    this
            ));
        }
        meteors++;
        waves++; //suma uno en cada ronda
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
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
        for(int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g2d);
        }
        Text.drawText(g, "Wave " + waves, new Vector2D(Constants.WIDTH / 2,
                Constants.HEIGHT / 2), true, Color.CYAN, Assets.fontBig); //dibujamos el texto de oleada
        drawScore(g);
        drawLives(g);
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
        Vector2D pos = new Vector2D(850, 25);

        String scoreToString = Integer.toString(score);

        for(int i = 0; i < scoreToString.length(); i++) {

            g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX() + 20);

        }
    }
    private void drawLives(Graphics g){

        Vector2D livePosition = new Vector2D(25, 25);

        g.drawImage(Assets.life, (int)livePosition.getX(), (int)livePosition.getY(), null);

        g.drawImage(Assets.numbers[10], (int)livePosition.getX() + 40,
                (int)livePosition.getY() + 5, null);

        String livesToString = Integer.toString(lives);

        Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());

        for(int i = 0; i < livesToString.length(); i ++)
        {
            int number = Integer.parseInt(livesToString.substring(i, i+1));

            if(number <= 0)
                break;
            g.drawImage(Assets.numbers[number],
                    (int)pos.getX() + 60, (int)pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
        }

    }
    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Player getPlayer() {
        return player;
    }

    public void subtractLife() {lives --;}

}

package com.project.states;

import com.project.gameObject.Message;
import com.project.gameObject.Meteor;
import com.project.gameObject.MovingObject;
import com.project.gameObject.Player;
import com.project.graphics.Assets;
import com.project.math.Vector2D;
import constants.Constants;
import constants.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState extends State{

    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList();
    private int meteors;                                                //Variable para indicar cuantos meteoritos habra en cada oleada
    private ArrayList<Message> messages = new ArrayList<Message>();
    private int score = 0; //Puntaje del jugador que al inicio empieza con 09
    private int waves = 1; //Contador iniciado en 0
    private int lives = 3;

    private Timer gameOverTimer;
    private boolean gameOver;

    public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constants.WIDTH/2 - ChoosePlayerState.getPlayerSkin().getWidth()/2,
            Constants.HEIGHT/2 - ChoosePlayerState.getPlayerSkin().getHeight()/2);

    public GameState() {
        player = new Player(PLAYER_START_POSITION, new Vector2D(),
                Constants.PLAYER_MAX_VEL, ChoosePlayerState.getPlayerSkin(), this);

        gameOverTimer = new Timer();
        gameOver = false;
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
        messages.add(new Message(position,true,"+"+value+" score",Color.WHITE,false,Assets.fontMed));
    }

    public void startWave() {
        messages.add(new Message(new Vector2D(Constants.WIDTH/2,Constants.HEIGHT/2),true,"Wawe "+waves,Color.WHITE,true,Assets.fontBig));
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

            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),       //Generamos un vector aleatorio entre 0º y 360º
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture1,
                    this
            ));
            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0, 1).setDirection(Math.random() * Math.PI * 2),
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture2,
                    this
            ));
        }
        meteors++;
        waves++;
    }

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            MovingObject movingObject = movingObjects.get(i);
            movingObject.update();
            if(movingObject.isDead()) {
                movingObjects.remove(i);
                i--;
            }
        }

        if(gameOver && !gameOverTimer.isRunning()) {
            State.changeState(new MenuState());
        }

        gameOverTimer.update();

        for (MovingObject movingObject : movingObjects) {
            if (movingObject instanceof Meteor) {
                return;
            }
        }
        startWave();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for(int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g2d);
            if(messages.get(i).isDead())
                messages.remove(i);
        }

        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }

        drawScore(g);
        drawLives(g);
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

        if (lives < 1)
            return;

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
    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean subtractLife() {
        score += -20;
        lives --;
        return lives > 0;
    }

    public void gameOver() {
        Message gameOverMsg = new Message(
                PLAYER_START_POSITION,
                true,
                "GAME OVER",
                Color.WHITE,
                true,
                Assets.fontBig);

        this.messages.add(gameOverMsg);
        gameOverTimer.run(Constants.GAME_OVER_TIME);
        gameOver = true;
    }

}

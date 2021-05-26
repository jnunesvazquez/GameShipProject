package com.project.states;

import com.project.graphics.Assets;
import com.project.states.choosePlayer.Factory;
import com.project.states.choosePlayer.IChoose;
import com.project.ui.Action;
import com.project.ui.Button;
import com.project.ui.ButtonBuilder;
import constants.Constants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Clase para definir el menu de seleccion de nave del juego
 */
public class ChoosePlayerState extends State{

    private final ArrayList<Button> buttons;

    private static BufferedImage playerSkin;        //Apariencia de la nave
    private static BufferedImage playerLaser;       //Color del laser
    /**
     * Constructor en el que añadimos los botones con el Factory
     */
    public ChoosePlayerState(){

        IChoose PlayerA, PlayerB, PlayerC;
        PlayerA = Factory.getPlayerFactory(Factory.PLAYER_A);
        PlayerB = Factory.getPlayerFactory(Factory.PLAYER_B);
        PlayerC = Factory.getPlayerFactory(Factory.PLAYER_C);

        buttons = new ArrayList<Button>();
        buttons.add(new ButtonBuilder(
                Assets.greyButton2,
                Assets.blueButton2,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyButton.getHeight() * 2,
                Constants.PLAYER_A,
                new Action() {
                    @Override
                    public void doAction() {
                        playerSkin = PlayerA.getPlayer();
                        playerLaser = PlayerA.getLaser();
                        State.changeState(new GameState());
                    }
                })
                .build());

        buttons.add(new ButtonBuilder(
                Assets.greyButton2,
                Assets.blueButton2,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2,
                Constants.PLAYER_B,
                new Action() {
                    @Override
                    public void doAction() {
                        playerSkin = PlayerB.getPlayer();
                        playerLaser = PlayerB.getLaser();
                        State.changeState(new GameState());
                    }
                })
                .build());

        buttons.add(new ButtonBuilder(
                Assets.greyButton2,
                Assets.blueButton2,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 + Assets.greyButton.getHeight() * 2 ,
                Constants.PLAYER_C,
                new Action() {
                    @Override
                    public void doAction() {
                        playerSkin = PlayerC.getPlayer();
                        playerLaser = PlayerC.getLaser();
                        State.changeState(new GameState());
                    }
                })
                .build());
    }

    /**
     * Metodo para actualizar el menu
     */
    @Override
    public void update() {
        for(Button b: buttons) {
            b.update();
        }

    }

    /**
     * Metodo para dibujar el menu
     * @param g Objeto a dibujar
     */
    @Override
    public void draw(Graphics g) {
        for(Button b: buttons) {
            b.draw(g);
        }
    }

    /**
     * Getter
     * @return Apariencia de la nave
     */
    public static BufferedImage getPlayerSkin() {
        return playerSkin;
    }

    /**
     * Getter
     * @return Color del laser
     */
    public static BufferedImage getPlayerLaser() {
        return playerLaser;
    }
}

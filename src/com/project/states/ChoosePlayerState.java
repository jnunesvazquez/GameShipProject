package com.project.states;

import com.project.graphics.Assets;
import com.project.states.choosePlayer.Factory;
import com.project.states.choosePlayer.IChoose;
import com.project.ui.Action;
import com.project.ui.Button;
import constants.Constants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ChoosePlayerState extends State{

    private ArrayList<Button> buttons;

    private static BufferedImage playerSkin;
    private static BufferedImage playerLaser;

    public ChoosePlayerState(){

        IChoose PlayerA, PlayerB, PlayerC;
        PlayerA = Factory.getPlayerFactory(Factory.PLAYER_A);
        PlayerB = Factory.getPlayerFactory(Factory.PLAYER_B);
        PlayerC = Factory.getPlayerFactory(Factory.PLAYER_C);

        buttons = new ArrayList<Button>();
        buttons.add(new Button(
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
                }
        ));
        buttons.add(new Button(
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
                }
        ));
        buttons.add(new Button(
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
                }
        ));
    }

    @Override
    public void update() {
        for(Button b: buttons) {
            b.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        for(Button b: buttons) {
            b.draw(g);
        }
    }

    public static BufferedImage getPlayerSkin() {
        return playerSkin;
    }

    public static BufferedImage getPlayerLaser() {
        return playerLaser;
    }
}

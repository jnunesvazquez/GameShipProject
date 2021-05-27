package com.project.states;

import com.project.graphics.Assets;
import com.project.ui.Button;
import com.project.ui.Action;
import com.project.ui.ButtonBuilder;
import constants.Constants;

import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuState extends State{

    private ArrayList<Button> buttons;

    public MenuState(){
        buttons = new ArrayList<Button>();
        buttons.add(new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyButton.getHeight() * 3,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ChoosePlayerState());
                    }
                })
                .build());
        buttons.add(new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyButton.getHeight(),
                "HIGHSCORE",
                new Action() {
                    @Override
                    public void doAction() throws SQLException {
                        changeState(new ScoreState());
                    }
                })
                .build());
        buttons.add(new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 + Assets.greyButton.getHeight(),
                Constants.EXIT,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);
                    }
                })
                .build());
    }

    @Override
    public void update() throws SQLException {
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
}

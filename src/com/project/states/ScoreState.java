package com.project.states;

import com.project.graphics.Assets;
import com.project.states.choosePlayer.PlayerB;
import com.project.ui.Action;
import com.project.ui.Button;
import com.project.ui.ButtonBuilder;
import constants.Constants;

import java.awt.*;

public class ScoreState extends State{

    private Button returnButton;

    public ScoreState(){
        returnButton = new ButtonBuilder(
                Assets.greyButton2,
                Assets.blueButton2,
                Assets.greyButton.getHeight(),
                Constants.HEIGHT / 2 - Assets.greyButton.getHeight() / 2,
                Constants.RETURN,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                })
                .build();
    }

    @Override
    public void update() {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);
    }
}

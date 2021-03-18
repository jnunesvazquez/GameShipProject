package com.project.states;

import com.project.graphics.Assets;
import com.project.ui.Button;
import com.project.ui.Action;
import constants.Constants;

import java.awt.Graphics;
import java.util.ArrayList;

public class MenuState extends State{

    private ArrayList<Button> buttons;

    public MenuState(){
        buttons = new ArrayList<Button>();
        buttons.add(new Button(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth()/2,
                Constants.HEIGHT / 2 - Assets.greyButton.getHeight(),
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ChoosePlayerState());
                    }
                }
        ));
        buttons.add(new Button(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth()/2,
                Constants.HEIGHT / 2 + Assets.greyButton.getHeight()/2 ,
                Constants.EXIT,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);
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
}

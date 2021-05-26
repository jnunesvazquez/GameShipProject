package com.project.states;

import com.project.graphics.Assets;
import com.project.ui.Button;
import com.project.ui.Action;
import com.project.ui.ButtonBuilder;
import constants.Constants;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Clase para definir el menu principal del juego
 */
public class MenuState extends State{

    private ArrayList<Button> buttons;  //Almacenamos nuestros botones

    /**
     * Constructor en el que añadimos los botones
     */
    public MenuState(){
        buttons = new ArrayList<Button>();
        buttons.add(new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyButton.getHeight(),
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ChoosePlayerState());         //Cambiamos al estado de escoger la nave
                    }
                })
                .build());

        buttons.add(new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Constants.WIDTH / 2 - Assets.greyButton.getWidth() / 2,
                Constants.HEIGHT / 2 + Assets.greyButton.getHeight() / 2,
                Constants.EXIT,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);                                     //Salimos de la ventana y cancelamos el proceso del sistema
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
}

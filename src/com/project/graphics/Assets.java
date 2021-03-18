package com.project.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    /**
     * Creamos imagen del jugador
     */
    public static BufferedImage playerA, playerB, playerC, blueLaser, greenLaser, redLaser;
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];

    //fuentes/fonts
    public static Font fontBig;
    public static Font fontMed;

    // numbers
    public static BufferedImage[] numbers = new BufferedImage[11];
    public static BufferedImage life;

    // ui
    public static BufferedImage blueButton;
    public static BufferedImage greyButton;
    public static BufferedImage blueButton2;
    public static BufferedImage greyButton2;

    /**
     * Le asociamos imagen de aspecto al jugador
     */
    public static void init(){
        playerA = Loader.imageLoader("/ships/player_a.png");
        playerB = Loader.imageLoader("/ships/player_b.png");
        playerC = Loader.imageLoader("/ships/player_c.png");
        blueLaser = Loader.imageLoader("/lasers/laserBlue01.png");
        greenLaser = Loader.imageLoader("/lasers/laserGreen11.png");
        redLaser = Loader.imageLoader("/lasers/laserRed01.png");
        life = Loader.imageLoader("/others/life.png");              //Vidas
        fontBig = Loader.loadFont("/fonts/futureFont.ttf",42); //Para mensajes grandes
        fontMed = Loader.loadFont("/fonts/futureFont.ttf",20); //Para mensajes no tan grandes
        greyButton = Loader.imageLoader("/ui/grey_button.png");     //Boton gris
        blueButton = Loader.imageLoader("/ui/blue_button.png");     //Boton azul
        greyButton2 = Loader.imageLoader("/ui/grey_button2.png");     //Boton gris
        blueButton2 = Loader.imageLoader("/ui/blue_button2.png");     //Boton azul

        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Loader.imageLoader("/numbers/"+i+".png");
        for(int i = 0; i < bigs.length; i++){
            bigs[i] = Loader.imageLoader("/meteors/big" + (i + 1) + ".png");
        }
        for(int i = 0; i < meds.length; i++){
            meds[i] = Loader.imageLoader("/meteors/med" + (i + 1) + ".png");
        }
        for(int i = 0; i < smalls.length; i++){
            smalls[i] = Loader.imageLoader("/meteors/small" + (i + 1) + ".png");
        }
    }
}

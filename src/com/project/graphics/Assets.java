package com.project.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    /**
     * Creamos imagen del jugador
     */
    public static BufferedImage player, blueLaser, greenLaser, redLaser;
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    //fuentes/fonts
    public static Font fontBig;
    public static Font fontMed;
    // numbers
    public static BufferedImage[] numbers = new BufferedImage[11];
    public static BufferedImage life;
    /**
     * Le asociamos imagen de aspecto al jugador
     */
    public static void init(){
        player = Loader.imageLoader("/ships/player.png");
        blueLaser = Loader.imageLoader("/lasers/laserBlue01.png");
        greenLaser = Loader.imageLoader("/lasers/laserGreen11.png");
        redLaser = Loader.imageLoader("/lasers/laserRed01.png");
        life = Loader.imageLoader("/others/life.png");
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

        fontBig =Loader.loadFont("/fonts/futureFont.ttf",42); //Para mensajes grandes
        fontMed =Loader.loadFont("/fonts/futureFont.ttf",20); //Para mensajes no tan grandes
    }



}

package com.project.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    /**
     * Creamos imagen del jugador
     */
    public static BufferedImage player, blueLaser, greenLaser, redLaser;

    /**
     * Le asociamos imagen de aspecto al jugador
     */
    public static void init(){
        player = Loader.imageLoader("/ships/player.png");
        blueLaser = Loader.imageLoader("/lasers/laserBlue01.png");
        greenLaser = Loader.imageLoader("/lasers/laserGreen11.png");
        redLaser = Loader.imageLoader("/lasers/laserRed01.png");

        fontBig =Loader.loadFont("/fonts/futureFont.ttf",42); //Para mensajes grandes
        fontMed =Loader.loadFont("/fonts/futureFont.ttf",20); //Para mensajes no tan grandes
    }

    //fuentes/fonts
    public static Font fontBig;
    public static Font fontMed;

}

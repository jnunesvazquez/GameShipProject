package com.project.graphics;

import java.awt.image.BufferedImage;

public class Assets {
    /**
     * Creamos imagen del jugador
     */
    public static BufferedImage player, blueLaser, greenLaser, redLaser;
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];

    /**
     * Le asociamos imagen de aspecto al jugador
     */
    public static void init(){
        player = Loader.imageLoader("/ships/player.png");
        blueLaser = Loader.imageLoader("/lasers/laserBlue01.png");
        greenLaser = Loader.imageLoader("/lasers/laserGreen11.png");
        redLaser = Loader.imageLoader("/lasers/laserRed01.png");
        for(int i = 0; i < bigs.length; i++){
            bigs[i] = Loader.imageLoader("/meteors/big" + (i + 1) + ".png");
        }
        for(int i = 0; i < meds.length; i++){
            meds[i] = Loader.imageLoader("/meteors/med" + (i + 1) + ".png");
        }
        for(int i = 0; i < smalls.length; i++){
            smalls[i] = Loader.imageLoader("/meteors/small" + (i + 1) + ".png");
        }
        for(int i = 0; i < tinies.length; i++){
            tinies[i] = Loader.imageLoader("/meteors/tiny" + (i + 1) + ".png");
        }
    }
}

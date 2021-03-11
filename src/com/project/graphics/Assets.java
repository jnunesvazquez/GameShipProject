package com.project.graphics;

import java.awt.image.BufferedImage;

public class Assets {
    /**
     * Creamos imagen del jugador
     */
    public static BufferedImage player;

    /**
     * Le asociamos imagen de aspecto al jugador
     */
    public static void init(){
        player=Loader.imageLoader("/ships/player.png");
    }
}

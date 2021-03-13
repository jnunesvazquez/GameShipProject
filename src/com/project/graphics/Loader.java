package com.project.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Loader {
    /**
     * Se encarga de cargar los archivos de sonido,imagenes.
     * @param path Nombre del archivo a cargar.
     * @return Buffer de imagenes.
     * Si falla al cargar una imagen devuelve un null.
     */
    public static BufferedImage imageLoader(String path){

        try {
            return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

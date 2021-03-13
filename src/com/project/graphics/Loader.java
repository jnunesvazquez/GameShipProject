package com.project.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Loader {
    /**
     * Se encarga de cargar los archivos de imagenes.
     * @param path Nombre del arcchivo a cargar.
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

    /**
     * Metodo para cargar las fuentes que vamos a introducir
     * @param path nombre del archivo
     * @param size tamaño
     * @return retorna la fuente que llamamos
     * en caso de que salte alguna escepcion retorna un nulo
     */
    public static Font loadFont(String path, int size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT,Loader.class.getResourceAsStream(path))
                    .deriveFont(Font.PLAIN,size);
        } catch (FontFormatException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

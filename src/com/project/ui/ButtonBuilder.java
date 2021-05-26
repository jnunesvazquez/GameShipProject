package com.project.ui;

import java.awt.image.BufferedImage;

/**
 * Clase constructora de botones
 */
public class ButtonBuilder {
    private BufferedImage mouseOutImage;
    private BufferedImage mouseInImage;
    private String text;
    private Action action;
    private int x;
    private int y;

    /**
     * Constructor
     * @param mouseOutImage Imagen sin el raton encima
     * @param mouseInImage  Imagen con el raton encima
     * @param x Primera coordena
     * @param y Segunda coordenada
     * @param text  Texto del boton
     * @param action    Accion del boton
     */
    public ButtonBuilder(BufferedImage mouseOutImage, BufferedImage mouseInImage, int x, int y, String text, Action action ) {
        this.mouseOutImage = mouseOutImage;
        this.mouseInImage = mouseInImage;
        this.text = text;
        this.action = action;
        this.x = x;
        this.y = y;
    }

    /**
     * Metodo para definir un boton
     * @param mouseOutImage Imagen sin el raton encima
     * @param mouseInImage  Imagen con el raton encima
     * @param x Primera coordena
     * @param y Segunda coordenada
     * @param text  Texto del boton
     * @param action    Accion del boton
     */
    public static ButtonBuilder aButton(BufferedImage mouseOutImage, BufferedImage mouseInImage, String text, Action action, int x, int y) {
        return new ButtonBuilder(mouseOutImage, mouseInImage, x, y, text, action);
    }

    /**
     * Metodo para construir un boton
     * @return  Nuevo boton
     */
    public Button build() {
        Button button = new Button(mouseOutImage, mouseInImage, x, y, text, action);
        return button;
    }
}
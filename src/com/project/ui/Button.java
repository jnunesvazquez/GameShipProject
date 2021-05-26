package com.project.ui;

import com.project.graphics.Assets;
import com.project.graphics.Text;
import com.project.input.MouseInput;
import com.project.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

/**
 * Clase para construir los botones del menu
 */
public class Button {

    private BufferedImage mouseOutImage;                //Imagen que muestra cuando el raton no esta encima del boton
    private BufferedImage mouseInImage;                 //Imagen que muestra cuando el raton esta encima del boton
    private boolean mouseIn;                            //Indica si el raton esta encima de un boton o no
    private Rectangle boundingBox;                      //Hitbox del boton
    private String text;                                //Texto encima del boton
    private Action action;                              //Accion que le aplicaremos al boton que creemos

    /**
     * Constructor
     * @param mouseOutImage Imagen sin el raton encima
     * @param mouseInImage  Imagen con el raton encima
     * @param x Primera coordena
     * @param y Segunda coordenada
     * @param text  Texto del boton
     * @param action    Accion del boton
     */
    public Button(BufferedImage mouseOutImage, BufferedImage mouseInImage,int x, int y, String text, Action action) {
        this.mouseOutImage = mouseOutImage;
        this.mouseInImage = mouseInImage;
        boundingBox = new Rectangle(x, y, mouseInImage.getWidth(), mouseInImage.getHeight());
        this.text = text;
        this.action = action;
    }

    /**
     * Metodo para actualizar nuestros botones
     */
    public void update() throws SQLException {
                                //Si la posicion del raton esta encima de un boton
        mouseIn = boundingBox.contains(MouseInput.X, MouseInput.Y);
        if (mouseIn && MouseInput.MOUSELEFTBUTTON){
            action.doAction();
        }
    }

    /**
     * Metodo para dibujar nuestros botones
     * @param g Objeto a dibujar
     */
    public void draw(Graphics g){
        if (mouseIn){                                                           //Lo dejamos en null para que no se modifique la imagen despues de haber sido creada
            g.drawImage(mouseInImage, boundingBox.x, boundingBox.y, null);
        }
        else {
            g.drawImage(mouseOutImage, boundingBox.x, boundingBox.y, null);
        }
        Text.drawText(g, text, new Vector2D(                                //Dibujamos el texto encima del boton
                    boundingBox.getX() + boundingBox.getWidth() / 2,
                    boundingBox.getY() + boundingBox.getHeight()),
                true,
                Color.BLACK,
                Assets.fontMed);
    }
}

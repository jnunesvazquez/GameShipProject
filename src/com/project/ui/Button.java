package com.project.ui;

import com.project.graphics.Assets;
import com.project.graphics.Text;
import com.project.input.MouseInput;
import com.project.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private BufferedImage mouseOutImage;                //Imagen que muestra cuando el raton no esta encima del boton
    private BufferedImage mouseInImage;                 //Imagen que muestra cuando el raton esta encima del boton
    private boolean mouseIn;                            //Indica si el raton esta encima de un boton o no
    private Rectangle boundingBox;                      //Hitbox del boton
    private String text;                                //Texto encima del boton
    private Action action;
    private int x, y;

    public Button(BufferedImage mouseOutImage, BufferedImage mouseInImage, int x, int y, String text, Action action) {
        this.mouseOutImage = mouseOutImage;
        this.mouseInImage = mouseInImage;
        this.text = text;
        boundingBox = new Rectangle(x, y, mouseInImage.getWidth(), mouseInImage.getHeight());
        this.action = action;
        this.x = x;
        this.y = y;
    }

    public void update(){
        if (boundingBox.contains(MouseInput.X, MouseInput.Y)){                      //Devuelve verdadero si las coordenadas del raton estan encima de un objetos
            mouseIn = true;
        }
        else {
            mouseIn = false;
        }

        if (mouseIn == true && MouseInput.MOUSELEFTBUTTON){
            action.doAction();
        }
    }

    public void draw(Graphics g){
        if (mouseIn == true){
            g.drawImage(mouseInImage, boundingBox.x, boundingBox.y, null);
        }
        else {
            g.drawImage(mouseOutImage, boundingBox.x, boundingBox.y, null);
        }
        Text.drawText(g, text, new Vector2D(
                    boundingBox.getX() + boundingBox.getWidth() / 2,
                    boundingBox.getY() + boundingBox.getHeight()),
                true,
                Color.BLACK,
                Assets.fontMed);
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }


    public BufferedImage getMouseOutImage() {
        return mouseOutImage;
    }

    public void setMouseOutImage(BufferedImage mouseOutImage) {
        this.mouseOutImage = mouseOutImage;
    }

    public BufferedImage getMouseInImage() {
        return mouseInImage;
    }

    public void setMouseInImage(BufferedImage mouseInImage) {
        this.mouseInImage = mouseInImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}

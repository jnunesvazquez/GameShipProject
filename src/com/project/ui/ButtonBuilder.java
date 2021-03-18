package com.project.ui;

import java.awt.image.BufferedImage;

public class ButtonBuilder {
    private BufferedImage mouseOutImage;
    private BufferedImage mouseInImage;
    private String text;
    private Action action;
    private int x;
    private int y;

    public ButtonBuilder(BufferedImage mouseOutImage, BufferedImage mouseInImage, int x, int y, String text, Action action ) {
        this.mouseOutImage = mouseOutImage;
        this.mouseInImage = mouseInImage;
        this.text = text;
        this.action = action;
        this.x = x;
        this.y = y;
    }

    public static ButtonBuilder aButton(BufferedImage mouseOutImage, BufferedImage mouseInImage, String text, Action action, int x, int y) {
        return new ButtonBuilder(mouseOutImage, mouseInImage, x, y, text, action);
    }

    public Button build() {
        Button button = new Button(mouseOutImage, mouseInImage, x, y, text, action);
        return button;
    }
}

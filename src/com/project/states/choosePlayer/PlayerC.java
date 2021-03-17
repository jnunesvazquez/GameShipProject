package com.project.states.choosePlayer;

import com.project.graphics.Assets;

import java.awt.image.BufferedImage;

public class PlayerC implements IChoose{

    public BufferedImage getPlayer() {
        return Assets.playerC;
    }

    @Override
    public BufferedImage getLaser() {
        return Assets.redLaser;
    }
}

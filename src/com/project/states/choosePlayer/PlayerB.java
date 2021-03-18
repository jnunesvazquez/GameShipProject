package com.project.states.choosePlayer;

import com.project.graphics.Assets;

import java.awt.image.BufferedImage;

public class PlayerB implements IChoose{

    public BufferedImage getPlayer() {
        return Assets.playerB;
    }

    @Override
    public BufferedImage getLaser() {
        return Assets.greenLaser;
    }
}

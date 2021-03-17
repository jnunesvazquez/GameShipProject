package com.project.states.choosePlayer;

import com.project.graphics.Assets;

import java.awt.image.BufferedImage;

public class PlayerA implements IChoose{

    @Override
    public BufferedImage getPlayer() {
        return Assets.playerA;
    }

    public BufferedImage getLaser(){
        return Assets.blueLaser;
    }
}

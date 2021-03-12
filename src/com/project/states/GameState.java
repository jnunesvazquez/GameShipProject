package com.project.states;

import com.project.gameObject.Player;
import com.project.graphics.Assets;
import com.project.math.Vector2D;

import java.awt.*;

public class GameState {

    private Player player;

    public GameState() {
        player=new Player(new Vector2D(100,500), Assets.player);
    }

    public void update(){
        player.update();
    }
    public void draw(Graphics g){
        player.draw(g);
    }
}

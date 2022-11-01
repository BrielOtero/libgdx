package com.gabriel.endure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture player;

    public static void load(){
        player=new Texture("assets/graphics/player.png");
    }
}

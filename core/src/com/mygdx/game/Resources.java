package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resources {

    TextureAtlas gameSprites;

    public TextureRegion logo;
    public TextureRegion ground;
    public TextureRegion wall;
    public TextureRegion water;
    public TextureRegion sand;

    public Sprite player;
    public Sprite bush;

    public static final int TILE_SIZE = 32;

    public Resources() {

        gameSprites = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));

        //Title screen assets
        logo = gameSprites.findRegion("logo");

        //Background assets
        ground = gameSprites.findRegion("ground");
        wall = gameSprites.findRegion("wall");
        water = gameSprites.findRegion("water");
        sand = gameSprites.findRegion("sand");

        //Object assets
        player = new Sprite(gameSprites.findRegion("player"));
        bush = new Sprite(gameSprites.findRegion("bush"));
    }

    public void dispose() {
        gameSprites.dispose();
    }
}

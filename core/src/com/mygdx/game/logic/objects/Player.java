package com.mygdx.game.logic.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Resources;

public class Player extends Sprite {
    private int fieldX;
    private int fieldY;

    public Player(int fx, int fy, Resources res) {
        fieldX = fx;
        fieldY = fy;
        set(res.player);
    }

    public int getFieldX() {
        return fieldX;
    }

    public void setFieldX(int fx) {
        fieldX = fx;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setFieldY(int fy) {
        fieldY = fy;
    }
}

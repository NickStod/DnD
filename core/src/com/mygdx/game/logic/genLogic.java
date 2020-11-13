package com.mygdx.game.logic;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.logic.objects.Player;

public class genLogic {
    public static final int MAX_BASE_X = 192;
    public static final int MAX_BASE_Y = 128;

    Player player;

    MyGdxGame game;

    public genLogic(MyGdxGame _game) {
        game = _game;
    }

    public Player getPlayer() {
        return player;
    }


    public void update(float delta) {

    }
}

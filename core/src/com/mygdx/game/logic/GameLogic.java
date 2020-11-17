package com.mygdx.game.logic;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.logic.objects.Player;

public class GameLogic {

    Player player;
    MyGdxGame game;

    int stageWidth;
    int stageHeight;

    public GameLogic(MyGdxGame _game) {
        game = _game;

        stageWidth = 32;

        stageHeight = 32;

        player = new Player(stageWidth, stageHeight, game.res);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean CheckMove(float fx, float fy, int mapWidth, int mapHeight) {
        return (fx >= 0 &&
                fx <= mapWidth && fy >= 0 &&
                fy <= mapHeight);
    }

    public void AssignPlayerPosition(float fx, float fy) {
        player.setX(fx);
        player.setY(fy);
    }

    public void update(float delta) {

    }
}

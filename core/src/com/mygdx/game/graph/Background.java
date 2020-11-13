package com.mygdx.game.graph;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Resources;

public class Background {

    public Background() {

    }

    //Draws bg for title screen
    public void drawTitle(Stage stage, Resources res) {
        stage.getBatch().begin();

        for(int y = (int)stage.getHeight(); y >= -Resources.TILE_SIZE; y -= Resources.TILE_SIZE) {
            for (int x = 0; x < stage.getWidth(); x += Resources.TILE_SIZE) {
                stage.getBatch().draw(res.ground,
                        x, y,
                        0, 0,
                        Resources.TILE_SIZE, Resources.TILE_SIZE,
                        1.01f, 1.01f,
                        0);
            }
        }
        stage.getBatch().end();
    }

    //Draws bg for first menu after title screen
    public void drawMenu(Stage stage, Resources res) {
        stage.getBatch().begin();

        for(int y = (int)stage.getHeight(); y >= -Resources.TILE_SIZE; y -= Resources.TILE_SIZE) {
            for (int x = 0; x < stage.getWidth(); x += Resources.TILE_SIZE) {
                stage.getBatch().draw(res.wall,
                        x, y,
                        0, 0,
                        Resources.TILE_SIZE, Resources.TILE_SIZE,
                        1.01f, 1.01f,
                        0);
            }
        }
        stage.getBatch().end();
    }
}

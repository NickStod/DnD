package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Resources;
import com.mygdx.game.graph.Background;
import com.mygdx.game.logic.genLogic;

public class MenuScreen extends DefaultScreen implements Screen, InputProcessor {

    // Important variables
    private final Stage menuStage;
    genLogic logic;
    MyGdxGame game;
    BitmapFont font = new BitmapFont();

    //Dimensions
    public final int STAGE_W = 192;
    public final int STAGE_H = 128;

    //Imagery variables
    Background bg;
    SpriteBatch batch;

    public MenuScreen(MyGdxGame _game) {
        super(_game);
        game = _game;
        bg = new Background();
        batch = new SpriteBatch();

        ExtendViewport viewp = new ExtendViewport(STAGE_W, STAGE_H);
        menuStage = new Stage(viewp, batch);
        logic = new genLogic(_game);
        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bg.drawMenu(menuStage, game.res);
        menuStage.draw();

        menuStage.getBatch().begin();
        for(int x = 0; x < menuStage.getWidth(); x += Resources.TILE_SIZE) {
            menuStage.getBatch().draw(game.res.wall, x, menuStage.getHeight() - Resources.TILE_SIZE, 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.01f, 1.01f, 0);
        }
        //Need to make into clickable or selectable buttons instead of useless text
        font.draw(batch, "New Game", 8, 100);
        font.draw(batch, "Load Game", 8, 88);
        font.draw(batch, "Options", 8, 76);
        font.draw(batch, "Read Me", 8, 64);
        font.draw(batch, "Exit", 8, 52);
        menuStage.getBatch().end();
    }

    public void resize(int w, int h) {
        super.resize(w, h);
        menuStage.getViewport().update(w, h, true);
    }

    public void dispose() {
        super.dispose();
        menuStage.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }

    private void update(float delta) {
        menuStage.act(delta);
        logic.update(delta);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                break;
            case Input.Keys.BACKSPACE:
                game.setScreen(new TitleScreen(game));
                dispose();
                break;
            case Input.Keys.ENTER:
                game.setScreen(new PlaceHolderGameScreen(game));
            default:
                break;
        }
            return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

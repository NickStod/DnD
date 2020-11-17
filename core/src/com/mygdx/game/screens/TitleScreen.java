package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.graph.Background;
import com.mygdx.game.logic.genLogic;

public class TitleScreen extends DefaultScreen implements Screen, InputProcessor {

    //Important variables
    private final Stage titleStage;
    genLogic logic;
    BitmapFont font = new BitmapFont();

    //Dimensions
    public final int STAGE_W = 192;
    public final int STAGE_H = 128;

    //Imagery variables
    Background bg;
    SpriteBatch batch;

    public TitleScreen(MyGdxGame _game) {
        super(_game);
        bg = new Background();
        batch = new SpriteBatch();

        ExtendViewport viewp = new ExtendViewport(STAGE_W, STAGE_H);
        titleStage = new Stage(viewp, batch);
        logic = new genLogic(_game);
        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bg.drawTitle(titleStage, game.res);
        titleStage.draw();

        titleStage.getBatch().begin();

        //Draws logo on the top of screen
        titleStage.getBatch().draw(game.res.logo, 48, 96);
        font.draw(batch, "Press Enter to start", 40, 32);
        titleStage.getBatch().end();
    }

    public void dispose() {
        super.dispose();
        titleStage.dispose();
        batch.dispose();
    }

    public void resize(int w, int h) {
        super.resize(w, h);
        titleStage.getViewport().update(w, h, true);
    }

    private void update(float delta) {
        titleStage.act(delta);
        logic.update(delta);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                break;
            case Input.Keys.ENTER:
                game.setScreen(new MenuScreen(game));
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



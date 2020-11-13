package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Resources;
import com.mygdx.game.graph.Background;
import com.mygdx.game.graph.SizeEvaluator;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.logic.objects.Player;
import com.mygdx.game.tilemaps.TiledTest;

public class PlaceHolderGameScreen extends DefaultScreen implements InputProcessor {

    //Important variables
    private final Stage gameStage;
    GameLogic gameLogic;
    Player player;
    public boolean keyPressed;
    Texture img;
    TiledMap map;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    //Dimensions
    public final int STAGE_W = 192;
    public final int STAGE_H = 128;

    //Imagery variables
    private final SizeEvaluator sizeEvaluator;
    Background bg;
    SpriteBatch batch;

    public PlaceHolderGameScreen(MyGdxGame _game) {
        super(_game);
        bg = new Background();
        batch = new SpriteBatch();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("tileMap1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        ExtendViewport viewp = new ExtendViewport(STAGE_W, STAGE_H);
        gameStage = new Stage(viewp, batch);
        sizeEvaluator = new SizeEvaluator(gameStage, game.res, GameLogic.MAX_BASE_X, GameLogic.MAX_BASE_Y);
        gameLogic = new GameLogic(game);
        player = gameLogic.getPlayer();
        Gdx.input.setInputProcessor(this);
    }


    public void resize(int w, int h) {
        super.resize(w, h);
        gameStage.getViewport().update(w, h, true);
    }

    private void update(float delta) {
        gameStage.act(delta);
        gameLogic.update(delta);
    }

    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.begin();
        player.draw(batch, sizeEvaluator);
        batch.end();
        gameStage.draw();

        gameStage.getBatch().begin();
        gameStage.getBatch().end();

        //Checks for keypressing, allows hold-down movement
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            AttemptMove(1, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            AttemptMove(-1, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            AttemptMove(0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            AttemptMove(0, -1);
        }

    }
    public void dispose() {
        super.dispose();
        gameStage.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.D:
                keyPressed = true;
                AttemptMove(1, 0);
                break;
            case Input.Keys.A:
                keyPressed = true;
                AttemptMove(-1, 0);
                break;
            case Input.Keys.W:
                keyPressed = true;
                AttemptMove(0, 1);
                break;
            case Input.Keys.S:
                keyPressed = true;
                AttemptMove(0, -1);
                break;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                break;
            case Input.Keys.BACKSPACE:
                game.setScreen(new MenuScreen(game));
                break;
            default:
                break;
        }
        return false;
    }
    public void AttemptMove(int dx, int dy) {
        if (gameLogic.CheckMove(player.getFieldX() + dx, player.getFieldY() + dy)) {
            gameLogic.AssignPlayerPosition(player.getFieldX() + dx, player.getFieldY() + dy);
        }
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

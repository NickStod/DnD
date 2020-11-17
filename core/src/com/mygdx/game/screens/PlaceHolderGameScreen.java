package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.graph.Background;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.logic.objects.Player;

public class PlaceHolderGameScreen extends DefaultScreen implements Screen, InputProcessor {

    //Important variables
    GameLogic gameLogic;
    Player player;
    public boolean keyPressed;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    MapObjects objects;
    TiledMapTileLayer collisionLayer;
    int objectLayerId = 3;

    //Imagery variables
    Background bg;
    SpriteBatch batch;

    public PlaceHolderGameScreen(MyGdxGame _game) {
        super(_game);
        bg = new Background();
        batch = new SpriteBatch();

        gameLogic = new GameLogic(game);
        player = gameLogic.getPlayer();
        Gdx.input.setInputProcessor(this);
    }

    public void init() {
        initMapMaterials();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        camera.position.set(player.getX(), player.getY(), 0);

        //Checks for key pressing, allows hold-down movement
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
        batch.end();

    }

    public void resize(int w, int h) {
        super.resize(w, h);
        camera.viewportWidth = w;
        camera.viewportHeight = h;
        camera.setToOrtho(false, 192, 128);
        camera.update();
    }

    public void show() {
        float unitScale = 1/ 4f;
        map = new TmxMapLoader().load("maps/testMap.tmx");

        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        camera = new OrthographicCamera();
    }

    //Gets the width of the tilemap.
    public int getMapWidth() {
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
        return layer.getTileWidth() * layer.getWidth();
    }

    //Gets the height of the tilemap.
    public int getMapHeight() {
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
        return layer.getTileHeight() * layer.getHeight();
    }

    public void hide() {
        dispose();
    }

    public void dispose() {
        super.dispose();
        map.dispose();
        renderer.dispose();
        batch.dispose();
    }

    public void initMapMaterials() {
        collisionLayer = (TiledMapTileLayer)map.getLayers().get(objectLayerId);
        objects = collisionLayer.getObjects();
        //RectangleMapObject[] rectangleObject = new RectangleMapObject[objects.getCount()];
    }

    //Checks the dimensions
    public void AttemptMove(int dx, int dy) {
        if (gameLogic.CheckMove(player.getX() + dx, player.getY() + dy, getMapWidth(), getMapHeight())) {
            gameLogic.AssignPlayerPosition(player.getX() + dx, player.getY() + dy);
            camera.position.set(player.getX(), player.getY(), 0);
        }
        else {
            for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

                Rectangle rectangle = rectangleObject.getRectangle();
                if (Intersector.overlaps(rectangle, player.getBoundingRectangle())) {
                    gameLogic.AssignPlayerPosition(player.getX(), player.getY());
                    camera.position.set(player.getX(), player.getY(), 0);
                }
            }
        }
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

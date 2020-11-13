package com.mygdx.game.graph;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Resources;

public class SizeEvaluator {

    Stage measuredStage;
    Resources resources;

    private final int maxTileBaseX;
    private final int maxTileBaseY;
    private final int BASE_MARGIN = 2;

    public SizeEvaluator(Stage _stage, Resources _resources, int _maxTileBaseX, int _maxTileBaseY) {
        measuredStage = _stage;
        resources = _resources;

        maxTileBaseX = _maxTileBaseX;
        maxTileBaseY = _maxTileBaseY;
    }

    public float getBaseScreenX(int baseX) {
        return baseX;
    }

    public float getBaseScreenY(int baseY) {
        return baseY;
    }

    public float getEnemyX(Sprite enemy) {
        return (measuredStage.getWidth() * 3 / 4) - enemy.getWidth() / 2;
    }

    public float getEnemyY(Sprite enemy) {
        return measuredStage.getHeight() / 2 - enemy.getHeight() / 2;
    }
}

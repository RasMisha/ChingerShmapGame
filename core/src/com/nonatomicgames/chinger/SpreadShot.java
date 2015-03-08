package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MandM on 09.03.2015.
 */
public class SpreadShot implements Shot {

    private static final int NUMBER_OF_SHOTS = 5;
    private static final float UPDATING_TIME = 0.02f;
    private static final float VELOCITY = 5f;

    private static Vector2[] directionVectors = new Vector2[5];

    private Vector2[] positions = new Vector2[5];
    private float lifeTime = 0;
    private boolean wasShoted = false;

    public SpreadShot() {
        wasShoted = false;
    }

    static {
        directionVectors[0] = new Vector2(0.87f * VELOCITY, 0.5f * VELOCITY);
        directionVectors[1] = new Vector2(0.96f * VELOCITY, 0.26f * VELOCITY);
        directionVectors[2] = new Vector2(1 * VELOCITY, 0f * VELOCITY);
        directionVectors[3] = new Vector2(0.96f * VELOCITY, -0.26f * VELOCITY);
        directionVectors[4] = new Vector2(0.87f * VELOCITY, -0.5f * VELOCITY);
    }

    @Override
    public void shoot(float spawnX, float spawnY) {
        for (int i = 0; i < NUMBER_OF_SHOTS; i++) {
            positions[i] = new Vector2(spawnX, spawnY);
        }
        lifeTime = 0f;
        wasShoted = true;
    }

    @Override
    public boolean onScreen() {
        return wasShoted;
    }

    @Override
    public void update(float delta) {
        lifeTime += delta;
        if (lifeTime >= UPDATING_TIME) {
            lifeTime -= UPDATING_TIME;
            for (int i = 0; i < 5; i++) {
                positions[i].x += directionVectors[i].x;
                positions[i].y += directionVectors[i].y;
            }
        }
    }

    @Override
    public void render(SpriteBatch batcher) {
        for (Vector2 position : positions) {
            batcher.draw(Assets.simpleShotRegion, position.x, position.y);
        }
    }
}

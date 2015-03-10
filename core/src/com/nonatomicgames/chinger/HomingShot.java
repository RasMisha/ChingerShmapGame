package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MandM on 10.03.2015.
 */
public class HomingShot implements Shot {

    public static final float UPDATING_TIME = 0.02f;
    public static final float ROTATING_TIME = 0.1f;
    private static final float VELOCITY = 5;

    private boolean wasShoted = true;
    private float lifeTime = 0f;
    private float rotatingTime = 0f;
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private float angle = 5f, curretAngle = 0f;

    public HomingShot() {
        wasShoted = false;
    }

    @Override
    public void shoot(float spawnX, float spawnY) {
        this.wasShoted = true;
        this.lifeTime = 0f;
        this.position.x = spawnX;
        this.position.y = spawnY;

        this.direction.x = 1;
        this.direction.y = 0;
    }

    @Override
    public boolean onScreen() {
        return wasShoted;
    }

    @Override
    public void update(float delta) {
        lifeTime += delta;
        rotatingTime += delta;
        if (lifeTime >= UPDATING_TIME) {
            lifeTime -= UPDATING_TIME;
            position.add(direction);
        }

        if (rotatingTime >= ROTATING_TIME) {
            rotatingTime -= ROTATING_TIME;
            curretAngle += angle;
            direction.rotate(angle).nor().scl(VELOCITY);
        }
    }

    @Override
    public void render(SpriteBatch batcher) {

        System.err.println(curretAngle);

        batcher.draw(Assets.simpleShotRegion,
                position.x,
                position.y,
                0f,//Assets.simpleShotRegion.getRegionWidth() / 2f,
                0f,//Assets.simpleShotRegion.getRegionHeight() / 2f,
                Assets.simpleShotRegion.getRegionWidth(),
                Assets.simpleShotRegion.getRegionHeight(),
                1,
                1,
                curretAngle,
                false
        );
    }

    @Override
    public boolean enemyShot() {
        return false;
    }
}

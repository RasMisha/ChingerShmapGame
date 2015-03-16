package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MandM on 14.03.2015.
 */
public class SinEnemy implements Enemy {

    private static final float DEFAULT_X_SPEED = 2f;
    private static final float UPDATING_TIME = 0.02f;
    private static final float SHOT_FREQ = 0.5f;

    private float radius;

    private boolean killed;
    private Vector2 position;
    private float lifeTime;
    private float shiftY;
    private Level level;
    private float xSpeed;
    private float lastShot = 0f;

    public SinEnemy(Level level, float y) {
        this.killed = false;
        this.position = new Vector2(Constants.WORLD_WIDTH, y);
        this.lifeTime = 0f;
        this.radius = 30f;
        this.level = level;
        this.xSpeed = DEFAULT_X_SPEED;
        this.shiftY = y;
    }

    @Override
    public void update(float delta) {
        position.x -= xSpeed * delta / UPDATING_TIME;
        position.y = shiftY + MathUtils.sin(lifeTime * 5) * radius;
        lifeTime += delta;

        lastShot += delta;
        if (lastShot >= SHOT_FREQ && level.ship.position.x < position.x) {
            lastShot -= SHOT_FREQ;
            Vector2 shotDirection =
                    new Vector2(level.ship.position.x, level.ship.position.y)
                            .sub(position.x, position.y)
                            .nor();
            Shot newShot = new SimpleShot(shotDirection, true);
            newShot.shoot(position.x, position.y + Assets.enemyRegion.getRegionHeight()/2);
            level.addShot(newShot);
        }
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(Assets.enemyRegion, position.x, position.y);
    }

    @Override
    public boolean killed() {
        return this.killed;
    }
}

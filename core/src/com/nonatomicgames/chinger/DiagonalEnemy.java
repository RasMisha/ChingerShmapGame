package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by RasMisha on 16.03.2015.
 */
public class DiagonalEnemy implements Enemy {

    public static final float VELOCITY = 4;
    public static final float UPDATING_TIME = 0.02f;
    private static final float SHOT_FREQ = 1f;

    public static final int UP_DIRECTION = 0;
    public static final int DOWN_DIRECTION = 1;

    private boolean killed;
    private Level level;
    private Vector2 position;
    private Vector2 velocity;
    private float lastShotIn = 0f;

    public DiagonalEnemy(Level level, float x, float y, int direction) {
        this.level = level;
        this.position = new Vector2(x,y);
        this.killed = false;
        this.lastShotIn = 0f;
        this.velocity = new Vector2(-VELOCITY, 0);
        switch (direction) {
            case UP_DIRECTION:
                this.velocity.rotate(45);
                break;
            case DOWN_DIRECTION:
                this.velocity.rotate(-45);
                break;
        }
    }

    @Override
    public void update(float delta) {
        Vector2 currentDelta = new Vector2(velocity.x, velocity.y).nor().scl(delta/UPDATING_TIME);
        this.position.add(currentDelta);

        lastShotIn+=delta;
        if (lastShotIn >= SHOT_FREQ) {
            lastShotIn -= SHOT_FREQ;
            Vector2 shotDirection = new Vector2(level.ship.position.x, level.ship.position.y).sub(position.x, position.y).nor();
            Shot newShot = new SimpleShot(shotDirection, true);;
            newShot.shoot(position.x, position.y + Assets.enemyRegion.getRegionHeight()/2);
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

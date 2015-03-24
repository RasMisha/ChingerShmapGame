package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by misha on 3/24/2015.
 */
public class ImpulseShot implements Shot {

    public static float IMPULSE_SHOT_LIFE_TIME = 2f;

    private static float lifeTime = 0f;
    private static Level level;
    private static Ship ship;

    public ImpulseShot(Level level, Ship ship) {
        this.level = level;
        this.ship = ship;
    }

    @Override
    public void shoot(float spawnX, float spawnY) {
        level.addSuperShot(this);
    }

    @Override
    public boolean onScreen() {
        return false;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batcher) {

    }

    @Override
    public boolean enemyShot() {
        return false;
    }
}

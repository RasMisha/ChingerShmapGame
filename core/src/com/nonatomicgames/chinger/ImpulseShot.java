package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by misha on 3/24/2015.
 */
public class ImpulseShot implements Shot {

    public static float IMPULSE_SHOT_LIFE_TIME = 2f;

    private static float lifeTime = 0f;
    private Ship ship;

    public ImpulseShot(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void shoot(float spawnX, float spawnY) {
        lifeTime = 0f;
    }

    @Override
    public boolean onScreen() {
        return lifeTime <= IMPULSE_SHOT_LIFE_TIME;
    }

    @Override
    public void update(float delta) {
        lifeTime+=delta;
    }

    @Override
    public void render(SpriteBatch batcher) {

        batcher.draw(
                Assets.laserRegion,
                ship.position.x + ship.width - 10,
                ship.position.y + ship.height/2 - 4,
                Constants.WORLD_WIDTH - ship.position.x + ship.width + 10,
                8
        );
    }

    @Override
    public boolean enemyShot() {
        return false;
    }
}

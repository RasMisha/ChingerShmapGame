package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MandM on 06.03.2015.
 */
public interface Shot {

    public static final int MAXIMUM_SHOTS_ON_SCREEN = 6;

    public void shoot(float spawnX, float spawnY);

    public boolean onScreen();

    public void update(float delta);

    public void render(SpriteBatch batcher);

    public boolean enemyShot();
}

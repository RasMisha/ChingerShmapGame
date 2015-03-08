package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MandM on 06.03.2015.
 */
public interface Shot {

    public void shoot(float spawnX, float spawnY);

    public boolean onScreen();

    public void update(float delta);

    public void render(SpriteBatch batcher);
}

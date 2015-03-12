package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MandM on 11.03.2015.
 */
public interface Enemy {

    public void update(float delta);

    public void render(SpriteBatch batcher);

    public boolean killed();

}

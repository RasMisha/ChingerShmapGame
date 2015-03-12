package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MandM on 12.03.2015.
 */
public interface Explosion {

    public void render(SpriteBatch batcher);

    public void update(float delta);

    public boolean finished();

}

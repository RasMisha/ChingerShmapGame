package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MandM on 05.03.2015.
 */
public class Level {

    private Ship ship;
    private SpriteBatch batcher;

    public Level(SpriteBatch batcher, int number) {
        this.ship = new Ship(0,0);
        this.batcher = batcher;
    }

    public void update(float delta) {
        ship.update(delta);
    }

    public void render() {
        batcher.begin();
        batcher.draw(Assets.shipRegion, ship.position.x, ship.position.y);
        ship.renderShots(batcher);
        batcher.end();
    }

}

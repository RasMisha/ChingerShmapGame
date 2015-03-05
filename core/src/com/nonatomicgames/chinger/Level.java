package com.nonatomicgames.chinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MandM on 05.03.2015.
 */
public class Level {

    private Ship ship;
    private SpriteBatch batcher;

    public Level(ChingerGame game, SpriteBatch batcher, int number) {
        this.ship = new Ship(0,0);
        this.batcher = batcher;
    }

    public void update(float delta) {
        ship.update(delta);
    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.draw(Assets.shipRegion, ship.position.x, ship.position.y);
        batcher.end();
    }

}

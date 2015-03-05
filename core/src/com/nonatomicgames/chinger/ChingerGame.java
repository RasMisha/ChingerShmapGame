package com.nonatomicgames.chinger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChingerGame extends ApplicationAdapter {

    SpriteBatch batch;
    Level currentLevel;
    FPSLogger fpsLogger;

	@Override
	public void create () {
		batch = new SpriteBatch();
        Assets.loadGfx();
        currentLevel = new Level(this, batch, 1);

        fpsLogger = new FPSLogger();
    }

	@Override
	public void render () {
        currentLevel.update(Gdx.graphics.getDeltaTime());
        currentLevel.render();

        fpsLogger.log();
	}

}

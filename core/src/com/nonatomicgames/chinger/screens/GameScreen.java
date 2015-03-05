package com.nonatomicgames.chinger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nonatomicgames.chinger.ChingerGame;
import com.nonatomicgames.chinger.Level;

/**
 * Created by MandM on 05.03.2015.
 */
public class GameScreen implements Screen {

    private ChingerGame game;
    private Level currentLevel;
    private FPSLogger fpsLogger;
    private SpriteBatch batcher;

    public GameScreen(ChingerGame game, SpriteBatch batcher) {
        this.game = game;
        this.batcher = batcher;
    }

    public void show() {
        currentLevel = new Level(batcher, 1);
        fpsLogger = new FPSLogger();
    }

    public void render(float delta) {
        currentLevel.update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentLevel.render();

        fpsLogger.log();
    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }
}

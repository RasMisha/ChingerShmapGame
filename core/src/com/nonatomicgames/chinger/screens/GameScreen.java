package com.nonatomicgames.chinger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.nonatomicgames.chinger.ChingerGame;
import com.nonatomicgames.chinger.Constants;
import com.nonatomicgames.chinger.Level;

/**
 * Created by MandM on 05.03.2015.
 */
public class GameScreen implements Screen {

    private ChingerGame game;
    private Level currentLevel;
    private FPSLogger fpsLogger;
    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera orthographicCamera;

    public GameScreen(ChingerGame game, SpriteBatch batcher) {
        this.game = game;
        this.batcher = batcher;
        this.shapeRenderer = new ShapeRenderer();
        initCamera();
    }

    private void initCamera() {
        orthographicCamera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
        orthographicCamera.update();
    }

    public void show() {
        currentLevel = new Level(batcher, shapeRenderer, 1);
        fpsLogger = new FPSLogger();
    }

    public void render(float delta) {

//        orthographicCamera.position.x += 1;

        currentLevel.update(delta);

        orthographicCamera.update();
        batcher.setProjectionMatrix(orthographicCamera.combined);

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
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

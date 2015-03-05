package com.nonatomicgames.chinger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nonatomicgames.chinger.screens.GameScreen;

public class ChingerGame extends Game {

	@Override
	public void create () {
        SpriteBatch batcher = new SpriteBatch();
        Assets.loadGfx();

        setScreen(new GameScreen(this, batcher));
    }

}

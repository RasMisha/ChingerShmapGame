package com.nonatomicgames.chinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by MandM on 05.03.2015.
 */
public class Assets {

    private static final String RESOURCES_PREFIX = "assets/";

    private static Texture shipTexture;
    private static Texture bulletTexure;

    public static TextureRegion shipRegion;
    public static TextureRegion simpleShotRegion;
    public static TextureRegion enemyRegion;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(RESOURCES_PREFIX + file));
    }

    public static void loadGfx() {

        shipTexture = loadTexture("ship.png");
        bulletTexure = loadTexture("bullet.png");

        shipRegion = new TextureRegion(shipTexture, 0, 0, 24, 24);
        simpleShotRegion = new TextureRegion(bulletTexure, 0, 0, 4, 4);
        enemyRegion = new TextureRegion(shipTexture, 0, 0, 24, 24);
        enemyRegion.flip(true, false);

    }


}

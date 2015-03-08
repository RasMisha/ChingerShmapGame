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

    public static TextureRegion shipRegion;
    public static TextureRegion simpleShotRegion;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(RESOURCES_PREFIX + file));
    }

    public static void loadGfx() {

        shipTexture = loadTexture("ship_sheet.png");

        shipRegion = new TextureRegion(shipTexture,5, 6, 36-5, 21-6);
        simpleShotRegion = new TextureRegion(shipTexture, 268, 121, 284 - 268, 125 - 121);

    }


}

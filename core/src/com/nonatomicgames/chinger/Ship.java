package com.nonatomicgames.chinger;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MandM on 05.03.2015.
 */
public class Ship {

    private Level gameLevel;
    private Vector2 position = new Vector2();

    public Rectangle bounds = new Rectangle();

    public Ship(Level gameLevel, float x, float y) {
        this.gameLevel = gameLevel;
        this.position.x = x;
        this.position.y = y;
    }

}

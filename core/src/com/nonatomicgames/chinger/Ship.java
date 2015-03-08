package com.nonatomicgames.chinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MandM on 05.03.2015.
 */
public class Ship {

    public static final int LEFT_DIRECTION = 0;
    public static final int RIGHT_DIRECTION = 1;
    public static final int DOWN_DIRECTION = 2;
    public static final int UP_DIRECTION = 3;

    public Vector2 position = new Vector2();

    private Vector2 velocity = new Vector2();

    private int currentDirection;
    private Shot[] currentShots;

    private float time = 0f;

    public int currentSpeed = 3;
    public float[] speedTickValues = new float[] {0.06f,0.05f,0.04f,0.03f};

    public Rectangle bounds = new Rectangle();

    public Ship(float x, float y) {
        this.position.x = x;
        this.position.y = y;

        this.velocity.x = 0;
        this.velocity.y = 0;

        currentShots = new Shot[5];
        for (int i = 0; i < SimpleShot.MAXIMUM_SHOTS_ON_SCREEN; i++) {
            currentShots[i] = new SimpleShot(-100, -100);
        }
    }

    public void update(float delta) {

        this.velocity.x = 0;
        this.velocity.y = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentDirection = LEFT_DIRECTION;
            this.velocity.x = -3;
            this.velocity.y = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentDirection = RIGHT_DIRECTION;
            this.velocity.x = 3;
            this.velocity.y = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentDirection = DOWN_DIRECTION;
            this.velocity.x = 0;
            this.velocity.y = -3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentDirection = UP_DIRECTION;
            this.velocity.x = 0;
            this.velocity.y = 3;
        }

        for (Shot shot : currentShots) {
            if (shot.onScreen()) {
                shot.update(delta);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Shot shotForShoot = null;
            for (Shot shot : currentShots) {
                if (!shot.onScreen()) {
                    shotForShoot = shot;
                    break;
                }
            }

            if (shotForShoot != null) {
                shotForShoot.shoot(this.position.x, this.position.y);
            }
        }

        time += delta;
        if (time > speedTickValues[currentSpeed]) {
            time -= speedTickValues[currentSpeed];
            this.position.add(velocity);
        }
    }

    public void renderShots(SpriteBatch batcher) {
        for (Shot shot : currentShots) {
            if (shot.onScreen()) {
                shot.render(batcher);
            }
        }
    }
}

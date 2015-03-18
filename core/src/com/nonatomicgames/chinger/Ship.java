package com.nonatomicgames.chinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by MandM on 05.03.2015.
 */
public class Ship {

    public static final float SHOT_PAUSE = 0.2f;
    public static final Random rnd = new Random();

    public static final int LEFT_DIRECTION = 0;
    public static final int RIGHT_DIRECTION = 1;
    public static final int DOWN_DIRECTION = 2;
    public static final int UP_DIRECTION = 3;

    public Vector2 position = new Vector2();

    private Vector2 velocity = new Vector2();

    private float timeFromLastShot = 0f;

    private int currentDirection;
    public LinkedList<Shot> shots = new LinkedList<Shot>();

    private float time = 0f;

    public int currentSpeed = 0;
    public float[] speedTickValues = new float[]{0.04f, 0.03f, 0.02f};

    public Rectangle bounds = new Rectangle();

    private Level level;

    public Ship(Level level, float x, float y) {
        this.level = level;

        this.position.x = x;
        this.position.y = y;

        this.velocity.x = 0;
        this.velocity.y = 0;

        this.timeFromLastShot = 0f;
    }

    public void update(float delta) {

        this.velocity.x = 0;
        this.velocity.y = 0;
        this.timeFromLastShot += delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentDirection = LEFT_DIRECTION;
            this.velocity.x = -2;
            this.velocity.y = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentDirection = RIGHT_DIRECTION;
            this.velocity.x = 2;
            this.velocity.y = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentDirection = DOWN_DIRECTION;
            this.velocity.x = 0;
            this.velocity.y = -2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentDirection = UP_DIRECTION;
            this.velocity.x = 0;
            this.velocity.y = 2;
        }

        for (int shotIndex = shots.size() - 1; shotIndex >= 0; shotIndex--) {
            Shot shot = shots.get(shotIndex);
            if (shot.onScreen()) {
                shot.update(delta);
            } else {
                shots.remove(shotIndex);
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && timeFromLastShot >= SHOT_PAUSE) {
            Shot nextShot = null;
            if (rnd.nextBoolean()) {
                nextShot = new SpreadShot();
            } else {
                nextShot = new SimpleShot();
            }

            nextShot.shoot(this.position.x + 24, this.position.y + 12);
            timeFromLastShot = 0f;
            level.shots.add(nextShot);
        }

        this.position.add(velocity.scl(delta/speedTickValues[currentSpeed]));
    }

    public void renderShots(SpriteBatch batcher) {
    }
}

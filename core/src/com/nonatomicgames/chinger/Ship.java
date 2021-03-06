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
    public static final float SUPER_SHOT_PAUSE = 5f;

    public static final Random rnd = new Random();

    public static final int LEFT_DIRECTION = 1;
    public static final int RIGHT_DIRECTION = 2;
    public static final int DOWN_DIRECTION = 4;
    public static final int UP_DIRECTION = 8;

    public float centerX = 12f, centerY = 12f;
    public float boundRadius = 7;

    public Vector2 position = new Vector2();

    private Vector2 velocity = new Vector2();

    private float timeFromLastShot = SHOT_PAUSE;
    private float timeFromLastSuperShot = SUPER_SHOT_PAUSE;

    private int currentDirection;

    public int currentSpeed = 2;
    public float[] speedTickValues = new float[]{0.04f, 0.03f, 0.02f};

    public Rectangle bounds = new Rectangle();

    private Level level;

    private Weapon weapon;
    private Weapon superWeapon;

    public float width, height;

    public Ship(Level level, float x, float y) {
        this.level = level;

        this.position.x = x;
        this.position.y = y;

        this.velocity.x = 0;
        this.velocity.y = 0;

        this.timeFromLastShot = 0f;

        this.width = Assets.shipRegion.getRegionWidth();
        this.height = Assets.shipRegion.getRegionHeight();

        //this.weapon = new SimpleWeapon(level, this);
        this.weapon = new SpreadWeapon(level, this);
        this.superWeapon = new ImpulseWeapon(level, this);
    }

    public void update(float delta) {

        velocity.x = 0;
        velocity.y = 0;
        timeFromLastShot += delta;
        timeFromLastSuperShot += delta;

        int previousDirection = currentDirection;
        currentDirection = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !((RIGHT_DIRECTION & previousDirection) > 0)) {
            currentDirection |= LEFT_DIRECTION;
            velocity.x = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !((LEFT_DIRECTION & previousDirection)>0)) {
            currentDirection |= RIGHT_DIRECTION;
            velocity.x = 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !((UP_DIRECTION & previousDirection)>0)) {
            currentDirection |= DOWN_DIRECTION;
            velocity.y = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && !((DOWN_DIRECTION & previousDirection)>0)) {
            currentDirection |= UP_DIRECTION;
            velocity.y = 1;
        }

        velocity.nor().scl(2);


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && timeFromLastShot >= SHOT_PAUSE) {
            weapon.shoot();
            timeFromLastShot = 0f;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && timeFromLastSuperShot >= SUPER_SHOT_PAUSE) {
            superWeapon.shoot();
            timeFromLastSuperShot = 0f;
        }

        this.position.add(velocity.scl(delta/speedTickValues[currentSpeed]));
    }

}

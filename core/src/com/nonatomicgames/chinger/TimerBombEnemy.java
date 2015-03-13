package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by MandM on 12.03.2015.
 */
public class TimerBombEnemy implements Enemy {

    public static class TimerBombEnemyExplosion implements Explosion {

        private float lifeTime;
        private boolean finished;
        private Vector2 position;

        public TimerBombEnemyExplosion(float x, float y) {
            this.lifeTime = 0f;
            this.finished = false;

            this.position = new Vector2(x, y);
        }

        @Override
        public void update(float delta) {
            lifeTime += delta;
            finished = Assets.timerBombExplosionAnimation.isAnimationFinished(lifeTime);
        }

        @Override
        public void render(SpriteBatch batcher) {
            batcher.draw(
                    Assets.timerBombExplosionAnimation.getKeyFrame(lifeTime, false),
                    this.position.x,
                    this.position.y,
                    24,
                    24
            );
        }

        @Override
        public boolean finished() {
            return this.finished;
        }
    }

    private static final float UPDATING_TIME = 0.02f;

    public static Random rnd = new Random();

    private Level level;

    public Vector2 position;
    public Vector2 direction;

    public float height, width;
    public float ttl;
    public float lifeTime;
    public boolean killed;

    public TimerBombEnemy(Level level, float y) {
        this.level = level;

        this.height = Assets.enemyRegion.getRegionHeight();
        this.width = Assets.enemyRegion.getRegionWidth();

        y = Math.min(y, Constants.WORLD_HEIGHT - this.height);
        this.position = new Vector2(Constants.WORLD_WIDTH, y);
        this.direction = new Vector2(-1f, 0);

        this.ttl = 2 + rnd.nextFloat(); // range from 2 to 3
        this.lifeTime = 0f;
        this.killed = false;
    }

    @Override
    public void update(float delta) {
        this.lifeTime += delta;
        this.ttl -= delta;
        if (this.ttl <= 0) {
            killed = true;
        }

        if (killed) {
            level.addExplosion(new TimerBombEnemyExplosion(this.position.x, this.position.y));
            Shot shot;
            for (int shotAngle = 0; shotAngle < 360; shotAngle += 30) {
                shot = new SimpleShot(shotAngle, true);
                shot.shoot(this.position.x + width / 2, this.position.y + height / 2);
                level.addShot(shot);
            }
        } else {
            this.direction = new Vector2(-2,0);
            this.position.add(direction.scl(delta /UPDATING_TIME));
        }
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(Assets.enemyRegion, this.position.x, this.position.y);
    }

    @Override
    public boolean killed() {
        return this.killed;
    }
}
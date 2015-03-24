package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by MandM on 05.03.2015.
 */
public class Level {

    public static Random rnd = new Random();

    public float lastTimeOfCreatingEnemy = 0f;
    public float nextEnemyIn = 0f;

    public Ship ship;
    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;

    public LinkedList<Enemy> enemies;
    public LinkedList<Explosion> explosions;
    public LinkedList<Shot> shots;
    public Shot superShot;

    public Level(SpriteBatch batcher, ShapeRenderer shapeRenderer, int number) {
        this.ship = new Ship(this, 0, 0);
        this.batcher = batcher;
        this.shapeRenderer = shapeRenderer;

        initEnemies();

        this.explosions = new LinkedList<Explosion>();
        this.shots = new LinkedList<Shot>();
    }

    private float generateTimeToNextEnemy() {
        return 0.2f;// + rnd.nextInt(4);
    }

    private void initEnemies() {
        this.nextEnemyIn = generateTimeToNextEnemy();
        this.enemies = new LinkedList<Enemy>();
    }

    public void addExplosion(Explosion explosion) {
        this.explosions.add(explosion);
    }

    public void addShot(Shot shot) {
        this.shots.add(shot);
    }

    public void addSuperShot(Shot shot) { this.superShot = shot; }

    public void update(float delta) {
        Enemy enemy;
        Explosion explosion;
        Shot shot;

        for (int explosionIndex = explosions.size() - 1; explosionIndex >= 0; explosionIndex--) {
            explosion = explosions.get(explosionIndex);
            if (explosion.finished()) {
                explosions.remove(explosionIndex);
            } else {
                explosion.update(delta);
            }
        }

        for (int enemyIndex = enemies.size() - 1; enemyIndex >= 0; enemyIndex--) {
            enemy = enemies.get(enemyIndex);
            if (enemy.killed()) {
                enemies.remove(enemyIndex);
            } else {
                enemy.update(delta);
            }
        }

        for (int shotIndex = shots.size() - 1; shotIndex >= 0; shotIndex--) {
            shot = shots.get(shotIndex);
            if (shot.onScreen()) {
                shot.update(delta);
            } else {
                shots.remove(shotIndex);
            }
        }

        this.lastTimeOfCreatingEnemy += delta;
        if (this.lastTimeOfCreatingEnemy > this.nextEnemyIn) {
            switch (rnd.nextInt()%4) {
                case 0: {
                    this.enemies.add(EnemyFactory.getTimerBombEnemy(this));
                    break;
                }
                case 1: {
                    this.enemies.add(EnemyFactory.getSinEnemy(this));
                    break;
                }
                case 2: {
                    this.enemies.add(EnemyFactory.getUpDiagonalEnemy(this));
                    break;
                }
                case 3: {
                    this.enemies.add(EnemyFactory.getDownDiagonalEnemy(this));
                    break;
                }
            }
            this.lastTimeOfCreatingEnemy = 0f;
        }

        this.ship.update(delta);
    }

    public void render() {


        batcher.begin();

        for (Enemy enemy : enemies) {
            enemy.render(batcher);
        }

        superShot.render(batcher);

//        batcher.draw(
//                Assets.laserRegion,
//                ship.position.x + ship.width,
//                ship.position.y + ship.height/2 - 4,
//                Constants.WORLD_WIDTH - ship.position.x + ship.width,
//                8
//        );

        batcher.draw(Assets.shipRegion, ship.position.x, ship.position.y);

        for (Shot shot : shots) {
            shot.render(batcher);
        }

        for (Explosion explosion : explosions) {
            explosion.render(batcher);
        }

        batcher.end();
    }


}

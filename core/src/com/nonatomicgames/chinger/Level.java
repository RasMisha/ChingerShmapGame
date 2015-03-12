package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by MandM on 05.03.2015.
 */
public class Level {

    public static Random rnd = new Random();

    public float lastTimeOfCreatingEnemy = 0f;
    public float nextEnemyIn = 0f;

    private Ship ship;
    private SpriteBatch batcher;

    public LinkedList<Enemy> enemies;

    public Level(SpriteBatch batcher, int number) {
        this.ship = new Ship(0, 0);
        this.batcher = batcher;

        initEnemies();
    }

    private float generateTimeToNextEnemy() {
        return 3 + rnd.nextInt(4);
    }

    private void initEnemies() {
        this.nextEnemyIn = generateTimeToNextEnemy();
        this.enemies = new LinkedList<Enemy>();
    }


    public void update(float delta) {
        Enemy enemy;
        for (int enemyIndex = enemies.size() - 1; enemyIndex >= 0; enemyIndex--) {
            enemy = enemies.get(enemyIndex);
            if (enemy.killed()) {
                enemies.remove(enemyIndex);
            } else {
                enemy.update(delta);
            }
        }

        this.lastTimeOfCreatingEnemy += delta;
        if (this.lastTimeOfCreatingEnemy > this.nextEnemyIn) {
            this.enemies.add(EnemyFactory.getTimerBombEnemy(this));
            this.lastTimeOfCreatingEnemy = 0f;
        }

        this.ship.update(delta);
    }

    public void render() {
        batcher.begin();

        for (Enemy enemy : enemies) {
            enemy.render(batcher);
        }

        batcher.draw(Assets.shipRegion, ship.position.x, ship.position.y);
        ship.renderShots(batcher);

        batcher.end();
    }

}

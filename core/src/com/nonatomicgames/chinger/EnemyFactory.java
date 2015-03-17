package com.nonatomicgames.chinger;

import java.util.Random;

/**
 * Created by MandM on 12.03.2015.
 */
public class EnemyFactory {

    private static Random rnd = new Random();

    public static Enemy getTimerBombEnemy(Level level) {
        return getTimerBombEnemy(level, rnd.nextFloat()*Constants.WORLD_HEIGHT);
    }

    public static Enemy getTimerBombEnemy(Level level, float y) {
        return new TimerBombEnemy(level, y);
    }

    public static Enemy getSinEnemy(Level level) {
        return getSinEnemy(level, rnd.nextFloat() * Constants.WORLD_HEIGHT);
    }

    public static Enemy getSinEnemy(Level level, float y) {
        return new SinEnemy(level, y);
    }

    public static Enemy getDownDiagonalEnemy(Level level) {
        return getDownDiagonalEnemy(level, Constants.WORLD_WIDTH, 0);
    }

    public static Enemy getDownDiagonalEnemy(Level level, float x, float y) {
        return new DiagonalEnemy(level, x, y, DiagonalEnemy.DOWN_DIRECTION);
    }


    public static Enemy getUpDiagonalEnemy(Level level) {
        return getUpDiagonalEnemy(level, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
    }

    public static Enemy getUpDiagonalEnemy(Level level, float x, float y) {
        return new DiagonalEnemy(level, x, y, DiagonalEnemy.UP_DIRECTION);
    }
}

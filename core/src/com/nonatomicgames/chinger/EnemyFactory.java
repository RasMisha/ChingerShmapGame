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
}

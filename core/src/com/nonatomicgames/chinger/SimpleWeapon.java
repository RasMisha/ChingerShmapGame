package com.nonatomicgames.chinger;

import com.nonatomicgames.chinger.Level;
import com.nonatomicgames.chinger.Weapon;

/**
 * Created by MandM on 21.03.2015.
 */
public class SimpleWeapon implements Weapon {

    private static boolean SIMPLE_WEAPON_ACTIVE_STATE = true;

    private Level level;
    private Ship ship;

    public SimpleWeapon(Level level, Ship ship) {
        this.level = level;
        this.ship = ship;
    }

    @Override
    public boolean active() {
        // always active
        return SIMPLE_WEAPON_ACTIVE_STATE;
    }

    @Override
    public void shoot() {
        Shot newShot = new SimpleShot();
        newShot.shoot(ship.position.x + 24, ship.position.y + 12);
        level.shots.add(newShot);
    }
}

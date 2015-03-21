package com.nonatomicgames.chinger;

/**
 * Created by MandM on 21.03.2015.
 */
public class SpreadWeapon implements Weapon {

    private boolean active;
    private Level level;
    private Ship ship;

    public SpreadWeapon(Level level, Ship ship) {
        this.level = level;
        this.ship = ship;
    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public void shoot() {
        Shot newShot;
        float angle = -30;
        for (int shotIndex = 0; shotIndex < 5; shotIndex++) {
            newShot = new SimpleShot(angle, false);
            newShot.shoot(ship.position.x + 24, ship.position.y + 12);
            level.shots.add(newShot);
            angle += 15;
        }
    }

    @Override
    public void found() {
        active = true;
    }

    @Override
    public void lost() {
        active = false;
    }


}

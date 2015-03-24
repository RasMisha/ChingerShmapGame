package com.nonatomicgames.chinger;

/**
 * Created by MandM on 22.03.2015.
 */
public class ImpulseWeapon implements Weapon {

    private Level level;
    private boolean active;
    private int numberOfBullets;
    private Ship ship;

    public ImpulseWeapon(Level level, Ship ship) {
        this.level = level;
        this.ship = ship;
        this.numberOfBullets = 3;
    }

    @Override
    public boolean active() {
        return numberOfBullets > 0;
    }

    @Override
    public void shoot() {
        if (numberOfBullets > 0) {
            numberOfBullets--;
            Shot newShot = new ImpulseShot(ship);
            newShot.shoot(0f,0f);
            level.addSuperShot(newShot);
        }
    }

    @Override
    public void found() {
        numberOfBullets++;
    }

    @Override
    public void lost() {/*nothing*/}
}

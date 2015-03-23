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
        this.numberOfBullets = 0;
    }

    @Override
    public boolean active() {
        return numberOfBullets > 0;
    }

    @Override
    public void shoot() {
    }

    @Override
    public void found() {
        numberOfBullets++;
    }

    @Override
    public void lost() {/*nothing*/}
}

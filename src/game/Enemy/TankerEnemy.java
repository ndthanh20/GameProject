package game.enemy;

import java.io.FileNotFoundException;

public class TankerEnemy extends Enemy {
    public TankerEnemy(int healthPoints) throws FileNotFoundException {
        super(healthPoints);
        setSpeed(1);
        setValue(5);
        setArmor(5);
    }

    @Override
    public void setSpeed(int speed) {
        this.speed=speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setValue(int value) {
        this.value=value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setArmor(int armor) {
        this.armor=armor;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public void setImageView() throws FileNotFoundException {

    }
}

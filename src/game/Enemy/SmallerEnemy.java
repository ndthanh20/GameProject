package game.enemy;

import java.io.FileNotFoundException;

public class SmallerEnemy extends Enemy{
    public SmallerEnemy(int healthPoints) throws FileNotFoundException {
        super(healthPoints);

        setSpeed(3);
        setValue(1);
        setArmor(1);
    }

    public void setArmor(int armor) {
        this.armor=armor;
    }

    public int getArmor(){
        return armor;
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


}

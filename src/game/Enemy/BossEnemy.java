package game.enemy;

import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class BossEnemy extends Enemy {
    public BossEnemy(int healthPoints) throws FileNotFoundException {
        super(healthPoints);
        setSpeed(2);
        setValue(10);
        setArmor(10);
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


}

package game.enemy;

import game.game_entity.Spawner;
import game.game_entity.Target;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TankerEnemy extends Enemy {
    private static String url= new String("C:\\Users\\ndtha\\GameProject\\src\\game\\images\\Enemy3.png");
    public TankerEnemy(int healthPoints) throws FileNotFoundException {
        super(healthPoints);
        setSpeed(1);
        setValue(5);
        setArmor(5);
        setImageView();
        Spawner spawner = new Spawner();
        spawnerC= Spawner.getSpawner();


        Target target = new Target();

        targetC = target.getTarget();

        enemyImage.setX(spawnerC.getX());
        enemyImage.setY(spawnerC.getY());

        setHealthBar(new Rectangle(60,10, Color.GREEN));
    }
    @Override
    public ImageView getImageView() {
        return enemyImage;
    }

    @Override
    public void drawHealthBar() {
        this.setHealthBar(this.getHealthPoints());
    }

    @Override
    public void setImageView() throws FileNotFoundException {
        Image image = new Image(new FileInputStream(url));
        enemyImage = new ImageView(image);
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
    public void setHealthPoints(int t) {
        healthPoints+=t;
    }


}

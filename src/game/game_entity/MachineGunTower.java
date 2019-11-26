package game.game_entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MachineGunTower extends Tower {

    private final String url= new String("C:\\Users\\ndtha\\GameProject\\src\\game\\images\\Tower3.PNG");


    public MachineGunTower(double x, double y) throws FileNotFoundException {
        super(x, y);
        setDamage(15);
        setShootRange(150);
        setShootSpeed(60);
        setImageView();
        setCost(40);
        towerImageView.setX(x);
        towerImageView.setY(y);
        towerImageView.setFitHeight(100);
        towerImageView.setFitWidth(100);
    }

    @Override
    public ImageView getImageView() {
        return towerImageView;
    }

    @Override
    public void setImageView() throws FileNotFoundException {
        Image image = new Image(new FileInputStream(url));
        towerImageView = new ImageView(image);
    }

    @Override
    public void setShootRange(int shootRange) {
        this.shootRange=shootRange;
    }

    @Override
    public int getShootRange() {
        return shootRange;
    }

    @Override
    public void setDamage(int damage) {
        this.damage=damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setShootSpeed(int shootSpeed) {
        this.shootSpeed=shootSpeed;
    }

    @Override
    public int getShootSpeed() {
        return shootSpeed;
    }

    @Override
    public void setCost(int cost) {
        this.cost=cost;
    }

    @Override
    public int getCost() {
        return cost;
    }
}

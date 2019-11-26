package game.enemy;

import game.Coordinate;
import game.GameEntity;

import game.game_entity.Spawner;
import game.game_entity.Target;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Enemy extends GameEntity {
    private int[][] path;
    private static String url= new String("C:\\Users\\ndtha\\GameProject\\src\\game\\Circle.PNG");

    private boolean moveX = true;
    private boolean upY = true;
    private boolean pathFinished = false;

    private int healthPoints;                   // Determines if the monster is still alive
    private boolean isDead;
    private boolean isPathFinished;

    protected int speed;
    protected int value;
    protected int armor;
    protected ImageView enemyImage;

    private Coordinate spawnerC;
    private Coordinate targetC;

    private Rectangle healthBar;

    public Enemy(int healthPoints) throws FileNotFoundException {

        this.healthPoints = healthPoints;
        isDead = false;
        isPathFinished = false;

        Spawner spawner = new Spawner();
        spawnerC= Spawner.getSpawner();


        Target target = new Target();

        targetC = target.getTarget();


        setImageView();

        enemyImage.setX(spawnerC.getX());
        enemyImage.setY(spawnerC.getY());

        setHealthBar(new Rectangle(30,10, Color.GREEN));

    }

    public void setImageView() throws FileNotFoundException {
        Image image2 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\game\\Circle.PNG"));
        enemyImage = new ImageView(image2);

    }

    public ImageView getImageView() {
        return enemyImage;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }

    public double getCenterX() {
        return enemyImage.getX() + 50;
    }

    public double getCenterY() {
        return enemyImage.getY() + 50;
    }

    public void updateLocation() {
        if (moveX) {
            enemyImage.setX(enemyImage.getX() + 1);
            int X = (int) (enemyImage.getX());
            int Y = (int) (enemyImage.getY());
            if ((X + 1) % 100 == 1)
                if (path[Y / 100][((X + 1) / 100) + 1] != 1) {

                    moveX = false;
                    if (path[(Y / 100) + 1][(X + 1) / 100] == 1) {
                        upY = false;
                    }
                    if (path[(Y / 100) - 1][(X + 1) / 100] == 1) {
                        upY = true;
                    }
                    if (path[(Y / 100) + 1][(X + 1) / 100] != 1 && path[(Y / 100) - 1][(X + 1) / 100] != 1) {
                        isPathFinished = true;
                    }

                }

        } else {
            if (upY) {
                enemyImage.setY(enemyImage.getY() - 1);
                int X = (int) (enemyImage.getX());
                int Y = (int) (enemyImage.getY());
                if ((Y - 1) % 100 != 0)
                    if (path[(Y - 1) / 100][X / 100] != 1) {
                        moveX = true;
                    }

            } else {
                enemyImage.setY(enemyImage.getY() + 1);
                int X = (int) (enemyImage.getX());
                int Y = (int) (enemyImage.getY());
                if ((Y + 1) % 100 != 0)
                    if (path[((Y + 1) / 100) + 1][X / 100] != 1) {
                        moveX = true;
                        //System.out.println(enemyImage.getX()+" "+enemyImage.getY());
                    }
            }
        }
        if (enemyImage.getX() == 950) pathFinished = true;
        setXYHealthBar();

    }

    public boolean getPathFinished() {
        return pathFinished;
    }


    public void setHealthBar(Rectangle rectangle) {
        this.healthBar = rectangle;
    }

    public void setHealthBar(double width) {
        this.healthBar.setWidth(width);
    }

    public Rectangle getHealthBar() {
        return this.healthBar;
    }

    public void setXYHealthBar() {
        if (this.getHealthPoints() > 0) {
            ;
            healthBar.setX(enemyImage.getX());
            healthBar.setY(enemyImage.getY() - 20);
            return;
        }
    }

    public void drawHealthBar() {

        if (this.getHealthPoints() == 1) {
            this.setHealthBar(10);
            return;
        }
        if (this.getHealthPoints() == 2) {
            this.setHealthBar(20);
            return;
        }
    }

    public abstract void setSpeed(int speed);

    public abstract int getSpeed();

    public abstract void setValue(int value);

    public abstract int getValue();

    public abstract void setArmor(int armor);

    public abstract int getArmor();


    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
        if (healthPoints <= 0) {
            isDead = true;
            pathFinished = false;
        }

    }


    public boolean isDead() {
        //System.out.println(healthPoints);
        return isDead;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean isPathFinished() {
        return isPathFinished;
    }


}

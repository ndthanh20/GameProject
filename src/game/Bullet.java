package game;
import game.enemy.Enemy;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.FileNotFoundException;

public class Bullet extends GameEntity {
    private Enemy target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;
    private ImageView bulletView;
    private Circle circle;

    public Bullet(Enemy target , int towerX , int towerY){
        this.target = target;
        startX = towerX;
        startY = towerY;
        circle = new Circle(startX,startY,12, Color.BLUE);
    }


    public Enemy getTarget(){
        return target;
    }

    public int getEndX(){
        return (int) target.getCenterX();
    }

    public int getEndY(){
        return (int) target.getCenterY();
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }


    @Override
    public void setImageView()  {

    }

    @Override
    public ImageView getImageView() {
        return bulletView;
    }

    public Circle getCircle(){
        return  circle;
    }
}
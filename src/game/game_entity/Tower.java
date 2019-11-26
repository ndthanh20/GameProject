package game.game_entity;

import game.Bullet;
import game.GameTile;
import game.enemy.Enemy;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Tower extends GameTile {
    protected ImageView towerImageView;
    private ArrayList<Bullet> bulletList;
    private int time=0;
    private double x;
    private double y;
    protected int shootRange;
    protected int damage;
    protected int shootSpeed;
    protected int cost;


    public abstract void setImageView() throws FileNotFoundException;

    public abstract void setShootRange(int shootRange);
    public abstract int getShootRange();

    public abstract void setDamage(int damage);
    public abstract int getDamage();

    public abstract void setShootSpeed(int shootSpeed);
    public abstract int getShootSpeed();

    public abstract void setCost(int cost);
    public abstract int getCost();

    public Tower(double x, double y){
        this.x=x+50;
        this.y=y+50;
        bulletList= new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBulletList(){
        return bulletList;
    }

    public void creatBullet(Enemy enemy){
        bulletList.add(new Bullet(enemy,(int)x,(int) y));
        //bulletList.add(new BulletLine(enemy,(int) x,(int) y,Color.BLUE));
    }

    public void updateTime(){
        time++;
    }
    public int getTime(){
        return time;
    }
    public boolean isShot(Enemy enemy){
        int towerMinXRange = (int) x - shootRange;
        int towerMaxXRange = (int) x + shootRange;
        int towerMinYRange = (int) y - shootRange;
        int towerMaxYRange = (int) y + shootRange;

        if (enemy.getCenterX() > towerMinXRange
                & enemy.getCenterX() < towerMaxXRange
                & enemy.getCenterY() > towerMinYRange
                & enemy.getCenterY() < towerMaxYRange)
            return true;
        else return  false;
    }

}

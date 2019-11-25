import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Tower extends GameTile {
    private ImageView towerImageView;
    private ArrayList<Bullet> bulletList;
    private int time=0;

    public ImageView getTowerImageView(){
        return this.towerImageView;
    }

    public void setTowerImageView(ImageView towerImageView){
        this.towerImageView=towerImageView;
    }

    private double x;
    private double y;

    public Tower(double x, double y){
        this.x=x;
        this.y=y;
        bulletList= new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBulletList(){
        return bulletList;
    }

    public void creatBullet(Enemy enemy){
        //bulletList.add(new Bullet(enemy,(int)x,(int)y, Color.BLACK));
        bulletList.add(new BulletLine(enemy,(int) x,(int) y,Color.BLUE));
    }
    public double getExectX(){
        return x+50;
    }
    public double getExactY(){
        return  y+50;
    }
    public void updateTime(){
        time++;
    }
    public int getTime(){
        return time;
    }
    public boolean isShot(Enemy enemy){
        int towerMinXRange = (int) getExectX() - 150;
        int towerMaxXRange = (int) getExectX() + 150;
        int towerMinYRange = (int) getExactY() - 150;
        int towerMaxYRange = (int) getExactY() + 150;


        if (enemy.getCenterX() > towerMinXRange
                & enemy.getCenterX() < towerMaxXRange
                & enemy.getCenterY() > towerMinYRange
                & enemy.getCenterY() < towerMaxYRange)
            return true;
        else return  false;
    }
}

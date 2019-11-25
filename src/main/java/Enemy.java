import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.*;

public class Enemy extends GameEntity {
    private int[][] path;
    private boolean moveX= true;
    private boolean upY=true;
    private boolean pathFinished=false;
    private ImageView enemyImage;
    private int healthPoints;                   // Determines if the monster is still alive
    private boolean isDead;
    private boolean isPathFinished;
    private boolean isAttached = false;
    private Rectangle healthBar ;

    public Enemy(int healthPoints){
        this.healthPoints=healthPoints;
        isDead=false;
        isPathFinished=false;
    }
    public void setPath(int[][] path){
        this.path=path;
    }
    public void setEnemyImage(ImageView enemyImage){
        this.enemyImage=enemyImage;
    }
    public ImageView getEnemyImage(){
        return enemyImage;
    }
    public boolean getPathFinished(){
        return pathFinished;
    }
    public void setHealthBar(Rectangle rectangle){
        this.healthBar = rectangle;
    }
    public void setHealthBar(double widht){
        this.healthBar.setWidth(widht);
    }
    public Rectangle getHealthBar(){
        return this.healthBar;
    }
    public double getCenterX(){
        return enemyImage.getX()+50;
    }
    public double getCenterY(){
        return enemyImage.getY()+50;
    }

    public void updateLocation(){
        if(moveX){
            enemyImage.setX(enemyImage.getX()+1);
            int X=(int) (enemyImage.getX());
            int Y=(int) (enemyImage.getY());
            if((X+1)%100== 1 )
                if(path[Y /100][((X+1)/100) +1] != 1)
                {

                    moveX=false;
                    if(path[(Y/100) +1][(X+1) /100]==1)
                    {
                        upY=false;
                    }
                    if(path[(Y/100) -1][(X+1) /100]==1)
                    {
                        upY=true;
                    }
                    if(path[(Y/100) +1][(X+1) /100]!=1&&path[(Y/100) -1][(X+1) /100]!=1){
                        isPathFinished=true;
                    }

                }
            if(enemyImage.getX()==1100) pathFinished=true;
        }
        else{
            if(upY){
                enemyImage.setY(enemyImage.getY()-1);
                int X=(int) (enemyImage.getX());
                int Y=(int) (enemyImage.getY());
                if((Y-1)%100!=0)
                    if(path[(Y-1) /100][X /100] !=1){
                        moveX=true;
                    }

            }
            else{
                enemyImage.setY(enemyImage.getY()+1);
                int X=(int) (enemyImage.getX());
                int Y=(int) (enemyImage.getY());
                if((Y+1)%100!=0)
                    if(path[((Y+1)/100) +1][X /100]!= 1)
                    {
                        moveX=true;
                        //System.out.println(enemyImage.getX()+" "+enemyImage.getY());
                    }
            }
        }
        setXYHealthBar();

    }
    public void takeDamage(int damage){
        healthPoints = healthPoints - damage;
        isAttached = true;
        if (healthPoints == 0){
            isDead = true;
            pathFinished = false;
        }

    }
    public void setXYHealthBar(){
        if (this.getHealthPoints() > 0  ) { ;
            healthBar.setX(enemyImage.getX());
            healthBar.setY(enemyImage.getY() - 20);
            return;
        }
    }
    public void drawHealthBar(){
        if (this.getHealthPoints() == 1){
            this.setHealthBar(10);
            return;
        }
        if (this.getHealthPoints() == 2) {
            this.setHealthBar(20);
            return;
        }
    }


    public boolean isDead(){
        //System.out.println(healthPoints);
        return isDead;
    }

    public int getHealthPoints(){
        return healthPoints;
    }
    public boolean isPathFinished(){
        return isPathFinished;
    }

}

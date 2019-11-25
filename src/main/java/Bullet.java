import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Circle {
    private Enemy target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;


    public Bullet(Enemy target , int towerX , int towerY , Color color){
        super(towerX , towerY , 15, color);
        this.target = target;
        startX = towerX;
        startY = towerY;
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



}
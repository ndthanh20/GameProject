import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class BulletLine extends Line {
    private Enemy target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;
    private Line line;
    private Circle circle;


    public BulletLine(Enemy target , int towerX , int towerY , Color color){
        super(towerX,towerY,target.getCenterX(),target.getCenterY());

        this.target = target;
        startX = towerX;
        startY = towerY;

    }

    public BulletLine(Enemy enemy, int x, int y) {
    }

    public Enemy getTarget(){
        return target;
    }

    public int getEndx(){
        return (int) target.getCenterX();
    }

    public int getEndy(){
        return (int) target.getCenterY();
    }

    public int getstartX(){
        return startX;
    }

    public int getstartY(){
        return startY;
    }



}


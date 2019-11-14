import javafx.animation.AnimationTimer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class GameController extends AnimationTimer {
    public void handle(long now) {

        /*Main main = new Main();

        ImageView target = main.getImageView2();
        int[][] map = main.getMap();

        if(true) {
            stop();
            //System.out.print(target.getX()+" "+target.getY());

        }

        if(checkPosi(target.getX()+1,target.getY(),map))
            {
                target.setX(target.getX()+1);
                main.setImageView2(target);
            }
        else
            if(checkPosi(target.getX()-1,target.getY(),map)){
                target.setX(target.getX()-1);
                main.setImageView2(target);
            }
            else
                if(checkPosi(target.getX(),target.getY()+1,map)){
                    target.setY(target.getY()+1);
                    main.setImageView2(target);
                }
                else
                    if(checkPosi(target.getX(),target.getY()-1,map)){
                        target.setY(target.getY()-1);
                        main.setImageView2(target);
                    }
        //if(target.getX()>1200||target.getY()>1100) stop();

    }
    public boolean checkPosi(double x,double y,int[][] map){
        int column= (int) (x/100);
        int row= (int) (y/100);
        if(column>12||row>10) return false;
        if(column<0||row<0) return false;
        if(map[row][column]==1) return true;

        return false;
    }
    */
    }
}

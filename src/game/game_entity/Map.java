package game.game_entity;

import game.GameTile;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Map extends GameTile {

    protected int[][] map;
    private ImageView mapView;

    public int[][] getMap(){
        return map;
    }
    public Map()  {
        map = new int[][]{
                {0, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0},
                {2, 1, 1, 1, 2, 1, 1, 1, 1, 0, 0, 0, 0},
                {2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 0, 0, 0},
                {2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 0, 0, 0},
                {2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 0, 0, 0},
                {2, 1, 2, 1, 1, 1, 2, 2, 1, 2, 0, 0, 0},
                {2, 1, 2, 2, 2, 2, 0, 2, 1, 2, 0, 0, 0},
                {2, 1, 2, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1},
                {1, 1, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2},
                {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

        };
    }


    @Override
    public void setImageView() throws FileNotFoundException {

    }

    @Override
    public javafx.scene.image.ImageView getImageView() {
        return null;
    }
}

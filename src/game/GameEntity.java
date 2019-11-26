package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public abstract class GameEntity {
    private ImageView imageView;

    private Coordinate coordinate;

    public GameEntity(){

        coordinate = new Coordinate(0,0);

    }

    public abstract void setImageView() throws FileNotFoundException;
    public abstract ImageView getImageView();

    public Coordinate getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(Coordinate coordinate){
        this.coordinate=coordinate;
    }
}

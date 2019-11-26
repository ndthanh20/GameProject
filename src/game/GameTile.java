package game;

import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public abstract class GameTile extends GameEntity {
    @Override
    public abstract void setImageView() throws FileNotFoundException;

    @Override
    public abstract ImageView getImageView() ;
}

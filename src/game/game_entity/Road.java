package game.game_entity;

import game.GameTile;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Road extends Map {

    @Override
    public void setImageView() {

    }

    @Override
    public ImageView getImageView() {
        return null;
    }

    public void loadRoad(Group group) throws FileNotFoundException {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 11; j++)
                if (map[i][j] == 1) {
                    Image image3 = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\GravelTile.png"));
                    javafx.scene.image.ImageView imageView3 = new javafx.scene.image.ImageView(image3);

                    imageView3.setX(j * 100);
                    imageView3.setY(i * 100);
                    group.getChildren().addAll(imageView3);
                }
        }
    }
}

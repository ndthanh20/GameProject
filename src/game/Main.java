package game;

import game.game_entity.Tower;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.util.Duration;
import sun.plugin.javascript.navig.Anchor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
    private int[][] map;
    private Group group = new Group();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private MediaPlayer mediaPlayer;
    private GameField gameField = new GameField();

    public Main() throws FileNotFoundException {
    }

    public void start(Stage primaryStage) throws Exception {


        /** Initialize path for enemy
         *
         */

        Scene scene = new Scene(group);
        //basic sniper//


        /*scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageViewSelect.setX(event.getX()- (event.getX()%100));
                imageViewSelect.setY(event.getY()-(event.getY()%100));
                imageViewSelect.setFitHeight(100);
                imageViewSelect.setFitWidth(100);
                group.getChildren().add(imageViewSelect);
            }

        });*/


        gameField.startGame();
        playMusic();

        primaryStage.setScene(gameField.getGameScene());


        primaryStage.setResizable(true);
        primaryStage.setTitle("Tower Defense");

        primaryStage.setHeight(1050);
        primaryStage.setWidth(1312);
        primaryStage.show();

        GameStage.setStage(primaryStage);

    }
    public void playMusic(){

        String path = "D:\\Java-game\\GameRepository\\src\\game\\Sound\\Sound.mp3";

        Media media = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}
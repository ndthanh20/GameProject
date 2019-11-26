package game;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameStage {
    private static Stage gameStage;
    private static int gameLevel=0;
    private static int playerHealth = 5;
    public static void setStage(Stage stage){
        gameStage=stage;
    }

    public static int getPlayerHealth() {
        return playerHealth;
    }

    public static void setPlayerHealth(int playerHealth) {
        GameStage.playerHealth = playerHealth;
    }

    public static Stage getStage(){
        return gameStage;
    }
    public static void setGameLevel(int i){
        gameLevel+=i;
    }
    public static int getGameLevel(){
        return gameLevel;
    }
}

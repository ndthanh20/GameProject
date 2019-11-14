
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
import sun.plugin.javascript.navig.Anchor;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Main extends Application {
    private Enemy enemy = new Enemy();
    private int[][] map;
    public void start(Stage primaryStage) throws Exception {
        GameController gameController = new GameController();
        map = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,1,1,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {1,1,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0}
        };


        final ImageView temp = new ImageView();
        Image image = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\Background.PNG"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(1000);
        imageView.setFitWidth(1200);

        Image image2 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\java\\Circle.PNG"));

        enemy.setEnemyImage(new ImageView(image2));

        enemy.getEnemyImage().setY(500);


        //Path path = new Path();

        //path.
        final Group group = new Group(imageView);

        /** Initialize path for enemy
         *
         */
        for(int i=0; i<=9; i++){
            for(int j=0; j<=11; j++)
                if(map[i][j]==1)
                {
                    Image image3 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\GravelTile.png"));
                    ImageView imageView3 = new ImageView(image3);

                    imageView3.setX(j*100);
                    imageView3.setY(i*100);
                    group.getChildren().addAll(imageView3);
                }
            System.out.println();
        }
        enemy.setPath(map);
        group.getChildren().addAll(enemy.getEnemyImage());

        Scene scene = new Scene(group);
        //basic sniper//

        final Image imageBasicSniper = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\BasicTowerGraphic.png"));
        final ImageView imageViewBasicSniper = new ImageView(imageBasicSniper);
        imageViewBasicSniper.setFitHeight(130);
        imageViewBasicSniper.setFitWidth(80);
        imageViewBasicSniper.setX(910);
        imageViewBasicSniper.setY(130);
        group.getChildren().addAll(imageViewBasicSniper);



        //drag and drop

            imageViewBasicSniper.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dragboard db = imageViewBasicSniper.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(imageBasicSniper);
                    db.setContent(content);
                    event.consume();
                }
            });



        imageView.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    if ( db.hasImage()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }

                    event.consume();
                }
            });
        imageView.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        temp.setImage(imageViewBasicSniper.getImage());
                        temp.setX(event.getX());
                        temp.setY(event.getY());
                        group.getChildren().addAll(temp);
                        success = true;
                    }
                    event.setDropCompleted(success);
                    System.out.println("dropped");
                    event.consume();
                }
            });
        imageView.setOnDragEntered( (DragEvent event) ->{
            if (event.getGestureSource() != temp && event.getDragboard().hasImage()){
                temp.setImage(imageViewBasicSniper.getImage());
            }
            event.consume();
        });



        primaryStage.setResizable(true);
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(scene);
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1200);
        primaryStage.show();

        System.out.println(primaryStage.getWidth() + " " + primaryStage.getHeight());


        //gameController.start();
        new AnimationTimer() {

            public void handle(long now) {
                if(!enemy.getPathFinished())
                enemy.updateLocation();
            }
        }.start();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
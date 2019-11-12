import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        GameController gameController = new GameController();

        int[][] map = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,1,1,1,0,0,0,0},
                {0,0,1,0,0,0,0,1,0,0,0,0},
                {1,1,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0}
        };



        Image image = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\java\\Background.jpg"));
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(1000);
        imageView.setFitWidth(1200);

        Image image2 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\java\\Circle.png"));

        ImageView imageView2 = new ImageView(image2);

        //imageView2.setX(300);
        imageView2.setY(530);


        //Path path = new Path();

        //path.

        Group group = new Group(imageView);
        int x=0;

        for(int i=0; i<=9; i++){
            for(int j=0; j<=11; j++)
            if(map[i][j]==1)
            {
                Image image3 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\java\\Path.png"));
                ImageView imageView3 = new ImageView(image3);

                imageView3.setX(j*100);
                imageView3.setY(i*100);
                group.getChildren().addAll(imageView3);
            }
            System.out.println();
        }

        Scene scene = new Scene(group);

        primaryStage.setResizable(true);
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(primaryStage.getWidth()+" "+primaryStage.getHeight());


        gameController.start();
    }
    public static void main(String[] args){
        Application.launch();
    }
}

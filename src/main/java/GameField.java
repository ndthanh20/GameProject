import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameField {
    //private Enemy enemy = new Enemy(3);
    private ArrayList<Tower> towers = new ArrayList<>();
    private Scene gameScene;
    private Group group = new Group();
    private Map map = new Map();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public GameField() throws FileNotFoundException {

    }

    public void loadMap() throws FileNotFoundException {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 11; j++)
                if (map.getMap()[i][j] == 1) {
                    Image image3 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\GravelTile.png"));
                    javafx.scene.image.ImageView imageView3 = new javafx.scene.image.ImageView(image3);

                    imageView3.setX(j * 100);
                    imageView3.setY(i * 100);
                    group.getChildren().addAll(imageView3);
                }
        }
    }

    public void initialize() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\Background.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(1000);
        imageView.setFitWidth(1312);

        group = new Group(imageView);

        loadMap();


        Image imageBasicSniper = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\BasicTowerGraphic.png"));
        ImageView imageViewBasicSniper = new ImageView(imageBasicSniper);
        imageViewBasicSniper.setX(955);
        imageViewBasicSniper.setY(140);
        imageViewBasicSniper.setFitHeight(100);
        imageViewBasicSniper.setFitWidth(100);
        group.getChildren().addAll(imageViewBasicSniper);


        //image tower 2 //
        Image imageTower2 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\SniperTowerGraphic.png"));
        ImageView imageViewTower2 = new ImageView(imageTower2);
        imageViewTower2.setX(1140);
        imageViewTower2.setY(140);
        imageViewTower2.setFitHeight(100);
        imageViewTower2.setFitWidth(100);
        group.getChildren().addAll(imageViewTower2);

        //image tower3
        Image imageTower3 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\FreezeTowerGraphic.png"));
        ImageView imageViewTower3 = new ImageView(imageTower3);
        imageViewTower3.setX(956);
        imageViewTower3.setY(297);
        imageViewTower3.setFitHeight(100);
        imageViewTower3.setFitWidth(100);
        group.getChildren().addAll(imageViewTower3);

        //handle drag and drop//
        imageViewBasicSniper.setOnMousePressed(event -> dragAndDrop(imageViewBasicSniper, imageView, group));
        imageViewTower2.setOnMousePressed(event -> dragAndDrop(imageViewTower2, imageView, group));
        imageViewTower3.setOnMousePressed(event -> dragAndDrop(imageViewTower3, imageView, group));

        //initEnemy();

        gameScene = new Scene(group);
    }

    public void creatMonster(int health) throws FileNotFoundException {
        Enemy enemy = new Enemy(health);
        enemies.add(enemy);
        initEnemy(enemy);

    }

    //drag and drop
    public void dragAndDrop(final ImageView source, final ImageView target, Group group) {
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();

            content.putImage(source.getImage());
            db.setContent(content);
            event.consume();
        });

        ImageView temp = new ImageView();
        target.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                temp.setImage(source.getImage());
                temp.setX(event.getX() - (event.getX() % 100));
                temp.setY(event.getY() - (event.getY() % 100));
                temp.setFitHeight(100);
                temp.setFitWidth(100);
                Tower tower = new Tower(temp.getX(), temp.getY());
                tower.setTowerImageView(temp);
                addTower(tower);

                group.getChildren().addAll(temp);
                success = true;


            }
            event.setDropCompleted(success);
            event.consume();
        });

    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void initEnemy(Enemy enemy) throws FileNotFoundException {
        Image image2 = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\java\\Circle.PNG"));

        enemy.setEnemyImage(new ImageView(image2));

        enemy.getEnemyImage().setY(500);

        //enemy.getEnemyImage().setFitWidth(50);

        //enemy.getEnemyImage().setFitHeight(50);


        enemy.setPath(map.getMap());

        group.getChildren().addAll(enemy.getEnemyImage());

    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void removeEnemy(Enemy enemy){
        if(enemy.isPathFinished()){
            /**
             *
             */
        }
        else{
            /**
             *
             */
        }
        enemy.getEnemyImage().setVisible(false);
        enemies.remove(enemy);
    }

    private void updateLocation(){
        if(!enemies.isEmpty()){
            Iterator<Enemy> enemiesIterator = enemies.iterator();
            Enemy enemy;
            while(enemiesIterator.hasNext()) {
                enemy = enemiesIterator.next();
                enemy.updateLocation();
                createBullets(enemy);
                if(enemy.isPathFinished()){
                    removeEnemy(enemy);
                }
            }
        }
    }

    private void createBullets(Enemy enemy) {
        Path bulletPath;
        PathTransition animation;


        for (Tower tower : towers) {
            for (Bullet bullet : tower.getBulletList()) {
                // Create animation path

                // Create animation path

                bulletPath = new Path(new MoveTo(bullet.getStartX(), bullet.getStartY()));
                bulletPath.getElements().add(new LineTo(bullet.getEndX(), bullet.getEndY()));
                animation = new PathTransition(Duration.millis(300), bulletPath, bullet);


                // When the animation finishes, hide it and remove it
                animation.setOnFinished(actionEvent -> {

                    PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                    Bullet finishedBullet = (Bullet) finishedAnimation.getNode();

                    // Hide and remove from gui
                    finishedBullet.setVisible(false);
                    group.getChildren().remove(bullet);

                    // Remove monster if they are dead
                    if (enemy.isDead()) {
                        removeEnemy(finishedBullet.getTarget());
                    }
                });
                group.getChildren().add(bullet);
                animation.play();
            }
            tower.getBulletList().clear();
        }
    }

    void start() {

        //initialize();

        final LongProperty secondUpdate = new SimpleLongProperty(0);
        final LongProperty fpstimer = new SimpleLongProperty(0);


        new AnimationTimer() {
            int timer = 2 ;

            public void handle(long now) {

                if (now / 1000000000 != secondUpdate.get()) {
                    timer--;
                    if (timer > 19) {
                        try {
                            creatMonster(3);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (timer <= 0) {
                        //game.setLevel(game.getLevel() + 1);
                        timer = 24;
                    }
                }

                updateLocation();

                /*for (Tower tower : towers)
                    for(Enemy enemy : enemies)
                    if (tower.isShot(enemy)) {
                        if (tower.getTime() % 100 == 0) {
                            tower.creatBullet(enemy);
                            enemy.takeDamage(1);
                            //if(enemy.isDead()) stop();
                        }
                        tower.updateTime();
                    }*/

                for(Enemy enemy : enemies)
                    {for(Tower tower: towers)
                        if(tower.getBulletList().size()==0)
                        {
                            if(tower.isShot(enemy)){
                                if(tower.getTime()%100==0){
                                    tower.creatBullet(enemy);
                                    enemy.takeDamage(1);
                                }
                                tower.updateTime();
                            }
                        }
                    }




                //System.out.println(i);

                fpstimer.set(now / 10000000);
                secondUpdate.set(now / 1000000000);

                //if(enemy.getCenterY()==350&&enemy.getCenterX()>450) stop();
            }
        }.start();
    }


}

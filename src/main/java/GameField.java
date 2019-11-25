import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;


import java.awt.*;
import java.io.FileInputStream;

import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import static javafx.scene.paint.Color.YELLOW;

public class GameField {

    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private Scene gameScene;
    private Group group = new Group();
    private Map map = new Map();
    private boolean checkSelect = true;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private int score;

    private int index;

    Text money;

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

    public void startGame() throws FileNotFoundException {
        Image imageGame = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\StartGameGraphic.png"));
        ImageView imageViewGame = new ImageView(imageGame);

        imageViewGame.setFitHeight(1050);
        imageViewGame.setFitWidth(1312);


        group = new Group(imageViewGame);

        Image imageStartGame = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\StartGameButton.png"));

        ImageView imageViewStartGame = new ImageView(imageStartGame);

        Button startGame = new Button();
        startGame.setGraphic(imageViewStartGame);
        startGame.setLayoutX(450);
        startGame.setLayoutY(500);

        group.getChildren().add(startGame);


        startGame.setOnAction(event -> {
            try {
                initialize();
                GameStage.getStage().setScene(gameScene);
                start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        gameScene = new Scene(group);
    }

    public void initialize() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\Background1.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(1050);
        imageView.setFitWidth(1312);

        group = new Group(imageView);

        Image towerLabel = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\BuyTowerTitle.PNG"));
        ImageView towerLabelView = new ImageView(towerLabel);

        towerLabelView.setX(950);

        towerLabelView.setFitWidth(300);
        towerLabelView.setFitHeight(100);

        group.getChildren().add(towerLabelView);

        loadMap();

        Image imageSelect = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\TileSelectGraphic.png"));
        ImageView imageViewSelect = new ImageView(imageSelect);

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

        //image StartWave
        Image imageNextWaveStart = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\nextWaveActive.png"));
        ImageView imageViewNextWaveStart = new ImageView(imageNextWaveStart);
        imageViewNextWaveStart.setX(700);
        imageViewNextWaveStart.setY(920);
        imageViewNextWaveStart.setFitHeight(80);
        imageViewNextWaveStart.setFitWidth(200);
        group.getChildren().addAll(imageViewNextWaveStart);


        // image currency
        Image imageCurrency = new Image(new FileInputStream("C:\\Users\\ndtha\\GameProject\\src\\main\\images\\CurrencyGraphic.png"));
        ImageView imageViewCurrency = new ImageView(imageCurrency);
        imageViewCurrency.setX(0);
        imageViewCurrency.setY(920);
        imageViewCurrency.setFitHeight(80);
        imageViewCurrency.setFitWidth(100);
        group.getChildren().add(imageViewCurrency);

        score = 0;

        money = new Text(100, 980, String.valueOf(score));
        money.setFill(YELLOW);
        money.setFont(Font.font(java.awt.Font.SERIF, 50));


        group.getChildren().addAll(money);


        imageViewNextWaveStart.setOnMousePressed(event -> group.getChildren().remove(imageViewNextWaveStart));

        //handle drag and drop//
        imageViewBasicSniper.setOnMousePressed(event -> dragAndDrop(imageViewBasicSniper, imageView, group));

        imageViewTower2.setOnMousePressed(event -> dragAndDrop(imageViewTower2, imageView, group));

        imageViewTower3.setOnMousePressed(event -> dragAndDrop(imageViewTower3, imageView, group));

        //handle select Tower
        group.setOnMousePressed(event -> {
            group.getChildren().removeAll(imageViewSelect);

            for (int i = 0; i < towers.size(); i++) {
                if (event.getTarget().equals(towers.get(i).getTowerImageView())) {

                    imageViewSelect.setX(event.getX() - (event.getX() % 100));
                    imageViewSelect.setY(event.getY() - (event.getY() % 100));
                    imageViewSelect.setFitHeight(100);
                    imageViewSelect.setFitWidth(100);

                    checkSelect = true;
                    group.getChildren().addAll(imageViewSelect);
                    index = i;

                    return;
                }
            }
            if ((1120 <= event.getX() && event.getX() <= 1253) && (event.getY() >= 439 && event.getY() <= 548)) {
                if (checkSelect) {

                    towers.get(index).getTowerImageView().setVisible(false);
                    towers.remove(index);

                    checkSelect = false;
                }
            } else {
                checkSelect = false;
            }
        });

        //handle drag and drop//
        imageViewBasicSniper.setOnMousePressed(event -> dragAndDrop(imageViewBasicSniper, imageView, group));
        imageViewTower2.setOnMousePressed(event -> dragAndDrop(imageViewTower2, imageView, group));
        imageViewTower3.setOnMousePressed(event -> dragAndDrop(imageViewTower3, imageView, group));

        gameScene = new Scene(group);

    }

    public void createEnemy(int health) throws FileNotFoundException {
        Enemy enemy = new Enemy(health);
        enemies.add(enemy);
        initEnemy(enemy);

    }


    //drag and drop
    void dragAndDrop(final ImageView source, final ImageView target, Group group) {
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
                if (map.getMap()[1][2] == 2) {
                    return;
                }
                if (event.getX() <= 900 && event.getY() <= 800) {
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

        enemy.getEnemyImage().setY(850);

        //enemy.getEnemyImage().setFitWidth(50);

        //enemy.getEnemyImage().setFitHeight(50);


        enemy.setPath(map.getMap());

        group.getChildren().addAll(enemy.getEnemyImage());


    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void removeEnemy(Enemy enemy) {
        if (enemy.isPathFinished()) {
            /**
             *
             */
        } else {
            score++;
        }
        enemy.getEnemyImage().setVisible(false);
        enemies.remove(enemy);
    }

    private void updateLocation() {
        if (!enemies.isEmpty()) {
            Iterator<Enemy> enemiesIterator = enemies.iterator();
            Enemy enemy;
            while (enemiesIterator.hasNext()) {
                enemy = enemiesIterator.next();
                enemy.updateLocation();
                //createBullets(enemy);
                if (enemy.isPathFinished()) {
                    removeEnemy(enemy);
                }
            }
        }
    }

    public void createBullets(Enemy enemy) {
        Path bulletPath;
        PathTransition animation;
        for (Tower tower : towers) {
            for (Bullet bullet : tower.getBulletList()) {
                // Create animation path

                // Create animation path

                //System.out.println(enemies.indexOf(enemy));

                bulletPath = new Path(new MoveTo(bullet.getStartX(), bullet.getStartY()));
                bulletPath.getElements().add(new LineTo(bullet.getEndX(), bullet.getEndY()));
                animation = new PathTransition(Duration.millis(200), bulletPath, bullet);


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


    public void start() {

        //initialize();

        final LongProperty secondUpdate = new SimpleLongProperty(0);
        final LongProperty fpstimer = new SimpleLongProperty(0);
        //gameController.start();s
        new AnimationTimer() {
            int timer = 2;

            public void handle(long now) {

                if (now / 1000000000 != secondUpdate.get()) {
                    timer--;
                    if (timer > 19) {
                        try {
                            if(timer%2==0)
                            createEnemy(3);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (timer <= 0) {
                        //game.setLevel(game.getLevel() + 1);
                        timer = 29;
                    }
                }

                updateLocation();

                for (Tower tower : towers) {
                    for (Enemy enemy : enemies)
                        if (tower.isShot(enemy)) {
                            if (tower.getTime() % 100 == 0&&tower.getBulletList().size()==0) {
                                tower.creatBullet(enemy);
                                enemy.takeDamage(1);
                            }
                        }
                    tower.updateTime();
                    System.out.println(tower.getTime());
                }

                //System.out.println(i);

                fpstimer.set(now / 10000000);
                secondUpdate.set(now / 1000000000);

                money.setText(String.valueOf(score));

                //if(enemy.getCenterY()==350&&enemy.getCenterX()>450) stop();
            }
        }.start();
    }

}
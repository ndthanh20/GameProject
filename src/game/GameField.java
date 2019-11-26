package game;


import game.enemy.*;
import game.game_entity.*;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


import java.io.File;
import java.io.FileInputStream;

import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import static javafx.scene.paint.Color.*;

public class GameField {

    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private Scene gameScene;
    private Group group = new Group();
    private MediaPlayer mediaPlayerGun;
    private boolean checkSelect = true;
    private ArrayList<ImageView> child = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private int Money;
    private Text Health;

    private Road road = new Road();
    private int indexGroup;

    Text money, wave;

    private int index;
    private ImageView imageViewNextWaveStart, AutoPlay;
    boolean autoplay = false;

    public GameField() {

    }


    public void startGame() throws FileNotFoundException {
        Image imageGame = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\StartGameGraphic.png"));
        ImageView imageViewGame = new ImageView(imageGame);

        imageViewGame.setFitHeight(1050);
        imageViewGame.setFitWidth(1312);


        group = new Group(imageViewGame);

        Image imageStartGame = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\StartGameButton.png"));

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
        Image image = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\Background1.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(1050);
        imageView.setFitWidth(1312);

        group = new Group(imageView);

        road.loadRoad(group);

        Image imageSelect = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\TileSelectGraphic.png"));
        ImageView imageViewSelect = new ImageView(imageSelect);

        Image imageBasicSniper = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\Tower1.png"));
        ImageView imageViewBasicSniper = new ImageView(imageBasicSniper);
        imageViewBasicSniper.setX(955);
        imageViewBasicSniper.setY(140);
        imageViewBasicSniper.setFitHeight(100);
        imageViewBasicSniper.setFitWidth(100);
        group.getChildren().addAll(imageViewBasicSniper);
        //image health
        Image imageHealthPlayer = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\Heart.png"));
        ImageView imageViewHealthPlayer = new ImageView(imageHealthPlayer);
        imageViewHealthPlayer.setX(200);
        imageViewHealthPlayer.setY(930);
        imageViewHealthPlayer.setFitHeight(70);
        imageViewHealthPlayer.setFitWidth(100);
        group.getChildren().addAll(imageViewHealthPlayer);

        Health = new Text(320, 980, String.valueOf(GameStage.getPlayerHealth()));
        Health.setFill(YELLOW);
        Health.setFont(Font.font(java.awt.Font.SERIF, 60));

        group.getChildren().add(Health);

        //image tower 2 //
        Image imageTower2 = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\Tower2.png"));
        ImageView imageViewTower2 = new ImageView(imageTower2);
        imageViewTower2.setX(1140);
        imageViewTower2.setY(140);
        imageViewTower2.setFitHeight(100);
        imageViewTower2.setFitWidth(100);
        group.getChildren().addAll(imageViewTower2);

        //image tower3
        Image imageTower3 = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\Tower3.png"));
        ImageView imageViewTower3 = new ImageView(imageTower3);
        imageViewTower3.setX(956);
        imageViewTower3.setY(297);
        imageViewTower3.setFitHeight(100);
        imageViewTower3.setFitWidth(100);
        group.getChildren().addAll(imageViewTower3);

        //image StartWave
        Image imageNextWaveStart = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\nextWaveActive.png"));
        imageViewNextWaveStart = new ImageView(imageNextWaveStart);
        imageViewNextWaveStart.setX(700);
        imageViewNextWaveStart.setY(920);
        imageViewNextWaveStart.setFitHeight(80);
        imageViewNextWaveStart.setFitWidth(200);
        group.getChildren().addAll(imageViewNextWaveStart);

        Image imageAutoPlay = new Image((new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\AutoPlay.png")));
        AutoPlay = new ImageView(imageAutoPlay);
        AutoPlay.setX(1000);
        AutoPlay.setY(910);
        AutoPlay.setFitHeight(80);
        AutoPlay.setFitWidth(200);

        group.getChildren().addAll(AutoPlay);

        //image Wave

        Image waveImage = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\WaveGraphic.PNG"));
        ImageView waveImageView = new ImageView(waveImage);
        waveImageView.setX(450);
        waveImageView.setY(920);
        group.getChildren().add(waveImageView);

        wave = new Text(535, 965, String.valueOf(GameStage.getGameLevel()));
        wave.setFill(GREEN);
        wave.setFont(Font.font(java.awt.Font.SERIF, 35));

        group.getChildren().add(wave);


        // image currency
        Image imageCurrency = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\CurrencyGraphic.png"));
        ImageView imageViewCurrency = new ImageView(imageCurrency);
        imageViewCurrency.setX(0);
        imageViewCurrency.setY(920);
        imageViewCurrency.setFitHeight(80);
        imageViewCurrency.setFitWidth(100);
        group.getChildren().add(imageViewCurrency);

        Money = 50;

        money = new Text(100, 980, String.valueOf(Money));
        money.setFill(YELLOW);
        money.setFont(Font.font(java.awt.Font.SERIF, 50));

        group.getChildren().addAll(money);

        Image exitImage = new Image(new FileInputStream("D:\\Java-game\\GameRepository\\src\\game\\images\\ExitPointGraphic.PNG"));
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.setX(1200);
        exitImageView.setY(700);
        exitImageView.setFitHeight(100);
        exitImageView.setFitWidth(100);
        group.getChildren().add(exitImageView);


        //handle select Tower

        group.setOnMousePressed(event -> {

            group.getChildren().removeAll(imageViewSelect);

            for (int i = 0; i < child.size(); i++) {
                //System.out.println(event.getTarget()+" "+child.get(i));
                if (event.getTarget().equals(child.get(i))) {
                    imageViewSelect.setX(event.getX() - (event.getX() % 100));
                    imageViewSelect.setY(event.getY() - (event.getY() % 100));
                    imageViewSelect.setFitHeight(100);
                    imageViewSelect.setFitWidth(100);
                    checkSelect = true;
                    group.getChildren().addAll(imageViewSelect);
                    index = i;
                    indexGroup = group.getChildren().indexOf(child.get(i));
                    return;
                }
            }
            if ((1120 <= event.getX() && event.getX() <= 1253) && (event.getY() >= 439 && event.getY() <= 548)) {
                if (checkSelect) {
                    if (towers.get(index) instanceof SniperTower) Money += 25;
                    if (towers.get(index) instanceof NormalTower) Money += 15;
                    if (towers.get(index) instanceof MachineGunTower) Money += 10;
                    towers.get(index).getImageView().setVisible(false);
                    towers.remove(index);
                    child.remove(index);


                    group.getChildren().remove(indexGroup);

                    checkSelect = false;
                }
            } else checkSelect = false;
            if ((858 <= event.getX() && event.getX() <= 985) && (event.getY() >= 439 && event.getY() <= 548)) {
                towers.get(index).setDamage(towers.get(index).getDamage() + 2);
                towers.get(index).setShootRange(towers.get(index).getShootRange() + 100);
                if (towers.get(index) instanceof SniperTower && Money >= 40) Money -= 40;
                if (towers.get(index) instanceof NormalTower && Money >= 30) Money -= 30;
                if (towers.get(index) instanceof MachineGunTower && Money >= 50) Money -= 50;

            }
        });


        //handle drag and drop//
        imageViewBasicSniper.setOnMousePressed(event -> dragAndDrop(imageViewBasicSniper, imageView, group, 1));
        imageViewTower2.setOnMousePressed(event -> dragAndDrop(imageViewTower2, imageView, group, 2));
        imageViewTower3.setOnMousePressed(event -> dragAndDrop(imageViewTower3, imageView, group, 3));

        gameScene = new Scene(group);

    }

    public void createEnemy(int a) throws FileNotFoundException {
        Enemy enemy = null;
        switch (a) {
            case 1: {
                enemy = new NormalEnemy(40);
                break;
            }
            case 2: {
                enemy = new TankerEnemy(60);
                break;
            }
            case 3: {
                enemy = new SmallerEnemy(20);
                break;
            }
            case 4: {
                enemy = new BossEnemy(80);
                break;
            }
            default:
                break;
        }
        enemies.add(enemy);
        initEnemy(enemy);

    }


    //drag and drop
    void dragAndDrop(final ImageView source, final ImageView target, Group group, int t) {
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();

                content.putImage(source.getImage());
                db.setContent(content);
                event.consume();
            }
        });

        ImageView temp = new ImageView();
        target.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                if (road.getMap()[1][2] == 2) {
                    return;
                }
                if (event.getX() <= 900 && event.getY() <= 800) {
                    temp.setImage(source.getImage());
                    temp.setX(event.getX() - (event.getX() % 100));
                    temp.setY(event.getY() - (event.getY() % 100));
                    temp.setFitHeight(100);
                    temp.setFitWidth(100);
                    Tower tower = null;
                    try {
                        switch (t) {
                            case 1: {
                                if (Money >= 20)
                                    tower = new NormalTower(temp.getX(), temp.getY());
                                break;
                            }
                            case 2: {
                                if (Money >= 30)
                                    tower = new SniperTower(temp.getX(), temp.getY());
                                break;
                            }
                            case 3: {
                                if (Money >= 40)
                                    tower = new MachineGunTower(temp.getX(), temp.getY());
                                break;
                            }
                            default:
                                break;

                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (tower != null) {
                        Money -= tower.getCost();
                        addTower(tower);
                        child.add(temp);
                        group.getChildren().addAll(temp);
                        success = true;
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }
    public void playSoundGun(){

        String path = "D:\\Java-game\\GameRepository\\src\\game\\Sound\\GunSound.mp3";

        Media media = new Media(new File(path).toURI().toString());

        mediaPlayerGun = new MediaPlayer(media);
        mediaPlayerGun.play();

    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void initEnemy(Enemy enemy) {

        enemy.setPath(road.getMap());
        group.getChildren().addAll(enemy.getImageView());
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void removeEnemy(Enemy enemy) {
        if (enemy.isPathFinished()) {

        } else {
            Money += enemy.getValue();
        }
        enemy.getImageView().setVisible(false);
        enemies.remove(enemy);
        group.getChildren().removeAll(enemy.getHealthBar());
    }

    private void updateLocation() {
        if (!enemies.isEmpty()) {
            Iterator<Enemy> enemiesIterator = enemies.iterator();
            Enemy enemy;
            while (enemiesIterator.hasNext()) {
                enemy = enemiesIterator.next();
                for (int i = 0; i < enemy.getSpeed(); i++)
                    enemy.updateLocation();

                createBullets(enemy);
                if (enemy.isPathFinished()) {
                    removeEnemy(enemy);
                }

            }
        }
    }

    public void putNewTower() throws FileNotFoundException {

        for (int j = 0; j < 11; j++)
            for (int i = 0; i < 9; i++) {
                Tower tower = null;
                if (road.getMap()[j][i] == 2 && Money >= 0) {
                    if (Money >= 20) {
                        tower = new NormalTower(j * 100, i * 100);
                    }
                    if (tower != null) {

                        Money -= tower.getCost();
                        addTower(tower);
                        group.getChildren().addAll(tower.getImageView());
                        road.getMap()[j][i] = 0;
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
                animation = new PathTransition(Duration.millis(tower.getShootSpeed()), bulletPath, bullet.getCircle());


                // When the animation finishes, hide it and remove it
                animation.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override

                    public void handle(ActionEvent actionEvent) {

                        PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                        //Bullet finishedBullet = (Bullet) finishedAnimation.getNode();

                        // Hide and remove from gui
                        bullet.getCircle().setVisible(false);
                        group.getChildren().remove(bullet.getCircle());

                        // Remove monster if they are dead
                        if (enemy.isDead()) {
                            removeEnemy(bullet.getTarget());
                        }
                    }
                });
                group.getChildren().add(bullet.getCircle());
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

        AutoPlay.setOnMousePressed(event -> {
            autoplay = true;
        });
        new AnimationTimer() {
            int timer = 2;

            public void handle(long now) {

                if (now / 1000000000 != secondUpdate.get()) {
                    timer--;
                    if (timer > 19) {
                        if (timer % 2 == 0)
                            try {
                                if (GameStage.getGameLevel() % 4 == 1)
                                    if (timer != 2)
                                        createEnemy(1);
                                    else {
                                        createEnemy(1);
                                        enemies.get(enemies.size() - 1).setHealthPoints(10);
                                        enemies.get(enemies.size() - 1).setSpeed(enemies.get(enemies.size() - 1).getSpeed() + 2);
                                    }
                                if (GameStage.getGameLevel() % 4 == 2)
                                    if (timer != 2)
                                        createEnemy(2);
                                    else {
                                        createEnemy(2);
                                        enemies.get(enemies.size() - 1).setHealthPoints(10);
                                        enemies.get(enemies.size() - 1).setSpeed(enemies.get(enemies.size() - 1).getSpeed() + 2);
                                    }
                                if (GameStage.getGameLevel() % 4 == 3)
                                    if (timer != 2)
                                        createEnemy(3);
                                    else {
                                        createEnemy(3);
                                        enemies.get(enemies.size() - 1).setHealthPoints(10);
                                        enemies.get(enemies.size() - 1).setSpeed(enemies.get(enemies.size() - 1).getSpeed() + 2);
                                    }
                                if (GameStage.getGameLevel() % 4 == 0 && timer % 4 == 0)
                                    if (timer != 2)
                                        createEnemy(4);
                                    else {
                                        createEnemy(4);
                                        enemies.get(enemies.size() - 1).setHealthPoints(30);
                                        enemies.get(enemies.size() - 1).setSpeed(enemies.get(enemies.size() - 1).getSpeed() + 3);
                                    }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                    } else if (timer <= 0) {
                        GameStage.setGameLevel(1);

                        timer = 29;
                    }
                }

                updateLocation();

                for (Tower tower : towers) {
                    for (Enemy enemy : enemies)
                        if (tower.isShot(enemy)) {
                            if (tower.getTime() % 100 == 0 && tower.getBulletList().size() == 0) {
                                tower.creatBullet(enemy);
                                enemy.takeDamage(tower.getDamage());
                                if (enemy.isDead() == false) {
                                    enemy.setXYHealthBar();
                                    group.getChildren().removeAll(enemy.getHealthBar());
                                    enemy.drawHealthBar();
                                    group.getChildren().addAll(enemy.getHealthBar());
                                }

                            }

                        }
                    tower.updateTime();
                }


                //System.out.println(i);

                fpstimer.set(now / 10000000);
                secondUpdate.set(now / 1000000000);
                money.setText(String.valueOf(Money));
                wave.setText(String.valueOf(GameStage.getGameLevel()));

                if (timer > 10) imageViewNextWaveStart.setVisible(false);
                else imageViewNextWaveStart.setVisible(true);

                imageViewNextWaveStart.setOnMousePressed(event -> {
                    timer = 1;
                    imageViewNextWaveStart.setVisible(false);
                });

                if (autoplay) {
                    try {
                        putNewTower();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }


                //if(enemy.getCenterY()==350&&enemy.getCenterX()>450) stop();
            }
        }.start();
    }

}
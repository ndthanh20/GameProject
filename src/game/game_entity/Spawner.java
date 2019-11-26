package game.game_entity;

import game.Coordinate;

public class Spawner extends Road {
    private static Coordinate coordinate;

    public Spawner(){
        coordinate = new Coordinate(0,800);
    }
    public static Coordinate getSpawner(){
        return coordinate;
    }
}

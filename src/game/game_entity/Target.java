package game.game_entity;

import game.Coordinate;

public class Target extends Road {

    private static Coordinate coordinate;

    public Target(){
        coordinate = new Coordinate(1000,700);
    }
    public static Coordinate getTarget(){
        return coordinate;
    }
}

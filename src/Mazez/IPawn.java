package Mazez;

public interface IPawn {
    void move(Move m); // changes this pawn's position according to move.
    Maze.Route getRoute(); // returns the current route.
}

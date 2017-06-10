package Mazez;

public interface IPawn {
    void move(Move m); // changes this pawn's position according to move.
    Route getRoute(); // returns the current route.
}

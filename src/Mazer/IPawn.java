package Mazer;

public interface IPawn {
    void move(Move m); // changes this pawn position according to move.
    Route getRoute(); // returns the current route.
}

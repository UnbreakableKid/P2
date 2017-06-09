package Mazer;

import Mazer.Route;

/**
 * Created by falad on 30-May-17.
 */
public interface IPawn {
    void move(Move m); // changes this pawn position according to move.
    Route getRoute(); // returns the current route.
}

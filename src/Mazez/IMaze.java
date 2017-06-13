package Mazez;

public interface IMaze {
    boolean canMove(Pawn p, Move m); // true IF pawn can do move in this maze.
    Move[] getOptions(Pawn p); // returns the possible moves of pawn.
    void move(Pawn p, Move m); // if pawn can move, change his position.
    boolean isSolvedBy(Pawn p); // true IF pawn is in EXIT position.
}



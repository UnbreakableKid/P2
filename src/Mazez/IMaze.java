package Mazez;

public interface IMaze {
    boolean canMove(Maze.Pawn p, Move m); // true IFF pawn can do move in this maze.
    Move[] getOptions(Maze.Pawn p); // returns the possible moves of pawn.
    void move(Maze.Pawn p, Move m); // if pawn can move, change his position.
    boolean isSolvedBy(Maze.Pawn p); // true IF pawn is in EXIT position.
}



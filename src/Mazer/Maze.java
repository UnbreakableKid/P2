package Mazer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Maze implements IMaze{

    //private ArrayList<MazeCell> maze = new ArrayList<MazeCell>();

   /*

    _SW______\n
    _WWW_W_WW\n
    _____W__E\n

    */

    public Move[] getOptions (Pawn p){
        Move[] movez = new Move[1];
        return movez;
    }

    public boolean isSolvedBy (Pawn p){
        return true;
    }

    public void move(Pawn p, Move m){
    }

    public boolean canMove(Pawn p, Move m){
       return true;
    }

    public Maze(){

        //for tests
        MazeCell[][] maze = new MazeCell[][]{{MazeCell.EMPTY, MazeCell.START, MazeCell.WALL, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY},
                {MazeCell.EMPTY, MazeCell.WALL, MazeCell.WALL, MazeCell.WALL, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.WALL, MazeCell.WALL,},
                {MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EXIT}
        };

    }

}

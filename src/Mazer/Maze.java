package Mazer;

public class Maze implements IMaze {

    private int numCols;
    private int numRows;
    private Pawn p;
    private Route r;

    //private ArrayList<MazeCell> maze = new ArrayList<MazeCell>();

   /*

    S_W______\n
    _WWW_W_WW\n
    _____W__E\n

    */

    public Move[] getOptions(Pawn p) {
        Move[] movez = new Move[1];
        return movez;
    }

    public boolean isSolvedBy(Pawn p) {
        return true;
    }

    public void move(Pawn p, Move m) {
    }

    public boolean canMove(Pawn p, Move m) {
        return true;
    }

    public Maze() {

        p = new Pawn();

        //for tests
        start();
        MazeCell[][] maze = new MazeCell[][]{{MazeCell.START, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY},
                {MazeCell.EMPTY, MazeCell.WALL, MazeCell.WALL, MazeCell.WALL, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.WALL, MazeCell.WALL,},
                {MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EXIT}
        };

    }

    private void start(){

    }

    class Pawn implements IPawn {

        public void move(Move m){
            if (canMove(p, Move.EAST)){
                //move
            }
            else{
                //no move :D
            }
        }

        public Route getRoute(){
            return r;
        }
    }

    class Route implements IRoute {

        private Move[] route;
        public int getCol(){
            return 1;
        }

        public int getRow(){
            return 2;
        }

        public int getCol(int i){
            return 3;
        }

        public int getRow(int i){
            return 4;
        }

        public int length(){
            return 5;
        }

        public void move(Move m) {

        }
    }
}

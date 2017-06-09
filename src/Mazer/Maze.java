package Mazer;

import java.io.BufferedReader;
import java.io.FileReader;

public class Maze implements IMaze {

    private MazeCell[][] maze;
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
        /*MazeCell[][] maze = new MazeCell[][]{{MazeCell.START, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY},
                {MazeCell.EMPTY, MazeCell.WALL, MazeCell.WALL, MazeCell.WALL, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.WALL, MazeCell.WALL,},
                {MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.WALL, MazeCell.EMPTY, MazeCell.EMPTY, MazeCell.EXIT}
        };
*/
    }

    public void iWonder (){
        for (MazeCell m : maze[1]){
            System.out.print(m);
        }
    }

    public void openFile(String filename){

        try
        {
            FileReader f = new FileReader(filename);
            BufferedReader reader = new BufferedReader(f);
            MazeCell m;
            int currentC = 0;
            int currentR = 0;
            String firstLine = reader.readLine();
            numCols = firstLine.length();
            int c = reader.read();
            while (c != 0){
                if(currentC > numCols){
                    throw new MazeFileNumCols(currentC);
                }
                if (c == (int)'\n') {
                    currentR++;
                    //muda de linha
                }
                m = judge(c, currentC, currentR);
                addTo(m, currentC, currentR);
                currentC++;
                c = reader.read();
            }
            reader.close();
        }
        catch (MazeFileWrongChar e){
            System.out.println(e.getMessage());
        }

        catch (MazeFileNumCols e){
            System.out.println(e.getMessage());
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addTo (MazeCell type, int cC, int cR){
        maze[cC][cR] = type;
    }

    private MazeCell judge (int asci, int cC, int cR ) throws MazeFileWrongChar{
        switch (asci) {
            case 83: return MazeCell.START; // S
            case 95: return MazeCell.EMPTY; //_
            case 69: return MazeCell.EXIT; //E
            case 87: return MazeCell.WALL; //W
            default:throw new MazeFileWrongChar(cC, cR);
        }
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

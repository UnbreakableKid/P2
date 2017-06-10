package Mazez;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Maze implements IMaze {

    private File f = new File("E:/PC/New/P2/src/d.maze");
    private MazeCell[][] maze;
    private int numCols;
    private int numRows;
    private Pawn p;
    private Route r;

    //private ArrayList<Mazez.MazeCell> maze = new ArrayList<Mazez.MazeCell>();

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
        /*Mazez.MazeCell[][] maze = new Mazez.MazeCell[][]{{Mazez.MazeCell.START, Mazez.MazeCell.EMPTY, Mazez.MazeCell.WALL, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY},
                {Mazez.MazeCell.EMPTY, Mazez.MazeCell.WALL, Mazez.MazeCell.WALL, Mazez.MazeCell.WALL, Mazez.MazeCell.EMPTY, Mazez.MazeCell.WALL, Mazez.MazeCell.EMPTY, Mazez.MazeCell.WALL, Mazez.MazeCell.WALL,},
                {Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.WALL, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EMPTY, Mazez.MazeCell.EXIT}
        };
*/
    }

    public void iWonder (){
        for (MazeCell m : maze[1]){
            System.out.print(m);
        }
    }

    private void chooseOne(){
        JButton b = new JButton();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Maze", "maze");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(b);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +chooser.getSelectedFile().getName());
            f = chooser.getSelectedFile();
        }
    }


    public void openFile(){

        try
        {
            //chooseOne();
            FileInputStream fis = new FileInputStream(this.f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            String firstLine = reader.readLine();
            numCols = firstLine.length();
            int i = 1;
            while (firstLine != null){

                if(firstLine.length() != numCols){
                    throw new MazeFileNumCols(i);
                }
                firstLine = reader.readLine();
                i++;
            }

            reader.close();
            isr.close();
            fis.close();

            FileInputStream fis2 = new FileInputStream(this.f);

            int fsize = (int) f.length();
            byte[] stuff = new byte[fsize];
            fis2.read(stuff);


        }
        //catch (MazeFileWrongChar e){
          //  System.out.println(e.getMessage());
        //}

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

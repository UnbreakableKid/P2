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

    }

    public void iWonder() {
        for (MazeCell m : maze[1]) {
            System.out.print(m);
        }
    }

    private void chooseOne() {
        JButton b = new JButton();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Maze file", "maze");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(b);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
            f = chooser.getSelectedFile();
        }
    }


    public void openFile() {

        try {
            //chooseOne();
            FileInputStream fis = new FileInputStream(this.f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            String firstLine = reader.readLine();
            numCols = firstLine.length();
            int i = 1;
            while (firstLine != null) {

                if (firstLine.length() != numCols) {
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
            judge(stuff);


        } catch (MazeFileWrongChar e) {
            System.out.println(e.getMessage());
        } catch (MazeFileNumCols e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addTo(MazeCell type, int cC, int cR) {
        //maze[cC][cR] = type;
        System.out.print(1);
    }

    private void judge(byte[] file) throws MazeFileWrongChar {
        int cC = 0, cR = 0;
        int a;
        for (byte b : file) {
            a = (int) b;
            System.out.println(a);

            if (isValid(a)){

                if (a == 95){
                    addTo(MazeCell.EMPTY, cC, cR); // _
                    cC++;
                }
                if (a == 87){
                    addTo(MazeCell.WALL, cC, cR); // W
                    cC++;
                }
                if (a == 83){
                    addTo(MazeCell.START, cC, cR); // S
                    cC++;
                }
                if (a == 69){
                    addTo(MazeCell.EXIT, cC, cR); // E
                    cC++;
                }
                if (a == 10){
                    cR++;
                }
            }
            else {
                //throw new MazeFileWrongChar(cC, cR);
            }
        }
     }

    private boolean isValid(int ascii){
        if (ascii == 95 | ascii == 83 | ascii == 87 | ascii == 13 | ascii == 10)
            return true;
        else
            return false;
    }
}

class Pawn implements IPawn {
    public void move(Move m) {
    }

    public Route getRoute() {return new Route();}
}

class Route implements IRoute {

    private Move[] route;

    public int getCol() {
        return 1;
    }

    public int getRow() {
        return 2;
    }

    public int getCol(int i) {
        return 3;
    }

    public int getRow(int i) {
        return 4;
    }

    public int length() {
        return 5;
    }

    public void move(Move m) {
    }
}

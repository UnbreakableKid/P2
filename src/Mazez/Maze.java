package Mazez;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Maze implements IMaze {

    public Maze() {
    }

    private final int ERROR = -1;
    private File f = new File("E:/PC/New/P2/src/d.maze");
    private MazeCell[][] maze;
    private int numColS, numRowS, numCols, numRows, numColE, numRowE;

    private static final int[] north = new int[]{-1, 0};
    private static final int[] south = new int[]{1, 0};
    private static final int[] west = new int[]{0, -1};
    private static final int[] east = new int[]{0, 1};

    public Move[] getOptions(Pawn p) {
        Move[] mov;
        int i = 0;
        for (Move move : Move.values()){
            if (canMove(p, move))
                i++;
        }
        mov = new Move[i];
        for (Move move : Move.values()){
            if (canMove(p, move))
                mov[i]=move;
        }
        return mov;
    }

    int[] getStartP(){
        return new int[]{numRowS, numColS};
    }

    public boolean isSolvedBy(Pawn p) {
        int[] a = p.position();
        return a[0] == numColE & a[1] == numRowE;
    }

    public void move(Pawn p, Move m) {
        if(canMove(p, m)){
            p.move(m);
        }
        else {
            //no move
        }
    }

    static int[] identifyM (Move m){
        switch (m){
            case NORTH:
                return north;
            case SOUTH:
                return south;
            case WEST:
                return west;
            case EAST:
                return east;
            case NOOP:
        }
        return new int[]{0,0};
    }

    public boolean canMove(Pawn p, Move m){
        int [] pos = p.position();
        int [] posToBe = Pawn.addMatrizes(identifyM(m), pos);
        try {
            return(canIgo(maze[posToBe[0]][posToBe[1]]));
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    private boolean canIgo(MazeCell mc) {
        switch (mc){
            case EXIT:
                return true;
            case WALL:
                return false;
            case EMPTY:
                return true;
            case START:
                return true;
        }
        return false;
    }

    public void start() {
        openFile();
    }

    /*for opening and doing maze


     */

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

    private void openFile() {

        try {
            //chooseOne();
            FileInputStream fis = new FileInputStream(this.f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            String firstLine = reader.readLine();
            numCols = firstLine.length();
            numRows = 0;
            int i = 1;
            while (firstLine != null) {
                numRows++;
                if (firstLine.length() != numCols) {
                    numCols = ERROR;
                    numRows = ERROR;
                    throw new MazeFileNumCols(i);
                }
                firstLine = reader.readLine();
                i++;
            }
            maze = new MazeCell[numRows][numCols];
            reader.close();
            isr.close();
            fis.close();

            FileInputStream fis2 = new FileInputStream(this.f);

            int fsize = (int) f.length();
            byte[] stuff = new byte[fsize];
            fis2.read(stuff);
            judge(stuff);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addTo(MazeCell type, int cR, int cC) {
        this.maze[cR][cC] = type;
    }

    private void judge(byte[] file) throws MazeFileWrongChar {
        int cC = 0, cR = 0;
        int a;
        for (byte b : file) {
            a = (int) b;
            if (isValid(a)) {
                if (a == 95) {
                    addTo(MazeCell.EMPTY, cR, cC); // _
                    cC++;
                }
                if (a == 87) {
                    addTo(MazeCell.WALL, cR, cC); // W
                    cC++;
                }
                if (a == 83) {
                    addTo(MazeCell.START, cR, cC); // S

                    numColS = cC;
                    numRowS = cR;

                    cC++;
                }
                if (a == 69) {
                    addTo(MazeCell.EXIT, cR, cC); // E
                    cC++;

                    numColE = cC;
                    numRowE = cR;

                }
                if (a == 10) {
                    cC = 0;
                    cR++;
                }
            }
            if (!isValid(a)) {
                throw new MazeFileWrongChar(cC, cR);
            }
        }
    }

    private boolean isValid(int ascii) {
        return ascii == 95 | ascii == 83 | ascii == 87 | ascii == 13 | ascii == 10 | ascii == 69;
    }
}


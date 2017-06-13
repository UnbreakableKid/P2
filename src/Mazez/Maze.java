package Mazez;

import java.io.*;
import java.util.Random;


public class Maze implements IMaze {

    public Maze(int rows, int cols){
        numRows = rows;
        numCols = cols;
        maze =  new MazeCell[numRows][numCols];
        generate(maze, 0, 0, numRows, numCols, how(numRows, numCols));
    }

    public Maze(File f) throws Exception {

        if (goodCols(f)) {
            if (goodtoRead(f)) {
                read();
                }
            }
        }

    private int i = 0;
    private Random r = new Random();;

    private final int ERROR = -1;
    private byte[] stuff;

    private MazeCell[][] maze;
    private int numColS, numRowS, numCols, numRows, numColE, numRowE;

    private static final int[] north = new int[]{-1, 0};
    private static final int[] south = new int[]{1, 0};
    private static final int[] west = new int[]{0, -1};
    private static final int[] east = new int[]{0, 1};

    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 2;

    private int how(int width, int heigth){
        if (width < heigth){
            return HORIZONTAL;
        }
        if (heigth > width){
            return VERTICAL;
        }
        else{
            return r.nextInt(2)+1;
        }
    }

    public boolean generate(MazeCell[][] mazez, int row, int col, int width, int height, int how ){

        if (width < 2 || height <2) {
            return true;
        }

        //no idea

        int lrow = 1;
        return true;

    }

    public void Solve(){
        Pawn p = new Pawn(this);
        Solve(Move.EAST, p);
    }

    private boolean Solve(Move m, Pawn p) {

        System.out.print(p.position()[0] + " ");
        System.out.print(p.position()[1]);
        System.out.println();

        if (isSolvedBy(p)){
            return true;
        }

        if (canMove(p, Move.SOUTH)){
            move(p, Move.SOUTH);
            if(Solve(Move.SOUTH, p)){
                return true;
            }
            //p.move(Move.NORTH);
        }

        if (canMove(p, Move.EAST)){
            move(p, Move.EAST);
            if(Solve(Move.EAST, p)){
                return true;
            }
            //p.move(Move.WEST);
        }

        if (canMove(p, Move.NORTH)){
            move(p, Move.NORTH);
            if(Solve(Move.NORTH, p)){
                return true;
            }
            //p.move(Move.SOUTH);
        }

        if (canMove(p, Move.WEST)){
            move(p, Move.WEST);
            if(Solve(Move.WEST, p)){
                return true;
            }
            //p.move(Move.EAST);
        }

        //if stuck

        return false;
    }


    public Move[] getOptions(Pawn p) {
        Move[] mov;
        int i = 0;
        int ii = 0;
        for (Move move : Move.values()) {
            if (canMove(p, move)) {
                i++;
            }
        }

        mov = new Move[i];

        for (Move mo : Move.values()) {
            if (canMove(p, mo)) {
                mov[ii] = mo;
                ii++;
            }
        }

        return mov;
    }

    static int[] identifyM(Move m) {
        switch (m) {
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
        return new int[]{0, 0};
    }

    static int[] addMatrizes(int[] a, int[] b) {
        int c[] = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    int[] getEndP() {
        return new int[]{numRowE, numColE};
    }

    int[] getStartP() {
        return new int[]{numRowS, numColS};
    }

    public boolean isSolvedBy(Pawn p) {
        int[] a = p.position();
        return a[0] == numRowE & a[1] == numColE;
    }

    public void move(Pawn p, Move m) {
        p.move(m);
    }

    public boolean canMove(Pawn p, Move m) {
        int[] pos = p.position();
        int[] posToBe = addMatrizes(identifyM(m), pos);
        try {
            return (canIgo(maze[posToBe[0]][posToBe[1]]));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean canIgo(MazeCell mc) {
        switch (mc) {
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


    /*for opening and doing maze


     */


    boolean goodCols(File f) throws Exception {

        FileInputStream fis = new FileInputStream(f);
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
        reader.close();
        isr.close();
        fis.close();
        return true;
    }

    boolean goodtoRead(File f) throws Exception {

        FileInputStream fis = new FileInputStream(f);
        int filesize = (int) f.length();
        this.stuff = new byte[filesize];
        fis.read(stuff);
        int a, cC = 0, cR = 0;

        for (byte b : stuff) {
            a = (int) b;

            if (!isValid(a)) {
                invalidChar(cR, cC);
            }

            cC++;

            if (a == 10) {
                cC = 0;
                cR++;
            }
        }
        fis.close();
        return true;
    }

    MazeCell[][] getMaze() {
        return this.maze;
    }

    int[] getDims (){
        return new int[]{numRows, numCols};
    }

    private void invalidChar(int cR, int cC) throws MazeFileWrongChar {
        throw new MazeFileWrongChar(cR, cC);
    }

    private void addTo(MazeCell type, int cR, int cC) {
        this.maze[cR][cC] = type;
    }

    private void read() {
        maze = new MazeCell[numRows][numCols];
        //System.out.println(numRows);
        //System.out.println(numCols);
        int cC = 0, cR = 0;
        int a;
        for (byte b : this.stuff) {
            a = (int) b;
            switch (a) {
                case 95:
                    addTo(MazeCell.EMPTY, cR, cC); // _
                    cC++;
                    break;

                case 87:
                    addTo(MazeCell.WALL, cR, cC); // W
                    cC++;
                    break;

                case 83:
                    addTo(MazeCell.START, cR, cC); // S
                    numColS = cC;
                    numRowS = cR;
                    cC++;
                    break;

                case 69:
                    addTo(MazeCell.EXIT, cR, cC); // E
                    numColE = cC;
                    numRowE = cR;
                    cC++;
                    break;
                case 10:
                    cR++;
                    cC = 0;
                    break;
            }
        }
    }

    private boolean isValid(int ascii) {
        return ascii == 95 | ascii == 83 | ascii == 87 | ascii == 13 | ascii == 10 | ascii == 69;
    }
}


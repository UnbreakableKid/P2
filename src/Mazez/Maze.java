package Mazez;

import java.io.*;


public class Maze implements IMaze {

    public Maze(File f) throws Exception {

        if (goodCols(f)) {
            if (goodtoRead(f)) {
                read();
                }
            }
        }

    private final int ERROR = -1;
    private byte[] stuff;

    private MazeCell[][] maze;
    private int numColS, numRowS, numCols, numRows, numColE, numRowE;

    private static final int[] north = new int[]{-1, 0};
    private static final int[] south = new int[]{1, 0};
    private static final int[] west = new int[]{0, -1};
    private static final int[] east = new int[]{0, 1};

    Pawn p;

    public Move[] getOptions(Pawn p) {
        Move[] mov;
        int i = 0;
        for (Move move : Move.values()) {
            if (canMove(p, move))
                i++;
        }
        mov = new Move[i];
        for (Move move : Move.values()) {
            if (canMove(p, move))
                mov[i] = move;
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

    int[] getStartP() {
        return new int[]{numRowS, numColS};
    }

    public boolean isSolvedBy(Pawn p) {
        int[] a = p.position();
        return a[0] == numColE & a[1] == numRowE;
    }

    public void move(Pawn p, Move m) {
        if (canMove(p, m)) {
            p.move(m);
        } else {
            //no move
        }
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



    private void invalidChar(int cR, int cC) throws MazeFileWrongChar {
        throw new MazeFileWrongChar(cR, cC);
    }

    private void addTo(MazeCell type, int cR, int cC) {
        this.maze[cR][cC] = type;
    }

    private void read() {
        maze = new MazeCell[numRows][numCols];
        int cC = 0, cR = 0;
        int a;
        for (byte b : this.stuff) {
            a = (int) b;
            switch (a) {
                case 95:
                    addTo(MazeCell.EMPTY, cR, cC); // _
                    cC++;
                    break;

                case 85:
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
                    cC++;
                    numColE = cC;
                    numRowE = cR;
                    break;

                case 10:
                    cC = 0;
                    cR++;
                    break;
            }
        }
    }

    private boolean isValid(int ascii) {
        return ascii == 95 | ascii == 83 | ascii == 87 | ascii == 13 | ascii == 10 | ascii == 69;
    }
}


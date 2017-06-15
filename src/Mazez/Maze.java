package Mazez;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Maze implements IMaze {

    private int mode = 0;

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

    public Maze(int rows, int cols){
        numRows = rows;
        numCols = cols;
        maze =  new MazeCell[numRows][numCols];
        mode = 1;
        try{
            generate();
        }catch (Exception e){
            e.getMessage();
        }
        mode = 0;
    }

    public Maze(File f) throws Exception {

        if (goodCols(f)) {
            if (goodtoRead(f)) {
                read();
            }
        }
    }

    void generate() {

        setStartAndEnd();

        int i = 0;

        boolean canDo;

        try {

            do {
                populate();
                i++;

                //if it gets stuckerino

                if (i == 5) {
                    System.out.print(1);
                    throw new Exception("Couldn't do it");
                }

                canDo = Solve();

            } while (!canDo);
        } catch (Exception e) {
            generate();
        }
    }

    private MazeCell choose(int type){
        switch (type){
            case 0:
                return MazeCell.EMPTY;
            case 1:
                return MazeCell.WALL;
            case 2:
                return MazeCell.EMPTY;
        }
        return MazeCell.EMPTY;
    }

    private void populate(){

        for (int i = 0; i < numRows; i++){
            for (int ii = 0; ii < numCols; ii++){
                if ((i == numRowS && ii == numColS) || (i == numRowE && ii == numColE)){
                    continue;
                }
                MazeCell type = choose(r.nextInt(3));
                maze[i][ii] = type;
            }
        }
    }

    void printMaze() {
        int a = 0;
        for (int i = 0; i < numRows; i++) {
            for (int ii = 0; ii < numCols; ii++) {
                a++;
                System.out.println(maze[i][ii].toString());
            }
        }
        System.out.println(a);
    }

        private void setStartAndEnd(){

        numRowS = r.nextInt(numRows);
        numColS = r.nextInt(numCols);

        maze[numRowS][numColS] = MazeCell.START;

        do {
            numRowE = r.nextInt(numRows);
            numColE = r.nextInt(numCols);
        }

        while ((numRowS == numRowE) || (numColS == numColE));

        maze[numRowE][numColE] = MazeCell.EXIT;

        //done[numRowS][numColS] = 1;
        //done[numRowE][numColE] = 1;

    }

    private ArrayList<Point> casasAntigas = new ArrayList<Point>();
    private Queue<Pawn> pawns;


    boolean Solve() {
        pawns = new LinkedList<Pawn>();
        pawns.add(new Pawn(this));

        try {

            while (!isSolvedBy(pawns.peek())) {

                //System.out.println(pawns.peek());
                Point p = new Point(pawns.peek().position()[0], pawns.peek().position()[1]);
                if (!casasAntigas.contains(p)) {
                    //System.out.println("Adicionei nova antiga");
                    casasAntigas.add(p);
                }

                Move[] moves = getOptions(pawns.peek());

                if (moves.length == 0) {
                    //System.out.println("Sem saida");
                    //tira a head
                    //
                    pawns.poll();
                } else {

                    if (moves.length > 1) {
                        //adiciona ramifica��es
                        //System.out.println("Ramifica��o");
                        for (int i = 1; i < moves.length; i++) {
                            Pawn filho = new Pawn(this);
                            filho.setPosition(pawns.peek().position());
                            filho.move(moves[i]);
                            pawns.add(filho);
                            //System.out.println(pawns);
                        }
                    }
                    //continua a rota original
                    pawns.peek().move(moves[0]);

                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    private boolean SolveRoute() {
    	
    	
    	
       /*
        for(Move move : moves){
        	routes.add(new Route(p));
        }
        
        routes.get(0).move(m);
        */
        
      
        
        
        /*
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
         */
        //if stuck

        return false;
    }

    public Move[] getOptions(Pawn p) {
        ArrayList<Move> move = new ArrayList<Move>();
        for (Move m : Move.values()) {
            if (canMove(p, m)) {
            	//System.out.println("Can Move "+m);
                move.add(m);
            }
        } 
        return move.toArray(new Move[move.size()]);
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
        if (mode == 1){
            if(casasAntigas.contains(new Point(posToBe[0], posToBe[1]))){
                return false;
            }
        }
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


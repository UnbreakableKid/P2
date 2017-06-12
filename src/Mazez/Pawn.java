package Mazez;

public class Pawn implements IPawn {

    private int currentColl, currentRow;

    public Pawn (int col, int row) {
        setPosition(new int[]{col, row});
    }

    public Pawn(Maze m){
        int [] a = m.getStartP();
        setPosition(a);
    }

    public int[] position (){
        int[] a = new int[2];
        a[0]=currentRow;
        a[1]=currentColl;
        return a;
    }

    private void setPosition(int[] positions){
        this.currentRow = positions[0];
        this.currentColl = positions[1];
    }

    public void move (Move m){
        int [] a = position();
        int [] b = Maze.identifyM(m);
        setPosition(Maze.addMatrizes(a, b));
    }

    public Route getRoute() {return new Route();}
}

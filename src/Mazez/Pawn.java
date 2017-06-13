package Mazez;

public class Pawn implements IPawn {

    private Route r;

    private int currentColl, currentRow;

    public Pawn (int row, int col) {
        setPosition(new int[]{row, col});
        r = new Route(this);
    }

    public Pawn(Maze m){
        int [] a = m.getStartP();
        setPosition(a);
        r = new Route(this);
    }

    public int[] position (){
        int[] a = new int[2];
        a[0]=currentRow;
        a[1]=currentColl;
        return a;
    }

    void setPosition(int[] positions){
        this.currentRow = positions[0];
        this.currentColl = positions[1];
    }

    public void move (Move m){
        int [] a = position();
        int [] b = Maze.identifyM(m);
        setPosition(Maze.addMatrizes(a, b));
        r.move(m);
    }

    public Route getRoute() {return r;}
}

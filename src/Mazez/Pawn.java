package Mazez;

public class Pawn implements IPawn {

    private int currentColl, currentRow;

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

    static int[] addMatrizes (int[] a, int[] b){
        int c[] = new int[a.length];

        for (int i=0; i< a.length; i++){
            c[i]=a[i]+b[i];
        }
        return c;
    }

    private void setPosition(int[] positions){
        this.currentRow = positions[0];
        this.currentColl = positions[1];
    }


    public void move (Move m){
        int [] a = position();
        int [] b = Maze.identifyM(m);
        setPosition(addMatrizes(a, b));
    }

    public Route getRoute() {return new Route();}
}

package Mazez;

public class Pawn implements IPawn {

    private int currentColl, currentRow;

    public int[] position (){
        int[] a = new int[2];
        a[0]=currentRow;
        a[1]=currentColl;
        return a;
    }

    private int[] addMatrizes (int[] a, int[] b){
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

    public Pawn(Maze m){
        int [] a = m.getStartP();
        setPosition(a);
    }

    private void goTo (Move m){
        int [] a = position();
        int [] b = new int[2];
        switch (m){
            case NORTH:
                b = Maze.north;
                break;
            case SOUTH:
                b = Maze.south;
                break;
            case WEST:
                b = Maze.west;
                break;
            case EAST:
                b = Maze.east;
                break;
            case NOOP:
                b[0] = 0;
                b[1] = 0;
        }
        setPosition(addMatrizes(a, b));
    }

    public void move(Move m) {
        goTo(m);
    }

    public Route getRoute() {return new Route();}
}

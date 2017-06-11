import Mazez.Maze;
import Mazez.Move;
import Mazez.Pawn;

public class t {
    public static void main (String[] args) {
        Maze m = new Maze();
        m.start();
        Pawn p = new Pawn(m);
        System.out.println(p.position()[0]);
        System.out.println(p.position()[1]);
        m.move(p, Move.NORTH);
        System.out.println(p.position()[0]);
        System.out.println(p.position()[1]);
    }
}

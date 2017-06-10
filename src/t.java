import Mazez.Maze;

public class t {
    public static void main (String[] args) {
        Maze m = new Maze();
        m.start();
        System.out.print(m.isSolvedBy(m.p));
    }
}

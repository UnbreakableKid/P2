package Mazez;

import java.awt.*;
import javax.swing.*;

public class GraphPawn extends JPanel {

    private Pawn p;
    private final Color PAWN = new Color(255,0,0);

    public GraphPawn(Pawn paw){
        p = paw;
        setBackground(PAWN);
        setSize(GraphMaze.SQUARE_SIZE, GraphMaze.SQUARE_SIZE);
        int[] a = p.position();
        setLocation(GraphMaze.BORDER + GraphMaze.SQUARE_SIZE * a[1], GraphMaze.BORDER + GraphMaze.SQUARE_SIZE * a[0]);
    }

    public void changeLocation(){
        int[] a = p.position();
        setLocation(GraphMaze.BORDER + GraphMaze.SQUARE_SIZE * a[1], GraphMaze.BORDER + GraphMaze.SQUARE_SIZE * a[0]);
    }
}

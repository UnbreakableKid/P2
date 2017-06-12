package Mazez;

import java.awt.*;
import javax.swing.*;

public class GraphPawn extends JPanel {

    private final Color PAWN = new Color(255,0,0);

    public GraphPawn(Pawn p){
        setBackground(PAWN);
        setSize(GraphMaze.SQUARE_SIZE, GraphMaze.SQUARE_SIZE);
        int[] a = p.position();
        System.out.println(a[0]);
        System.out.println(a[1]);
        setLocation(GraphMaze.BORDER + GraphMaze.SQUARE_SIZE * a[1], GraphMaze.BORDER + GraphMaze.SQUARE_SIZE * a[0]);
    }
}

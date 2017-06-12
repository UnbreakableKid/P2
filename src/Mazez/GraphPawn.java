package Mazez;

import java.awt.*;
import javax.swing.*;

public class GraphPawn extends JPanel {

    private final Color PAWN = new Color(105,105,105);
    private final int pawnW = 1, pawnH = 1;

    public GraphPawn(Pawn p){
        this.setBackground(PAWN);
        this.setSize(pawnW,pawnH);
        int[] pos = p.position();
        this.setLocation(pos[0], pos[1]);
    }

}

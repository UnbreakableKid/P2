package Mazez;

import java.awt.*;
import javax.swing.*;

public class GraphPawn extends JPanel {

    private final Color PAWN = new Color(055,055,055);
    private final int pawnW = 5, pawnH = 5;

    public GraphPawn(Pawn p){
        this.setBackground(PAWN);
        this.setSize(pawnW,pawnH);
        int[] pos = p.position();
        this.setLocation(pos[0], pos[1]);
    }

}

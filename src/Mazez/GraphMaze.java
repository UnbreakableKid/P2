package Mazez;

import javax.swing.*;

public class GraphMaze extends JFrame{

    private final int X_TO_ORIGIN = 100;
    private final int Y_TO_ORIGIN = 100;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public GraphMaze(){

        setVisible(true);
        setTitle("Playing maze...");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocation(X_TO_ORIGIN, Y_TO_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

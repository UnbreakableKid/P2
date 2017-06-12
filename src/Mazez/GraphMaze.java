package Mazez;

import javax.swing.*;
import java.awt.*;

public class GraphMaze extends JFrame{

    private JButton left, right, up, down;
    Container contentPane;

    private final int X_TO_ORIGIN = 100;
    private final int Y_TO_ORIGIN = 100;
    static final int SQUARE_SIZE = 30;
    static final int BORDER = 25;

    public GraphMaze(Maze m){

        setVisible(true);
        setTitle("Playing maze...");

        int [] a = m.getDims();
        setSize((a[1]*SQUARE_SIZE) + 200 + BORDER, (a[0]*SQUARE_SIZE)+ 200 + BORDER);
        setResizable(false);
        setLocation(X_TO_ORIGIN, Y_TO_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = getContentPane();
        contentPane.setBackground(new Color(155,155,155));
        contentPane.setLayout(new GridLayout(m.getDims()[0]+10, m.getDims()[1]));

        GraphPawn gp = new GraphPawn(m.p);
        gp.setVisible(true);
        contentPane.add(gp);

        drawMaze(m);

        revalidate();
        repaint();

        left = new JButton("left");
        //left.setSize(50, 50);
        left.setLocation(350, 100);

        right = new JButton("right");
        //right.setSize(50, 50);
        right.setLocation(150, 100);

        up = new JButton("up");
        //up.setSize(50, 50);
        up.setLocation(200, 0);

        down = new JButton("down");
        //down.setSize(50, 50);
        down.setLocation(200, 200);

        contentPane.add(left);
        contentPane.add(down);
        contentPane.add(up);
        contentPane.add(right);
    }

    private void drawMaze(Maze m){
        MazeCell mc;
        MazeCell[][] ma = m.getMaze();
        int [] a = m.getDims();
        for (int row = 0; row < a[0]; row++) {
            for (int col = 0; col < a[1]; col++) {
                mc = ma[row][col];
                drawType(mc, col, row);
            }
        }
    }


    private void drawType (MazeCell mc, int col, int row){
        switch (mc){
            case WALL:
                Wall w = new Wall(col, row);
                contentPane.add(w);
                break;
            case EMPTY:
                Empty e = new Empty(col, row);
                contentPane.add(e);
                break;
            case EXIT:
                Exit ex = new Exit(col, row);
                contentPane.add(ex);
                break;
            case START:
                break;
        }
    }

    abstract class Drawables extends JPanel{

        public Drawables(int row, int col){
            //this.setSize(GraphMaze.SQUARE_SIZE, GraphMaze.SQUARE_SIZE);
            this.setLocation(GraphMaze.BORDER + row * GraphMaze.SQUARE_SIZE ,GraphMaze.BORDER + col * GraphMaze.SQUARE_SIZE );
        }
    }

    class Wall extends Drawables {

        private final Color WALL = new Color(0,0,0);
        private Color type = WALL;


        public Wall(int row, int col){
            super(row, col);
            this.setBackground(type);
        }
    }

    class Empty extends Drawables {

        private final Color EMPTY = new Color(255,255,255);
        private Color type = EMPTY;

        public Empty(int row, int col){
            super(row, col);
            this.setBackground(type);
        }
    }

    class Exit extends Drawables{

        private final Color EXIT = new Color(100,100,255);
        private Color type = EXIT;

        public Exit(int row, int col){
            super(row, col);
            this.setBackground(type);
        }
    }

}

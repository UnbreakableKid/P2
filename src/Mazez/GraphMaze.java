package Mazez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphMaze extends JFrame{

    private JButton left, right, up, down;
    private Container contentPane;
    private Maze m;
    private Pawn p;
    private GraphPawn gp;

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private final int X_TO_ORIGIN = 100;
    private final int Y_TO_ORIGIN = 100;
    static final int SQUARE_SIZE = 30;
    static final int BORDER = 50;

    public GraphMaze(Maze maz){

        this.m = maz;
        setTitle("Playing maze...");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setResizable(false);
        setResizable(false);
        setLocation(X_TO_ORIGIN, Y_TO_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPane = getContentPane();
        contentPane.setBackground(new Color(155,155,155));
        contentPane.setLayout(null);

        p = new Pawn(m);

        gp = new GraphPawn(p);
        gp.setVisible(true);
        contentPane.add(gp);

        drawMaze();

        left = new JButton("left");
        left.setSize(100, 25);
        left.setLocation(25, 10);

        right = new JButton("right");
        right.setSize(100, 25);
        right.setLocation(25+100+25, 10);

        up = new JButton("up");
        up.setSize(100, 25);
        up.setLocation(25+25+25+100+100, 10);

        down = new JButton("down");
        down.setSize(100, 25);
        down.setLocation(25+25+100+25+25+100+100, 10);

        DirectionButton db = new DirectionButton();

        up.addActionListener(db);
        down.addActionListener(db);
        right.addActionListener(db);
        left.addActionListener(db);

        contentPane.add(left);
        contentPane.add(down);
        contentPane.add(up);
        contentPane.add(right);
    }

    private void drawMaze(){
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

    private void haveWon(){
        this.setVisible(false);
        Menu m = new Menu();
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
                Start s = new Start(col, row);
                contentPane.add(s);
                break;
        }
    }

    abstract class Drawables extends JPanel{

        public Drawables(int row, int col){
            this.setSize(GraphMaze.SQUARE_SIZE, GraphMaze.SQUARE_SIZE);
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

    class DirectionButton implements ActionListener {

        public void actionPerformed (ActionEvent evt){
            JButton clickedButton = (JButton) evt.getSource();
            if (clickedButton.equals(up)){
                m.move(p, Move.NORTH);
            }
            if (clickedButton.equals(down)){
                m.move(p, Move.SOUTH);
            }
            if (clickedButton.equals(left)){
                m.move(p, Move.WEST);
            }
            if (clickedButton.equals(right)){
                m.move(p, Move.EAST);
            }
            gp.changeLocation();
            if(m.isSolvedBy(p)){
                haveWon();
            }
        }

    }

    class Start extends Drawables{

        private final Color START = new Color(220,150,155);
        private Color type = START;

        public Start(int row, int col){
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

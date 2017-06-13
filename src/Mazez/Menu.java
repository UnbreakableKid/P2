package Mazez;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JFrame{

    static private File f = new File("E:/PC/New/P2/src/d.maze");

    private JButton play;
    private Maze m;

    private final int EACHW = 150;
    private final int EACHH = 30;
    private final int X_TO_ORIGIN = 100;
    private final int Y_TO_ORIGIN = 100;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public static void main(String[] args){
        //Menu m = new Menu();
        try{
            Maze ma = new Maze(f);
            Pawn pa = new Pawn(ma);
            ma.Solve();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Menu(){

        setTitle("Mazes");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setResizable(false);
        setLocation(X_TO_ORIGIN, Y_TO_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        play = new JButton("Play");
        play.setSize(EACHW, EACHH);
        play.setLocation(40, 460);

        Container contentPane = getContentPane();
        //contentPane.setLayout(new FlowLayout());
        contentPane.setLayout(null);
        contentPane.add(play);

        PlayButton toplay = new PlayButton();
        play.addActionListener(toplay);

    }

    class PlayButton implements ActionListener {

        private File f = new File("E:/PC/New/P2/src/d.maze");

        public void actionPerformed (ActionEvent evt){

            JButton clickedButton = (JButton) evt.getSource();
            JRootPane rootPane = clickedButton.getRootPane();
            Frame frame = (JFrame) rootPane.getParent();
            frame.setVisible(false);
            //chooseOne();

            try {
                m = new Maze(f);
                GraphMaze playmaze = new GraphMaze(m);
            }catch (Exception e){
                frame.setVisible(true);
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
        private void chooseOne() {
            JButton b = new JButton();
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Maze file", "maze");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(b);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                f = chooser.getSelectedFile();
            }
        }
    }
}

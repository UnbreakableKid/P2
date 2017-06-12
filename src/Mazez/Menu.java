package Mazez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame{

    private JButton play, choosefile;

    private final int EACHW = 80;
    private final int EACHH = 30;
    private final int X_TO_ORIGIN = 100;
    private final int Y_TO_ORIGIN = 100;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

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

        choosefile = new JButton("Choose a maze");

        Container contentPane = getContentPane();
        //contentPane.setLayout(new FlowLayout());
        contentPane.setLayout(null);
        contentPane.add(play);
        contentPane.add(choosefile);

        PlayButton toplay = new PlayButton( );

        play.addActionListener(toplay);
        //choosefile.addActionListener();

    }

    class PlayButton implements ActionListener {

        public void actionPerformed (ActionEvent evt){

        }
    }
}

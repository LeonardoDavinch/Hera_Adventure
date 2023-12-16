package org.example;


import javax.swing.*;

public class App {

    public  static  JFrame window;

    public static void main( String[] args ) {
        window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hera's Adventure ");
        //window.setUndecorated(true);


        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);




        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}

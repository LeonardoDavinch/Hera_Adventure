package org.example;


import javax.swing.*;

public class App {

    public  static  JFrame window;

    public static void main( String[] args ) {
        window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hera's Adventure ");
        new App().setIcon();

        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreanOn == true){
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    public  void setIcon(){

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("player/Walking sprites/boy_down_2.png"));
        window.setIconImage(icon.getImage());
    }
}

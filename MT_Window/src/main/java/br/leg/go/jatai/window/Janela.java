package br.leg.go.jatai.window;

import javax.swing.JFrame;

public class Janela extends JFrame{
    Janela(){
        //Janela
            this.setTitle("CMJ");
            this.setSize(700, 350);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
    }
}

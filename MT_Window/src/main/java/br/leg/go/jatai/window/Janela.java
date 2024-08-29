package br.leg.go.jatai.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame{
    //Atributos
        private JPanel pnDados, pnBotoes;
        private JTextField tfUser, tfSenha, tfUrl;
        private JLabel lbUser, lbSenha, lbUrl;
        private JButton btLogin;
        private Container tela;
    
    Janela(){
     //Configuração de container
        tela = this.getContentPane();
        tela.setLayout(new BorderLayout());

     //Configuração dos painéis
        pnDados = new JPanel();
        pnBotoes = new JPanel();
        
     //Configurando os painéis no container tela
        tela.add(pnDados,BorderLayout.CENTER);
        tela.add(pnBotoes,BorderLayout.SOUTH);
        
    //Adicionando cores aos painéis
        pnDados.setBackground(Color.CYAN);
        pnBotoes.setBackground(Color.GRAY);
        
    //Configuração dos campos
        lbUser = new JLabel("Usuário");
        lbSenha = new JLabel("Senha");
        lbUrl = new JLabel("URL:");
        
        tfUser = new JTextField(20);
        tfSenha = new JTextField(20);
        tfUrl = new JTextField(50);
        
        btLogin = new JButton("Login");
        
    //Adicionando os campos ao painel
        pnDados.setLayout(new GridLayout(3,2));
        pnDados.add(lbUser);
        pnDados.add(tfUser);
        pnDados.add(lbSenha);
        pnDados.add(tfSenha);
        pnDados.add(lbUrl);
        pnDados.add(tfUrl);
        
    //Adicionando os botões ao painel
        pnBotoes.setLayout(new GridLayout(1,1));
        pnBotoes.add(btLogin);
    //Configurações iniciais de janela
            this.setTitle("CMJ");
            this.setSize(700, 350);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
    }
}

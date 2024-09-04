package br.leg.go.jatai.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Janela extends JFrame implements ActionListener{
    //Atributos
        private JPanel pnDados, pnBotoes, pnToken;
        private JTextArea taToken;
        private JTextField tfUser, tfUrl;
        private JPasswordField tfSenha;
        private JLabel lbUser, lbSenha, lbUrl;
        private JButton btLogin, btGet;
        private String urlString, userString,passwordString,tokenString;
        private Container tela;
    
    Janela(){
      //Carregando a URL string e a token string
        urlString = "https://www.jatai.go.leg.br/api/auth/token";
        tokenString = "";
        
     //Configuração de container
        tela = this.getContentPane();
        tela.setLayout(new BorderLayout());

     //Configuração dos painéis
        pnDados = new JPanel();
        pnToken = new JPanel();
        pnBotoes = new JPanel();
        
     //Configurando os painéis no container tela
        tela.add(pnDados,BorderLayout.NORTH);
        tela.add(pnToken,BorderLayout.CENTER);
        tela.add(pnBotoes,BorderLayout.SOUTH);
        
    //Adicionando cores aos painéis
        pnDados.setBackground(Color.CYAN);
        pnBotoes.setBackground(Color.GRAY);
        
    //Configuração dos campos
        lbUser = new JLabel("Usuário");
        lbSenha = new JLabel("Senha");
        lbUrl = new JLabel("URL:");
        
        tfUser = new JTextField(20);
        tfSenha = new JPasswordField(20);
        tfUrl = new JTextField(50);
        
     //Deixando impossível mexer nesse campo de texto
        tfUrl.setEditable(true);
        
     //Definindo a área de texto com o token
        taToken = new JTextArea(300,300);
        
     //Criando o botão de login e de get
        btLogin = new JButton("Login");
        btGet = new JButton("Get");
        
    //Adicionando os campos ao painel
        pnDados.setLayout(new GridLayout(3,2));
        pnDados.add(lbUser);
        pnDados.add(tfUser);
        pnDados.add(lbSenha);
        pnDados.add(tfSenha);
        pnDados.add(lbUrl);
        pnDados.add(tfUrl);
        
    //Adicionando o painel da text area para o token
        pnToken.setLayout(new GridLayout(1,1));
        pnToken.add(taToken);
    
    //Adicionando os botões ao painel
        pnBotoes.setLayout(new GridLayout(1,2));
        pnBotoes.add(btLogin);
        pnBotoes.add(btGet);
        
    //Adicionando um listener no botão
        btLogin.addActionListener(this);
        btGet.addActionListener(this);
   
    //Configurações iniciais de janela
            this.setTitle("CMJ");
            this.setSize(1000, 1000);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Adicionando um Action Listener em login
            if(e.getSource()==btLogin){
            String userString = tfUser.getText();
            String passwordString = new String(tfSenha.getPassword());
            String urlString = tfUrl.getText();
                 try {
                        //Cria a URL e abre a conexão
                            URL url = new URL(urlString);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                
                        //Configura a conexão para POST
                            connection.setRequestMethod("POST");
                            connection.setDoOutput(true);
                            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                        //Cria a string de parâmetros
                            String params="username="+userString+"&password="+passwordString;

                        //Envia os parâmetros
                            OutputStream os = connection.getOutputStream();
                            os.write(params.getBytes(StandardCharsets.UTF_8));
                            os.flush();
                            os.close();

                        //Lê a resposta
                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String inputLine;
                            StringBuilder response = new StringBuilder();
                                while((inputLine=in.readLine())!=null){
                                    response.append(inputLine);
                                }
                            in.close();
                
                        //Adicionando a resposta a variavel que armazena o token     
                            tokenString = response.toString();
                
                        //Verifica se a resposta é HTTP OK
                            int responseCode = connection.getResponseCode();
                            if(responseCode == HttpURLConnection.HTTP_OK){
                               taToken.setText(tokenString);
                            }else{
                                JOptionPane.showMessageDialog(this, "Erro no login: " + responseCode);
                            }
                            
                       //Fecha a conexão
                            connection.disconnect();

                        }catch(Exception ex){
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this,"Erro ao tentar se conectar:" +ex.getMessage());
                        }    
        }
            
             if(e.getSource()==btGet){
                String userString = tfUser.getText();
                String passwordString = tokenString;
                String urlString = tfUrl.getText();
                
                try{
                    

                }catch(Exception ex){
                    
                }
             }
            
    }
}
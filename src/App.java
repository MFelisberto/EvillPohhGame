import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame implements ActionListener{
    
    private Tabuleiro tabuleiro;
    private Personagem personagem;
    private Inimigo inimigo;
    private Porta porta;
    
    public App(String niv,String nivelAt) {
        super();
        // Define os componentes da tela
        tabuleiro = new Tabuleiro(this,nivelAt);
        GridBagConstraints cnt= new GridBagConstraints();
        
        JPanel botoesDirecao = new JPanel(new GridBagLayout());
        
        JButton butDir = new JButton("Direita");
        butDir.addActionListener(this);
        JButton butEsq = new JButton("Esquerda");
        butEsq.addActionListener(this);
        JButton butCima = new JButton("Acima");
        butCima.addActionListener(this);
        JButton butBaixo = new JButton("Abaixo");
        butBaixo.addActionListener(this);
        

        cnt.gridx=0;
        cnt.gridy=1;
        botoesDirecao.add(butEsq,cnt);
        cnt.gridx=2;
        cnt.gridy=1;
        botoesDirecao.add(butDir,cnt);
        cnt.gridx=1;
        cnt.gridy=0;
        botoesDirecao.add(butCima,cnt);
        cnt.gridx=1;
        cnt.gridy=1;
        botoesDirecao.add(butBaixo,cnt);
        
              
        JPanel painelGeral = new JPanel();
        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.PAGE_AXIS));
        painelGeral.add(botoesDirecao);
        painelGeral.add(tabuleiro);
        
        // Insere os personagens no tabuleiro
        tabuleiro.loadLevel(niv);
        personagem = tabuleiro.getPrincipal();
        personagem.setAnterior(personagem.getAnterior());
        inimigo = tabuleiro.getAntg();
        inimigo.setAnterior(inimigo.getAnterior());
        porta = tabuleiro.getPort();
        porta.setAnterior(porta.getAnterior());
       
        // Exibe a janela
        this.add(painelGeral);
        this.setSize(900,830);
        this.setTitle("Evil Pooh");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        tabuleiro.atualizaVisualizacao();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton but = (JButton)arg0.getSource();
        if (but.getText().equals("Direita")){
            personagem.moveDireita();
            inimigo.movePerseguir(personagem);
            tabuleiro.verificarArmadilha(personagem);
            tabuleiro.verificarPrisioneiro(personagem);
            if (porta.verificarColisao(personagem)) {
                this.dispose(); // Fecha a janela atual
            }
        }
        if (but.getText().equals("Esquerda")){
            personagem.moveEsquerda();
            inimigo.movePerseguir(personagem);
            tabuleiro.verificarArmadilha(personagem);
            tabuleiro.verificarPrisioneiro(personagem);
            if (porta.verificarColisao(personagem)) {
                this.dispose(); // Fecha a janela atual
                
            }
        }
        if (but.getText().equals("Acima")){
            personagem.moveCima();
            inimigo.movePerseguir(personagem);
            tabuleiro.verificarArmadilha(personagem);
            tabuleiro.verificarPrisioneiro(personagem);
            if (porta.verificarColisao(personagem)) {
                this.dispose(); // Fecha a janela atual
                 // Encerra o método actionPerformed para evitar execuções adicionais desnecessárias
            }
        }
        if (but.getText().equals("Abaixo")){
            personagem.moveBaixo();
            inimigo.movePerseguir(personagem);
            tabuleiro.verificarArmadilha(personagem);
            tabuleiro.verificarPrisioneiro(personagem);
            if (porta.verificarColisao(personagem)) {
                this.dispose(); // Fecha a janela atual
                 // Encerra o método actionPerformed para evitar execuções adicionais desnecessárias
            }
        }
        tabuleiro.atualizaVisualizacao();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App("nivel5.txt","nivel5.txt");
            }
        });
    }
    
}

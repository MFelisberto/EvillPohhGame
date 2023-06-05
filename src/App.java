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

    public App() {
        super();
        // Define os componentes da tela
        tabuleiro = new Tabuleiro();
        
        JPanel botoesDirecao = new JPanel(new FlowLayout());
        
        JButton butDir = new JButton("Direita");
        butDir.addActionListener(this);
       
        JButton butEsq = new JButton("Esquerda");
        butEsq.addActionListener(this);
        
        JButton butCima = new JButton("Acima");
        butCima.addActionListener(this);
        
        JButton butBaixo = new JButton("Abaixo");
        butBaixo.addActionListener(this);
        
        botoesDirecao.add(butEsq);
        botoesDirecao.add(butDir);
        botoesDirecao.add(butCima);
        botoesDirecao.add(butBaixo);
        
      
        JPanel painelGeral = new JPanel();
        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.PAGE_AXIS));
        painelGeral.add(botoesDirecao);
        painelGeral.add(tabuleiro);
        
        
        // Insere os personagens no tabuleiro
        tabuleiro.loadLevel(1);
        personagem = tabuleiro.getPrincipal();
        personagem.setAnterior(personagem.getAnterior());
        
        // Exibe a janela
        this.add(painelGeral);
        this.setSize(900,650);
        this.setTitle("Dungeon raider");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        tabuleiro.atualizaVisualizacao();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton but = (JButton)arg0.getSource();
        if (but.getText().equals("Direita")){
            personagem.moveDireita();
        }
        if (but.getText().equals("Esquerda")){
            personagem.moveEsquerda();
        }
        if (but.getText().equals("Acima")){
            personagem.moveCima();
        }
        if (but.getText().equals("Abaixo")){
            personagem.moveBaixo();
        }
        tabuleiro.atualizaVisualizacao();
    }


    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
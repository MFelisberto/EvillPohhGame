import javax.swing.JOptionPane;

public class Armadilha extends ElementoBasico {
    private Sounds som = Sounds.HIT;
    public Armadilha(String id,String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id,iconPath, linInicial, colInicial, tabuleiro);
    }
    
    public void play(){
        som.play(true);

    } 

    @Override
    public void acao(ElementoBasico outro) {
        if (outro instanceof Personagem) {
            JOptionPane.showMessageDialog(null, "Você perdeu o jogo!");
            System.exit(0); // Fecha o jogo
        }
    }
}

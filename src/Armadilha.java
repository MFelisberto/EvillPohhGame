import javax.swing.JOptionPane;

public class Armadilha extends ElementoBasico {
    public Armadilha(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "portal.png", linInicial, colInicial, tabuleiro);
    }

    @Override
    public void acao(ElementoBasico outro) {
        if (outro instanceof Personagem) {
            JOptionPane.showMessageDialog(null, "Você perdeu o jogo!");
            System.exit(0); // Fecha o jogo
        }
    }
}

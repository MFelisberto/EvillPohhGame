import javax.swing.JOptionPane;

public class Armadilha extends ElementoBasico {
    
    public Armadilha(String id,String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id,iconPath, linInicial, colInicial, tabuleiro);
    }
    
    @Override
    public void acao(ElementoBasico outro) {
        SoundPlayer soundPlayer = new SoundPlayer();
        if (outro instanceof Personagem) {
            JOptionPane.showMessageDialog(null, "Você morreu!");
            soundPlayer.playSound("die.wav");
            soundPlayer.stopSound();
            System.exit(0); // Fecha o jogo
        }
    }
}

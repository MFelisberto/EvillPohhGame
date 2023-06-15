import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Personagem extends ElementoBasico {

    private String pri;
    private ElementoBasico anterior;

    public Personagem(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro,String pri) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
        this.pri = pri;
        
        
    }

    public String getPri() {
        return pri;
    }

    public void setAnterior(ElementoBasico anterior){
        this.anterior = anterior;
    }

    public ElementoBasico getAnterior() {
        return anterior;
    }

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = App.class.getResource("imagens/"+path);
        
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            System.exit(0);
            return null;
        }
    }
    
    public void moveDireita() { // DIREITA
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.incCol();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Fundo)) {
            elemento.acao(this);
            this.decCol();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void moveEsquerda() { // ESQUERDA
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.decCol();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Fundo)) {
            elemento.acao(this);
            this.incCol();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void moveCima() { // CIMA
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.decLin();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Fundo)) {
            elemento.acao(this);
            this.incLin();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void moveBaixo() { // BAIXO
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.incLin();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Fundo)) {
            elemento.acao(this);
            this.decLin();
            this.anterior = getTabuleiro().insereElemento(this);
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    @Override
public void acao(ElementoBasico outro) {
    SoundPlayer soundPlayer = new SoundPlayer();
    
    if (outro instanceof Inimigo) {
        this.setImage(createImageIcon("dead.png"));
        getTabuleiro().insereElemento(this);
        soundPlayer.playSound("die.wav");
                soundPlayer.stopSound();
                try {
                    Thread.sleep(1); // Espera para poder dar o som 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        JOptionPane.showMessageDialog(null, "Você perdeu o jogo!");
        try {
            Thread.sleep(1); // Espera para poder dar o som 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);; // Fecha o jogo
    }else if (outro instanceof Prisioner) {
        Prisioner prisioner = (Prisioner) outro;
        this.pri = prisioner.getCod(); // Define o código do prisioneiro como o valor de "pri" do personagem

        // Substituir o prisioneiro por uma parede
        int lin = prisioner.getLin();
        int col = prisioner.getCol();
        Parede parede = new Parede("Parede", lin, "wall.png", col, getTabuleiro());
        getTabuleiro().insereElemento(parede);
        getTabuleiro().removeElemento(prisioner); 
        
    }
}

   
}

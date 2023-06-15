import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class  Inimigo extends ElementoBasico {
   
    private ElementoBasico anterior;
    
    public Inimigo(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
    }
    public void setAnterior(ElementoBasico anterior){
        this.anterior = anterior;
    }
    public ElementoBasico getAnterior() {
        return anterior;
    }
    
    
    public void moveDireita() {
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

    public void moveEsquerda() {
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

    public void moveCima() {
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

    public void moveBaixo() {
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

    public void movePerseguir(Personagem personagem) {
        int distLin = Math.abs(personagem.getLin() - this.getLin());
        int distCol = Math.abs(personagem.getCol() - this.getCol());
    
        if (distLin <= 4 && distCol <= 4 && (distLin == 0 || distCol == 0)) {
            // Remove o inimigo da posição atual e avança em direção ao personagem
            getTabuleiro().insereElemento(anterior);
    
            if (distLin == 0) {
                if (personagem.getCol() < this.getCol()) {
                    this.decCol();
                } else if (personagem.getCol() > this.getCol()) {
                    this.incCol();
                }
            } else if (distCol == 0) {
                if (personagem.getLin() < this.getLin()) {
                    this.decLin();
                } else if (personagem.getLin() > this.getLin()) {
                    this.incLin();
                }
            }
    
            // Verifica se tem algum elemento de interesse na nova posição e interage de acordo
            ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
            if (!(elemento instanceof Fundo)) {
                elemento.acao(this);
                this.anterior = getTabuleiro().insereElemento(this);
            } else {
                this.anterior = getTabuleiro().insereElemento(this);
            }
        } else {
            // Se o personagem estiver longe demais, move-se aleatoriamente
            moveAleat();
        }
    }

    public void moveAleat(){
        int min = 0; 
        int max = 3; 
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        getTabuleiro().insereElemento(anterior);
        switch(randomNumber){
            case 0:
                this.moveDireita();
            break;
            case 1:
                this.moveEsquerda();
            break;
            case 2:
                this.moveCima();;
            break;
            case 3:
                this.moveBaixo();
            break;
        }
       



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

    @Override
    public void acao(ElementoBasico outro) {
        SoundPlayer soundPlayer = new SoundPlayer();
       
        
        if (outro instanceof Personagem) {
            if (this.getLin() == outro.getLin() && this.getCol() == outro.getCol()) {
                Personagem per = (Personagem) outro;
        
                per.setImage(createImageIcon("dead.png"));
                getTabuleiro().insereElemento(per);
                soundPlayer.playSound("die.wav");
                soundPlayer.stopSound();
                JOptionPane.showMessageDialog(null, "Você perdeu o jogo!");
                try {
                    Thread.sleep(1); // Espera para poder dar o som 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                System.exit(0);
                 // Fecha o jogo
            }
            
            Personagem per = (Personagem) outro;
                per.setImage(createImageIcon("wall.png"));
                getTabuleiro().insereElemento(per);
                soundPlayer.playSound("die.wav");
                soundPlayer.stopSound();
                try {
                    Thread.sleep(1); // Espera para poder dar o som 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                System.exit(0);
            JOptionPane.showMessageDialog(null, "Você perdeu o jogo!");
                System.exit(0); 
        }
    }
}

import java.util.Random;

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

    @Override
    public void acao(ElementoBasico outro) {
        throw new UnsupportedOperationException("Unimplemented method 'acao'");
    }
}
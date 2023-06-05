public class Pista extends ElementoBasico{
    private boolean fechada;
    private int nroPista;

    public Pista(String id, int nroPista, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "hint.jpg", linInicial, colInicial, tabuleiro);
        this.fechada = true;
        this.nroPista = nroPista;
    }

    public int getNroPista(){
        return nroPista;
    }

    @Override
    public void acao(ElementoBasico outro) {
        if (fechada){
            fechada = false;
           // setImage(Tabuleiro.createImageIcon("Pista"+nroPista+".jpg"));
           setImage(Tabuleiro.createImageIcon("hint.jpg"));
        }else{
            fechada = true;
            setImage(Tabuleiro.createImageIcon("hintFundo.jpg"));
        }
    }    
}

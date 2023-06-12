public class Fundo extends ElementoBasico{

    public Fundo(String id, int linInicial,String iconPath, int colInicial, Tabuleiro tabuleiro) {
        super(id, "parquet.png", linInicial, colInicial, tabuleiro);
      
        
    }

    @Override
    public void acao(ElementoBasico outro) {
        throw new UnsupportedOperationException("Unimplemented method 'acao'");
    }
}



public class TBD extends ElementoBasico {


  public TBD(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
      super(id, iconPath, linInicial, colInicial, tabuleiro);
  }
  @Override
  public void acao(ElementoBasico outro) {
    String s = String.format("Action: %s (%d, %d)",
                      this.getId(),getLin(), getCol());
      System.out.println(s);
  }

 
}


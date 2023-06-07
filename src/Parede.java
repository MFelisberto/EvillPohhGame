

public class Parede extends ElementoBasico {


    public Parede(String id, int linInicial,String iconPath, int colInicial, Tabuleiro tabuleiro) {
        super(id, "wall.jpg", linInicial, colInicial, tabuleiro);
        
    }

    @Override
    public void acao(ElementoBasico outro) {

    }
}

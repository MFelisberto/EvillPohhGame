public class Prisioner extends ElementoBasico {
    private ElementoBasico anterior;
    private String cod;

    public Prisioner(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro,String cod ) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
        this.cod = cod;
        
    }

    public String getCod() {
        return cod;
    }

    public void setAnterior(ElementoBasico anterior){
        this.anterior = anterior;
    }
    public ElementoBasico getAnterior() {
        return anterior;
    }


    @Override
    public void acao(ElementoBasico outro){
        if (outro instanceof Personagem) {
            Personagem personagem = (Personagem) outro;
            personagem.setPri(getCod()); // Define o código do prisioneiro como o valor do atributo "pri" do personagem
            int lin = getLin();
            int col = getCol();
            Parede parede = new Parede("Parede", lin, "wall.png", col, getTabuleiro());
            getTabuleiro().insereElemento(parede);
            getTabuleiro().removeElemento(this); 
        }
    }

    }
    


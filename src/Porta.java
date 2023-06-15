import javax.swing.SwingUtilities;

public class Porta extends ElementoBasico {
    private Nivel nivel;
    private ElementoBasico anterior;
    private App app;

    public Porta(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro, Nivel nivel, App app) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
        this.nivel = nivel;
        this.app = app;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public ElementoBasico getAnterior() {
        return anterior;
    }

    public void setAnterior(ElementoBasico anterior) {
        this.anterior = anterior;
    }

    public boolean verificarColisao(Personagem personagem) {
        int linPersonagem = personagem.getLin();
        int colPersonagem = personagem.getCol();

        if (linPersonagem == getLin() && colPersonagem == getCol()) {
            return true;
        }

        return false;
    }

    @Override
    public void acao(ElementoBasico outro) {
        if (outro instanceof Personagem && nivel != null) {
            Personagem personagem = (Personagem) outro;
            if (verificarColisao(personagem)) {
                String proximoNivel = carregarProximoNivel();
                if (proximoNivel != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            app.dispose(); // Fechar a janela atual antes de abrir uma nova
                            new App(proximoNivel); // Carregar um novo App com o próximo nível
                        }
                    });
                } else {
                    System.out.println("Não há mais níveis disponíveis!");
                }
            }
        }
    }

    public String carregarProximoNivel() {
        nivel.avancarProximaPosicao();
        return nivel.getCurrentContent();
    }
}
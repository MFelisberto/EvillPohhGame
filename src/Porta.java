import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Porta extends ElementoBasico {
    private Nivel nivel;
    private ElementoBasico anterior;
    private App app;
    private String nivelAt;

    public Porta(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro, Nivel nivel, App app,String nivelAt) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
        this.nivel = nivel;
        this.app = app;
        this.nivelAt = nivelAt;
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
                switch (personagem.getPri()) {
                    case "g":
                    case "i":
                    case "l":
                        String proximoNivel = carregarProximoNivel();
                        if (proximoNivel != null) {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    app.dispose(); // Fechar a janela atual antes de abrir uma nova
                                    new App(proximoNivel, proximoNivel); // Carregar um novo App com o próximo nível
                                }
                            });
                        } else {
                            System.out.println("Não há mais níveis disponíveis!");
                        }
                        break;
                    default:
                        // Caso em que o código do prisioneiro não corresponde a nenhum dos casos permitidos
                        JOptionPane.showMessageDialog(null, "Prisioneiro ainda não foi resgatado");
                        break;
                }
            }
        }
    }
    
    public String carregarProximoNivel() {
        String proximoNivel = nivel.proximoElemento(nivelAt);
        return proximoNivel;
    }
}
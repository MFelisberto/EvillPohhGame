
import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private List<String> contents;
    private int currentIndex;

    public Nivel() {
        this.contents = new ArrayList<>();
        this.currentIndex = 0;
    }
    public void adicione(String nomeArquivo) {
        contents.add(nomeArquivo);
     
    }

    public String getCurrentContent() {
        return contents.get(currentIndex);
    }
    public String getPrimeiraPosicao() {
        if (contents.isEmpty()) {
            return null; // Retorna null se a lista de arquivos estiver vazia
        }
        return contents.get(0); // Retorna o primeiro arquivo da lista
    }

    public void avancarProximaPosicao() {
        currentIndex++;
       
    }
    
}
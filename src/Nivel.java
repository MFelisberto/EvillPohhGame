
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
    public String proximoElemento(String elemento) {
        int index = contents.indexOf(elemento);
        if (index != -1) {
            int proximoIndex = (index + 1) % contents.size();
            return contents.get(proximoIndex);
        }
        return null; // Elemento não encontrado
    }


    public void avancarProximaPosicao() {
        currentIndex++;
        if (currentIndex >= contents.size()) {
            currentIndex = 0; // Volta para o primeiro nível
        }
    }
}
    

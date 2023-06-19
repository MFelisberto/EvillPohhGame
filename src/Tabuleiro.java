import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tabuleiro extends JPanel {
    
    private static final int MAXLIN = 13;
    private static final int MAXCOL = 20;
    private ElementoBasico[][] celulas;
    private Nivel niveis;
    private App app;

    private String nivelAt;
    private Porta port;
    private Personagem principal;
    private Inimigo antg;

    public Tabuleiro(App app,String nivelAt) {
        super();
        this.app= app;
        this.nivelAt = nivelAt;
        niveis = new Nivel();
        
        
        niveis.adicione("nivel1.txt");
        niveis.adicione("nivel2.txt");
        niveis.adicione("nivel3.txt");
        
       
        // Cria o conjunto de células vazia e as insere no layout
        celulas = new ElementoBasico[MAXLIN][MAXCOL];
        this.setLayout(new GridLayout(MAXLIN, MAXCOL));
        for (int i = 0; i < MAXLIN; i++) {
            for (int j = 0; j < MAXCOL; j++) {

                celulas[i][j] = new Fundo("Fundo[" + i + "][" + j + "]", i,"parquet.png", j, this);
                this.add(celulas[i][j]);
            }
        }
    }

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = App.class.getResource("imagens/"+path);
        
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            System.exit(0);
            return null;
        }
    }
    

    public static int getMaxlin() {
        return MAXLIN;
    }

    public static int getMaxcol() {
        return MAXCOL;
    }
    public Nivel getNiveis() {
        return niveis;
    }

    public boolean posicaoValida(int lin, int col) {
        if ((lin < 0) || (col < 0) ||
                (lin >= MAXLIN) || (col >= MAXCOL)) {
            return false;
        } else {
            return true;
        }
    }

    // Retorna referencia em determinada posição
    public ElementoBasico getElementoNaPosicao(int lin, int col) {
        if (!posicaoValida(lin, col)) {
            return null;
        }
        return celulas[lin][col];
    }
    public void removeElemento(ElementoBasico elemento) {
        int lin = elemento.getLin();
        int col = elemento.getCol();
        if (posicaoValida(lin, col) && celulas[lin][col] == elemento) {
            celulas[lin][col] = new Fundo("Fundo[" + lin + "][" + col + "]", lin, "wall.png", col, this);
        }
    }

    public ElementoBasico insereElemento(ElementoBasico elemento) {
        int lin = elemento.getLin();
        int col = elemento.getCol();
        if (!posicaoValida(lin, col)) {
            throw new IllegalArgumentException("Posicao invalida:" + lin + " ," + col);
        }
        ElementoBasico elementoAnterior = celulas[lin][col];
        celulas[lin][col] = elemento;
        return elementoAnterior;
    }

    public void atualizaVisualizacao() {
        // Atualiza o conteúdo do JPanel (ver algo melhor)
        this.removeAll(); // erase everything from your JPanel
        this.revalidate();
        this.repaint();// I always do these steps after I modify my JPanel
        for (int i = 0; i < MAXLIN; i++) {
            for (int j = 0; j < MAXCOL; j++) {
                this.add(celulas[i][j]);
            }
        }
    }
    
    public void loadLevel(String nivel) {
        Path path1 = Paths.get(nivel);
        try (BufferedReader reader = Files.newBufferedReader(path1)) {
            String line = null;
            int lin = 0;
            while ((line = reader.readLine()) != null) {
   
                for (int col=0; col<MAXCOL; col++) {
                    if (line.charAt(col) != ' ') {
                    ElementoBasico elem = this.getElem(line.charAt(col), lin, col);
                    this.insereElemento(elem);
                    }
                }
                lin++;
                
            }
        }
        catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }
        
    public ElementoBasico getElem(char elem, int lin, int col) {
        switch (elem) {
           case ' ': return new Fundo("Fundo",lin,"parquet.png",col,this);
           case '=': return new Fundo("Fundo1",lin,"grass.png",col,this);
           case '-': return new Parede("Parede",lin,"wall.png",col,this);
           case '~': return new Parede("Parede1",lin,"wallArv.png",col,this);
           case '+': return new Armadilha("armadilha","parquet.png", lin, col, this);
           case 'g': return new Prisioner("tigrao.png","tigrao.png", lin, col, this,"g");
           case 'i': return new Prisioner("io.png","io.png", lin, col, this,"i");
           case 'l': return new Prisioner("porco.png","porco.png", lin, col, this,"l");
           case 't': return new Prisioner("tigrao.png","tigrao.png", lin, col, this,"l");
           case 'c': return new Prisioner("key.png","key.png", lin, col, this,"c");
           case '!': {  ElementoBasico anterior = new Fundo("Fundo",lin,"parquet.png",col,null);
                        port =new Porta("porta", "door.png", lin, col, this, niveis,app,nivelAt);
                         port.setAnterior(anterior);
                         return port;}
           case 'x':{  ElementoBasico anterior = new Fundo("Fundo",lin,"parquet.png",col,null);
                        antg = new Inimigo("Inimigo","Pooh.png",lin,col,this);
                        antg.setAnterior(anterior);
                        return antg;
       }
       case '#':{  ElementoBasico anterior = new Fundo("Fundo",lin,"grass.png",col,null);
                        antg = new Inimigo("Inimigo","Pooh1.png",lin,col,this);
                        antg.setAnterior(anterior);
                        return antg;
       }

           case '*': {  ElementoBasico anterior = new Fundo("Fundo",lin,"parquet.png",col,null);
                        principal = new Personagem("boneco","Personagem.png",lin,col,this,"p");
                        principal.setAnterior(anterior);
                        return principal;
                    }
                    case 'p': {  ElementoBasico anterior = new Fundo("Fundo",lin,"grass.png",col,null);
                    principal = new Personagem("boneco","Personagem1.png",lin,col,this,"p");
                    principal.setAnterior(anterior);
                    return principal;
                }
           default: throw new IllegalArgumentException("Personagem invalido: "+elem);
        }

    }
    public Personagem getPrincipal() {
        return principal;
    }
    public Inimigo getAntg() {
        return antg;
    }
    public Porta getPort() {
        return port;
    }

    public void verificarArmadilha(Personagem personagem) {
        int linPersonagem = personagem.getLin();
        int colPersonagem = personagem.getCol();
        SoundPlayer soundPlayer = new SoundPlayer();
        

        for (int lin = 0; lin < MAXLIN; lin++) {
           
            for (int col = 0; col < MAXCOL; col++) {
                ElementoBasico elemento = celulas[lin][col];
                if (elemento instanceof Armadilha) {
                    int linArmadilha = elemento.getLin();
                    int colArmadilha = elemento.getCol();

                    int distancia = Math.abs(linPersonagem - linArmadilha) + Math.abs(colPersonagem - colArmadilha);
                    if (distancia <= 1) {
                        // Atualizar a imagem da armadilha
                        Armadilha armadilha = (Armadilha) elemento;
                        armadilha.setImage(createImageIcon("trap.png"));
                    } else {
                        // Reverter para a imagem padrão da armadilha
                        Armadilha armadilha = (Armadilha) elemento;
                        armadilha.setImage(createImageIcon("parquet.png"));}
                        if (distancia == 2) {
                            soundPlayer.playSound("rock.wav");
                            soundPlayer.stopSound();
                           
                             
                        }}}}
                       
                    
    }
    
    public void verificarPrisioneiro(Personagem personagem) {
        int linPersonagem = personagem.getLin();
        int colPersonagem = personagem.getCol();
        SoundPlayer soundPlayer = new SoundPlayer();
        SoundPlayer sound = new SoundPlayer();
        

        for (int lin = 0; lin < MAXLIN; lin++) {
           
            for (int col = 0; col < MAXCOL; col++) {
                ElementoBasico elemento = celulas[lin][col];
                if (elemento instanceof Prisioner) {
                    int linP = elemento.getLin();
                    int colP = elemento.getCol();

                    int distancia = Math.abs(linPersonagem - linP) + Math.abs(colPersonagem - colP);
                    if (distancia <= 1) {
                        // Atualizar a imagem d
                        Prisioner prisioneiro = (Prisioner) elemento;
                        String id = prisioneiro.getId();
                        prisioneiro.setImage(createImageIcon(id));
                    } else {
                        // Reverter para a imagem padrão 
                        Prisioner prisioneiro = (Prisioner) elemento;
                        if(prisioneiro.getCod().equals("g")||prisioneiro.getCod().equals("l")||prisioneiro.getCod().equals("i")){
                        prisioneiro.setImage(createImageIcon("wall.png"));
                        if (distancia == 2) {
                            soundPlayer.playSound("help.wav");
                            soundPlayer.stopSound();
                           
                        }}else prisioneiro.setImage(createImageIcon("wallArv.png"));
                        if (distancia == 2) {
                            sound.playSound("hole.wav");
                            sound.stopSound();
                        }
                    }}
                       
                    
                    }
                    
                   
    
}
}
}



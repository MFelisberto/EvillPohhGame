import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class ElementoBasico extends JButton {
    private Tabuleiro tabuleiro;
    private String id;
    private ImageIcon imagem;
    private int lin;
    private int col;

    
    public ElementoBasico(String id,String iconPath,int linInicial,int colInicial,Tabuleiro tabuleiro){
        this.id = id;
        this.lin = linInicial;
        this.col = colInicial;
        this.tabuleiro = tabuleiro;

        this.imagem = Tabuleiro.createImageIcon(iconPath); 
        this.setIcon( resize(this.imagem, 50, 40) ); 
        //this.setBorder(BorderFactory.createBevelBorder(0,Color.RED, Color.BLACK ));
        this.setMargin(new Insets(0,0,0,0));
    }

    public ElementoBasico(String id,int linInicial,int colInicial,Tabuleiro tabuleiro){
        this.id = id;
        this.lin = linInicial;
        this.col = colInicial;
        this.tabuleiro = tabuleiro;
    }

    public ImageIcon getImagem(){
        return this.imagem;
    }
    public void setImage(ImageIcon imagem){
        this.imagem = imagem;
        //this.setIcon(imagem); 
        this.setIcon(resize(this.imagem, 50, 40));
    }

    public String getId() {
        return id;
    }

    public int getLin() {
        return lin;
    }

    public void incLin(){
        if (lin < Tabuleiro.getMaxlin()-1){
            lin++;
        }
    }

    public void decLin(){
        if (lin > 0){
            lin--;
        }
    }

    public void incCol(){
        if (col < Tabuleiro.getMaxcol()-1){
            col++;
        }
    }

    public void decCol(){
        if (col > 0){
            col--;
        }
    }

    public int getCol() {
        return col;
    }

    public Tabuleiro getTabuleiro(){
        return tabuleiro;
    }

    public abstract void acao(ElementoBasico outro);

    public static ImageIcon resize(ImageIcon src, int destWidth,
            int destHeight) {
        return new ImageIcon(src.getImage().getScaledInstance(destWidth,
                destHeight, Image.SCALE_SMOOTH));
    }


}


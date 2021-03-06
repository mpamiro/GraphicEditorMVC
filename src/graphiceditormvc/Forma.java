package graphiceditormvc;

import java.awt.*;
import java.awt.geom.Ellipse2D; // Per disegnare cerchi
import java.io.Serializable; // Per salvare la forma in un file binario


/**
 * Rappresenta un elemento grafico di un documento di tipo Model.
 * Puo' essere di uno dei tipi definiti nella enumerazone TipoForma.
 * 
 * @author mauropamiro
 * 
 * @see TipoForma
 * @see Model
 */
public class Forma implements Serializable{
    /** Il tipo della forma. */
    private TipoForma tipo;
    /** Il colore della forma. */
    private Color colore;
    /** La coordinata x dell'angolo in alto a sinistra del quadrato che inscrive la forma. */
    private int x;
    /** La coordinata y dell'angolo in alto a sinistra del quadrato che inscrive la forma. */
    private int y;
    /** La larghezza forma. */
    private int width;
    /** L'altezza della forma. */
    private int height;
    
    
    /**
     * Crea una nuova forma
     * 
     * @param tipo      il tipo di forma
     * @param x         la coordinata x del vertice in alto a sinistra del rettangolo che contiene la forma
     * @param y         la coordinata y del vertice in alto a sinistra del rettangolo che contiene la forma
     * @param width     la larghezza del rettangolo che contiene la forma
     * @param height    l'altezza del rettangolo che contiene la forma
     * @param colore    il colore di riempimento della forma
     */
    public Forma(TipoForma tipo, int x, int y, int width, int height, Color colore){
        this.tipo=tipo;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.colore=colore;
    }


    /**
     * Crea una copia della forma ricevuta come parametro
     * 
     * @param copia     la forma di cui fare la copia
     */
    public Forma(Forma copia){
        this(copia.tipo, copia.x, copia.y, copia.width, copia.height, copia.colore);
    }
    
    
    /**
     * Restituisce la larghezza del rettangolo che contiene la forma.
     * 
     * @return  la larghezza del rettangolo che contiene la forma
     */
    public int getWidth() {
        return width;
    }

    
    /**
     * Restituisce l'altezza del rettangolo che contiene la forma.
     * 
     * @return  l'altezza del rettangolo che contiene la forma
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Restituisce la coordinata x del vertice in alto a sinistra 
     * del rettangolo che contiene la forma.
     * 
     * @return  la coordinata x del vertice in alto a sinistra del rettangolo che contiene la forma
     */
    public int getX() {
        return x;
    }

    /**
     * Restituisce la coordinata y del vertice in alto a sinistra 
     * del rettangolo che contiene la forma.
     * 
     * @return  la coordinata y del vertice in alto a sinistra del rettangolo che contiene la forma
     */
    public int getY() {
        return y;
    }

    /**
     * Restituisce il colore di riempimento della forma.
     * 
     * @return  il colore di riempimento della forma
     */
    public Color getColore() {
        return colore;
    }

    /**
     * Restituisce il tipo della forma.
     * 
     * @return  il tipo della forma
     * 
     * @see TipoForma
     */
    public TipoForma getTipo() {
        return tipo;
    }

    
    /**
     * Sposta la forma nel punto (x,y).
     * Il punto rappresenta il vertice in alto a sinistra del rettangolo che contiene la forma.
     * 
     * @param x la nuova coordinata x del vertice in alto a sinistra del rettangolo che contiene la forma
     * @param y la nuova coordinata y del vertice in alto a sinistra del rettangolo che contiene la forma
     */
    public void sposta(int x, int y){
        this.x=x;
        this.y=y;
    }


    /**
     * Fornisce una descrizione testuale della forma, del tipo:
     * TIPOFORMA (x,y) [W,H]->Colore
     * <p>
     * Ad esempio:
     * QUADRATO (136,137) [W:50,H:50]->java.awt.Color[r=0,g=0,b=255]
     * 
     * @return  la descrizione testuale della forma
     */
    @Override
    public String toString() {
        String descrizione=""+tipo+" ("+x+","+y+") [W:"+width+",H:"+height+"]->"+colore;
        return descrizione;
    }
    
    
    /**
     * Stabilisce se il punto di coordinate (x,y) e' contenuto nella forma.
     * 
     * @param x la coordinata x del punto da controllare
     * @param y la coordinata y del punto da controllare
     * 
     * @return  true se il punto (x,y) e' contenuto nella forma, false altrimenti
     */
    public boolean contiene(int x, int y){
        // Creo un oggetto Point che rappresenta il punto alle coordinate del mouse
        Point punto=new Point(x, y);
        // A seconda del tipo della forma, controllo che il punto sia contenuto nel cerchio
        if(tipo==TipoForma.CERCHIO){
            Ellipse2D.Float forma=new Ellipse2D.Float(this.x, this.y, width, height);
            return forma.contains(punto);
        }
        else if(tipo==TipoForma.QUADRATO){
            Rectangle forma=new Rectangle(this.x,this.y,width,height);
            return forma.contains(punto);
        }
        else{
            // Triangolo: devo prima creare degli array per memorizzare le coordinate dei tre vertici
            int[] xPoints={this.x+width/2,this.x+width,this.x};
            int[] yPoints={this.y,this.y+height,this.y+height};
            Polygon forma=new Polygon(xPoints,yPoints,3);
            return forma.contains(punto);
        }
    }
}
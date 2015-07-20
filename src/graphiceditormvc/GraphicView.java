package graphiceditormvc;

import javax.swing.*;
import java.awt.*;
/**
 * Un pannello che rappresenta una vista in grafica vettoriale per i documenti di tipo Model.
 * 
 * @author mauropamiro
 */
public class GraphicView extends JPanel{
    private Model documento;
    private int selezionata;

    /**
     * Associa alla vista un documento di tipo Model
     * 
     * @param documento il documento da associare alla vista
     */
    public final void setDocumento(Model documento) {
        this.documento = documento;
        selezionata=-1;
    }

    /**
     * Restituisce l'indice della forma selezionata o -1 se non e' selezionata nessuna forma.
     * 
     * @return l'indice della forma selezionata o -1 se non e' selezionata nessuna forma
     */
    public int getSelezionata() {
        return selezionata;
    }
        
    /**
     * Seleziona la forma in primo piano alle coordinate x,y
     * 
     * @param x la coordinata x del punto da controllare
     * @param y la coordinata y del punto da controllare
     * 
     * @return  l'indice, all'interno del documento di tipo Model, della forma selezionata o -1 se nel punto (x,y) non e' presente alcuna forma
     */
    public int seleziona(int x, int y){
        selezionata=-1;
        for(int i=documento.nForme()-1;i>=0;i--){
            if(documento.getForma(i).contiene(x, y)){
                selezionata=i;
                break;
            }
        }
        return selezionata;
    }
    
    /**
     * Costruttore che associa alla vista un documento di tipo Model
     * 
     * @param documento il documento di tipo Model da associare alla vista
     */
    public GraphicView(Model documento){
        setDocumento(documento);
        setBackground(Color.white);
        setPreferredSize(new Dimension(documento.getWidth(), documento.getHeight()));
    }
    
    /**
     * Aggiorna la vista ridisegnando il documento.
     * Questo metodo viene chiamato automaticamente ogni volta che il componente deve essere ridisegnato.
     * 
     * @param g il gestore grafico del componente in cui disegnare il documento
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<documento.nForme();i++){
            documento.getForma(i).disegna((Graphics2D)g);
        }
        g.setColor(Color.GRAY);
        g.fillRect(documento.getWidth(), 0, this.getWidth()-documento.getWidth(), this.getHeight());
        g.fillRect(0, documento.getHeight(), this.getWidth(), this.getHeight()-documento.getHeight());
    }
}

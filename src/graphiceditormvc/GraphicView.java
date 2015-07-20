package graphiceditormvc;

import javax.swing.*;
import java.awt.*;

/**
 * Un pannello che rappresenta una vista in grafica vettoriale per i documenti di tipo Model.
 * 
 * @author mauropamiro
 */
public class GraphicView extends JScrollPane{
    private Model documento;
    private int selezionata;
    private ViewPanel pannello;

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
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setDocumento(documento);
        pannello=new ViewPanel(this);
        pannello.setPreferredSize(new Dimension(documento.getWidth(),documento.getHeight()));
        setViewportView(pannello);
    }
    
    public void disegna(Graphics2D g){
        g.setColor(Color.white);
        g.fillRect(0, 0, pannello.getWidth(), pannello.getHeight());
        for(int i=0;i<documento.nForme();i++){
            documento.getForma(i).disegna(g);
        }
        g.setColor(Color.GRAY);
        g.fillRect(documento.getWidth(), 0, pannello.getWidth()-documento.getWidth(), pannello.getHeight());
        g.fillRect(0, documento.getHeight(), pannello.getWidth(), pannello.getHeight()-documento.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pannello.repaint();
    }

    void aggiungiAscoltatore(Controller.AscoltaMouse ascoltaVista) {
        pannello.addMouseListener(ascoltaVista);
        pannello.addMouseMotionListener(ascoltaVista);
    } 
}


class ViewPanel extends JPanel{
    GraphicView parent;
    
    ViewPanel(GraphicView parent){
        this.parent=parent;
        setBackground(Color.white);
    }
    
    /**
     * Aggiorna la vista ridisegnando il documento.
     * Questo metodo viene chiamato automaticamente ogni volta che il componente deve essere ridisegnato.
     * 
     * @param g il gestore grafico del componente in cui disegnare il documento
     */
    @Override
    public void paintComponent(Graphics g){
        parent.disegna((Graphics2D)g);
    }    
}
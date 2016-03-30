package graphiceditormvc;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;




/**
 * Un pannello scorrevole (cioè con con barra di scorrimento orizzontale e verticale)
 * dove disegnare una vista in grafica vettoriale per i documenti di tipo Model.
 * La vista del documento viene disegnata in un pannello interno,
 * aggiunto al pannello scorrevole dal costruttore.
 * 
 * @author mauropamiro
 */
public class GraphicView extends JPanel implements Observer{
    /** Il Controller associato alla vista. */
    private final Controller controller; 
    
    /**
     * Costruttore che crea il pannello scorrevole, aggiungendogli un secondo pannello
     * interno in cui verrà disegnata la vista grafica del documento.
     * 
     * @param controller la finestra (Controller) a cui viene aggiunto il pannello scorrevole
     * @param dimensioni le dimensioni del documento da visualizzare
     */
    public GraphicView(Controller controller, Dimension dimensioni){
        // Associo il controller alla vista
        this.controller=controller;
        setPreferredSize(dimensioni);
        setBackground(Color.white);
    }



    /**
     * Associa al pannello interno un ascoltatore (listener) per gli eventi generati dal mouse
     * 
     * @param ascoltatore un ascoltatore (listener) per gli eventi generati dal mouse
     */
    public void aggiungiAscoltatore(Controller ascoltatore) {
        addMouseListener(ascoltatore);
        addMouseMotionListener(ascoltatore);
    }
    
    

    /***********************************************************************************/
    /**************** Aggiornamento della vista e disegno del documento ****************/
    /***********************************************************************************/    
    
    /** Disegna il documento.
     */    
    @Override
    public void update(Observable o, Object arg) {
        //disegna((Model)o);
        revalidate();
        repaint();
    }

    public void disegna(Model documento){
        boolean isSelezionata;
        Graphics2D g=(Graphics2D)getGraphics();
        if(documento==null) documento=controller.getDocumento();
        // Colora di bianco lo sfondo del documento
        // (in realtà viene colorato solo lo sfondo della porzione di documento visibile)
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Disegno una alla volta le forme contenute nel documento
        for(int i=0;i<documento.nForme();i++){
            // Controllo se la forma da disegnare è selezionata
            // (la forma selezionata viene disegnata più chiara delle altre)
            if(controller.getSelezionata()==i) isSelezionata=true;
            else isSelezionata=false;
            // Disegno la forma
            disegnaForma(g,documento.getForma(i),isSelezionata);
        }
        // Coloro di grigio la parte di pannello che non contiene il documento
        // (se il documento è più piccolo del pannello, intorno al documento c'è un'area grigia)
        g.setColor(Color.GRAY);
        // Disegno una striscia grigia alla destra del documento
        g.fillRect(documento.getWidth(), 0, getWidth()-documento.getWidth(), getHeight());
        // Disegno una striscia grigia sotto il documento
        g.fillRect(0, documento.getHeight(), getWidth(), getHeight()-documento.getHeight());
    }
    
    /** Disegno una forma usando il contesto grafico ricevuto come parametro.
     * 
     * @param g il contesto grafico in cui disegnare
     * @param f la forma da disegnare
     * @param selezionata indica se la forma è quella selezionata 
     */
    private void disegnaForma(Graphics2D g,Forma f,boolean selezionata){
        int alpha=100; // Opacità della forma bianca da sovrapporre alla forma selezionata
        
        // Disegno, a seconda del suo tipo, la forma colorata
        g.setColor(f.getColore());
        if(f.getTipo()==TipoForma.QUADRATO) g.fillRect(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        else if(f.getTipo()==TipoForma.CERCHIO) g.fillOval(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        else{
            // Triangolo: devo prima creare un array con le coordinate dei vertici
            int[] xPoints={f.getX()+f.getWidth()/2,f.getX()+f.getWidth(),f.getX()};
            int[] yPoints={f.getY(),f.getY()+f.getHeight(),f.getY()+f.getHeight()};
            g.fillPolygon(xPoints, yPoints, 3);
        }
        // La forma selezionata deve apparire più chiara: le sovrappongo una forma uguale bianca semitraspaente.
        // Se la forma appena disegnata è quella selezionata
        if(selezionata){
            // Creo una forma uguale bianca semitrasparente alla stessa posizione della forma appena disegnata
            Forma highlight=new Forma(f.getTipo(),f.getX(), f.getY(), f.getWidth(), f.getHeight(),new Color(255,255,255,alpha));
            // Disegno la forma semitrasparente appena creata (chiamata ricorsiva)
            disegnaForma(g,highlight,false);
        }        
    }
    
    /** Metodo chiamato quando il componente deve essere ridisegnato.
     * Quando viene ridisegnata la GraphicView viene ridisegnato anche il pannello interno.
     * 
     * @param g il contesto grafico in cui disegnare
     */
    @Override
    public void paintComponent(Graphics g) {
        disegna(null);
    }
}

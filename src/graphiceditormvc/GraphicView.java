package graphiceditormvc;

import javax.swing.*;
import java.awt.*;




/**
 * Un pannello scorrevole (cioè con con barra di scorrimento orizzontale e verticale)
 * dove disegnare una vista in grafica vettoriale per i documenti di tipo Model.
 * La vista del documento viene disegnata in un pannello interno,
 * aggiunto al pannello scorrevole dal costruttore.
 * 
 * @author mauropamiro
 */
public class GraphicView extends JScrollPane{
    private Controller controller; // Il Controller
    private ViewPanel pannello; // Il pannello in cui visualizzare la vista grafica del documento
    
    /**
     * Costruttore che crea il pannello scorrevole, aggiungendogli un secondo pannello
     * interno in cui verrà disegnata la vista grafica del documento.
     * 
     * @param controller la finestra (Controller) a cui viene aggiunto il pannello scorrevole
     * @param dimensioni le dimensioni del documento da visualizzare
     */
    public GraphicView(Controller controller, Dimension dimensioni){
        // creo il pannello con le eventuali barre di scorrimento orizzontali e verticali
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Associo il controller alla vista
        this.controller=controller;
        // Gestione del ViewPort, per visualizzare solo una porzione del documento agendo sulle barre di scorrimento
        pannello=new ViewPanel(this,dimensioni);
        setViewportView(pannello);
    }

    
    /**
     * Restituisce le dimensioni del pannello interno.
     * Il pannello interno non può essere più piccolo del pannello scorrevole 
     * e non può essere più grande del documento.
     * 
     * @return le dimensioni del pannello interno
     */
    public Dimension getViewSize(){
        return pannello.getSize();
    }


    /**
     * Associa al pannello interno un ascoltatore (listener) per gli eventi generati dal mouse
     * 
     * @param ascoltaVista un ascoltatore (listener) per gli eventi generati dal mouse
     */
    public void aggiungiAscoltatore(Controller.AscoltaMouse ascoltaVista) {
        pannello.addMouseListener(ascoltaVista);
        pannello.addMouseMotionListener(ascoltaVista);
    }
    
    

    /***********************************************************************************/
    /**************** Aggiornamento della vista e disegno del documento ****************/
    /***********************************************************************************/    

    
    /**
     * Chiama il metodo disegna, passandogli il contesto grafico
     * del pannello il cui disegnare la vista del documento
     * 
     * @param g il contesto grafico del pannello in cui disegnare la vista
     */
    public void aggiorna(){
        // Chiama il metodo disegna
        disegna((Graphics2D)pannello.getGraphics());
        // Ridisegna il contenuto della finestra
        repaint();
    }
    
    
    // Disegna il documento usando il contesto grafico g.
    // Il contesto grafico può essere quello del pannello interno o quello associato a un file pdf
    // (vedi metodo per l'esportazione in pdf nella classe Controller).
    public void disegna(Graphics2D g){
        // Ottiene il documento dal Controller
        Model documento=controller.getDocumento();
        boolean isSelezionata;
        // Colora di bianco lo sfondo del documento
        // (in realtà viene colorato solo lo sfondo della porzione di documento visibile)
        g.setColor(Color.white);
        g.fillRect(0, 0, pannello.getWidth(), pannello.getHeight());
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
        g.fillRect(documento.getWidth(), 0, pannello.getWidth()-documento.getWidth(), pannello.getHeight());
        // Disegno una striscia grigia sotto il documento
        g.fillRect(0, documento.getHeight(), pannello.getWidth(), pannello.getHeight()-documento.getHeight());
    }
    
    
    // Disegno una forma usando il contesto grafico ricevuto come parametro.
    // Parametri:
    // - il contesto grafico in cui disegnare
    // - laforma da disegnare
    // - un boolean che indica se la forma è quella selezionata
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
        

    /**
     * Restituisce il contesto grafico del pannello interno.
     * Il contesto grafico restituito può essere usato dal Controller per disegnare
     * la vista del documento.
     * 
     * @return il contesto grafico del pannello interno
     */
    public Graphics2D getViewGraphics() {
        return (Graphics2D)pannello.getGraphics();
    }
    
    
    // Quando viene ridisegnata la GraphicView viene ridisegnato anche il pannello interno
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pannello.repaint();
    }
    
}





/**
 * Il pannello interno in cui disegnare la vista del documento.
 * Viene creato e aggiunto alla GraphicView dal costruttore di GraphicView.
 * 
 * @author mauropamiro
 */
class ViewPanel extends JPanel{
    GraphicView parent;
    
    ViewPanel(GraphicView parent, Dimension dimensioni){
        this.parent=parent;
        this.setPreferredSize(dimensioni);
        setBackground(Color.white);
    }
    
    /**
     * Aggiorna la vista ridisegnando il documento.
     * Questo metodo viene chiamato automaticamente ogni volta che il componente deve essere ridisegnato.
     * Chiama il metodo disegna del Controller.
     * 
     * @param g il contesto grafico del componente
     */
    @Override
    public void paintComponent(Graphics g){
        parent.disegna((Graphics2D)g);
    }    
}
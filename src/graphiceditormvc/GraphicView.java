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
    private Controller controller;
    private ViewPanel pannello;
    
    /**
     * Costruttore che crea il pannello scorrevole, aggiungendogli un secondo pannello
     * interno in cui verrà disegnata la vista del documento.
     * 
     * @param controller la finestra (Controller) a cui viene aggiunto il pannello scorrevole
     * @param dimensioni le dimensioni del documento da visualizzare
     */
    public GraphicView(Controller controller, Dimension dimensioni){
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.controller=controller;
        pannello=new ViewPanel(this,dimensioni);
        setViewportView(pannello);
    }
    
    /**
     * Chiama il metodo aggiornaVistaGrafica del controller, passandogli il contest grafico
     * del pannello il cui disegnare la vista del documento
     * 
     * @param g il contesto grafico del pannello in cui disegnare la vista
     */
    public void aggiorna(Graphics2D g){
        controller.aggiornaVistaGrafica(g);
        repaint();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pannello.repaint();
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
        parent.aggiorna((Graphics2D)g);
    }    
}
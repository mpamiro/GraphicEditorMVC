package graphiceditormvc;

import javax.swing.*;
import java.awt.*;




/**
 * Un pannello che rappresenta una vista in grafica vettoriale per i documenti di tipo Model.
 * 
 * @author mauropamiro
 */
public class GraphicView extends JScrollPane{
    private Controller controller;
    private ViewPanel pannello;
    
    /**
     * Costruttore che associa alla vista un documento di tipo Model
     * 
     * @param documento il documento di tipo Model da associare alla vista
     */
    public GraphicView(Controller controller, Dimension dimensioni){
        super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.controller=controller;
        pannello=new ViewPanel(this,dimensioni);
        setViewportView(pannello);
    }
    
    public void disegna(Graphics2D g){
        controller.aggiornaVistaGrafica(g);
        repaint();
    }
    
    public Dimension getViewSize(){
        return pannello.getSize();
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

    Graphics2D getViewGraphics() {
        return (Graphics2D)pannello.getGraphics();
    }
}
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
     * 
     * @param g il gestore grafico del componente in cui disegnare il documento
     */
    @Override
    public void paintComponent(Graphics g){
        parent.disegna((Graphics2D)g);
    }    
}
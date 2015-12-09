package graphiceditormvc;

import java.awt.Point;

/**
 * Una finestra di dialogo in cui viene visualizzata una rappresentazione testuale del documento
 * 
 * @author mauropamiro
 */
public class WndVistaView extends javax.swing.JDialog {
    Controller controller; // Il Controller
    
    /**
     * Costruttore: crea la finestra e memorizza un riferimento al Controller (la finestra principale)
     * 
     * @param cotroller il Controller associato alla vista (la finestra principale)
     */
    public WndVistaView(Controller controller) {
        super(controller, false);
        initComponents();
        this.controller=controller;
        // La finestra viene visualizzata alla destra della finestra principale (Controller)
        this.setLocation(new Point(controller.getX()+controller.getWidth()*3/4,controller.getY()+50));
    }
    
     /**
     * Aggiorna la vista del documento, modificando il contenuto dell'area di testo
     */
    public void aggiorna(String testo){
        txtArea.setText(testo);
        repaint(); // Ridisegna la finestra
    }
    

    // All'apertura della finestra, visualizza al suo interno la descrizione testuale del documento
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        aggiorna(controller.getTestoDocumento());
    }//GEN-LAST:event_formWindowOpened


    // Chiusura della finesra
    private void btnChiudiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiudiActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnChiudiActionPerformed



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnChiudi = new javax.swing.JButton();

        setTitle("Elenco Forme");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(300, 400));
        setPreferredSize(new java.awt.Dimension(300, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnChiudi.setText("Chiudi");
        btnChiudi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiudiActionPerformed(evt);
            }
        });
        getContentPane().add(btnChiudi, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiudi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}

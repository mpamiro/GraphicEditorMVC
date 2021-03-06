package graphiceditormvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * La classe e' la finestra principale del programma GraphicEditorMVC. Il
 * programma e' un editor per creare documenti formati da forme geometriche
 * colorate. Il programma implementa il pattern Model/View/Controller: il
 * Controller (interfaccia utente) modifica il Model (documento), notificando le
 * View (rappresentazione grafica o testuale del documento) delle avvenute
 * modifiche. Ad ogni modifica le View si aggiornano.
 * <p>
 * Classi principali utilizzate dal programma:
 * <ul>
 * <li>Controller: la finestra principale. Crea o apre dei documenti di tipo
 * Model e gestisce le azioni dell'utente.
 * <li>Model: il documento gestito dal programma.
 * <li>Forma: la singola forma geometrica inclusa nel documento di tipo Model.
 * <li>GraphicView: un pannello che fornisce una vista in grafica vettoriale del
 * documento di tipo Model.
 * <li>WndTextView: una finestra di dialogo che fornisce una vista testuale del
 * documento di tipo Model.
 * </ul>
 * <p>
 * Il tipo di possibili forme e' descritto dall'enumerazione TipoForma.
 * 
 * @author mauropamiro
  */
public class Controller extends JFrame implements MouseListener, MouseMotionListener, WindowListener{
    /** Il documento aperto all'interno del programma */
    private Model documento; 
    /**  File in cui è salvato il documento */
    private File file;
    /** Indica se il documento è stato salvato dopo l'ultima modifica */
    private boolean saved; 
    
    /** Vista grafica del documento (sottoclasse di JPanel) */
    private GraphicView vistaGrafica; 
    /** Finestra di dialogo con una vista testuale del documento (sottoclasse di JDialog) */
    private WndTextView vistaTesto; 

    /** Lista dei documenti per gli annullamenti */
    private ArrayList<UndoItem> undoList; 
    /** Lista dei documenti per il ripristino degli annullamenti */
    private ArrayList<UndoItem> redoList; 
    
    /** Indice della forma selezionata nel documento (-1 se nessuna forma è selezionata) */
    private int selezionata; 
    /** Indica se la forma selezionata è stata appena spostata */
    private boolean moved; 
    
    /** Copia del documento per gestire l'undo del trascinamento */
    private Model copia; 
    /** Oggetto dove memorizzare le forme copiate con il comando Copia o Taglia */
    private Forma appunti; 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColor;
    private javax.swing.JToggleButton btn_circle;
    private javax.swing.JToggleButton btn_delete;
    private javax.swing.JToggleButton btn_select;
    private javax.swing.JToggleButton btn_square;
    private javax.swing.JToggleButton btn_triangle;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem menuClose;
    private javax.swing.JMenuItem menuCopy;
    private javax.swing.JMenuItem menuCut;
    private javax.swing.JMenuItem menuElenco;
    private javax.swing.JMenuItem menuNew;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuPaste;
    private javax.swing.JMenuItem menuPortaAvanti;
    private javax.swing.JMenuItem menuPortaInFondo;
    private javax.swing.JMenuItem menuRedo;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JMenuItem menuSaveAs;
    private javax.swing.JMenuItem menuUndo;
    private javax.swing.JPanel statusPanel;
    private javax.swing.ButtonGroup tools_group;
    // End of variables declaration//GEN-END:variables

    /**
     * crea una nuova finestra di tipo Controller
     */
    public Controller() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tools_group = new javax.swing.ButtonGroup();
        statusPanel = new javax.swing.JPanel();
        lbl_status = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        btn_select = new javax.swing.JToggleButton();
        btn_delete = new javax.swing.JToggleButton();
        btn_square = new javax.swing.JToggleButton();
        btn_circle = new javax.swing.JToggleButton();
        btn_triangle = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnColor = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNew = new javax.swing.JMenuItem();
        menuOpen = new javax.swing.JMenuItem();
        menuClose = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSave = new javax.swing.JMenuItem();
        menuSaveAs = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuUndo = new javax.swing.JMenuItem();
        menuRedo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuCut = new javax.swing.JMenuItem();
        menuCopy = new javax.swing.JMenuItem();
        menuPaste = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuPortaAvanti = new javax.swing.JMenuItem();
        menuPortaInFondo = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuElenco = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drawing");
        setLocation(new java.awt.Point(200, 200));
        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        statusPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        statusPanel.setMaximumSize(new java.awt.Dimension(1400, 26));
        statusPanel.setMinimumSize(new java.awt.Dimension(43, 26));
        statusPanel.setLayout(new javax.swing.BoxLayout(statusPanel, javax.swing.BoxLayout.LINE_AXIS));

        lbl_status.setText("Status");
        statusPanel.add(lbl_status);

        getContentPane().add(statusPanel, java.awt.BorderLayout.SOUTH);

        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(65911, 42));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        tools_group.add(btn_select);
        btn_select.setSelected(true);
        btn_select.setText("Seleziona");
        jPanel1.add(btn_select);

        tools_group.add(btn_delete);
        btn_delete.setText("Elimina");
        jPanel1.add(btn_delete);

        tools_group.add(btn_square);
        btn_square.setText("Quadrato");
        jPanel1.add(btn_square);

        tools_group.add(btn_circle);
        btn_circle.setText("Cerchio");
        jPanel1.add(btn_circle);

        tools_group.add(btn_triangle);
        btn_triangle.setText("Triangolo");
        jPanel1.add(btn_triangle);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setMaximumSize(new java.awt.Dimension(10, 38));
        jSeparator3.setMinimumSize(new java.awt.Dimension(10, 38));
        jSeparator3.setPreferredSize(new java.awt.Dimension(10, 38));
        jPanel1.add(jSeparator3);

        btnColor.setBackground(new java.awt.Color(0, 0, 255));
        btnColor.setForeground(new java.awt.Color(0, 0, 255));
        btnColor.setToolTipText("Choose color");
        btnColor.setBorder(null);
        btnColor.setBorderPainted(false);
        btnColor.setFocusable(false);
        btnColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnColor.setMargin(new java.awt.Insets(0, 5, 0, 5));
        btnColor.setMaximumSize(new java.awt.Dimension(38, 38));
        btnColor.setMinimumSize(new java.awt.Dimension(38, 38));
        btnColor.setOpaque(true);
        btnColor.setPreferredSize(new java.awt.Dimension(38, 38));
        btnColor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        jPanel1.add(btnColor);

        jToolBar1.add(jPanel1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        mainPanel.setBackground(java.awt.Color.gray);
        mainPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        menuNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuNew.setText("Nuovo");
        menuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewActionPerformed(evt);
            }
        });
        jMenu1.add(menuNew);

        menuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOpen.setText("Apri");
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpen);

        menuClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuClose.setText("Chiudi");
        menuClose.setEnabled(false);
        menuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseActionPerformed(evt);
            }
        });
        jMenu1.add(menuClose);
        jMenu1.add(jSeparator1);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText("Salva");
        menuSave.setEnabled(false);
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        jMenu1.add(menuSave);

        menuSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSaveAs.setText("Salva come ...");
        menuSaveAs.setEnabled(false);
        menuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(menuSaveAs);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Modifica");

        menuUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuUndo.setText("Annulla");
        menuUndo.setEnabled(false);
        menuUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUndoActionPerformed(evt);
            }
        });
        jMenu2.add(menuUndo);

        menuRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuRedo.setText("Ripristina");
        menuRedo.setEnabled(false);
        menuRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRedoActionPerformed(evt);
            }
        });
        jMenu2.add(menuRedo);
        jMenu2.add(jSeparator2);

        menuCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuCut.setText("Taglia");
        menuCut.setEnabled(false);
        menuCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCutActionPerformed(evt);
            }
        });
        jMenu2.add(menuCut);

        menuCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuCopy.setText("Copia");
        menuCopy.setEnabled(false);
        menuCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCopyActionPerformed(evt);
            }
        });
        jMenu2.add(menuCopy);

        menuPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        menuPaste.setText("Incolla");
        menuPaste.setEnabled(false);
        menuPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPasteActionPerformed(evt);
            }
        });
        jMenu2.add(menuPaste);
        jMenu2.add(jSeparator5);

        menuPortaAvanti.setText("Porta avanti");
        menuPortaAvanti.setEnabled(false);
        menuPortaAvanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPortaAvantiActionPerformed(evt);
            }
        });
        jMenu2.add(menuPortaAvanti);

        menuPortaInFondo.setText("Porta in fondo");
        menuPortaInFondo.setEnabled(false);
        menuPortaInFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPortaInFondoActionPerformed(evt);
            }
        });
        jMenu2.add(menuPortaInFondo);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Visualizza");

        menuElenco.setText("Elenco forme");
        menuElenco.setEnabled(false);
        menuElenco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuElencoActionPerformed(evt);
            }
        });
        jMenu3.add(menuElenco);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    /* ******************************************************************************** */
    /* **** Comandi del menu File: Nuovo, Apri, Chiudi, Salva, Salva con nome ********* */
    /* ******************************************************************************** */
    
    
    /** Metodo eseguito alla pressione della voce di menu File->Salva. 
     * Chiama il metodo salva.
     * 
     * @param evt l'evento generato dal mouse
     */
    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        salva();
    }//GEN-LAST:event_menuSaveActionPerformed

    
    /** Metodo eseguito alla pressione della voce di menu File->Salva con nome.
     *  Chiama il metodo salvaConNome.
     * 
     * @param evt  evento generato dal click sulla voce di menu
     */
    private void menuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveAsActionPerformed
        salvaConNome();
    }//GEN-LAST:event_menuSaveAsActionPerformed

    
    /** Salvo il documento nel file ad esso associato. */
    private void salva() {
        // Se c'è un documento aperto ed il documento ha modifiche non ancora salvate
        if (documento != null && !saved) {
            // Se non c'è ancora nessun file associato al documento eseguo "Salva con nome"
            if (file == null) salvaConNome();     
            // Altrimenti scrivo il documento nel file
            else { 
                try {
                    // Scrivo il documento nel file (grazie all'interfaccia Serializable implementata da Model l'operazione è banale):
                    // 1. Associo un flusso di output al file;
                    FileOutputStream fileOut = new FileOutputStream(file.getName());
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    // 2. Scrivo il documento nel flusso di output (e quindi nel file)
                    out.writeObject(documento);
                    // 3. Chiudo il file e il flusso ad esso associato
                    out.close();
                    fileOut.close();
                    // Il documento è stato salvato
                    saved = true;
                    // Aggiorno la barra di stato
                    setStatus("Salvato in " + file.getName());
                } catch (IOException i) {
                    // In caso di errore, lo segnalo nella barra di stato
                    setStatus("Errore durante il salvataggio");               
                    file = null; // Nessun file associato al documento
                }
            }
        }
    }

    
    /** Salva il documento aperto in un nuovo file. */
    private void salvaConNome() {
        // Se c'è un documento aperto
        if (documento != null) {
            // Faccio scegliere il file in cui salvare il documento con la finestra di dialogo predefinita JFileChooser.
            // La finestra mostra il contenuto della directory corrente: System.getProperty("user.dir") 
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Drawing","drw");
            fc.setFileFilter(filter);
        
            int returnVal = fc.showSaveDialog(this);
            // Se la finestra è stata chiusa premendo OK
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Associo il nuovo file al documento (aggiungendo eventualmente l'estensione .drw)
                file = fc.getSelectedFile();
                if(file.getName().indexOf(".drw")!=file.getName().length()-4) file=new File(file.getPath()+".drw");
                // Salvo il documento nel file appena creato
                saved = false;
                salva();
                // Aggiungo il nome del file alla barra del titolo del programma
                setTitle("Drawing - " + file.getName());
            }
        }
    }

    
    /** Metodo eseguito alla pressione della voce di menu File->Nuovo:
     * crea un nuovo documento chiedendo all'utente larghezza e altezza
     * e associa al nuovo documento una nuova vista grafica.
     * 
     * @param evt evento generato dal click sulla voce di menu
     * */
    private void menuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewActionPerformed
        // Chiudo l'eventuale documento aperto
        chiudi();
        // Chiedo le dimensioni del documento con una finestra della classe SizeDialog (vedi file SizeDialog.java)
        SizeDialog dlgSize = new SizeDialog(this, true);
        dlgSize.setVisible(true);
        // Ottengo dalla finestra SizeDialog le dimensoni scelte dall'utente
        Dimension d = dlgSize.getDimensioni();
        if (d != null) {
            // Creo un nuovo documento, associandolo alla vista grafica
            documento = new Model(d.width, d.height);
            associaVista();
            // Aggiorno la barra di stato
            setStatus("Nuovo documento " + documento.getWidth() + "x" + documento.getHeight() + " pixel");
        }       
        dlgSize.dispose(); // Dealloco la finestra
    }//GEN-LAST:event_menuNewActionPerformed

    
    /** Metodo eseguito alla pressione della voce di menu File->Apri:
     * crea un nuovo documento leggendolo dal file scelto dall'utente
     * e associa al documento una nuova vista grafica.
     * 
     * @param evt evento generato dal click sulla voce di menu
     * */
    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        // Chiudo l'eventuale documento aperto
        chiudi();
        // Faccio scegliere il file da aprire dalla finestra di dialogo predefinita JFileChooser.
        // La finestra mostra il contenuto della directory corrente: System.getProperty("user.dir") 
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Drawing","drw");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        // Se la finestra è stata chiusa premendo OK
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Associo al documento il fle appena aperto
            file = fc.getSelectedFile();
            try {
                // Apro il file scelto
                FileInputStream fileIn = new FileInputStream(file.getName());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                // Leggo il documento dal file e lo associo alla vista
                documento = (Model) in.readObject();
                associaVista();
                // Chiudo il file
                in.close();
                fileIn.close();
                // Aggiungo il nome del file appena aperto alla barra del titolo della finestra Controller
                setTitle(getTitle() + " - " + file.getName());
                // Aggiorno la barra di stato
                setStatus("File " + file.getName() + " aperto");
            } catch (Exception e) {
                // In caso di errore, lo segnalo nella barra di stato
                setStatus("Errore nell'apertura di " + file.getName());
                // Nessun file è stato aperto
                file = null;
            }
        }
    }//GEN-LAST:event_menuOpenActionPerformed

    
    /** Metodo eseguito alla pressione della voce di menu File->Chiudi.
     *  Chiama il metodo chiudi.
     * 
     * @param evt 
     */
    private void menuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseActionPerformed
        chiudi();
    }//GEN-LAST:event_menuCloseActionPerformed

    
    /** Chiude il documento aperto. */
    private void chiudi() {
        // Se c'è un documento aperto
        if (documento != null) {
            // Controllo se ci sono modifiche da salvare
            if (!saved) {
                // Chiedo se si desidere salvare le modifiche con una finestra di dialogo
                int risposta = JOptionPane.showConfirmDialog(this, "Vuoi salvare le modifiche prima di chiudere il file?", "Salvataggio richiesto", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                switch (risposta) {
                    // Se ho chiuso la finestra con OK salvo il documento
                    case JOptionPane.OK_OPTION:
                        salva();
                        break;
                    // altrimenti chiudo senza salvare
                    case JOptionPane.CANCEL_OPTION:
                        return;
                }
            }
            // Svuoto la lista di undo e quella di redo
            undoList.clear();
            redoList.clear();
            // Disabilito le voci di menu legate al documento
            menuUndo.setText("Annulla");
            menuUndo.setEnabled(false);
            menuRedo.setText("Ripristina");
            menuRedo.setEnabled(false);
            menuCut.setEnabled(false);
            menuCopy.setEnabled(false);
            menuPortaAvanti.setEnabled(false);
            menuPortaInFondo.setEnabled(false);
            menuSave.setEnabled(false);
            menuSaveAs.setEnabled(false);
            menuClose.setEnabled(false);
            menuElenco.setEnabled(false);
            // Rimuovo il nome del file dalla barra del titolo della finestra
            setTitle("Drawing");
            // Elimino la vista grafica del documento
            mainPanel.removeAll();
            vistaGrafica = null;
            // Se aperta, chiudo la finestra con la vista testuale del documento
            if (vistaTesto != null) {
                vistaTesto.dispose();
                vistaTesto = null;
            }
            // Elimino il documento
            documento = null;
            file = null;
            saved = false;
            selezionata = -1;
            // Ridisegno il pannello della finestra Controller che conteneva la vista grafica
            mainPanel.revalidate();
            mainPanel.repaint();
            // Aggiorno la barra di stato
            setStatus("Chiuso documento");
        }
    }
    
    
    
    /* ********************************************************************************************* */
    /* ******************************** Gestione delle viste *************************************** */
    /* ********************************************************************************************* */
    
    
    /** Metodo eseguito quando un documento viene aperto (da file o creato nuovo).
     * - crea una nuova vista grafica, associandola a un gestore di eventi per permettere
     *   al Controller di intercettare le azioni che l'utente esegue sulla vista;
     * - aggiorna la vista in modo che mostri il documento appena aperto
     * - aggiorna le voci di menu
     */
    private void associaVista() {
        // Creo una nuova vista grafica e la aggiungo al pannello nella finestra del Controller
        vistaGrafica = new GraphicView(this, new Dimension(documento.getWidth(), documento.getHeight()));
        
        // Inserisco la vista in un pannello scorrevole, che aggiungo alla finestra
        JScrollPane mainScrollPane=new JScrollPane(vistaGrafica,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(mainScrollPane,BorderLayout.CENTER);        
        // Impostazioni per un documento appena aperto:
        saved = false;     
        selezionata = -1; // Nessuna forma selezionata        
        // Disabilito le voci di menu che agiscono sulla forma selezionata
        menuPortaAvanti.setEnabled(false);
        menuPortaInFondo.setEnabled(false);
        menuCut.setEnabled(false);
        menuCopy.setEnabled(false);        
        // Creo una nuova lista di undo e di redo
        undoList = new ArrayList<UndoItem>();
        redoList = new ArrayList<UndoItem>();
        // Abilito le voci di menu che agiscono sul documento aperto 
        menuSave.setEnabled(true);
        menuSaveAs.setEnabled(true);
        menuClose.setEnabled(true);
        menuElenco.setEnabled(true);        
        // Aggiungo la vista agli osservatori del documento
        documento.addObserver(vistaGrafica);        
        // Aggiorno la vista
        vistaGrafica.update(documento,null);
    }

    /** Metodo eseguito alla pressione della voce di menu Visualizza -> Lista Forme. 
     * Visualizza una finestra con una descrizione testuale del documento (vista testuale).
     * 
     * @param evt evento generato dal click sulla voce di menu
     */
    private void menuElencoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuElencoActionPerformed
        // Se c'è un documento aperto
        if (documento != null) {
            // Visualizzo la finestra
            vistaTesto = new WndTextView(this);
            vistaTesto.setVisible(true);
            // Metto la finestra in primo piano
            vistaTesto.requestFocus();
            // Il Controller dovrà gestire la chiusura della finestra
            vistaTesto.addWindowListener(this);
            // Aggiungo la finestra agli ossrvatori del documento
            documento.addObserver(vistaTesto);
            // Aggiorno la vista appena creata
            vistaTesto.update(documento, null);
        }
    }//GEN-LAST:event_menuElencoActionPerformed

    /**
     * Restituisce il documento aperto, null se non c'è nessun documento aperto.
     * Metodo chiamato da GraphicView per disegnare il documento.
     *
     * @return il documento aperto o null se non c'è nessun documento aperto
     */
    public Model getDocumento() {
        return documento;
    }
    
    
    /* ******************************************************************************************* */
    /* ****************************** Gestione della barra di stato ****************************** */
    /* ******************************************************************************************* */
 
    
    /** Scrive nella barra di stato la stringa ricevuta come argomento.
     * 
     * @param status la stringa da scrivere nella barra di stato
     */
    private void setStatus(String status) {
        this.lbl_status.setText(status);
    }
    
    
    /* ***************************************************************************************** */
    /* ********************* Gestione comandi Annulla e Ripristina ***************************** */
    /* ***************************************************************************************** */
    
    
    /** Metodo eseguito alla pressione della voce di menu Annulla dal menu Modifica.
     * - il documento corrente viene sostituito da quello memorizzato nella lista degli undo;
     * - la lista degli undo viene aggiornata eliminando l'elemento appena annullato (il primo dell'ArrayList);
     * - viene creato un nuovo redo per ripristinare l'annullamento appena effettuato.
     * 
     * @param evt l'evento generato cliccando sulla voce di menu
     */
    private void menuUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUndoActionPerformed
        if (undoList.size() > 0) {
            // Inserisco un elemento nella lista di redo (descrizione dell'ultimo undo, documento corrente)
            createRedo(undoList.get(0).descrizione, documento);
            // Sostituisco il documento con quello memorizzato nella lista di Undo
            documento = undoList.get(0).documento;
            // Le viste osservano il nuovo documento
            if(vistaTesto!=null) documento.addObserver(vistaTesto);
            documento.addObserver(vistaGrafica);           
           
            saved = false; // Il documento è stato modificato
            
            // Se ho annullato un inserimento e c'era una forma selezionata, annullo la selezione.
            if (selezionata != -1 && undoList.get(0).descrizione.equals("inserisci")) {
                // Nessuna forma selezionata (la forma da eliminare potrebbe essere quella selezionata)
                selezionata = -1;
                // Aggiorno le vocie di menu
                menuCut.setEnabled(false);
                menuCopy.setEnabled(false);
                menuPortaAvanti.setEnabled(false);
                menuPortaInFondo.setEnabled(false);
                // Aggiorno la barra di stato
                setStatus("Nessuna forma selezionata");
            }

            // Elimino l'ultimo elemento inserito nella lista di undo (il primo dell'ArrayList)
            undoList.remove(0);
            // Se la lista degli undo contiene altri elementi
            if (undoList.size() > 0) {
                // aggiorno la descrizione della voce di menu Undo
                menuUndo.setText("Annulla " + undoList.get(0).descrizione);
            } else {
                // altrimenti disabilito la voce di menu Annulla
                menuUndo.setText("Annulla");
                menuUndo.setEnabled(false);
            }
            // Aggiorno le viste
            vistaGrafica.update(documento,null);
            if(vistaTesto!=null) vistaTesto.update(documento,null);
        }
    }//GEN-LAST:event_menuUndoActionPerformed

    
    /** Crea una voce di undo, salvando la versione corrente del documento.
     * 
     * @param description una descrizione dell'ultima operazione effettuata. 
     */
    private void createUndo(String description) {
        // Creo una copia del documento corrente
        Model undo_copia = new Model(documento);
        // Creo una voce di undo
        createUndo(description, undo_copia);
    }
    

    /** Aggiunge una voce di undo all'elenco degli annullamenti.
     * Premendo Annulla, viene caricata la versione del documento PRIMA dell'ultima operazione eseguita.
     * La descrizione che compare nel menu Annulla si riferisce all'ultima operazione eseguita. 
     *
     * @param description una descrizione dell'operazione da annullare
     * @param copia una copia del documento PRIMA che l'ultima operazione venisse effettuata 
     */
    private void createUndo(String description, Model copia) {
        // Aggiungo un oggetto UndoItem in testa all'ArrayList degli undo
        // (è uno stack: l'ultimo undo inserito è il primo che viene eseguito premendo Annulla).
        undoList.add(0, new UndoItem(description, copia));
        // La lista degli undo contiene al massimo 10 elementi: elimino l'elemento più vecchio (l'ultimo dell'ArrayList)
        if (undoList.size() > 10) {
            undoList.remove(10);
        }
        // Se è il primo elemento inserito, abilito la voce di menu Annulla
        if (undoList.size() == 1) {
            menuUndo.setEnabled(true);
        }
        // Associo una descrizione testuale dell'operazione da annullare
        menuUndo.setText("Annulla " + description);
    }


    /** Metodo eseguito alla pressione della voce di menu Ripristina dal menu Modifica.
     * - il documento corrente viene sostituito da quello memorizzato nella lista dei redo;
     * - la lista dei redo viene aggiornata eliminando l'elemento appena ripristinato (il primo dell'ArrayList);
     * - viene creato un nuovo undo per annullare il redo appena effettuato.
     *
     * @param evt l'evento generato cliccando sulla voce di menu 
     */
    private void menuRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRedoActionPerformed
        // Se esistono elementi nell'ArrayList dei redo
        if (redoList.size() > 0) {
            // Inserisco un elemento nella lista di undo (descrizione dell'ultimo redo, documento corrente)
            createUndo(redoList.get(0).descrizione, documento);
            // Sostituisco il documento con quello memorizzato nella lista dei Redo
            documento = redoList.get(0).documento;
            // Le viste osservano il nuovo documento
            if(vistaTesto!=null) documento.addObserver(vistaTesto);
            documento.addObserver(vistaGrafica);
            
            // Il documento è stato modificato
            saved = false;
            
            // Elimino l'ultimo elemento inserito nella lista di Redo
            redoList.remove(0);
            // Se l'ArrayList dei redo contiene altri elementi
            if (redoList.size() > 0) {
                // aggiorno la scritta della voce di menu Ripristina
                menuRedo.setText("Ripristina " + redoList.get(0).descrizione);
            } else {
                // altrimenti disabilito la voce di menu Ripristina
                menuRedo.setText("Ripristina");
                menuRedo.setEnabled(false);
            }           
            // Aggiorno le viste
            vistaGrafica.update(documento,null);
            if(vistaTesto!=null) vistaTesto.update(documento,null);
        }
    }//GEN-LAST:event_menuRedoActionPerformed

    /** Aggiunge una voce di redo all'elenco dei ripristini.
     * Premendo Ripristina, viene caricata la versione del documento DOPO l'ultima operazione annullata.
     * La descrizione che compare nel menu Annulla si riferisce all'ultima operazione annullata. 
     * 
     * @param description una descrizione dell'operazione da ripristinare (l'ultima annullata)
     * @param copia una copia del documento DOPO l'ultima operazione annullata
     */
    private void createRedo(String description, Model copia) {
        // Aggiungo un oggetto UndoItem in testa all'ArrayList dei redo
        // (è uno stack: l'ultimo redo inserito è il primo che viene ripristinato).
        redoList.add(0, new UndoItem(description, copia));
        // La lista dei redo contiene al massimo 10 elementi: elimino l'elemento più vecchio (l'ultimo dell'ArrayList)
        if (redoList.size() > 10) {
            redoList.remove(10);
        }
        // Se è il primo elemento inserito, abilito la voce di menu Ripristina
        if (redoList.size() == 1) {
            menuRedo.setEnabled(true);
        }
        // Associo una descrizione testuale dell'operazione da ripristinare
        menuRedo.setText("Ripristina " + description);
    }

    
    
    
    /* ***************************************************************************************** */
    /* ********************* Gestione comandi Taglia, Copia e Incolla ************************** */
    /* ***************************************************************************************** */
    

    /** Copia negli appunti la forma selezionata.
     * 
     * @param evt l'evento generato cliccando sulla voce di menu  
     */
    private void menuCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCopyActionPerformed
        // Se c'è una forma selezionata
        if (selezionata != -1) {
            // La copio negli appunti
            appunti = new Forma(documento.getForma(selezionata));
            // Abilito la voce di menu "Incolla"
            menuPaste.setEnabled(true);
        }
    }//GEN-LAST:event_menuCopyActionPerformed

    
    /** Inserisce nel documento la forma contenuta negli appunti, spostandola leggermente in basso e a destra.
     * 
     * @param evt l'evento generato cliccando sulla voce di menu  
     */
    private void menuPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPasteActionPerformed
        int delta = 5; // Spostamento della forma incollata rispetto alla forma originale
        Forma copia_appunti;
        // Se c'è un documento aperto e gli appunti contengono qualcosa
        if (documento != null && appunti != null) {
            // Creo una voce di undo
            createUndo("incolla");
            // sposto la forma negli appunti di delta pixel in basso e a destra (in modo che non si sovrapponga alla forma originale)
            appunti.sposta(appunti.getX() + delta, appunti.getY() + delta);
            // creo una copia della forma negli appunti (e' possible incollare piu' volte la forma memorizzata negli appunti)
            copia_appunti = new Forma(appunti);
            // aggiungo la forma al documento
            documento.add(copia_appunti);
            // Il documento è stato modificato        
            saved = false;
            // Aggiorno la barra di stato (stampo anche il tipo della forma incollata)
            setStatus("Incollato " + copia_appunti.getTipo());
        }
    }//GEN-LAST:event_menuPasteActionPerformed

    
    /** Elimina la forma selezionata dal documento e la copia negli appunti.
     * 
     * @param evt l'evento generato cliccando sulla voce di menu  
     */
    private void menuCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCutActionPerformed
        // Se c'è una forma selezionata
        if (selezionata != -1) {
            // Creo una voce di undo
            createUndo("taglia");
            // Aggiorno la barra di stato
            setStatus("Tagliata forma " + selezionata);
        }
        // Copio la forma negli appunti
        menuCopyActionPerformed(evt);
        // Elimino la forma dal documento
        elimina();
    }//GEN-LAST:event_menuCutActionPerformed

    
    
    
    
    /* ******************************************************************************************* */
    /* ***** Gestione del documento: inserimento, selezione, modifica, eliminazione di forme ***** */
    /* ******************************************************************************************* */
    
    
    /** Apre una finestra di dialogo per la scelta del colore con cui colorare le nuove forme da inserire.
     * Utilizza la finestra di dialogo predefinita JColorChooser
     * 
     * @param evt l'evento generato cliccando sulla voce di menu   
     */
    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        // Faccio scegliere il colore da una finestra di dialogo
        Color newColor = JColorChooser.showDialog(null, "Scegli colore", this.btnColor.getBackground());
        // Se ho chiuso la finestra premendo OK (quindi ho effettivamente scelto un colore)
        if (newColor != null) {
            // Coloro il contorno e lo sfondo del pulsante btnColo con il colore scelto.
            // Il colore dello sfondo di btnColor verrà usato per colorare le nuove forme inserite.
            this.btnColor.setForeground(newColor);
            this.btnColor.setBackground(newColor);
        }
    }//GEN-LAST:event_btnColorActionPerformed

    /** Inserisce una forma alla posizione del mouse.
     * Il tipo di forma inserita dipende dallo strumento selezionato.
     * Il colore della forma dipende dallo sfondo del pulsante btnColor. 
     * La dimensione della forma inserita (cioè la dimensione del quadrato in cui la forma è inscritta) è 50x50 pixel.
     * 
     * @param e l'evento generato dal click del mouse
     */
    private void inserisci(MouseEvent e) {
        TipoForma tipo;
        // Creo una voce di undo
        createUndo("inserisci");
        // Ottengo le coordinate del mouse
        Point posizione = e.getPoint();
        // Il tipo di forma dipende dallo strumento selezionato
        if (btn_square.isSelected()) {
            tipo = TipoForma.QUADRATO;
        } else if (btn_circle.isSelected()) {
            tipo = TipoForma.CERCHIO;
        } else {
            tipo = TipoForma.TRIANGOLO;
        }
        // Creo la forma e la aggiungo al documento:
        // - La nuova forma è inscritta in un quadrato di lato 50 pixel.
        // - L'angolo in alto a sinistra del quadrato è alle coordinate del mouse.
        // - Il colore della forma corrisponde al colore di sfondo del pulsante btnColor.
        Forma f = new Forma(tipo, posizione.x, posizione.y, 50, 50, btnColor.getBackground());
        documento.add(f);
        // Il documento è stato modificato       
        saved = false;
        // Aggiorno la barra di stato
        setStatus("Inserito " + f.getTipo());
    }

    
    /** Seleziona la forma alla posizione del mouse.
     * 
     * @param e l'evento generato dal click del mouse
     * @return l'indice della forma selezionata all'interno dell'ArrayList delle forme
     * 
     * @see Model
     */
    private int seleziona(MouseEvent e) {
        // Ottengo le coordinate del mouse
        Point posizione = e.getPoint();
        // Seleziono la forma alle coordinate del mouse
        selezionata = seleziona(posizione.x, posizione.y);

        if (selezionata != -1) {
            // Ho selezionato una forma: aggiorno barra di stato e voci di menu
            setStatus("Selezionata forma " + selezionata);
            menuPortaAvanti.setEnabled(true);
            menuPortaInFondo.setEnabled(true);
            menuCut.setEnabled(true);
            menuCopy.setEnabled(true);
        } else {
            // Non ho selezionato nessuna forma: aggiorno barra di stato e voci di menu
            setStatus("Nessuna forma selezionata");
            menuPortaAvanti.setEnabled(false);
            menuPortaInFondo.setEnabled(false);
            menuCut.setEnabled(false);
            menuCopy.setEnabled(false);
        }
        // Restituisco l'indice della forma selezionata (-1 se nessuna forma è stata selezionata)
        return selezionata;
    }

    /**
     * Seleziona la forma in primo piano alle coordinate x,y
     *
     * @param x la coordinata x del punto da controllare
     * @param y la coordinata y del punto da controllare
     *
     * @return l'indice, all'interno del documento di tipo Model, della forma
     * selezionata o -1 se nel punto (x,y) non e' presente alcuna forma
     */
    public int seleziona(int x, int y) {
        selezionata = -1;
        // Parto dall'ultima forma inserita (l'ultima disegnata, quindi quella in primo piano)
        for (int i = documento.nForme() - 1; i >= 0; i--) {
            // controllo se il punto (x,y) è contenuto nella forma considerata
            if (documento.getForma(i).contiene(x, y)) {
                // memorizzo l'indice della forma selezionata
                selezionata = i;
                vistaGrafica.update(documento, null);
                break;
            }
        }
        // Restituisco l'indice della forma selezionata (-1 se nessuna forma è stata selezionata)
        return selezionata;
    }

    /**
     * Restituisce l'indice della forma selezionata o -1 se non e' selezionata
     * nessuna forma.
     *
     * @return l'indice della forma selezionata o -1 se non e' selezionata
     * nessuna forma
     * 
     * @see Model
     */
    public int getSelezionata() {
        return selezionata;
    }

    
    /** Elimina la forma alla posizione del mouse.
     * 
     * @param e l'evento generato dal click del mouse
     */
    private void elimina(MouseEvent e) {
        // Se c'è una forma alla posizione del mouse la seleziono
        if (seleziona(e) != -1) {
            // Creo una voce di undo
            createUndo("elimina");
            // Elimino la forma selezionata
            elimina();
            // Aggiorno la barra di stato
            setStatus("Eliminata forma");
        }
    }

    
    /** Elimina la forma selezionata. */
    private void elimina() {
        // Se c'è una forma selezionata
        if (selezionata != -1) {
            // Elimino la forma dal documento
            documento.elimina(getSelezionata());
            // Ora nessuna forma è selezionata
            selezionata = -1;
            // Aggiorno le voci di menu
            menuPortaAvanti.setEnabled(false);
            menuPortaInFondo.setEnabled(false);
            menuCut.setEnabled(false);
            menuCopy.setEnabled(false);
            // Il documento è stato modificato
            saved = false;
        }
    }

    /** Sposta alle coordinate del mouse la forma selezionata.
     * 
     * @param e l'evento generato dal click del mouse
     */
    private void sposta(MouseEvent e) {
        // Ottengo le coordinate del mouse
        Point posizione = e.getPoint();
        // Se c'è una forma selezionata, la sposto
        if (getSelezionata() != -1) {
            // Aggiorno, nel documento, la posizione della forma selezionata
            documento.spostaForma(getSelezionata(),posizione);
            moved = true;
            // Il documento è stato modificato
            saved = false;
            // Aggiorno la barra di stato
            setStatus("Spostata forma " + getSelezionata());
        }
    }

    /** Porta sullo sfondo la forma selezionata. 
     * Utile se, nel documento, più forme si sovrappongono.
     * Nel documento (classe Model), le forme sono contenute in un ArrayList.
     * Per disegnare la vista grafica del documento (metodo disegna della classe Model) disegno le singole forme contenute nell'ArrayList
     * partendo dalla prima inserita: in questo modo la prima forma dell'array sarà la prima disegnata e, quindi, sarà la forma sullo sfondo.
     * 
     * @param evt l'evento generato dal click sulla voce di menu
     */
    private void menuPortaInFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPortaInFondoActionPerformed
        // Se c'è una forma selezionata, la sposto sullo sfondo
        if (selezionata != -1) {
            // Creo una voce di undo
            createUndo("porta avanti");
            // Aggiorno la barra di stato
            setStatus("Porta avanti forma " + selezionata);

            // Sposto sullo sfondo la forma sezionata:
            // 1. Copio la forma selezionata
            Forma f = documento.getForma(selezionata);
            // 2. La elimino dall'array delle forme
            documento.elimina(selezionata);
            // 3. La aggiungo in testa all'array delle forme (indice 0, spostando le altre forme di una posizione verso destra)
            documento.add(0, f);

            // Seleziono la forma appena spostata (ora è in testa all'array, indice 0)
            selezionata = 0;
            // Il documento è stato modificato
            saved = false;
        }
    }//GEN-LAST:event_menuPortaInFondoActionPerformed


    /** Porta in primo piano la forma selezionata. 
     * Utile se, nel documento, più forme si sovrappongono.
     * Nel documento (classe Model), le forme sono contenute in un ArrayList.
     * Per disegnare la vista grafica del documento (metodo disegna della classe Model) disegno le singole forme contenute nell'ArrayList
     * partendo dalla prima inserita: in questo modo l'ultima forma dell'array sarà l'ultima disegnata e, quindi, sarà la forma in primo piano.
     * 
     * @param evt l'evento generato dal click sulla voce di menu
     */
    private void menuPortaAvantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPortaAvantiActionPerformed

        if (selezionata != -1) {
            // Creo una voce di undo
            createUndo("porta avanti");
            // Aggiorno la barra di stato
            setStatus("Porta avanti forma " + selezionata);

            // Sposta in primo piano la forma selezionata
            // 1. Copio la forma selezionata
            Forma f = documento.getForma(selezionata);
            // 2. La elimino dall'array delle forme
            documento.elimina(selezionata);
            // 3. La aggiungo in coda all'array delle forme
            documento.add(f);

            // Seleziono la forma appena spostata (ora è in fondo all'array)
            selezionata = documento.nForme() - 1;
            // Il documento è stato modificato
            saved = false;
        }
    }//GEN-LAST:event_menuPortaAvantiActionPerformed

    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        chiudi();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Il main crea e visualizza una finestra di tipo Controller senza nessun
     * documento associato.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller().setVisible(true);
            }
        });
    }



    /**
     * Gestione del click del mouse sulla vista. A seconda dello strumento
     * selezionato è possibile:
     * <ul>
     * <li>selezionare una forma</li>
     * <li>eliminare una forma</li>
     * <li>inserire una nuova forma</li>
     * </ul>
     *
     * @param e l'evento generato dal click del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (btn_select.isSelected()) {
            seleziona(e);
        } else if (btn_delete.isSelected()) {
            elimina(e);
        } else {
            inserisci(e);
        }
    }

    /**
     * Cliccando su una forma, ne viene fatta una copia: se la forma
     * selezionata verrà spostata, la copia verrà inserita nella lista degli
     * annullamenti.
     *
     * @param e l'evento generato dalla pressione del tasto sinistro del
     * mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (getSelezionata() != -1) {
            copia = new Model(documento);
        }
    }

    /**
     * Al rilascio del tasto destro del mouse, se la forma selezionata è
     * stata spostata, la copia del documento fatta in precedenza viene
     * inserita nella lista degli annullamenti.
     *
     * @param e l'evento generato dal rilascio del tasto sinistro del mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (copia != null && moved && getSelezionata() != -1) {
            createUndo("sposta", copia);
            copia = null;
            moved = false;
        }
    }

    /**
     * Trascinando la forma selezionata è possibile spostarla all'interno
     * del documento
     *
     * @param e l'evento generato dal trascinamento del mouse
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        sposta(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

    /**
     * Chiude la finestra della vista testuale del documento
     * 
     * @param e evento generato dalla finestra
     */
    @Override
    public void windowClosing(WindowEvent e) {
        // Elimino la finestra dall'elenco degli osservatori del documento
        documento.deleteObserver(vistaTesto);
        vistaTesto.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}


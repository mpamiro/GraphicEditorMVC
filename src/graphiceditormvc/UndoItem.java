/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphiceditormvc;

/**
 * Rappresenta un elemento della lista degli annullamenti (menu Modifica->Annulla).
 * Ogni elemento della lista di annullamenti e' composto dal documento così come era 
 * prima della modifica da annullare e dalla descrizione dell'operazione da annullare.
 * 
 * @author mauropamiro
 */
public class UndoItem{
    String description;
    Model documento;

    /**
     * Cre un elemento della lista degli annullamenti.
     * 
     * @param description   descrizione dell'operazione da annullare
     * @param documento     il documento così come era prima della modifica da annullare
     */
    public UndoItem(String description, Model documento) {
        this.description = description;
        this.documento = documento;
    }
}

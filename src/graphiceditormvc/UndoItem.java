package graphiceditormvc;

/**
 * Rappresenta un elemento della lista degli annullamenti (menu Modifica->Annulla).
 * Ogni elemento della lista di annullamenti e' composto dal documento così come era 
 * prima della modifica da annullare e dalla descrizione dell'operazione da annullare.
 * 
 * @author mauropamiro
 */
public class UndoItem{
    /** una stringa che rappresenta l'operazione annullata o da ripristinare. */
    String descrizione;
    /** una copia del documento prima dell'operazione da annullare o dopo l'operazione da ripristinare. */
    Model documento;

    /**
     * Cre un elemento della lista degli annullamenti.
     * 
     * @param descrizione   descrizione dell'operazione da annullare
     * @param documento     il documento così come era prima della modifica da annullare
     */
    public UndoItem(String descrizione, Model documento) {
        this.descrizione = descrizione;
        this.documento = documento;
    }
}

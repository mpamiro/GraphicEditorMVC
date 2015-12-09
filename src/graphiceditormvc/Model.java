package graphiceditormvc;

import java.util.*;
import java.io.Serializable; // Per salvare il documento in un file binario
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D; // Graphics2D rappresenta un contesto grafico, cioè una "penna" con cui disegnare. 
                            // Ogni componente in cui si può disegnare ha il proprio contesto grafico.
                            // Disegnare utilizando un contesto grafico significa disegnare nel componente ad esso associato.
                            
/**
 * Un documento di tipo Model che contiene una serie di forme colorate 
 * disposte all'interno di uno spazio di dimensioni definite.
 * 
 * @see TipoForma
 * @see Forma
 * 
 * @author mauropamiro
 */
public class Model implements Serializable{
    private int height, width; // Dimensioni del documento in pixel
    private ArrayList<Forma> forme; // L'array in cui vengono memorizzate le forme via via aggiunte al documento



    /***********************************************************************************/
    /********************** Costruttori e metodi get/set *******************************/
    /***********************************************************************************/

    /**
     * Crea un nuovo documento di tipo Model
     * 
     * @param width la larghezza del documento in pixel
     * @param height l'altezza del documento in pixel
     */
    public Model(int width, int height){
        // Imposto le dimensioni del documento
        this.height=height;
        this.width=width;
        // Creo l'ArrayList vuoto delle forme
        forme=new ArrayList<Forma>();
    }
        
    /**
     * Crea un nuovo documento 200x200 px di tipo Model
     */
    public Model(){
        // Chiama il costruttore precedente
        this(200,200);
    }
    
    /**
     * Crea un nuovo documento di tipo Model a partire dal documento ricevuto come parametro
     * 
     * @param copy il documento di cui fare la copia 
     */
    public Model(Model copy){
        // Imposto la dimensione del documento come quella del documento da copiare
        this.height=copy.height;
        this.width=copy.width;
        // Creo l'array delle forme e vi copio una alla volta le forme del documento da copiare
        forme=new ArrayList<Forma>();
        for(int i=0;i<copy.forme.size();i++){
            // Ottengo una forma dal documento da copiare
            Forma c=copy.forme.get(i);
            // Creo una copia della forma
            Forma f=new Forma(c.getTipo(),c.getX(),c.getY(),c.getWidth(),c.getHeight(),c.getColore());
            // Aggiungo la copia della forma all'array delle forme
            this.forme.add(f);
        }
    }
    
    /**
     * Restituisce l'altezza del documento
     * 
     * @return l'altezza del documento
     */
    public int getHeight(){
        return height;
    }
     
    /**
     * Restituisce la larghezza del documento
     * 
     * @return la larghezza del documento
     */   
    public int getWidth(){
        return width;
    }    
    
    /**
     * Restituisce il numero di forme contenute nel documento, 
     * cioe' la dimensione dell'ArrayList contenente le forme.
     * 
     * @return il numero di forme contenute nel documento
     */
    public int nForme(){
        return forme.size();
    }    
    
    /**
     * Restituisce un riferimento alla forma il cui indice viene passato come parametro.
     * Le forme sono contenute in un oggetto di tipo ArrayList.
     * 
     * @param index l'indice della forma da restituire
     * 
     * @return un riferimento alla forma di indice index o null se non esiste
     */
    public Forma getForma(int index){
        try{
            return forme.get(index);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    /**
     * Fornisce una descrizione testuale del documento, specificando indice, tipo,
     * coordinate, dimensioni e colore dell forme che contiene.
     * 
     * @return una descrizione testuale del documento
     * 
     * @see Forma
     */
    @Override
    public String toString(){
        String descrizione="";
        // Aggiungo alla stringa le descrizioni delle forme contenute nel documento 
        for(int i=0;i<forme.size();i++){
            descrizione+=i+": "+forme.get(i).toString()+"\n";
        }
        return descrizione;
    }    
    
    
    

    /***********************************************************************************/
    /********************** Aggiunta ed eliminazione di forme **************************/
    /***********************************************************************************/    
    
    /**
     * Aggiunge una forma al documento
     * 
     * @param f la forma da aggiungere
     */
    public void add(Forma f){
        forme.add(f);
    }

    /**
     * Aggiunge una forma al documento nella posizione index dell'array delle forme, 
     * spostando tutti gli altri elementi di una posizione verso destra.
     * 
     * 
     * @param f la forma da aggiungere
     */
    public void add(int index, Forma f){
        forme.add(index,f);
    }
        
    
    /**
     * Elimina la forma il cui indice viene passato come parametro.
     * Le forme sono contenute in un oggetto di tipo ArrayList.
     * 
     * @param index l'indice della forma da eliminare
     */
    public void elimina(int index){
        try{
            forme.remove(index);
        }catch(IndexOutOfBoundsException e){}        
    }
    
  }

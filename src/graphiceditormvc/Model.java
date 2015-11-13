package graphiceditormvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.*;
import java.io.Serializable;

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
    private int height, width;
    private ArrayList<Forma> forme;

    /**
     * Crea un nuovo documento di tipo Model
     * 
     * @param width la larghezza del documento in pixel
     * @param height l'altezza del documento in pixel
     */
    public Model(int width, int height){
        this.height=height;
        this.width=width;
        forme=new ArrayList<Forma>(); // Aggiunto Forma per compatibilità con Java 6
    }
        
    /**
     * Crea un nuovo documento 200x200 px di tipo Model
     */
    public Model(){
        this(200,200);
    }
    
    /**
     * Crea un nuovo documento di tipo Model a partire dal documento ricevuto come parametro
     * 
     * @param copy il documento di cui fare la copia 
     */
    public Model(Model copy){
        this.height=copy.height;
        this.width=copy.width;
        forme=new ArrayList<Forma>(); // Aggiunto Forma per compatibilità con Java 6
        for(int i=0;i<copy.forme.size();i++){
            Forma c=copy.forme.get(i);
            Forma f=new Forma(c.getTipo(),c.getX(),c.getY(),c.getWidth(),c.getHeight(),c.getColore());
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
     * Aggiunge una forma al documento
     * 
     * @param f la forma da aggiungere
     */
    public void add(Forma f){
        forme.add(f);
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
     * Restituisce il numero di forme contenute nel documento, 
     * cioe' la dimensione dell'ArrayList contenente le forme.
     * 
     * @return il numero di forme contenute nel documento
     */
    public int nForme(){
        return forme.size();
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
        for(int i=0;i<forme.size();i++){
            descrizione+=i+": "+forme.get(i).toString()+"\n";
        }
        return descrizione;
    }
  }

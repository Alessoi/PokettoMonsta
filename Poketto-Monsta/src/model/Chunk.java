package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Vector;

import control.SpriteSheetMappa;
import model.Caselle.Casella;

/**
 * Classe che fa da contenitore per una matrice di 16*16 caselle che formano la mappa.
 * Può essere scritta e letta da file perchè implementa l'interfaccia Serializable
 * 
 * @see Serializable
 * @see Casella
 * 
 */

public class Chunk implements Serializable{
	private static final long serialVersionUID = -1510758868188965874L;
	public final static int DIM_MATRICE_CASELLE=16;
	// transient serve a non fare scrivere su file l'attributo quando la classe viene serializzata
	private transient Image chunkImage;
	private Casella[][] caselle;
	private String regione;
	private Vector<Npc> personaggi = new Vector<Npc>();
	
	/**
	 * Costruttore della classe
	 * 
	 * @param caselle la matrice di caselle che conpongono il chunk
	 * @param regione il nome della regione all'interno della quale si trova il chunk
	 */
	public Chunk(Casella[][] caselle, String regione, Vector<Npc> personaggi) {
		super();
		
//		if(caselle.length == DIM_MATRICE_CASELLE && caselle[0].length == DIM_MATRICE_CASELLE) {
//		}
		
		this.caselle = caselle;
		this.regione = regione;
		this.personaggi = personaggi;
	}
	
	/**
	 * Metodo per generare l'immagine del chunk a partire dalla matrice di caselle.
	 * L'immagine generata viene salvata in un attributo.
	 * 
	 * @see Casella
	 * @see Image
	 */
	
	public void generateImage() {
		BufferedImage img = new BufferedImage(DIM_MATRICE_CASELLE*16, DIM_MATRICE_CASELLE*16, BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics();
		
		for (int x = 0; x < DIM_MATRICE_CASELLE; x++) {
			for (int y = 0; y < DIM_MATRICE_CASELLE; y++) {
				Casella casella = getCasella(x, y);
				g.drawImage(SpriteSheetMappa.getImage(casella.getxCordinateImmagine(),casella.getyCordinateImmagine()), x*16, y*16, DIM_MATRICE_CASELLE, DIM_MATRICE_CASELLE, null);
			}
		}
		
		chunkImage = img;
		
		if(personaggi != null) {
			for(int i = 0; i < personaggi.size(); i++) {
				Npc personaggio = personaggi.get(i);
				
				personaggio.caricaImmagini();
			}
		}
	}
	
	/**
	 * Metodo per ottenere l'immagine del chunk
	 * 
	 * @return l'immagine del chunk
	 */
	public Image getImage() {
		return chunkImage;
	}
	
	/**
	 * Metodo per ottenere la casella che si trova all'interno della matrice alle coordinate passate al metodo
	 * 
	 * @param x la coordinata x della casella richiesta all'interno del chunk
	 * @param y la coordinata y della casella richiesta all'interno del chunk
	 * @return la casella alle coordinate richieste
	 * 
	 * @see Casella
	 */
	public Casella getCasella(int x, int y) {
		return caselle[x][y];
	}
	
	/**
	 * Metodo per ottenere il nome della regione in cui si trova il chunk
	 * 
	 * @return il nome della regione in cui si trova il chunk
	 */
	public String getRegione() {
		return regione;
	}
	
	public Vector<Npc> getPersonaggi(){
		return personaggi;
	}
}

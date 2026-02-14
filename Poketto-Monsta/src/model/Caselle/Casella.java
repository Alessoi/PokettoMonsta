package model.Caselle;

import java.io.Serializable;

import model.Giocatore;
import model.Gioco;

/**
 * Classe che serve a rappresentare le caselle che compongono un chunk.
 * Può essere scritta e letta da file perchè implementa l'interfaccia Serializable
 * 
 * @since chunk
 * @see Serializable 
 */

public class Casella implements Serializable{
	private static final long serialVersionUID = -6225261007225874477L;
	protected int xCordinateImmagine, yCordinateImmagine;
	private boolean camminabile;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param xCordinateImmagine la coordinata x a cui si trova immagine della casella nello spritesheet
	 * @param yCordinateImmagine la coordinata y a cui si trova immagine della casella nello spritesheet
	 * @param camminabile se si può camminare sulla casella
	 */
	
	public Casella(int xCordinateImmagine, int yCordinateImmagine, boolean camminabile) {
		super();
		this.xCordinateImmagine = xCordinateImmagine;
		this.yCordinateImmagine = yCordinateImmagine;
		this.camminabile = camminabile;
	}
	
	/**
	 * L'azione che la casella fa se calpestata
	 * 
	 * @param gioco il gioco
	 * @param giocatore il giocatore
	 */
	public void azioneSeCalpestata(Gioco gioco, Giocatore giocatore) {}
	
	/**
	 * Metodo che restituisce la coordinata x a cui si trova immagine della casella nello spritesheet
	 * @return la coordinata x a cui si trova immagine della casella nello spritesheet
	 */
	public int getxCordinateImmagine() {
		return xCordinateImmagine;
	}
	
	/**
	 * Metodo che restituisce la coordinata y a cui si trova immagine della casella nello spritesheet
	 * @return la coordinata y a cui si trova immagine della casella nello spritesheet
	 */
	public int getyCordinateImmagine() {
		return yCordinateImmagine;
	}
	
	/**
	 * Metodo che restituisce se si può camminare sulla casella
	 * @return true se si può camminare sulla casella
	 */
	public boolean isCamminabile() {
		return camminabile;
	}
}

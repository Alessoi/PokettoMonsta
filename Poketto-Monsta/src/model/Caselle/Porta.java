package model.Caselle;

import model.Giocatore;
import model.Gioco;

/**
 * Classe che estende Casella e che ne aggiunge aggiunge funzionalità quando calpestata,
 * teletrasportando il giocatore a delle coordiante prefissate 
 * 	
 *	@see Casella
 */
public class Porta extends Casella{
	private static final long serialVersionUID = 5901935503097554580L;
	//le cordinate a cui teletrasportare il giocatore
	private int tpX, tpY;

	/**
	 * Costruttore della classe
	 * 
	 * @param xCordinateImmagine la coordinata x a cui si trova immagine della casella nello spritesheet
	 * @param yCordinateImmagine la coordinata y a cui si trova immagine della casella nello spritesheet
	 * @param camminabile se si può camminare sulla casella
	 * @param tpX le coordinate a cui teletrasportare il giocatore
	 * @param tpY le coordinate a cui teletrasportare il giocatore
	 */
	public Porta(int xCordinateImmagine, int yCordinateImmagine, boolean camminabile, int tpX, int tpY) {
		super(xCordinateImmagine, yCordinateImmagine, camminabile);
		this.tpX = tpX;
		this.tpY = tpY;
	}
	
	/**
	 * L'azione che la casella fa se calpestata, teletrasporta il giocatore a delle coordinate prefissate
	 * 
	 * @param gioco il gioco
	 * @param giocatore il giocatore
	 */
	@Override
	public void azioneSeCalpestata(Gioco gioco, Giocatore giocatore) {
		giocatore.setPosX(tpX);
		giocatore.setPosY(tpY);
	}
}

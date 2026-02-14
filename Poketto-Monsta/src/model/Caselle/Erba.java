package model.Caselle;

import model.Giocatore;
import model.Gioco;
import model.ListaMostri;

/**
 * Classe che estende Casella e che ne aggiunge aggiunge funzionalità quando calpestata,
 * facendo cominciare una battaglia 
 * 	
 *	@see Casella
 */
public class Erba extends Casella{
	private static final long serialVersionUID = 923111131061862007L;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param xCordinateImmagine la coordinata x a cui si trova immagine della casella nello spritesheet
	 * @param yCordinateImmagine la coordinata y a cui si trova immagine della casella nello spritesheet
	 * @param camminabile se si può camminare sulla casella
	 */
	public Erba(int xCordinateImmagine, int yCordinateImmagine, boolean camminabile) {
		super(xCordinateImmagine, yCordinateImmagine, camminabile);
	}
	
	/**
	 * L'azione che la casella fa se calpestata, una volta su dieci fa cominciare una battaglia
	 * 
	 * @param gioco il gioco
	 * @param giocatore il giocatore
	 */
	@Override
	public void azioneSeCalpestata(Gioco gioco, Giocatore giocatore) {
		if((int) (Math.random()*8) == 0) {
			gioco.setMostro_randomico(ListaMostri.getMostroNuovo((int) (Math.random() * ListaMostri.N_MOSTRI)));
			gioco.getMostro_randomico().riempiMosse();
			
			if((int) (Math.random()*5) == 0) {
				gioco.getMostro_randomico().setNome("BOSS: " + gioco.getMostro_randomico().getNome());
				
				gioco.getMostro_randomico().setHp(gioco.getMostro_randomico().getHp()*2);
				gioco.getMostro_randomico().setHp_attuali(gioco.getMostro_randomico().getHp());
				
				gioco.getMostro_randomico().setAttacco(gioco.getMostro_randomico().getAttacco() + 20);
				gioco.getMostro_randomico().setDifesa(gioco.getMostro_randomico().getDifesa() + 30);
			}
			//inizia la battaglia
			gioco.setInBattaglia(true);
		}
	}
}

package model.Caselle;

import model.Giocatore;
import model.Gioco;
import model.Mostro;

public class Curatore extends Casella{
	private static final long serialVersionUID = 923111131061862007L;
	
	public Curatore(int xCordinateImmagine, int yCordinateImmagine, boolean camminabile) {
		super(xCordinateImmagine, yCordinateImmagine, camminabile);
	}
	
	@Override
	public void azioneSeCalpestata(Gioco gioco, Giocatore giocatore) {
		for(Mostro mostro : giocatore.getSquadra()) {
			mostro.setHp_attuali(mostro.getHp());
		}
	}
}

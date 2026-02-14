

import control.SpriteSheetMappa;

import model.ListaMosse;
import model.ListaMostri;
import view.Finestra;

public class Main {	
	public static void main(String[] args) {
		//carica lo spriteSheet della mappa e lo separe nelle sottoimmagini...
		SpriteSheetMappa.init();
		ListaMosse.init();
		ListaMostri.init();
		
		//ciao bellissimi
		Finestra f = new Finestra();
		
		
	}
	
	
}

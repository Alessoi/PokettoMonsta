package control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import model.PuntiCardinali;
import view.Finestra;
import view.PannelloGioco;
import view.PannelloMenu;

/**
 * 
 * Classe che ascolta i tasti della tastiera, implementa KeyEventDispatcher in modo da rispondere
 * in maniera adeguata alle interazioni dell'utente con la tastiera. Viene usata nel PannelloGioco
 *
 */
public class GestioneTastieraGioco implements KeyEventDispatcher{
	private PannelloGioco pannello;
	private Finestra finestra;
	
	/**Il costrutore della classe
	 * 
	 * @param pannello il pannello 
	 * @param finestra la finestra che contiene il pannello
	 */
	public GestioneTastieraGioco(PannelloGioco pannello, Finestra finestra) {
		this.pannello = pannello;
		this.finestra = finestra;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		Container padre2 = pannello.getParent();
		String name = padre2.getComponent(padre2.getComponentCount()-1).getName();
		
		if(e.getID()==KeyEvent.KEY_PRESSED && Finestra.GIOCO.compareTo(name) == 0) {
			String key = ""+e.getKeyChar();
			if(key.equalsIgnoreCase("W")) {
				pannello.setDirezione(PuntiCardinali.Nord);
			}else if(key.equalsIgnoreCase("A")) {
				pannello.setDirezione(PuntiCardinali.Ovest);
			}else if(key.equalsIgnoreCase("S")) {
				pannello.setDirezione(PuntiCardinali.Sud);
			}else if(key.equalsIgnoreCase("D")) {
				pannello.setDirezione(PuntiCardinali.Est);
			}else if(key.equalsIgnoreCase("E")) {
				pannello.getTimer().stop();
				Container padre = pannello.getParent();
				padre.add(new PannelloMenu(pannello.getGioco(), finestra), Finestra.MENU_GIOCO);
				CardLayout layout = (CardLayout) padre.getLayout();
				layout.show(padre, Finestra.MENU_GIOCO);
			}else if(key.equalsIgnoreCase("\n")) {
				pannello.setInteragisci(true);
			}
		}
		
		return false;
	}

}

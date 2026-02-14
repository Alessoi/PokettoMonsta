package control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import view.Finestra;
import view.PannelloGioco;
import view.PannelloMenu;

/**
 * 
 * Classe che ascolta i bottoni del pannelloMenu, implementa ActionListener in modo da rispondere
 * in maniera adeguata alle interazioni dell'utente con il pannello
 *
 */
public class GestioneBottoniMenu implements ActionListener{
	private PannelloMenu pannello;
	private Finestra finestra;
	
	/**Il costrutore della classe
	 * 
	 * @param pannello il pannello che contiene i componenti da ascoltare
	 * @param finestra la finestra che contiene il pannello
	 */
	public GestioneBottoniMenu(PannelloMenu pannello, Finestra finestra) {
		this.pannello = pannello;
		this.finestra = finestra;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == pannello.getSquadra()) {
			pannello.setSquadraVisibile(!pannello.isSquadraVisibile());
			pannello.mostraSquadra();
			
		}else if(e.getSource() == pannello.getSalva()) {
			boolean salva = false;
			
			if(SalvataggioGiocatore.carica() != null) {
				int i = JOptionPane.showConfirmDialog(finestra, "E' stata trovato un salvataggio, vuoi sovrascriverlo?", "Conferma", JOptionPane.YES_NO_OPTION);
				if(i==JOptionPane.YES_OPTION) {
					salva = true;
				}
			}else {
				salva = true;
			}
			
			if(salva) {
				SalvataggioGiocatore.salva(pannello.getGioco().getPlayer());
				JOptionPane.showMessageDialog(null, "Salvataggio completato");
			}
			
		}else if(e.getSource() == pannello.getEsci()) {
			Container padre = pannello.getParent();
			padre.remove(pannello);
			CardLayout layout = (CardLayout) padre.getLayout();
			layout.last(padre);
			PannelloGioco p = (PannelloGioco) padre.getComponent(padre.getComponentCount()-1);
			p.getTimer().start();
			
		}else if(e.getSource() == pannello.getMenuStart()) {
			Container padre = pannello.getParent();
			padre.remove(pannello);
			
			
			finestra.musica_gioco.stop();
			
			finestra.musica.start();
			finestra.musica.loop(Clip.LOOP_CONTINUOUSLY);
			PannelloGioco p = (PannelloGioco) (padre.getComponent(padre.getComponentCount()-1));
			p.removeDispatcher();
			
			padre.remove(padre.getComponentCount()-1);
			CardLayout layout = (CardLayout) padre.getLayout();
			layout.last(padre);
		}
	}
}

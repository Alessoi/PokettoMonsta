package control;

import static view.Finestra.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import model.Giocatore;
import model.Gioco;
import model.PuntiCardinali;
import view.Finestra;
import view.PannelloGioco;
import view.PannelloImpostazioni;
import view.PannelloStart;

/**
 * 
 * Classe che ascolta i bottoni del pannelloStart, implementa ActionListener e MouseListener in modo da rispondere
 * in maniera adeguata alle interazioni dell'utente con il pannello
 * 
 * @author Enrico Gatti
 */
public class GestioneBottoniStart implements ActionListener, MouseListener {

	private PannelloStart pannello;
	private Finestra finestra;
	
	/**Il costrutore della classe
	 * 
	 * @param pannello il pannello che contiene i componenti da ascoltare
	 * @param finestra la finestra che contiene il pannello
	 */
	public GestioneBottoniStart(Finestra finestra,PannelloStart pannello) {
		super();
		this.finestra=finestra;
		this.pannello = pannello;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stb
		if(e.getSource()==pannello.getStart()) {
			Giocatore giocatore = SalvataggioGiocatore.carica();
			
			if(giocatore != null) {
				int i = JOptionPane.showConfirmDialog(finestra, "E' stata trovato un salvataggio, vuoi caricarlo?", "Conferma", JOptionPane.YES_NO_OPTION);
				if(i==JOptionPane.NO_OPTION) {
					giocatore = new Giocatore("Gigi",19,19,PuntiCardinali.Sud);
				}
			}else {
				giocatore = new Giocatore("Gigi",19,19,PuntiCardinali.Sud);
			}
			
			Gioco gioco = new Gioco(giocatore); 
			
			finestra.getMusica().stop();
			finestra.musica_gioco.start();
			finestra.musica_gioco.loop(Clip.LOOP_CONTINUOUSLY);
			Container padre = pannello.getParent();
			padre.add(new PannelloGioco(gioco, finestra), Finestra.GIOCO);
			
			CardLayout layout = (CardLayout) padre.getLayout();
			layout.show(padre, Finestra.GIOCO);
		}else if(e.getSource()==pannello.getImpostazioni()){
			Container padre2 = pannello.getParent();
			padre2.add(new PannelloImpostazioni(finestra), Finestra.MENU_IMPOSTAZIONI);
			CardLayout layout = (CardLayout) padre2.getLayout();
			layout.show(padre2, MENU_IMPOSTAZIONI);
		}else if(e.getSource()==pannello.getEsci()) {
			System.out.println();
			WindowEvent chiuso = new WindowEvent(finestra, WindowEvent.WINDOW_CLOSING);
			finestra.dispatchEvent(chiuso);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==pannello.getStart()) {
			pannello.getStart().setForeground(Color.red);
			
		}else if(e.getSource()==pannello.getImpostazioni()){
			pannello.getImpostazioni().setForeground(Color.red);
			
		}else if(e.getSource()==pannello.getEsci()) {
			pannello.getEsci().setForeground(Color.red);
			
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==pannello.getStart()) {
			pannello.getStart().setForeground(Color.darkGray);
			
		}else if(e.getSource()==pannello.getImpostazioni()){
			pannello.getImpostazioni().setForeground(Color.darkGray);
			
		}else if(e.getSource()==pannello.getEsci()) {
			pannello.getEsci().setForeground(Color.darkGray);
			
		}
	}
}

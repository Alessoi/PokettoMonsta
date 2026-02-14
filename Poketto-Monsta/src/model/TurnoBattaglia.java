package model;

import java.awt.CardLayout;
import java.awt.Container;

import javax.sound.sampled.Clip;

import view.Finestra;
import view.PannelloBattaglie;
import view.PannelloGioco;

public class TurnoBattaglia implements Runnable{
	private PannelloBattaglie pannello;
	private Finestra finestra;
	private Mossa mossaAlleato;
	
	public TurnoBattaglia(PannelloBattaglie pannello, Mossa mossaAlleato, Finestra finestra) {
		this.pannello = pannello;
		this.finestra = finestra;
		
		this.mossaAlleato = mossaAlleato;
	}
	
	@Override
	public void run() {
		pannello.setMyTurn(false);
		
		Mostro alleato = pannello.getGioco().getPlayer().getSquadra()[0];
		Mostro nemico = pannello.getGioco().getMostro_randomico();
		
		//mossa alleato
		pannello.getDescrizione().setText(alleato.getNome() + " usa " + mossaAlleato.getNome_mossa() + "!");
		int hpPrecedenti = nemico.getHp_attuali();
		
		int danno = mossaAlleato.getBasePower();
		int precisione = mossaAlleato.getPrecisione();
		
		if(alleato.getHp_attuali() > 0 && calcolaDanno(danno, precisione, alleato, nemico)) {
			int i = hpPrecedenti;
			pannello.getBarra_vita_avversario().setValue(i);
			
			while(i > nemico.getHp_attuali() && i > 0) {
				i--;
				pannello.getBarra_vita_avversario().setValue(i);
				pannello.getPunti_vita_avversario().setText(i + "/" + nemico.getHp() + " hp");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//se la vita scende a 0 si esce dalla lotta
			if(nemico.getHp_attuali() <= 0) {
				pannello.getGioco().setInBattaglia(false);
				finestra.musica_battaglia.stop();
				finestra.musica_gioco.start();
				finestra.musica_gioco.loop(Clip.LOOP_CONTINUOUSLY);
				Container padre = pannello.getParent();
				padre.remove(pannello);
				CardLayout layout = (CardLayout) padre.getLayout();
				layout.last(padre);
				PannelloGioco p = (PannelloGioco) padre.getComponent(padre.getComponentCount()-1);
				p.getTimer().start();
			}
		}else {
			pannello.getDescrizione().setText("L'attacco di " + alleato.getNome() + " fallisce!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//mossa nemico
		Mossa mossa = nemico.getMosse()[(int) (Math.random()*3)];
		pannello.getDescrizione().setText(nemico.getNome() + " usa " + mossa.getNome_mossa() + "!");
		
		hpPrecedenti = alleato.getHp_attuali();
		
		danno = mossa.getBasePower();
		precisione = mossa.getPrecisione();
		
		if(nemico.getHp_attuali() > 0 && calcolaDanno(danno, precisione, nemico, alleato)) {
			int i = hpPrecedenti;
			pannello.getBarra_vita().setValue(i);
			
			while(i > alleato.getHp_attuali() && i > 0) {
				i--;
				pannello.getBarra_vita().setValue(i);
				pannello.getPunti_vita().setText(i + "/" + alleato.getHp() + " hp");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//se la vita scende a 0 si esce dalla lotta
			if(alleato.getHp_attuali() <= 0) {
				
				if(controlloVitaMostri()) {
					pannello.getGioco().setInBattaglia(false);
					finestra.musica_battaglia.stop();
					finestra.musica_gioco.start();
					finestra.musica_gioco.loop(Clip.LOOP_CONTINUOUSLY);
					Container padre = pannello.getParent();
					padre.remove(pannello);
					CardLayout layout = (CardLayout) padre.getLayout();
					layout.last(padre);
					PannelloGioco p = (PannelloGioco) padre.getComponent(padre.getComponentCount()-1);
					p.getTimer().start();
					//momentaneamente curo la mia squadra se tutti i mostri finiscono gli hp
					for(int x = 0; x < 3; x++) {
						pannello.getGioco().getPlayer().getSquadra()[x].setHp_attuali(pannello.getGioco().getPlayer().getSquadra()[x].getHp());
					}
				}else
				{
					pannello.cambiaMostro(false);
				}
			}
		}else {
			pannello.getDescrizione().setText("L'attacco di " + nemico.getNome() + " fallisce!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		pannello.setMyTurn(true);
	}
	
	//ritorna true se l'attacco va a segno, false se non va a segno
	private boolean calcolaDanno(int danno, int precisone, Mostro attaccante, Mostro bersaglio) {
		if((int) (Math.random()*100) <= precisone) {
			double moltiplicatore = attaccante.getAttacco() - bersaglio.getDifesa();
			//se no il mostro recupera vita
			if(moltiplicatore < -100) {
				moltiplicatore = 0;
			}
			
			moltiplicatore = 1 + moltiplicatore/100;
			danno = (int) (danno * moltiplicatore);
			
			//applicazione del danno e relativa modifica dello stato del pmostro
			int hp = bersaglio.getHp_attuali() - danno;
			if(hp < 0) {
				hp = 0;
			}
			
			bersaglio.setHp_attuali(hp);
			return true;
		}else {
			return false;
		}
	}
	
	//controlla se sono rimasti pokemon con della vita
	private boolean controlloVitaMostri() {
		
		if(pannello.getGioco().getPlayer().getSquadra()[0].getHp_attuali() <= 0 && pannello.getGioco().getPlayer().getSquadra()[1].getHp_attuali() <= 0 && pannello.getGioco().getPlayer().getSquadra()[2].getHp_attuali() <= 0 && pannello.getGioco().getPlayer().getSquadra()[3].getHp_attuali() <= 0 ) {
			
			return true;
		}else {
			
			return false;
		}
	}
	
}

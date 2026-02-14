package control;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;

import model.Mossa;
import model.TurnoBattaglia;
import view.Finestra;
import view.PannelloBattaglie;
import view.PannelloGioco;

/**
 * 
 * Classe che ascolta i bottoni del pannelloBattaglie, implementa ActionListener e MouseListener in modo da rispondere
 * in maniera adeguata alle interazioni dell'utente con il pannello
 *
 */
public class GestioneBottoniBattaglie implements ActionListener, MouseListener{
	private PannelloBattaglie pannello;
	private Finestra finestra;
	private Thread t;
	
	
	/**Il costrutore della classe
	 * 
	 * @param pannello il pannello che contiene i componenti da ascoltare
	 * @param finestra la finestra che contiene il pannello
	 */
	public GestioneBottoniBattaglie(PannelloBattaglie pannello, Finestra finestra) {
		this.pannello = pannello;
		this.finestra = finestra;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(pannello.isMyTurn() == true) {
			if(e.getSource() == pannello.getLotta()) {
				
				pannello.getLotta().setForeground(Color.black);
				
				pannello.remove(pannello.getLotta());
				pannello.remove(pannello.getMostri());
				pannello.remove(pannello.getFuga());
				pannello.remove(pannello.getZaino());
				
				pannello.add(pannello.getMossa1());
				pannello.add(pannello.getMossa2());
				pannello.add(pannello.getMossa3());
				pannello.add(pannello.getMossa4());
				
				pannello.add(pannello.getIndietro());
				
				pannello.repaint();
				
			}else if(e.getSource() == pannello.getMostri()) {
				
					pannello.cambiaMostro(true);
					
			}else if(e.getSource() == pannello.getFuga()) {
				
				finestra.musica_battaglia.stop();
				finestra.musica_gioco.start();
				finestra.musica_gioco.loop(Clip.LOOP_CONTINUOUSLY);
				pannello.getGioco().setInBattaglia(false);
				Container padre = pannello.getParent();
				padre.remove(pannello);
				CardLayout layout = (CardLayout) padre.getLayout();
				layout.last(padre);
				PannelloGioco p = (PannelloGioco) padre.getComponent(padre.getComponentCount()-1);
				p.getTimer().start();
				
			}else if(e.getSource() == pannello.getZaino()) {
				//qualcosa
				pannello.getDescrizione().setText("Coming Soon!");
				
			}else if(e.getSource() == pannello.getMossa1() || e.getSource() == pannello.getMossa2() || e.getSource() == pannello.getMossa3() || e.getSource() == pannello.getMossa4()){
				int nMossa = 0;
				
				if(e.getSource() == pannello.getMossa1()) {
					nMossa = 0;
				}else if(e.getSource() == pannello.getMossa2()) {
					nMossa = 1;
				}else if(e.getSource() == pannello.getMossa3()) {
					nMossa = 2;
				}else if(e.getSource() == pannello.getMossa4()) {
					nMossa = 3;
				}
				
				Mossa mossa = pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[nMossa];
//				int danno = mossa.getBasePower();
//				int precisione = mossa.getPrecisione();
				
				t = new Thread(new TurnoBattaglia(pannello, mossa, finestra));
				t.start();
				
			}else if(e.getSource() == pannello.getIndietro()) {
				pannello.remove(pannello.getMossa1());
				pannello.remove(pannello.getMossa2());
				pannello.remove(pannello.getMossa3());
				pannello.remove(pannello.getMossa4());
				
				pannello.remove(pannello.getNome_mostro2());
				pannello.remove(pannello.getBarra_vita2());
				pannello.remove(pannello.getPunti_vita2());
				
				pannello.remove(pannello.getNome_mostro3());
				pannello.remove(pannello.getBarra_vita3());
				pannello.remove(pannello.getPunti_vita3());
				
				pannello.remove(pannello.getNome_mostro4());
				pannello.remove(pannello.getBarra_vita4());
				pannello.remove(pannello.getPunti_vita4());
				
				pannello.remove(pannello.getScelta1());
				pannello.remove(pannello.getScelta2());
				pannello.remove(pannello.getScelta3());
				
				pannello.remove(pannello.getIndietro());
				
				pannello.add(pannello.getLotta());
				pannello.add(pannello.getMostri());
				pannello.add(pannello.getFuga());
				pannello.add(pannello.getZaino());
				pannello.add(pannello.getDescrizione());
				
				pannello.repaint();
				
			}else if(e.getSource() == pannello.getScelta1() || e.getSource() == pannello.getScelta2() || e.getSource() == pannello.getScelta3()) {
				int nMostro = 1;
				
				if(e.getSource() == pannello.getScelta1()) {
					nMostro = 1;
				}else if(e.getSource() == pannello.getScelta2()) {
					nMostro = 2;
				}else if(e.getSource() == pannello.getScelta3()) {
					nMostro = 3;
				}
				
				//impedisco di mandare in campo un mostro con meno di 0 hp
				if(pannello.getGioco().getPlayer().getSquadra()[nMostro].getHp_attuali() > 0) {
					pannello.remove(pannello.getNome_mostro2());
					pannello.remove(pannello.getBarra_vita2());
					pannello.remove(pannello.getPunti_vita2());
					
					pannello.remove(pannello.getNome_mostro3());
					pannello.remove(pannello.getBarra_vita3());
					pannello.remove(pannello.getPunti_vita3());
					
					pannello.remove(pannello.getNome_mostro4());
					pannello.remove(pannello.getBarra_vita4());
					pannello.remove(pannello.getPunti_vita4());
					
					pannello.remove(pannello.getScelta1());
					pannello.remove(pannello.getScelta2());
					pannello.remove(pannello.getScelta3());
					
					pannello.remove(pannello.getIndietro());
					
					pannello.add(pannello.getLotta());
					pannello.add(pannello.getMostri());
					pannello.add(pannello.getFuga());
					pannello.add(pannello.getZaino());
					pannello.add(pannello.getDescrizione());
					
					pannello.cambiaMostro(nMostro);
				}
				
				pannello.repaint();
			}		
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
	
	//cambio la text area con la descrizione a seconda del testo dove è posizionato il cursore
	public void mouseEntered(MouseEvent e) {
		if(pannello.isMyTurn()) {
			if(e.getSource() == pannello.getIndietro()) {
				pannello.getIndietro().setForeground(Color.red);
				pannello.getDescrizione().setText("torna indietro");
				
			}else if(e.getSource() == pannello.getMossa1()) {
				pannello.getMossa1().setForeground(Color.red);
				pannello.getDescrizione().setText("BasePower: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[0].getBasePower() + "\nPrecisione: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[0].getPrecisione() + "\n" + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[0].getDescrizione());
				
			}else if(e.getSource() == pannello.getMossa2()) {
				pannello.getMossa2().setForeground(Color.red);
				pannello.getDescrizione().setText("BasePower: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[1].getBasePower() + "\nPrecisione: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[1].getPrecisione() + "\n" + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[1].getDescrizione());
				
			}else if(e.getSource() == pannello.getMossa3()) {
				pannello.getMossa3().setForeground(Color.red);
				pannello.getDescrizione().setText("BasePower: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[2].getBasePower() + "\nPrecisione: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[2].getPrecisione() + "\n" + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[2].getDescrizione());
				
			}else if(e.getSource() == pannello.getMossa4()) {
				pannello.getMossa4().setForeground(Color.red);
				pannello.getDescrizione().setText("BasePower: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[3].getBasePower() + "\nPrecisione: " + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[3].getPrecisione() + "\n" + pannello.getGioco().getPlayer().getSquadra()[0].getMosse()[3].getDescrizione());
				
			}else if(e.getSource() == pannello.getLotta()) {
				pannello.getLotta().setForeground(Color.red);
				pannello.getDescrizione().setText("Manda in campo un mostro per combattere");
				
			}else if(e.getSource() == pannello.getMostri()) {
				pannello.getMostri().setForeground(Color.red);
				pannello.getDescrizione().setText("Visualizza i mostri nella squadra");
				
			}else if(e.getSource() == pannello.getFuga()) {
				pannello.getFuga().setForeground(Color.red);
				pannello.getDescrizione().setText("scappa dalla lotta");
				
			}else if(e.getSource() == pannello.getZaino()) {
				pannello.getZaino().setForeground(Color.red);
				pannello.getDescrizione().setText("visualizza lo zaino");
				
			}else if(e.getSource() == pannello.getScelta1()) {
				pannello.getScelta1().setForeground(Color.red);
				
			}else if(e.getSource() == pannello.getScelta2()) {
				pannello.getScelta2().setForeground(Color.red);
				
			}
			else if(e.getSource() == pannello.getScelta3()) {
				pannello.getScelta3().setForeground(Color.red);
				
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(pannello.isMyTurn()) {
			if(e.getSource() == pannello.getIndietro()) {
				pannello.getIndietro().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getMossa1()) {
				pannello.getMossa1().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getMossa2()) {
				pannello.getMossa2().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getMossa3()) {
				pannello.getMossa3().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getMossa4()) {
				pannello.getMossa4().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getLotta()) {
				pannello.getLotta().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getMostri()) {
				pannello.getMostri().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getFuga()) {
				pannello.getFuga().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getZaino()) {
				pannello.getZaino().setForeground(Color.black);
				pannello.getDescrizione().setText(pannello.getDescrizioneBase());
				
			}else if(e.getSource() == pannello.getScelta1()) {
				pannello.getScelta1().setForeground(Color.black);
				
			}else if(e.getSource() == pannello.getScelta2()) {
				pannello.getScelta2().setForeground(Color.black);
				
			}
			else if(e.getSource() == pannello.getScelta3()) {
				pannello.getScelta3().setForeground(Color.black);
				
			}
		}
	}
	
	
}

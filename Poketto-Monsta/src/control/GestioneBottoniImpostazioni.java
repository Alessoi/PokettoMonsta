package control;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.Finestra;
import view.PannelloGioco;
import view.PannelloImpostazioni;
import view.PannelloStart;

/**
 * 
 * Classe che ascolta i bottoni del pannelloImpostazioni, implementa ActionListener e ChangeListener in modo da rispondere
 * in maniera adeguata alle interazioni dell'utente con il pannello
 *
 */
public class GestioneBottoniImpostazioni implements ActionListener, ChangeListener{
	PannelloImpostazioni pannello;
	Finestra finestra;
	
	/**Il costrutore della classe
	 * 
	 * @param pannello il pannello che contiene i componenti da ascoltare
	 * @param finestra la finestra che contiene il pannello
	 */
	public GestioneBottoniImpostazioni(PannelloImpostazioni pannello, Finestra finestra) {
		this.pannello = pannello;
		this.finestra = finestra;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == pannello.getFullScreenOn()) {
	        pannello.getFullScreenOn().setEnabled(false);
	        pannello.getFullScreenOff().setEnabled(true);
	        finestra.fullScreenOn();
	        pannello.setSize(Finestra.DIMENSIONI_FINESTRA);
	        pannello.pannelloSetBounds();
	    } else if(e.getSource() == pannello.getFullScreenOff()){
	    	pannello.getFullScreenOn().setEnabled(true);
	    	pannello.getFullScreenOff().setEnabled(false);
	    	finestra.fullScreenOff();
	    	pannello.setSize(Finestra.DIMENSIONI_FINESTRA);
	    	pannello.pannelloSetBounds();
	    }else if(e.getSource() == pannello.getEsci()) {
			Container padre = pannello.getParent();
			padre.remove(pannello);
			CardLayout layout = (CardLayout) padre.getLayout();
			PannelloStart p = (PannelloStart) padre.getComponent(padre.getComponentCount()-1);
			p.pannelloSetBounds();
			layout.last(padre);
	    }else if(e.getSource() == pannello.getAssistPulsantiOn()) {
	    	pannello.getAssistPulsantiOn().setEnabled(false);
	    	pannello.getAssistPulsantiOff().setEnabled(true);
	    	PannelloGioco.setAiutoPulsanti(true);
	    }else if(e.getSource() == pannello.getAssistPulsantiOff()) {
	    	pannello.getAssistPulsantiOn().setEnabled(true);
	    	pannello.getAssistPulsantiOff().setEnabled(false);
	    	PannelloGioco.setAiutoPulsanti(false);
	    }
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider src = (JSlider)e.getSource();
        if (src.getValue() % 5 !=0) return;
        float value = src.getValue() / 100.0f;
        try {
            pannello.getVolumeControl().setValue(value);
         } catch (Exception ex) {
            System.out.println(ex);
        }
	}
}

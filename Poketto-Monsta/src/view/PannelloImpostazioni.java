package view;

import java.awt.Color;
import java.awt.Graphics;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.swing.*;

import control.GestioneBottoniStart;
import control.GestioneBottoniImpostazioni;

/**
 * Classe che crea un pannello dove poter modificare delle cose appartenenti al gioco
 * 
 * @author Enrico Gatti
 *
 */
public class PannelloImpostazioni extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Finestra finestra;
	private JLabel volume, fullScreen, assistPulsanti; 
	private JSlider volumeValue;
	private JButton fullScreenOn, fullScreenOff;
	private JButton esci;
	private JButton assistPulsantiOn, assistPulsantiOff;
	private GestioneBottoniImpostazioni gestioneBottoni;
	
	/**
	 * Costruttore del pannelloImpostazioni
	 * @param finestra
	 */
	
	public PannelloImpostazioni(Finestra finestra) {
		this.setLayout(null);
		setName(Finestra.GIOCO);
		setSize(Finestra.DIMENSIONI_FINESTRA);
		this.setFinestra(finestra);
		
		fullScreen=new JLabel("FULL SCREEN");
		fullScreen.setForeground(Color.red);
		
		fullScreenOn=new JButton("ON");
		fullScreenOn.setFocusPainted(false);
		if(finestra.isFullScreen()) {
			fullScreenOn.setEnabled(false);
		}
		
		fullScreenOff=new JButton("OFF");
		fullScreenOff.setFocusPainted(false);
		if(!finestra.isFullScreen()) {
			fullScreenOff.setEnabled(false);
		}
		
		volume=new JLabel("VOLUME");
		volume.setForeground(Color.red);
		
		volumeValue = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volumeValue.setOpaque(false);
		try {
			volumeValue.setValue(15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		volumeValue.setMajorTickSpacing(5);
		volumeValue.setPaintTicks(false);
		volumeValue.setPaintLabels(true);
		
		assistPulsanti=new JLabel("PULSANTI AIUTO");
		assistPulsanti.setForeground(Color.red);
		assistPulsantiOn=new JButton("ON");
		assistPulsantiOn.setFocusPainted(false);
		assistPulsantiOn.setEnabled(false);
		assistPulsantiOff=new JButton("OFF");
		assistPulsantiOff.setFocusPainted(false);
		
		esci=new JButton("ESCI");
		esci.setFocusPainted(false);
		
		pannelloSetBounds();
		
		gestioneBottoni = new GestioneBottoniImpostazioni(this, finestra);
		controlloBottoni(gestioneBottoni);
		
		add(volumeValue);
		add(fullScreen);
		add(fullScreenOn);
		add(fullScreenOff);
		add(assistPulsanti);
		add(assistPulsantiOn);
		add(assistPulsantiOff);
		add(volume);
		add(esci);
	}
	
	/**
	 * Metodo che serve per disegnare lo sfondo
	 */
	protected void paintComponent(Graphics g) {
	    setOpaque(false);
	    g.drawImage(new ImageIcon("src/resources/sfondi/Sfondo.png").getImage(), 0, 0,this.getWidth(), this.getHeight(), null);
	    super.paintComponent(g);
	}
	
	/**
	 * 
	 * Metodo per attivare gli ascoltatori dei bottoni 
	 * 
	 * @param gs
	 * @see GestioneBottoniStart
	 */
	public void controlloBottoni(GestioneBottoniImpostazioni gs) {
		fullScreenOff.addActionListener(gs);
		fullScreenOn.addActionListener(gs);
		assistPulsantiOn.addActionListener(gs);
		assistPulsantiOff.addActionListener(gs);
		esci.addActionListener(gs);
		volumeValue.addChangeListener(gs);
	}
	
	/**
	 * Metodo che permette di impostare le posizioni di tutti i componenti del pannello
	 * 
	 */
	public void pannelloSetBounds() {
		fullScreen.setBounds(getWidth()/2-140, getHeight()/2-160, 100, 40);
    	fullScreenOn.setBounds(getWidth()/2-40, getHeight()/2-160, 60, 40);
    	fullScreenOff.setBounds(getWidth()/2+20, getHeight()/2-160, 60, 40);
    	volume.setBounds(getWidth()/2-240, getHeight()/2-220, 100, 40);
    	esci.setBounds(getWidth()-110, getHeight()-90, 80, 40);
    	volumeValue.setBounds(getWidth()/2-170, getHeight()/2-220, 400, 50);
    	assistPulsanti.setBounds(getWidth()/2-155, getHeight()/2-100, 140, 40);
    	assistPulsantiOn.setBounds(getWidth()/2-40, getHeight()/2-100, 60, 40);
    	assistPulsantiOff.setBounds(getWidth()/2+20, getHeight()/2-100, 60, 40);
	}
	
	/**
	 * Metodo che ritorna il bottone assistPulsantiOn
	 * @return assistPulsantiOn
	 */
	public JButton getAssistPulsantiOn() {
		return assistPulsantiOn;
	}
	
	/**
	 * Metodo che modifica il bottone assistPulsantiOn
	 * @param assistPulsantiOn
	 */
	public void setAssistPulsantiOn(JButton assistPulsantiOn) {
		this.assistPulsantiOn = assistPulsantiOn;
	}
	
	/**
	 * Metodo che ritorna il bottone assistPulsantiOff
	 * @return assistPulsantiOff
	 */
	public JButton getAssistPulsantiOff() {
		return assistPulsantiOff;
	}
	
	/**
	 * Metodo che modifica il bottone assistPulsantiOff
	 * @param assistPulsantiOff
	 */
	public void setAssistPulsantiOff(JButton assistPulsantiOff) {
		this.assistPulsantiOff = assistPulsantiOff;
	}
	
	/**
	 * metodo che ritorna il bottone fullScreenOn
	 * @return fullScreenOn
	 */
	public JButton getFullScreenOn() {
		return fullScreenOn;
	}
	
	/**
	 * Metodo che modifica il bottone fullScreenOn
	 * @param fullScreenOn
	 */
	public void setFullScreenOn(JButton fullScreenOn) {
		this.fullScreenOn = fullScreenOn;
	}
	
	/**
	 * Metodo che restituisce il bottone fullScreenOff
	 * @return
	 */
	public JButton getFullScreenOff() {
		return fullScreenOff;
	}
	
	/**
	 * Metodo che modifica il bottone fullScreenOff
	 * @param fullScreenOff
	 */
	public void setFullScreenOff(JButton fullScreenOff) {
		this.fullScreenOff = fullScreenOff;
	}
	
	/**
	 * Metodo che restituisce il bottone esci
	 * @return
	 */
	public JButton getEsci() {
		return esci;
	}
	
	/**
	 * Metodo che modifica il bottone esci
	 * @param esci
	 */
	public void setEsci(JButton esci) {
		this.esci = esci;
	} 
	
	/**
	 * Metodo che modifica grazie al valore nello JSlider il volume della musica di sottofondo da 0 a 100, in caso di errore nella creazione del oggetto crea una eccezione che segnala
	 * un problema con la sua creazione e lo stampa
	 * 
	 * @return
	 * @throws Exception
	 */
	public FloatControl getVolumeControl() throws Exception {
	    try {
	        Mixer.Info mixers[] = AudioSystem.getMixerInfo();
	        for (Mixer.Info mixerInfo : mixers) {
	            Mixer mixer = AudioSystem.getMixer(mixerInfo);
	            mixer.open();

	            for (Line.Info info : mixer.getTargetLineInfo()) {
	                if (info.toString().contains("SPEAKER")) {
	                    Line line = mixer.getLine(info);
	                    try {
	                        line.open();
	                    } catch (IllegalArgumentException iae) {}
	                    return (FloatControl) line.getControl(FloatControl.Type.VOLUME);
	                }
	            }
	        }
	    } catch (Exception ex) {
	        System.out.println("Problema:"+ex);
	        throw ex;
	    }
	    throw new Exception("Problema creazione oggetto");
	}

	public Finestra getFinestra() {
		return finestra;
	}

	public void setFinestra(Finestra finestra) {
		this.finestra = finestra;
	}
}

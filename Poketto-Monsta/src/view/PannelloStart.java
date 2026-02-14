package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.GestioneBottoniStart;

/**
 * Classe che fa apparire il pannello dove è presente il pulsante per iniziare la partita, uno per modificare delle impostazioni e uno per uscire 
 * dal gioco
 * 
 * @see Finestra
 * @author Enrico Gatti
 *
 */

public class PannelloStart extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel titoloScritta;
	private JButton start;
	private JButton esci;
	private JButton impostazioni;
	private GestioneBottoniStart gs;
	//
	/**
	 * 
	 * Costruttore del pannello	
	 * @param finestra
	 * 
	 */
	public PannelloStart(Finestra finestra) {
		setLayout(null);
		setName(Finestra.START);
		setSize(Finestra.DIMENSIONI_FINESTRA);
		
		titoloScritta = new JLabel("PokettoMonsta", SwingConstants.CENTER);
		titoloScritta.setForeground(Color.red);
		titoloScritta.setFont(new Font("Times New Roman", Font.PLAIN, 98));
		
		start = new JButton("START");
		start.setForeground(Color.darkGray);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.setFocusPainted(false);
		start.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		
		impostazioni = new JButton("IMPOSTAZIONI");
		impostazioni.setContentAreaFilled(false);
		impostazioni.setBorderPainted(false);
		impostazioni.setFocusPainted(false);
		impostazioni.setForeground(Color.darkGray);
		impostazioni.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		esci = new JButton("ESCI");
		esci.setForeground(Color.darkGray);
		esci.setContentAreaFilled(false);
		esci.setBorderPainted(false);
		esci.setFocusPainted(false);
		esci.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		
		pannelloSetBounds();
		
		gs = new GestioneBottoniStart(finestra, this);
		
		controlloBottoni(gs);
		
		this.add(titoloScritta);
		this.add(start);
		this.add(impostazioni);
		this.add(esci);
	}
	
	/**
	 * Metodo per disegnare l'immagine di sfondo e aggiornare la posizione dei componenti se si attiva il full Screen
	 */
	protected void paintComponent(Graphics g) {
	    setOpaque(false);
	    g.drawImage(new ImageIcon("src/resources/sfondi/Sfondo.png").getImage(), 0, 0,this.getWidth(), this.getHeight(), null);
	    super.paintComponent(g);
	 }
	
	/**
	 * Metodo per attivare gli ascoltatori dei bottoni 
	 * 
	 * @param gs
	 * @see GestioneBottoniStart
	 */
	private void controlloBottoni(GestioneBottoniStart gs) {
		start.addActionListener(gs);
		impostazioni.addActionListener(gs);
		esci.addActionListener(gs);
		
		start.addMouseListener(gs);
		impostazioni.addMouseListener(gs);
		esci.addMouseListener(gs);
	}
	
	/**
	 * Metodo che permette di impostare le posizioni di tutti i componenti del pannello
	 * 
	 */
	public void pannelloSetBounds() {
		titoloScritta.setBounds(0, getHeight()*10/100, getWidth(), 150);
		start.setBounds(getWidth()*50/100 - 100, getHeight()*35/100, 200, 100);
		impostazioni.setBounds(getWidth()*50/100 - 100, getHeight()*50/100, 200, 100);
		esci.setBounds(getWidth()*50/100 - 100, getHeight()*65/100, 200, 100);
	}
	
	/**
	 * Metodo che ritorna il bottone start
	 * @return il pulsante start
	 */
	public JButton getStart() {
		return start;
	}
	/**
	 * Metodo che ritorna il Bottone Esci
	 * @return il pulsante esci
	 */
	public JButton getEsci() {
		return esci;
	}
	/**
	 * Metodo che ritorna il bottone impostazioni
	 * @return il bottone impostazioni
	 */
	public JButton getImpostazioni() {
		return impostazioni;
	}
}
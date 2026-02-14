package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Classe che crea la finestra dove all'interno andremmo a piazzare i nostri pannelli, implementa la classe windowListener con cui si captano i cambiamenti che
 * avvengono alla finestra.
 * 
 * @author Enrico Gatti
 *
 */

public class Finestra extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	private Dimension posizione = getToolkit().getScreenSize();
	public static Dimension DIMENSIONI_FINESTRA = new Dimension(785, 600);
	public final static  String START = "Start";
	public final static String GIOCO = "Gioco";
	public final static String MENU_GIOCO = "Menu";
	public final static String MENU_BATTAGLIA = "Battaglia";
	public final static String MENU_IMPOSTAZIONI = "Impostazioni";
	private PannelloStart pannello;
	private CardLayout cardLayout;
	public static JPanel cards;
	private boolean fullScreen=false;
	public Clip musica;
	public Clip musica_battaglia;
	public Clip musica_gioco;
	
	/**
	 * Costruttore della finestra
	 * 
	 */
	
	public Finestra() {
		super("Poketto-Monsta");
		fullScreen=false;
		
		ImageIcon img=new ImageIcon("src/resources/sfondi/icona.png");
		
		this.setIconImage(img.getImage());
		
		
		try {
			musica_battaglia = AudioSystem.getClip();
			musica_battaglia.open(AudioSystem.getAudioInputStream(new File("src/resources/music/XenobladeOST.wav")));
			
			musica_gioco = AudioSystem.getClip();
			musica_gioco.open(AudioSystem.getAudioInputStream(new File("src/resources/music/musicaDiGioco.wav")));
			
			musica = AudioSystem.getClip();
			musica.open(AudioSystem.getAudioInputStream(new File("src/resources/music/Halo-2-Soundtrack-Halo-Theme.wav")));
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);
		cards.setPreferredSize(Finestra.DIMENSIONI_FINESTRA);
		this.setContentPane(cards);
		
		this.pannello=new PannelloStart(this);
		
		cards.add(pannello, START);

		this.setLocation((int) (posizione.width / 2 - DIMENSIONI_FINESTRA.getWidth() / 2), (int) (posizione.height / 2 - DIMENSIONI_FINESTRA.getHeight() / 2));
		this.setSize(DIMENSIONI_FINESTRA);
		
		this.getContentPane().setBackground(Color.black);
		cardLayout.show(cards, START);
		
		this.addWindowListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
//	
	
	/**
	 * Metodo per impostare la finestra che riempie tutto lo schermo
	 */
	
	public void fullScreenOn() {
		this.fullScreen=true;
		DIMENSIONI_FINESTRA = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.dispose();
		
		this.setUndecorated(true);
	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setVisible(true);
	}
	/**
	 * Metodo per disattivare la finestra che riempie tutto lo schermo
	 */
	public void fullScreenOff() {
		this.fullScreen=false;
		DIMENSIONI_FINESTRA = new Dimension(785, 600);
		
		this.dispose();
		
		this.setUndecorated(false);
	
		this.setExtendedState(NORMAL);
		
		this.setLocation((int) (posizione.width / 2 - DIMENSIONI_FINESTRA.getWidth() / 2), (int) (posizione.height / 2 - DIMENSIONI_FINESTRA.getHeight() / 2));
		this.setSize(DIMENSIONI_FINESTRA);
		cards.setPreferredSize(DIMENSIONI_FINESTRA);
		pack();

		this.setVisible(true);
	}
	/**
	 * Metodo che restituisce la variabile fullScreen per capire come l'utente vuole visualiuzzare la finestra
	 * @return fullScreen  per capire come l'utente vuole visualiuzzare la finestra
	 */
	public boolean isFullScreen() {
		return fullScreen;
	}
	/**
	 * Metodo che modificare la variabile fullScreen
	 * @param fullScreen
	 */
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}
	
	/**
	 * Metodo che restituisce la musica 
	 * @return musica
	 */
	public Clip getMusica() {
		return musica;
	}
	/**
	 * Metodo che modifica la musica 
	 * @param musica
	 */
	public void setMusica(Clip musica) {
		this.musica = musica;
	}
	
	/**
	 * Metodo implementato dal windowListener che permette di effettuare azioni quando la finestra si è aperta in questo caso avvia la musica
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		this.musica.start();
		this.musica.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/**
	 * Metodo implementato dal windowListener che permette di effetuare delle azioni quando si sta chiudendo la finestra in questo caso apre una piccola finestra di conferma 
	 * dove bisogna confermare di voler uscire dalla partita
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int i = JOptionPane.showConfirmDialog(this, "Vuoi abbandonare la partita??", "Conferma", JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION) {
			System.exit(0);
		}else {
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
	}
	/**
	 * Metodo implementato dal windowListener che permette di effettuare azioni quando la finestra è stata chiusa, in questo caso non l'abbiamo usato
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	/**
	 * Metodo implementato dal windowListener che permette di effettuare azioni quando la finestra è stata ridotta a icona, in questo caso stoppa la musica
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		this.musica.stop();
		this.musica_gioco.stop();
		this.musica_battaglia.stop();
	}
	/**
	 * Metodo implementato dal windowListener che permette di effettuare azioni quando la finestra è stata richiamata da icona dalla barra delle applicazioni, 
	 * in questo caso stoppa la musica
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		this.musica.start();
	}
	/**
	 * Metodo implementato dal windowListener che permette di effettuare azioni quando: la finestra è aperta per la prima volta, l'utente passa da una finestra a questa con ALT+TAB,
	 * l'utente fa clic sulla barra delle applicazioni della finestra. In questo caso non l'abbiamo utilizzato.
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Metodo implementato dal windowListener che permette di effettuare azioni quando:l' utente passa a un'altra finestra nell'applicazione corrente,
	 * l'utente passa alla finestra in un'altra applicazione usando ALT + TAB o gestione attività, l'utente fa clic sul pulsante della barra delle applicazioni
	 * per una finestra in un'altra applicazione. In questo caso non l'abbiamo utilizzato.
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

import control.GestioneTastieraGioco;
import control.SpriteSheetMappa;

import java.util.Map.Entry;
import java.util.Vector;

import model.Chunk;
import model.Giocatore;
import model.Gioco;
import model.IntPosizione;
import model.Npc;
import model.PuntiCardinali;

public class PannelloGioco extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public Finestra finestra;
	private JLabel aiutoPulsantiInventario, aiutoPulsantiMuoversi, aiutoPulsantiParlare;
	private JLabel imgPulsanteInventario, imgPulsanteMuoversi, imgPulsanteParlare; 
	private JTextArea textAreaLuogo, textAreaDialogo;
	private Timer timer = new Timer(50,this);
	private GestioneTastieraGioco gt;
	//
	private Gioco gioco;
	private PuntiCardinali direzione = null;
	private float scale = 1.5f;
	private int frames = 0;
	private long time = 0, time2 = System.currentTimeMillis(); 
	
	private boolean interagisci = false, siPuoMuovere = true;
	private static boolean aiutoPulsanti=true;
	
	
	public PannelloGioco(Gioco game,Finestra finestra) {
		this.setLayout(null);
		setFocusable(true);
		setName(Finestra.GIOCO);
		setSize(Finestra.DIMENSIONI_FINESTRA);
		
		scale = (int) (getWidth()/350);
		
		this.finestra=finestra;
		this.gioco=game;
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		textAreaLuogo = new JTextArea();
		textAreaLuogo.setEditable(false);
		textAreaLuogo.setBounds(20, 20, 110, 35);
		textAreaLuogo.setText("inizio");
		textAreaLuogo.setFont(new Font("Times New Roman", Font.PLAIN,20));
		textAreaLuogo.setVisible(true);
		textAreaLuogo.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		textAreaDialogo = new JTextArea();
		textAreaDialogo.setEditable(false);
		textAreaDialogo.setBounds(20, getHeight()*85/100, getWidth()-50, getHeight()*12/100);
		textAreaDialogo.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		textAreaDialogo.setVisible(false);
		textAreaDialogo.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		aiutoPulsantiInventario = new JLabel("INVENTARIO");
		aiutoPulsantiInventario.setBounds(this.getWidth()*6/100, (this.getHeight())*85/100, 100, 30);
		aiutoPulsantiInventario.setForeground(Color.black);
		
		aiutoPulsantiMuoversi = new JLabel("PER MUOVERSI");
		aiutoPulsantiMuoversi.setBounds(this.getWidth()*13/100, (this.getHeight())*94/100, 100, 30);
		aiutoPulsantiMuoversi.setForeground(Color.black);
		
		aiutoPulsantiParlare = new JLabel("PER PARLARE");
		aiutoPulsantiParlare.setBounds(this.getWidth()*10/100, (this.getHeight())*80/100, 100, 30);
		aiutoPulsantiParlare.setForeground(Color.black);
		
		imgPulsanteInventario = new JLabel(new ImageIcon(new ImageIcon("src/resources/pulsantiTastiera/Pulsante_E.png").getImage().getScaledInstance((this.getHeight())*5/100, (this.getHeight())*5/100, Image.SCALE_SMOOTH)));
		imgPulsanteInventario.setBounds(5, (this.getHeight())*85/100, (this.getHeight())*5/100, (this.getHeight())*5/100);
		
		imgPulsanteMuoversi = new JLabel(new ImageIcon(new ImageIcon("src/resources/pulsantiTastiera/Arrow_keys.png").getImage().getScaledInstance((this.getHeight())*13/100, (this.getHeight())*8/100, Image.SCALE_SMOOTH)));
		imgPulsanteMuoversi.setBounds(5, (this.getHeight())*89/100, (this.getHeight())*15/100, (this.getHeight())*10/100);
		
		imgPulsanteParlare = new JLabel(new ImageIcon(new ImageIcon("src/resources/pulsantiTastiera/Pulsante_Enter.png").getImage().getScaledInstance((this.getHeight())*10/100, (this.getHeight())*5/100, Image.SCALE_SMOOTH)));
		imgPulsanteParlare.setBounds(5, (this.getHeight())*80/100, (this.getHeight())*10/100, (this.getHeight())*5/100);
		
		if(!aiutoPulsanti) {
			aiutoPulsantiInventario.setVisible(false);
			aiutoPulsantiMuoversi.setVisible(false);
			aiutoPulsantiParlare.setVisible(false);
			imgPulsanteInventario.setVisible(false);
			imgPulsanteMuoversi.setVisible(false);
			imgPulsanteParlare.setVisible(false);
		}
		
		this.add(textAreaLuogo);
		this.add(textAreaDialogo);
		this.add(aiutoPulsantiInventario);
		this.add(aiutoPulsantiMuoversi);
		this.add(aiutoPulsantiParlare);
		this.add(imgPulsanteInventario);
		this.add(imgPulsanteParlare);
		this.add(imgPulsanteMuoversi);
		
		game.aggiorna(game.getPlayer().getPosX(), game.getPlayer().getPosY());
		
		gt = new GestioneTastieraGioco(this, finestra);		
		
		myKeabordEventManager();
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		setOpaque(false);

		Vector<Npc> personaggi = new Vector<Npc>();
		
	    Iterator<Entry<IntPosizione, Chunk>> it = gioco.getMappa().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<IntPosizione, Chunk> pair = it.next(); 
	        IntPosizione coord = pair.getKey();
	        Chunk chunk = gioco.getMappa().get(coord);
	        
	        int posX = gioco.getPlayer().getPosX() - coord.getX()*16;
			int posY = gioco.getPlayer().getPosY() - coord.getY()*16;
	        
			int drawX = (int) (getWidth()/2 - (posX*16 + gioco.getPlayer().getSpostamentoX())*scale);
			int drawY = (int) (getHeight()/2 - (posY*16 + gioco.getPlayer().getSpostamentoY())*scale);
			int dim = (int) (Chunk.DIM_MATRICE_CASELLE*SpriteSheetMappa.PIXEL_SIZE*scale);
			
			//disegna l'immagine sul pannello
			g.drawImage(chunk.getImage(), drawX, drawY, dim, dim, this);
			
			
			Vector<Npc> personaggiChunk = chunk.getPersonaggi();
			if(personaggiChunk != null) {
				personaggi.addAll(personaggiChunk);
			}
	    }
	   
	    personaggi.add(gioco.getPlayer());
	    
	    Collections.sort(personaggi);
		for(int i = 0; i < personaggi.size(); i++) {
			if(personaggi.get(i) instanceof Giocatore) {
				int drawX = (int)(getWidth()/2+1);
				int drawY = (int)(getHeight()/2-((12)*scale));
				
				g.drawImage(gioco.getPlayer().immagine(), drawX, drawY, (int) (16*scale), (int) (25*scale), this);
			}else {
				int drawX = (int)((getWidth()/2+1) - ((gioco.getPlayer().getPosX() - personaggi.get(i).getPosX())*16 + gioco.getPlayer().getSpostamentoX())*scale);
				int drawY = (int)(getHeight()/2-((12)*scale) - ((gioco.getPlayer().getPosY() - personaggi.get(i).getPosY())*16 + gioco.getPlayer().getSpostamentoY())*scale);
				
				g.drawImage(personaggi.get(i).immagine(), drawX, drawY, (int)(16*scale), (int)(25*scale), this);
			}
		}
	    
	    Toolkit.getDefaultToolkit().sync();
		super.paintComponent(g);
	}

	private void myKeabordEventManager(){
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(gt);
	}
	
	public void removeDispatcher() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(gt);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		
		frames++;
		if(System.currentTimeMillis() > time + 1000) {
			System.out.println("FPS:  " + frames);
			time = System.currentTimeMillis();
			frames = 0;
		}
	}
	
	public void update() {
		if(gioco.getPlayer().isFermo() && direzione != null && siPuoMuovere) {
			gioco.getPlayer().muovi(direzione, gioco);
		}
		
		int oldCX = gioco.getPlayer().getPosX()/16, oldCY = gioco.getPlayer().getPosY()/16;
		
		direzione = null;
		gioco.getPlayer().update(gioco);
		
		int newCX = gioco.getPlayer().getPosX()/16, newCY = gioco.getPlayer().getPosY()/16;
		if(oldCX != newCX || oldCY != newCY) {
			gioco.aggiorna(gioco.getPlayer().getPosX(), gioco.getPlayer().getPosY());
			
			if(textAreaLuogo.getText().compareTo(gioco.getChunk(newCX, newCY).getRegione()) != 0) {
				textAreaLuogo.setText(gioco.getChunk(newCX, newCY).getRegione());
				textAreaLuogo.setVisible(true);
				time2 = System.currentTimeMillis();
			}
		}
		
		//la textArea in alto a destra dopo 3 secondi sparisce
		if(textAreaLuogo.isVisible() && System.currentTimeMillis() > time2 + 3000) {
			textAreaLuogo.setVisible(false);
			time2 = System.currentTimeMillis();
		}
		
		if(textAreaDialogo.isVisible() && interagisci) {
			textAreaDialogo.setVisible(false);
			interagisci = false;
			siPuoMuovere = true;
		}else if(interagisci) {
			System.out.println("inizio");
			int dx = 0, dy = 0;
			PuntiCardinali dir = null;
			switch(gioco.getPlayer().getDirezione()) {
				case Nord:
					dir = PuntiCardinali.Sud;
					dy = -1;
					break;
				case Sud:
					dir = PuntiCardinali.Nord;
					dy = +1;
					break;
				case Est:
					dir = PuntiCardinali.Ovest;
					dx = 1;
					break;
				case Ovest:
					dir = PuntiCardinali.Est;
					dx = -1;
					break;
			}
			
			Npc personaggio = gioco.interagisci(gioco.getPlayer().getPosX()+dx,  gioco.getPlayer().getPosY()+dy, dir);
			if(personaggio != null) {
				textAreaDialogo.setText(personaggio.getFrase());
				textAreaDialogo.setVisible(true);
				siPuoMuovere = false;
			}
			
			interagisci = false;
		}
		
		if(gioco.isInBattaglia()) {
			finestra.musica_gioco.stop();
			finestra.musica_battaglia.loop(Clip.LOOP_CONTINUOUSLY);
			timer.stop();
			Container dad = getParent();
			dad.add(new PannelloBattaglie(gioco, finestra), Finestra.MENU_BATTAGLIA);
			CardLayout layout = (CardLayout) dad.getLayout();
			layout.show(dad, Finestra.MENU_BATTAGLIA);
		}
	}

	public static void setAiutoPulsanti(boolean aiutoPulsanti) {
		PannelloGioco.aiutoPulsanti = aiutoPulsanti;
	}
	
	public void setDirezione(PuntiCardinali direzione) {
		this.direzione = direzione;
	}
	
	public void setInteragisci(boolean interagisci) {
		this.interagisci = interagisci;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public Gioco getGioco() {
		return gioco;
	}
}
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import control.GestioneBottoniBattaglie;
import model.Gioco;
import model.ListaMostri;
import model.Mostro;

public class PannelloBattaglie extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton lotta, mostri, fuga, zaino; 				//tasti menu di battaglia
	private JButton mossa1, mossa2, mossa3, mossa4, indietro;	//tasi sottomenu di "lotta"
	private JButton scelta1, scelta2, scelta3;					//tsti dottomenu cambio mostro
	private JTextArea descrizione;								//descrizione delle opzioni dei tasti	
	private final String descrizione_base = "scegli un opzione";
	private Gioco gioco;
	private Finestra finestra;
	
	private JLabel nome_mostro;
	private JProgressBar barra_vita;
	private JLabel punti_vita;
	
	private JLabel nome_mostro2, nome_mostro3, nome_mostro4;
	private JProgressBar barra_vita2, barra_vita3, barra_vita4;
	private JLabel punti_vita2, punti_vita3, punti_vita4;
	
	private JLabel nome_mostro_avversario;
	private JProgressBar barra_vita_avversario;
	private JLabel punti_vita_avversario;
	private Image backSprite, frontSprite;
	
	private GestioneBottoniBattaglie gs;
	private boolean myTurn = true;
	
	public PannelloBattaglie(Gioco gioco, Finestra finestra) {
		setLayout(null);
		setName(Finestra.MENU_BATTAGLIA);
		setSize(Finestra.DIMENSIONI_FINESTRA);
		this.gioco = gioco;
		this.finestra=finestra;
		
		if(gioco.getPlayer().getSquadra()[0].getHp_attuali() <= 0) {
			for(int i = 1; i < 4; i++) {
				if(gioco.getPlayer().getSquadra()[i].getHp_attuali() > 0) {
					Mostro temp = gioco.getPlayer().getSquadra()[0];
					
					gioco.getPlayer().getSquadra()[0] = gioco.getPlayer().getSquadra()[i];
					gioco.getPlayer().getSquadra()[i] = temp;
				}
			}
		}
		
	//creazione area di testo per la descrizione delle azioni
		descrizione=new JTextArea();
		descrizione.setEditable(false);
		descrizione.setBorder(new EmptyBorder(5, 5, 5, 5));
		descrizione.setText(descrizione_base);
		descrizione.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
		descrizione.setForeground(Color.white);
		descrizione.setBackground(new Color(80, 95, 175 ,255));
		
		
	//creazione aree con le caratteristiche principali dei mostri durante la lotta
			
		//area relativa al mostro del giocatore
		nome_mostro = new JLabel(gioco.getPlayer().getSquadra()[0].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[0].getLevel());
		
		barra_vita = new JProgressBar(0, gioco.getPlayer().getSquadra()[0].getHp());
		barra_vita.setValue(gioco.getPlayer().getSquadra()[0].getHp_attuali());
		barra_vita.setForeground(Color.green);
		
		punti_vita = new JLabel(gioco.getPlayer().getSquadra()[0].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[0].getHp() + " hp");
		
		//area relativa alla squadra del giocatore, per lo scambio di mostri
		
			//secondo mostro
		nome_mostro2 = new JLabel(gioco.getPlayer().getSquadra()[1].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[1].getLevel());
		
		barra_vita2 = new JProgressBar(0, gioco.getPlayer().getSquadra()[1].getHp());
		barra_vita2.setValue(gioco.getPlayer().getSquadra()[1].getHp_attuali());
		barra_vita2.setForeground(Color.green);
		
		punti_vita2 = new JLabel(gioco.getPlayer().getSquadra()[1].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[1].getHp() + " hp" );
			
			//terzo mostro
		nome_mostro3 = new JLabel(gioco.getPlayer().getSquadra()[2].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[2].getLevel());
		
		barra_vita3= new JProgressBar(0, gioco.getPlayer().getSquadra()[2].getHp());
		barra_vita3.setValue(gioco.getPlayer().getSquadra()[2].getHp_attuali());
		barra_vita3.setForeground(Color.green);
		
		punti_vita3 = new JLabel(gioco.getPlayer().getSquadra()[2].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[2].getHp() + " hp");
		
			//quarto mostro
		nome_mostro4 = new JLabel(gioco.getPlayer().getSquadra()[3].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[3].getLevel());
		
		barra_vita4 = new JProgressBar(0, gioco.getPlayer().getSquadra()[3].getHp());
		barra_vita4.setValue(gioco.getPlayer().getSquadra()[3].getHp_attuali());
		barra_vita4.setForeground(Color.green);
		
		punti_vita4 = new JLabel(gioco.getPlayer().getSquadra()[3].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[3].getHp() + " hp");
		
		
		//area relativa al mostro avversario
		nome_mostro_avversario = new JLabel(gioco.getMostro_randomico().getNome() + "   Lv. " + gioco.getMostro_randomico().getLevel());
		
		barra_vita_avversario = new JProgressBar(0, gioco.getMostro_randomico().getHp());
		barra_vita_avversario.setValue(gioco.getMostro_randomico().getHp());
		barra_vita_avversario.setForeground(Color.green);
		
		punti_vita_avversario = new JLabel(gioco.getMostro_randomico().getHp_attuali() + "/" + gioco.getMostro_randomico().getHp() + " hp");
		
	//creazione e posizionamento dei tasti relativia al menu di battaglia
		lotta = new JButton("Lotta");
		lotta.setBackground(new Color(255, 190, 122, 255));
		lotta.setBorderPainted(false);
		lotta.setFocusPainted(false);
		
		mostri = new JButton("Mostri");
		mostri.setBackground(new Color(255, 190, 122, 255));
		mostri.setBorderPainted(false);
		mostri.setFocusPainted(false);
		
		fuga = new JButton("Fuga");
		fuga.setBackground(new Color(255, 190, 122, 255));
		fuga.setBorderPainted(false);
		fuga.setFocusPainted(false);
		
		zaino = new JButton("Zaino");
		zaino.setBackground(new Color(255, 190, 122, 255));
		zaino.setBorderPainted(false);
		zaino.setFocusPainted(false);
		
	//creazione e posizionamento dei tasti relativia al sottomenu di "lotta"
		mossa1 = new JButton(gioco.getPlayer().getSquadra()[0].getMosse()[0].getNome_mossa());
		mossa1.setBackground(new Color(255, 190, 122, 255));
		mossa1.setBorderPainted(false);
		mossa1.setFocusPainted(false);
		
		mossa2 = new JButton(gioco.getPlayer().getSquadra()[0].getMosse()[1].getNome_mossa());
		mossa2.setBackground(new Color(255, 190, 122, 255));
		mossa2.setBorderPainted(false);
		mossa2.setFocusPainted(false);
		
		mossa3 = new JButton(gioco.getPlayer().getSquadra()[0].getMosse()[2].getNome_mossa());
		mossa3.setBackground(new Color(255, 190, 122, 255));
		mossa3.setBorderPainted(false);
		mossa3.setFocusPainted(false);
		
		mossa4 = new JButton(gioco.getPlayer().getSquadra()[0].getMosse()[3].getNome_mossa());
		mossa4.setBackground(new Color(255, 190, 122, 255));
		mossa4.setBorderPainted(false);
		mossa4.setFocusPainted(false);
		
		indietro = new JButton("back");
		indietro.setBackground(new Color(255, 190, 122, 255));
		indietro.setBorderPainted(false);
		indietro.setFocusPainted(false);	
		
	//creazione e posizionamento dei tasti relativia al sottomenu di "scambio dei mostri"
		scelta1 = new JButton("1  " + gioco.getPlayer().getSquadra()[1].getNome());
		scelta1.setBackground(new Color(255, 190, 122, 255));
		scelta1.setBorderPainted(false);
		scelta1.setFocusPainted(false);
		
		
		scelta2 = new JButton("2  " + gioco.getPlayer().getSquadra()[2].getNome());
		scelta2.setBackground(new Color(255, 190, 122, 255));
		scelta2.setBorderPainted(false);
		scelta2.setFocusPainted(false);
		
		
		scelta3 = new JButton("3  " + gioco.getPlayer().getSquadra()[3].getNome());
		scelta3.setBackground(new Color(255, 190, 122, 255));
		scelta3.setBorderPainted(false);
		scelta3.setFocusPainted(false);
		
		gs = new GestioneBottoniBattaglie(this, finestra);
		controlloBottoni(gs);
		
		pannellosetBounds();
		
		add(descrizione);
		add(nome_mostro);
		add(barra_vita);
		add(punti_vita);
		add(nome_mostro_avversario);
		add(barra_vita_avversario);
		add(punti_vita_avversario);
		add(lotta);
		add(mostri);
		add(fuga);
		add(zaino);
	}
	
	public void controlloBottoni(GestioneBottoniBattaglie gs) {
		lotta.addActionListener(gs);
		mostri.addActionListener(gs);
		fuga.addActionListener(gs);
		zaino.addActionListener(gs);
		
		mossa1.addActionListener(gs);
		mossa2.addActionListener(gs);
		mossa3.addActionListener(gs);
		mossa4.addActionListener(gs);
		indietro.addActionListener(gs);	
		
		scelta1.addActionListener(gs);
		scelta2.addActionListener(gs);
		scelta3.addActionListener(gs);
		
		lotta.addMouseListener(gs);
		mostri.addMouseListener(gs);
		fuga.addMouseListener(gs);
		zaino.addMouseListener(gs);
		
		indietro.addMouseListener(gs);
		mossa1.addMouseListener(gs);
		mossa2.addMouseListener(gs);
		mossa3.addMouseListener(gs);
		mossa4.addMouseListener(gs);
		
		scelta1.addMouseListener(gs);
		scelta2.addMouseListener(gs);
		scelta3.addMouseListener(gs);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    setOpaque(false);
//	    System.out.println(getWidth() + " - " + getHeight());
	    //w
//	    System.out.println("carica immagine");
	    g.drawImage(new ImageIcon("src/resources/sfondi/sfondo_lotta.png").getImage(), 0, 0, getWidth(), getHeight(), null);
//	    System.out.println("finiro immagine");
	    
	    backSprite = ListaMostri.getImmagini(gioco.getPlayer().getSquadra()[0].getNumero())[1];
	    frontSprite = ListaMostri.getImmagini(gioco.getMostro_randomico().getNumero())[0];
    	
    	g.drawImage(backSprite, this.getWidth()*15/100, this.getHeight()*49/100+2, this.getHeight()*36/100-3, this.getHeight()*36/100-3, this);
    	g.drawImage(frontSprite, this.getWidth()*68/100, this.getHeight()*23/100+2, this.getHeight()*36/100-3, this.getHeight()*36/100-3, this);
	    
//	    g.setColor(new Color(236, 231, 93 ,255));
//	    g.fillRoundRect(getWidth()*52/100, getHeight()*68/100, getWidth()*32/100, getHeight()*10/100, 25, 25);
//	    g.fillRoundRect(getWidth()*15/100, getHeight()*24/100, getWidth()*32/100, getHeight()*10/100, 25, 25);
//	    
	    super.paintComponent(g);
	}
	
	public void pannellosetBounds() {
		int i=(this.getWidth()*45)/100;
    	int y=(int) ((this.getHeight()*17.5)/100);
    	
    	descrizione.setBounds(15, this.getHeight()+3-y,this.getWidth()-30-i , y-10);
    	lotta.setBounds(this.getWidth()-i, this.getHeight()-y, i/2, y/2);
    	mostri.setBounds(this.getWidth()-i/2, this.getHeight()-y, i/2, y/2);
    	fuga.setBounds(this.getWidth()-i, this.getHeight()-y/2, i/2, y/2);
    	zaino.setBounds(this.getWidth()-i/2, this.getHeight()-y/2, i/2, y/2);
    	
    	mossa1.setBounds(this.getWidth()-i, this.getHeight()-y, i/3, y/2);
    	mossa2.setBounds(this.mossa1.getX()+this.mossa1.getWidth(), this.getHeight()-y, i/3, y/2);
    	mossa3.setBounds(this.getWidth()-i, this.getHeight()-y/2, i/3, y/2);
    	mossa4.setBounds(this.mossa3.getX()+this.mossa3.getWidth(), this.getHeight()-y/2, i/3, y/2);
    	indietro.setBounds(this.getWidth()-i/3, this.getHeight()-y/2-50, i/3, y/2);
    	
    	nome_mostro2.setBounds(5, getHeight()*83/100, 100, 40);	    	
    	barra_vita2.setBounds(5, getHeight()*89/100, 100, 10);
    	punti_vita2.setBounds(5, getHeight()*88/100, 150, 60);
    	
    	nome_mostro3.setBounds(getWidth()*19/100, getHeight()*83/100, 100, 40);	    	
    	barra_vita3.setBounds(getWidth()*19/100, getHeight()*89/100, 100, 10);
    	punti_vita3.setBounds(getWidth()*19/100, getHeight()*88/100, 150, 60);
    	
    	nome_mostro4.setBounds(getWidth()*37/100, getHeight()*83/100, 100, 40);	    	
    	barra_vita4.setBounds(getWidth()*37/100, getHeight()*89/100, 100, 10);
    	punti_vita4.setBounds(getWidth()*37/100, getHeight()*88/100, 150, 60);
    	
		scelta1.setBounds(getWidth()-i, getHeight()-y, i/2, y/2);
		scelta2.setBounds(getWidth()-i/2, getHeight()-y, i/2, y/2);
		scelta3.setBounds(getWidth()-i, getHeight()-y/2, i/2, y/2);
		indietro.setBounds(getWidth()*90/100, getHeight()*92/100, 100, 30);
    	
    	if(!finestra.isFullScreen()) {
	    	nome_mostro.setBounds(getWidth()*53/100, getHeight()*65/100, 150, 60);
	    	nome_mostro_avversario.setBounds(getWidth()*16/100, getHeight()*21/100, 150, 60);
	    	barra_vita.setBounds(getWidth()*53/100, getHeight()*72/100, getWidth()*30/100, 10);
	    	barra_vita_avversario.setBounds(getWidth()*16/100, getHeight()*28/100 , getWidth()*30/100, 10);
	    	punti_vita.setBounds(getWidth()*53/100, getHeight()*71/100, 150, 60);
	    	punti_vita_avversario.setBounds(getWidth()*16/100, getHeight()*27/100, 150, 60);
	    	
	    	
    	}else {
	    	nome_mostro.setBounds(getWidth()*53/100, getHeight()*67/100, 150, 60);
	    	nome_mostro_avversario.setBounds(getWidth()*16/100, getHeight()*23/100, 150, 60);
	    	barra_vita.setBounds(getWidth()*53/100, getHeight()*73/100, getWidth()*30/100, 10);
	    	barra_vita_avversario.setBounds(getWidth()*16/100, getHeight()*29/100 , getWidth()*30/100, 10);
	    	punti_vita.setBounds(getWidth()*53/100, getHeight()*73/100, 150, 60);
	    	punti_vita_avversario.setBounds(getWidth()*16/100, getHeight()*29/100, 150, 60);
    	}
	}

	
	public void cambiaMostro(int x) {
		
		Mostro m = gioco.getPlayer().getSquadra()[0];
		gioco.getPlayer().getSquadra()[0] = gioco.getPlayer().getSquadra()[x];
		gioco.getPlayer().getSquadra()[x] = m;
		
		
		punti_vita.setText(gioco.getPlayer().getSquadra()[0].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[0].getHp() + " hp");
		nome_mostro.setText(gioco.getPlayer().getSquadra()[0].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[0].getLevel());

		barra_vita.setMaximum(gioco.getPlayer().getSquadra()[0].getHp());
		barra_vita.setValue(gioco.getPlayer().getSquadra()[0].getHp_attuali());
		
		//devo aggiornare i componenti grafici
		aggiornaMenuCambioSquadra();
		aggiornaMosse();
	}
	
	public void aggiornaMenuCambioSquadra() {
		//secondo mostro
		scelta1.setText("1  " + gioco.getPlayer().getSquadra()[1].getNome());
		nome_mostro2.setText(gioco.getPlayer().getSquadra()[1].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[1].getLevel());
		barra_vita2.setMaximum(gioco.getPlayer().getSquadra()[1].getHp());
		barra_vita2.setValue(gioco.getPlayer().getSquadra()[1].getHp_attuali());
		punti_vita2.setText(gioco.getPlayer().getSquadra()[1].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[1].getHp() + " hp" );
					
			//terzo mostro
		scelta2.setText("2  " + gioco.getPlayer().getSquadra()[2].getNome());
		nome_mostro3.setText(gioco.getPlayer().getSquadra()[2].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[2].getLevel());
		barra_vita3.setMaximum(gioco.getPlayer().getSquadra()[2].getHp());
		barra_vita3.setValue(gioco.getPlayer().getSquadra()[2].getHp_attuali());
		punti_vita3.setText(gioco.getPlayer().getSquadra()[2].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[2].getHp() + " hp"); 
		
			//quarto mostro
		scelta3.setText("3  " + gioco.getPlayer().getSquadra()[3].getNome());
		nome_mostro4.setText(gioco.getPlayer().getSquadra()[3].getNome() + "   Lv. " + gioco.getPlayer().getSquadra()[3].getLevel());
		barra_vita4.setMaximum(gioco.getPlayer().getSquadra()[3].getHp());
		barra_vita4.setValue(gioco.getPlayer().getSquadra()[3].getHp_attuali());
		punti_vita4.setText(gioco.getPlayer().getSquadra()[3].getHp_attuali() + "/" + gioco.getPlayer().getSquadra()[3].getHp() + " hp"); 
	}
	
	public void aggiornaMosse() {
		mossa1.setText(gioco.getPlayer().getSquadra()[0].getMosse()[0].getNome_mossa());
		mossa2.setText(gioco.getPlayer().getSquadra()[0].getMosse()[1].getNome_mossa());
		mossa3.setText(gioco.getPlayer().getSquadra()[0].getMosse()[2].getNome_mossa());
		mossa4.setText(gioco.getPlayer().getSquadra()[0].getMosse()[3].getNome_mossa());
	}
	
public void cambiaMostro(boolean x) {
		
		remove(lotta);
		remove(mostri);
		remove(fuga);
		remove(zaino);
		
		remove(mossa1);
		remove(mossa2);
		remove(mossa3);
		remove(mossa4);
		remove(indietro);
		
		add(scelta1);
		add(scelta2);
		add(scelta3);
		
		remove(descrizione);
		
		add(nome_mostro2);
		add(barra_vita2);
		add(punti_vita2);
		
		add(nome_mostro3);
		add(barra_vita3);
		add(punti_vita3);
		
		add(nome_mostro4);
		add(barra_vita4);
		add(punti_vita4);
		
		
		
		
		if(x == true) {
			add(indietro);
		}
		
		
		repaint();
	}
	
	
	//getters and setters
	public JButton getLotta() {
		return lotta;
	}
	
	public void setLotta(JButton lotta) {
		this.lotta = lotta;
	}
	
	public JButton getMostri() {
		return mostri;
	}
	
	public void setMostri(JButton mostro) {
		this.mostri = mostro;
	}
	
	public JButton getFuga() {
		return fuga;
	}
	
	public void setFuga(JButton fuga) {
		this.fuga = fuga;
	}
	
	public JButton getZaino() {
		return zaino;
	}
	
	public void setZaino(JButton zaino) {
		this.zaino = zaino;
	}
	
	public JTextArea getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(JTextArea descrizione) {
		this.descrizione = descrizione;
	}
	
	public boolean isMyTurn() {
		return myTurn;
	}
	
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
	
	public Gioco getGioco() {
		return gioco;
	}

	public JButton getMossa1() {
		return mossa1;
	}

	public JButton getMossa2() {
		return mossa2;
	}

	public JButton getMossa3() {
		return mossa3;
	}

	public JButton getMossa4() {
		return mossa4;
	}

	public JButton getIndietro() {
		return indietro;
	}

	public JButton getScelta1() {
		return scelta1;
	}

	public JButton getScelta2() {
		return scelta2;
	}

	public JButton getScelta3() {
		return scelta3;
	}

	public Finestra getFinestra() {
		return finestra;
	}

	public JLabel getNome_mostro() {
		return nome_mostro;
	}

	public JProgressBar getBarra_vita() {
		return barra_vita;
	}

	public JLabel getPunti_vita() {
		return punti_vita;
	}

	public JLabel getNome_mostro2() {
		return nome_mostro2;
	}

	public JProgressBar getBarra_vita2() {
		return barra_vita2;
	}

	public JLabel getPunti_vita2() {
		return punti_vita2;
	}

	public JLabel getNome_mostro3() {
		return nome_mostro3;
	}

	public JProgressBar getBarra_vita3() {
		return barra_vita3;
	}

	public JLabel getPunti_vita3() {
		return punti_vita3;
	}

	public JLabel getNome_mostro4() {
		return nome_mostro4;
	}

	public JProgressBar getBarra_vita4() {
		return barra_vita4;
	}

	public JLabel getPunti_vita4() {
		return punti_vita4;
	}

	public JLabel getNome_mostro_avversario() {
		return nome_mostro_avversario;
	}

	public JProgressBar getBarra_vita_avversario() {
		return barra_vita_avversario;
	}

	public JLabel getPunti_vita_avversario() {
		return punti_vita_avversario;
	}

	public Image getBackSprite() {
		return backSprite;
	}

	public Image getFrontSprite() {
		return frontSprite;
	}
	
	public String getDescrizioneBase() {
		return descrizione_base;
	}
}

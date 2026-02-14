package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import control.GestioneBottoniMenu;
import model.Gioco;
import model.ListaMostri;

public class PannelloMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton salva, squadra, esci, menuStart;
	private JTextArea mostro1, mostro2, mostro3, mostro4;
	private JProgressBar vitaMostro1, vitaMostro2, vitaMostro3, vitaMostro4;
	private JLabel nomeMostro1,nomeMostro2,nomeMostro3,nomeMostro4,vita1,vita2,vita3,vita4,livelloMostro1,livelloMostro2,livelloMostro3,livelloMostro4,attaccoMostro1, attaccoMostro2, attaccoMostro3,attaccoMostro4, difesaMostro1, difesaMostro2, difesaMostro3, difesaMostro4, mosseMostro1, mosseMostro2, mosseMostro3,mosseMostro4,imgMostro1,imgMostro2,imgMostro3,imgMostro4;
	private Gioco gioco;
	private boolean squadraVisibile = false;
	private GestioneBottoniMenu gb;
	
	public PannelloMenu(Gioco gioco, Finestra finestra) {
		this.gioco=gioco;
		setLayout(null);
		setName(Finestra.MENU_GIOCO);
		setSize(Finestra.DIMENSIONI_FINESTRA);
		
		squadra = new JButton("Squadra");
		squadra.setBounds((getWidth())*75/100, (getHeight()/2)*60/100 , this.getWidth()-this.getWidth()*85/100, this.getHeight()-this.getHeight()*95/100);
		squadra.setBackground(Color.orange);
		squadra.setFocusPainted(false);
		add(squadra);
		
		salva = new JButton("Salva");
		salva.setBounds((getWidth())*75/100, (getHeight()/2)*80/100, this.getWidth()-this.getWidth()*85/100, this.getHeight()-this.getHeight()*95/100);
		salva.setBackground(Color.orange);
		salva.setFocusPainted(false);
		add(salva);
		
		esci = new JButton("Esci");
		esci.setBounds((getWidth())*75/100, (getHeight())*50/100, this.getWidth()-this.getWidth()*85/100, this.getHeight()-this.getHeight()*95/100);
		esci.setBackground(Color.orange);
		esci.setFocusPainted(false);
		add(esci);
		
		menuStart = new JButton("menuStart");
		menuStart.setBounds((getWidth())*75/100, (getHeight())*60/100, this.getWidth()-this.getWidth()*85/100, this.getHeight()-this.getHeight()*95/100);
		menuStart.setBackground(Color.orange);
		menuStart.setFocusPainted(false);
		add(menuStart);
		
		nomeMostro1= new JLabel(gioco.getPlayer().getSquadra()[0].getNome());
		nomeMostro1.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		nomeMostro1.setBounds(this.getWidth()*26/100,this.getHeight()*19/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		nomeMostro1.setVisible(false);		
		add(nomeMostro1);
		
		nomeMostro2= new JLabel(gioco.getPlayer().getSquadra()[1].getNome());
		nomeMostro2.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		nomeMostro2.setBounds(this.getWidth()*26/100,this.getHeight()*39/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		nomeMostro2.setVisible(false);
		add(nomeMostro2);
		
		nomeMostro3= new JLabel(gioco.getPlayer().getSquadra()[2].getNome());
		nomeMostro3.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		nomeMostro3.setBounds(this.getWidth()*26/100,this.getHeight()*59/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		nomeMostro3.setVisible(false);
		add(nomeMostro3);
		
		nomeMostro4= new JLabel(gioco.getPlayer().getSquadra()[3].getNome());
		nomeMostro4.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		nomeMostro4.setBounds(this.getWidth()*26/100,this.getHeight()*79/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		nomeMostro4.setVisible(false);
		add(nomeMostro4);
		
		vita1= new JLabel(gioco.getPlayer().getSquadra()[0].getHp_attuali()+"/"+gioco.getPlayer().getSquadra()[0].getHp());
		vita1.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		vita1.setBounds(this.getWidth()*50/100,this.getHeight()*19/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		vita1.setVisible(false);
		add(vita1);
		
		vita2= new JLabel(gioco.getPlayer().getSquadra()[1].getHp_attuali()+"/"+gioco.getPlayer().getSquadra()[1].getHp());
		vita2.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		vita2.setBounds(this.getWidth()*50/100,this.getHeight()*39/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		vita2.setVisible(false);
		add(vita2);
		
		vita3= new JLabel(gioco.getPlayer().getSquadra()[2].getHp_attuali()+"/"+gioco.getPlayer().getSquadra()[2].getHp());
		vita3.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		vita3.setBounds(this.getWidth()*50/100,this.getHeight()*59/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		vita3.setVisible(false);		
		add(vita3);
		
		vita4= new JLabel(gioco.getPlayer().getSquadra()[3].getHp_attuali()+"/"+gioco.getPlayer().getSquadra()[3].getHp());
		vita4.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		vita4.setBounds(this.getWidth()*50/100,this.getHeight()*79/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		vita4.setVisible(false);
		add(vita4);
		
		vitaMostro1= new JProgressBar(0, gioco.getPlayer().getSquadra()[0].getHp());
		vitaMostro1.setBounds(this.getWidth()*34/100,this.getHeight()*20/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*99/100);
		vitaMostro1.setForeground(Color.green);
		vitaMostro1.setValue(gioco.getPlayer().getSquadra()[0].getHp_attuali());
		vitaMostro1.setVisible(false);
		add(vitaMostro1);
		
		vitaMostro2= new JProgressBar(0, gioco.getPlayer().getSquadra()[1].getHp());
		vitaMostro2.setBounds(this.getWidth()*34/100,this.getHeight()*40/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*99/100);
		vitaMostro2.setForeground(Color.green);
		vitaMostro2.setVisible(false);
		vitaMostro2.setValue(gioco.getPlayer().getSquadra()[1].getHp_attuali());
		add(vitaMostro2);
		
		vitaMostro3= new JProgressBar(0, gioco.getPlayer().getSquadra()[2].getHp());
		vitaMostro3.setBounds(this.getWidth()*34/100,this.getHeight()*60/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*99/100);
		vitaMostro3.setForeground(Color.green);
		vitaMostro3.setValue(gioco.getPlayer().getSquadra()[2].getHp_attuali());
		vitaMostro3.setVisible(false);
		add(vitaMostro3);
		
		vitaMostro4= new JProgressBar(0, gioco.getPlayer().getSquadra()[3].getHp());
		vitaMostro4.setBounds(this.getWidth()*34/100,this.getHeight()*80/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*99/100);
		vitaMostro4.setForeground(Color.green);
		vitaMostro4.setValue(gioco.getPlayer().getSquadra()[3].getHp_attuali());
		vitaMostro4.setVisible(false);
		add(vitaMostro4);
		
		livelloMostro1= new JLabel("Liv: "+gioco.getPlayer().getSquadra()[0].getLevel());
		livelloMostro1.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		livelloMostro1.setBounds(this.getWidth()*26/100,this.getHeight()*22/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		livelloMostro1.setVisible(false);
		add(livelloMostro1);
		
		livelloMostro2= new JLabel("Liv: "+gioco.getPlayer().getSquadra()[1].getLevel());
		livelloMostro2.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		livelloMostro2.setBounds(this.getWidth()*26/100,this.getHeight()*42/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		livelloMostro2.setVisible(false);
		add(livelloMostro2);
		
		livelloMostro3= new JLabel("Liv: "+gioco.getPlayer().getSquadra()[2].getLevel());
		livelloMostro3.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		livelloMostro3.setBounds(this.getWidth()*26/100,this.getHeight()*62/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		livelloMostro3.setVisible(false);
		add(livelloMostro3);
		
		livelloMostro4= new JLabel("Liv: "+gioco.getPlayer().getSquadra()[3].getLevel());
		livelloMostro4.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		livelloMostro4.setBounds(this.getWidth()*26/100,this.getHeight()*82/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		livelloMostro4.setVisible(false);
		add(livelloMostro4);
		
		attaccoMostro1= new JLabel("Attacco: "+gioco.getPlayer().getSquadra()[0].getAttacco());
		attaccoMostro1.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		attaccoMostro1.setBounds(this.getWidth()*32/100,this.getHeight()*22/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		attaccoMostro1.setVisible(false);
		add(attaccoMostro1);
		
		difesaMostro1= new JLabel("Difesa: "+gioco.getPlayer().getSquadra()[0].getDifesa());
		difesaMostro1.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		difesaMostro1.setBounds(this.getWidth()*45/100,this.getHeight()*22/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		difesaMostro1.setVisible(false);
		add(difesaMostro1);
		
		attaccoMostro2= new JLabel("Attacco: "+gioco.getPlayer().getSquadra()[1].getAttacco());
		attaccoMostro2.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		attaccoMostro2.setBounds(this.getWidth()*32/100,this.getHeight()*42/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		attaccoMostro2.setVisible(false);
		add(attaccoMostro2);
		
		difesaMostro2= new JLabel("Difesa: "+gioco.getPlayer().getSquadra()[1].getDifesa());
		difesaMostro2.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		difesaMostro2.setBounds(this.getWidth()*45/100,this.getHeight()*42/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		difesaMostro2.setVisible(false);
		add(difesaMostro2);
		
		attaccoMostro3= new JLabel("Attacco: "+gioco.getPlayer().getSquadra()[2].getAttacco());
		attaccoMostro3.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		attaccoMostro3.setBounds(this.getWidth()*32/100,this.getHeight()*62/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		attaccoMostro3.setVisible(false);
		add(attaccoMostro3);
		
		difesaMostro3= new JLabel("Difesa: "+gioco.getPlayer().getSquadra()[2].getDifesa());
		difesaMostro3.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		difesaMostro3.setBounds(this.getWidth()*45/100,this.getHeight()*62/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		difesaMostro3.setVisible(false);
		add(difesaMostro3);
		
		attaccoMostro4= new JLabel("Attacco: "+gioco.getPlayer().getSquadra()[3].getAttacco());
		attaccoMostro4.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		attaccoMostro4.setBounds(this.getWidth()*32/100,this.getHeight()*82/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		attaccoMostro4.setVisible(false);
		add(attaccoMostro4);
		
		difesaMostro4= new JLabel("Difesa: "+gioco.getPlayer().getSquadra()[3].getDifesa());
		difesaMostro4.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		difesaMostro4.setBounds(this.getWidth()*45/100,this.getHeight()*82/100 , this.getWidth()-this.getWidth()*85/100,  this.getHeight()-this.getHeight()*97/100);
		difesaMostro4.setVisible(false);
		add(difesaMostro4);
		
		mosseMostro1= new JLabel("Mosse: \n"+gioco.getPlayer().getSquadra()[0].getMosse()[0].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[0].getMosse()[1].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[0].getMosse()[2].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[0].getMosse()[3].getNome_mossa());
		mosseMostro1.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		mosseMostro1.setBounds(this.getWidth()*26/100,this.getHeight()*25/100 , this.getWidth()-this.getWidth()*65/100,  this.getHeight()-this.getHeight()*98/100);
		mosseMostro1.setVisible(false);		
		add(mosseMostro1);
		
		mosseMostro2= new JLabel("Mosse: \n"+gioco.getPlayer().getSquadra()[1].getMosse()[0].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[1].getMosse()[1].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[1].getMosse()[2].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[1].getMosse()[3].getNome_mossa());
		mosseMostro2.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		mosseMostro2.setBounds(this.getWidth()*26/100,this.getHeight()*45/100 , this.getWidth()-this.getWidth()*65/100,  this.getHeight()-this.getHeight()*98/100);
		mosseMostro2.setVisible(false);
		add(mosseMostro2);
		
		mosseMostro3= new JLabel("Mosse: \n"+gioco.getPlayer().getSquadra()[2].getMosse()[0].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[2].getMosse()[1].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[2].getMosse()[2].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[2].getMosse()[3].getNome_mossa());
		mosseMostro3.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		mosseMostro3.setBounds(this.getWidth()*26/100,this.getHeight()*65/100 , this.getWidth()-this.getWidth()*65/100,  this.getHeight()-this.getHeight()*98/100);
		mosseMostro3.setVisible(false);
		add(mosseMostro3);
		
		mosseMostro4= new JLabel("Mosse: \n"+gioco.getPlayer().getSquadra()[3].getMosse()[0].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[3].getMosse()[1].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[3].getMosse()[2].getNome_mossa()+" "+gioco.getPlayer().getSquadra()[3].getMosse()[3].getNome_mossa());
		mosseMostro4.setFont(new Font("Dialog", Font.BOLD, (int) (this.getWidth()*1.20/100)));
		mosseMostro4.setBounds(this.getWidth()*26/100,this.getHeight()*85/100 , this.getWidth()-this.getWidth()*65/100,  this.getHeight()-this.getHeight()*98/100);
		mosseMostro4.setVisible(false);
		add(mosseMostro4);
		
		int dimImmagine = getHeight()*7/100;
		imgMostro1 = new JLabel(new ImageIcon(ListaMostri.getImmagini(gioco.getPlayer().getSquadra()[0].getNumero())[0].getScaledInstance(dimImmagine, dimImmagine, Image.SCALE_SMOOTH)));
		imgMostro1.setBounds(this.getWidth()*20/100, this.getHeight()*20/100, dimImmagine, dimImmagine);
		imgMostro1.setVisible(false);
		setComponentZOrder(imgMostro1, 1);
//		add(imgMostro1);
		
		imgMostro2 = new JLabel(new ImageIcon(ListaMostri.getImmagini(gioco.getPlayer().getSquadra()[1].getNumero())[0].getScaledInstance(dimImmagine, dimImmagine, Image.SCALE_SMOOTH)));
		imgMostro2.setBounds(this.getWidth()*20/100, this.getHeight()*40/100, dimImmagine, dimImmagine);
		imgMostro2.setVisible(false);
		setComponentZOrder(imgMostro2, 1);
//		add(imgMostro2);
		//
		imgMostro3 = new JLabel(new ImageIcon(ListaMostri.getImmagini(gioco.getPlayer().getSquadra()[2].getNumero())[0].getScaledInstance(dimImmagine, dimImmagine, Image.SCALE_SMOOTH)));
		imgMostro3.setBounds(this.getWidth()*20/100, this.getHeight()*60/100, dimImmagine, dimImmagine);
		imgMostro3.setVisible(false);
		setComponentZOrder(imgMostro3, 1);
//		add(imgMostro3);
		
		imgMostro4 = new JLabel(new ImageIcon(ListaMostri.getImmagini(gioco.getPlayer().getSquadra()[3].getNumero())[0].getScaledInstance(dimImmagine, dimImmagine, Image.SCALE_SMOOTH)));
		imgMostro4.setBounds(this.getWidth()*20/100, this.getHeight()*80/100, dimImmagine, dimImmagine);
		imgMostro4.setVisible(false);
		setComponentZOrder(imgMostro4, 1);
//		add(imgMostro4);
		
		mostro1=new JTextArea();
		mostro1.setBounds(this.getWidth()*18/100,this.getHeight()*18/100 , this.getWidth()-this.getWidth()*60/100,  this.getHeight()-this.getHeight()*90/100);
		mostro1.setBackground(Color.orange);
		mostro1.setEditable(false);
		mostro1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		mostro1.setVisible(false);
		add(mostro1);
		
		mostro2=new JTextArea();
		mostro2.setBounds(this.getWidth()*18/100,this.getHeight()*38/100 , this.getWidth()-this.getWidth()*60/100,  this.getHeight()-this.getHeight()*90/100);
		mostro2.setBackground(Color.orange);
		mostro2.setEditable(false);
		mostro2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		mostro2.setVisible(false);
		add(mostro2);
		
		
		mostro3=new JTextArea();
		mostro3.setBounds(this.getWidth()*18/100,this.getHeight()*58/100 , this.getWidth()-this.getWidth()*60/100,  this.getHeight()-this.getHeight()*90/100);
		mostro3.setBackground(Color.orange);
		mostro3.setEditable(false);
		mostro3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		mostro3.setVisible(false);
		add(mostro3);
		
		
		mostro4=new JTextArea();
		mostro4.setBounds(this.getWidth()*18/100,this.getHeight()*78/100 , this.getWidth()-this.getWidth()*60/100,  this.getHeight()-this.getHeight()*90/100);
		mostro4.setBackground(Color.orange);
		mostro4.setEditable(false);
		mostro4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		mostro4.setVisible(false);
		add(mostro4);
		
		
		gb = new GestioneBottoniMenu(this, finestra);
		ascoltatoreBottone(gb);
	}
	
	protected void paintComponent(Graphics g) {
	    setOpaque(false);
	    g.drawImage(new ImageIcon("src/resources/sfondi/Sfondomenu.png").getImage(), 0, 0,this.getWidth(), this.getHeight(), null);
	    super.paintComponent(g);
	}
	
	public void mostraSquadra() {
	    this.vitaMostro1.setVisible(squadraVisibile);
	    this.vitaMostro2.setVisible(squadraVisibile);
	    this.vitaMostro3.setVisible(squadraVisibile);
	    this.vitaMostro4.setVisible(squadraVisibile);
	    
		nomeMostro1.setVisible(squadraVisibile);
		nomeMostro2.setVisible(squadraVisibile);
		nomeMostro3.setVisible(squadraVisibile);
		nomeMostro4.setVisible(squadraVisibile);
		
		vita1.setVisible(squadraVisibile);
		vita2.setVisible(squadraVisibile);
		vita3.setVisible(squadraVisibile);
		vita4.setVisible(squadraVisibile);
		
		livelloMostro1.setVisible(squadraVisibile);
		livelloMostro2.setVisible(squadraVisibile);
		livelloMostro3.setVisible(squadraVisibile);
		livelloMostro4.setVisible(squadraVisibile);
		
		mosseMostro1.setVisible(squadraVisibile);
		mosseMostro2.setVisible(squadraVisibile);
		mosseMostro3.setVisible(squadraVisibile);
		mosseMostro4.setVisible(squadraVisibile);
		
		attaccoMostro1.setVisible(squadraVisibile);
		attaccoMostro2.setVisible(squadraVisibile);
		attaccoMostro3.setVisible(squadraVisibile);
		attaccoMostro4.setVisible(squadraVisibile);
		
		difesaMostro1.setVisible(squadraVisibile);
		difesaMostro2.setVisible(squadraVisibile);
		difesaMostro3.setVisible(squadraVisibile);
		difesaMostro4.setVisible(squadraVisibile);
		
		imgMostro1.setVisible(squadraVisibile);
		imgMostro2.setVisible(squadraVisibile);
		imgMostro3.setVisible(squadraVisibile);
		imgMostro4.setVisible(squadraVisibile);
		
		mostro1.setVisible(squadraVisibile);
		mostro2.setVisible(squadraVisibile);
		mostro3.setVisible(squadraVisibile);
		mostro4.setVisible(squadraVisibile);
	}

	public void ascoltatoreBottone(GestioneBottoniMenu gB) {
		squadra.addActionListener(gB);
		salva.addActionListener(gB);		
		esci.addActionListener(gB);
		menuStart.addActionListener(gB);
	}

	public JButton getSalva() {
		return salva;
	}

	public JButton getSquadra() {
		return squadra;
	}

	public JButton getEsci() {
		return esci;
	}
	
	public JButton getMenuStart() {
		return menuStart;
	}

	public Gioco getGioco() {
		return gioco;
	}

	public JTextArea getMostro1() {
		return mostro1;
	}

	public JTextArea getMostro2() {
		return mostro2;
	}

	public JTextArea getMostro3() {
		return mostro3;
	}

	public JTextArea getMostro4() {
		return mostro4;
	}

	public JLabel getLivelloMostro1() {
		return livelloMostro1;
	}

	public JLabel getLivelloMostro2() {
		return livelloMostro2;
	}

	public JLabel getLivelloMostro4() {
		return livelloMostro4;
	}

	public JLabel getAttaccoMostro1() {
		return attaccoMostro1;
	}

	public JLabel getAttaccoMostro2() {
		return attaccoMostro2;
	}

	public JLabel getAttaccoMostro3() {
		return attaccoMostro3;
	}

	public JLabel getAttaccoMostro4() {
		return attaccoMostro4;
	}

	public JLabel getDifesaMostro1() {
		return difesaMostro1;
	}

	public JLabel getDifesaMostro2() {
		return difesaMostro2;
	}

	public JLabel getDifesaMostro3() {
		return difesaMostro3;
	}

	public JLabel getDifesaMostro4() {
		return difesaMostro4;
	}

	public boolean isSquadraVisibile() {
		return squadraVisibile;
	}

	public void setSquadraVisibile(boolean squadraVisibile) {
		this.squadraVisibile = squadraVisibile;
	}
}

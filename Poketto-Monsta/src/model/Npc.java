package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Npc implements Serializable, Comparable<Npc>{

	private static final long serialVersionUID = -8958625974995055691L;
	private String nome, frase;
	private Mostro squadra[] = new Mostro[4];
	private int posX,posY;
	private PuntiCardinali direzione;
	private transient Image img_npc[]= new Image[12];
	private int nPassi = 0;
	private boolean fermo = true;
	
	public Npc(String nome, String frase, Mostro[] squadra, int posX, int posY, PuntiCardinali direzione) {
		super();
		this.nome = nome;
		this.frase = frase;
		this.squadra = squadra;
		this.posX = posX;
		this.posY = posY;
		this.direzione = direzione;
	}

	public void caricaImmagini() {
		img_npc = new Image[12];
		try {
			this.img_npc[0] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/Avanti.png"));
			this.img_npc[1] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaAvantiDx.png"));
			this.img_npc[2] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaAvantiSx.png"));
			this.img_npc[3] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/Dietro.png"));
			this.img_npc[4] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaDietroDx.png"));
			this.img_npc[5] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaDietroSx.png"));
			this.img_npc[6] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/Destra.png"));
			this.img_npc[7] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaDestraDx.png"));
			this.img_npc[8] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaDestraSx.png"));
			this.img_npc[9] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/Sinistra.png"));
			this.img_npc[10] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaSinistraDx.png"));
			this.img_npc[11] = ImageIO.read(new File("src/resources/ImmaginiNPG/" + nome + "/CamminaSinistraSx.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Image immagine() {
		Image img = null;
		
		if(fermo) {
			switch(direzione) {
			case Nord:
				img = img_npc[3];
				break;
			case Sud:
				img = img_npc[0];
				break;
			case Est:
				img = img_npc[6];
				break;
			case Ovest:
				img = img_npc[9];
				break;
			}
	    }else if(getnPassi()%2 == 0){
	    	switch(direzione) {
			case Nord:
				img = img_npc[4];
				break;
			case Sud:
				img = img_npc[1];
				break;
			case Est:
				img = img_npc[7];
				break;
			case Ovest:
				img = img_npc[10];
				break;
			}
	    } else {
	    	switch(direzione) {
			case Nord:
				img = img_npc[5];
				break;
			case Sud:
				img = img_npc[2];
				break;
			case Est:
				img = img_npc[8];
				break;
			case Ovest:
				img = img_npc[11];
				break;
			}
	    }
		
		return img;
	}
	
	public void setDirezione(PuntiCardinali direzione) {
		this.direzione = direzione;
	}

	public String getFrase() {
		return frase;
	}

	public Mostro[] getSquadra() {
		return squadra;
	}
	
	public void setSquadra(Mostro[] squadra) {
		this.squadra = squadra;
	}
	
	public String stampaSquadra() {
		String s="";
		
		for (int i = 0; i < squadra.length; i++) {
			s=s+squadra[i].toString()+"\n";
		}
		
		return s;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Image[] getImg_npc() {
		return img_npc;
	}

	public void setImg_npc(Image[] img_npc) {
		this.img_npc = img_npc;
	}

	public boolean isFermo() {
		return fermo;
	}

	public void setFermo(boolean fermo) {
		this.fermo = fermo;
	}

	public PuntiCardinali getDirezione() {
		return direzione;
	}

	@Override
	public int compareTo(Npc o) {
		// TODO Auto-generated method stub
		if(posY > o.getPosY()) {
			return 1;
		}else if(posY < o.getPosY()){
			return -1;
		}
		
		return 0;
	}

	public int getnPassi() {
		return nPassi;
	}

	public void setnPassi(int nPassi) {
		this.nPassi = nPassi;
	}
}

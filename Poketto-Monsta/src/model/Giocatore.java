package model;

public class Giocatore extends Npc{
	private static final long serialVersionUID = 6978322925295540897L;
	private String nome;
	private int dx=0, dy=0;
	private int velocità = 4;
	private int spostamentoX=0, spostamentoY=0;
	
	public Giocatore(String nomeGiocatore, int posX, int posY, PuntiCardinali direzione) {
		super("Player", null, null, posX, posY, direzione);
		nome = nomeGiocatore;
		riempiSquadra();
	}
	
	public void riempiSquadra() {
		Mostro[] squadra = new Mostro[4];
		
		for(int i = 0; i < 4; i++) {
			squadra[i] = ListaMostri.getMostroNuovo((int)(Math.random()* ListaMostri.N_MOSTRI));
			squadra[i].riempiMosse();
		}
		
		setSquadra(squadra);
	}
	
	public void update(Gioco gioco) {
		if(!isFermo()) {
			switch(getDirezione()) {
				case Nord:
					spostamentoY -= velocità;
					break;
				case Sud:
					spostamentoY += velocità;
					break;
				case Est:
					spostamentoX += velocità;
					break;
				case Ovest:
					spostamentoX -= velocità;
					break;
			}
			
			if(spostamentoX < -16 || spostamentoX > 16 || spostamentoY < -16 || spostamentoY > 16) {
				gioco.muovi(this, getPosX()+dx, getPosY()+dy);
				spostamentoX = 0;
				spostamentoY = 0;
				setFermo(true);
			}
		}
	}
	
	public void muovi(PuntiCardinali direzione, Gioco gioco) {
		if(isFermo()) {
			if(this.getDirezione() == direzione) {
				dx = 0; dy = 0;
				switch(direzione) {
					case Nord:
						dy = -1;
						break;
					case Sud:
						dy = +1;
						break;
					case Est:
						dx = 1;
						break;
					case Ovest:
						dx = -1;
						break;
				}
				
				if(gioco.siPuoCamminareSullaCasella(getPosX()+dx, getPosY()+dy)) {
					setFermo(false);
					setnPassi(getnPassi() + 1);
				}
			}else {
				setDirezione(direzione);
			}
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getSpostamentoX() {
		return spostamentoX;
	}

	public int getSpostamentoY() {
		return spostamentoY;
	}
	
}

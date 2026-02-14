package model;

import java.io.Serializable;

public class Mostro implements Serializable{
	private static final long serialVersionUID = 335706528560932571L;
	private String nome;
	private int numero;
	private Mossa mosse[] = new Mossa[4];
	private int hp;
	private int hp_attuali;
	private int attacco;
	private int difesa;
	private int level;
	private int exp;
	
	//riempio il vettore di mosse del mostro
	public void riempiMosse() {
		for(int i = 0; i < 4; i++) {
		
			this.mosse[i] = ListaMosse.getListaMosse()[ (int)( Math.random()*ListaMosse.N_MOSSE)];
			
		}
	}
	 
	
	public Mostro(String nome, int numero, int hp, int attacco, int difesa, int level,
			int exp) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.hp = hp;
		this.hp_attuali = hp;
		this.attacco = attacco;
		this.difesa = difesa;
		this.level = level;
		this.exp = exp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public Mossa[] getMosse() {
		return mosse;
	}

	public void setMosse(Mossa[] mosse) {
		this.mosse = mosse;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getHp_attuali() {
		return hp_attuali;
	}

	public void setHp_attuali(int hp_attuali) {
		this.hp_attuali = hp_attuali;
	}

	public int getAttacco() {
		return attacco;
	}

	public void setAttacco(int attacco) {
		this.attacco = attacco;
	}

	public int getDifesa() {
		return difesa;
	}

	public void setDifesa(int difesa) {
		this.difesa = difesa;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	
	public String stampaMosse() {
		String s="";
		
		
		for (int i = 0; i < mosse.length; i++) {
			s=s+mosse[i].toString()+"\n";
		}
		return s;
	}

	@Override
	public String toString() {
		return "Mostro [nome=" + nome + ", mosse=" + stampaMosse() + ", hp=" + hp + ", level=" + level + "]";
	}
}
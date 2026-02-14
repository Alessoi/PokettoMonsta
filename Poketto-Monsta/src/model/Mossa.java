package model;

import java.io.Serializable;

public class Mossa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome_mossa;
	private int basePower;
	private int precisione;
	private String descrizione;
	private boolean mossa_fisica;
	
	public Mossa(String nome_mossa, int basePower, int precisione, String descrizione, boolean mossa_fisica) {
		super();
		this.nome_mossa = nome_mossa;
		this.basePower = basePower;
		this.precisione = precisione;
		this.descrizione = descrizione;
		this.mossa_fisica = mossa_fisica;
	}
	
	public String getNome_mossa() {
		return nome_mossa;
	}
	
	public void setNome_mossa(String nome_mossa) {
		this.nome_mossa = nome_mossa;
	}
	
	public int getBasePower() {
		return basePower;
	}
	
	public void setBasePower(int basePower) {
		this.basePower = basePower;
	}
	
	public int getPrecisione() {
		return precisione;
	}
	
	public void setPrecisione(int precisione) {
		this.precisione = precisione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public boolean isMossa_fisica() {
		return mossa_fisica;
	}
	
	public void setMossa_fisica(boolean mossa_fisica) {
		this.mossa_fisica = mossa_fisica;
	}

	@Override
	public String toString() {
		return "Mossa [nome_mossa=" + nome_mossa + "]";
	}
	
	
}
	
	
package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import control.GestioneChunkFile;
import model.Caselle.Casella;

public class Gioco {
	private HashMap<IntPosizione, Chunk> mappa; 
	private Giocatore player;
	private boolean inBattaglia = false;
	private Mostro mostro_randomico = null;
	//
	public Gioco(Giocatore player) {
		super();
		mappa =  new HashMap<IntPosizione, Chunk>();
		this.player = player;
		this.player.caricaImmagini();
	}
	
	public Giocatore getPlayer() {
		return player;
	}

	public void setPlayer(Giocatore player) {
		this.player = player;
	}

	public void aggiorna(int x, int y) {
		int chunkX = x / 16, chunkY = y / 16;
		
		for(int cX = chunkX-1; cX <= chunkX+1; cX++) {
			for(int cY = chunkY-1; cY <= chunkY+1; cY++) {
				if(!mappa.containsKey(new IntPosizione(cX, cY))) {
					//carica il chunk.
					Chunk chunk = GestioneChunkFile.caricaChunk(cX, cY);
					chunk.generateImage();
					mappa.put(new IntPosizione(cX, cY), chunk);
				}
			}
		}
		
		Iterator<Entry<IntPosizione, Chunk>> it = mappa.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<IntPosizione, Chunk> pair = it.next(); 
	        IntPosizione coord =  pair.getKey();
	        
	        if(coord.getX() < chunkX-1 || coord.getX() > chunkX+1 || coord.getY() < chunkY-1 || coord.getY() > chunkY+1) {
	        	it.remove();
	        }
	    }
	}
	
	public boolean siPuoCamminareSullaCasella(int x, int y) {
		//trasformo le coordinate da globali a quelle dei chunk
		int chunkX = x / 16, chunkY = y / 16;
		int casellaX = x % 16, casellaY = y % 16;
		IntPosizione chunkPos = new IntPosizione(chunkX, chunkY);
		
		if(mappa.containsKey(chunkPos) && casellaX >= 0 && casellaY >= 0){
			Chunk chunk = mappa.get(chunkPos);
			
			if(chunk.getCasella(casellaX, casellaY).isCamminabile()) {
				Vector<Npc> personaggi = chunk.getPersonaggi();
				if(personaggi != null) {
					for(int i = 0; i < personaggi.size(); i++) {
						if(personaggi.get(i).getPosX() == x && personaggi.get(i).getPosY() == y) {
							return false;
						}
					}
				}
				return true;
			}
		}
		
		return false;
	}
	
	public Npc interagisci(int x, int y, PuntiCardinali direzione) {
		//trasformo le coordinate da globali a quelle dei chunk
		int chunkX = x / 16, chunkY = y / 16;
		int casellaX = x % 16, casellaY = y % 16;
		IntPosizione chunkPos = new IntPosizione(chunkX, chunkY);
		
		Chunk chunk = mappa.get(chunkPos);
	
		if(chunk != null) {
			Vector<Npc> personaggi = chunk.getPersonaggi();
			if(personaggi != null) {
				for(int i = 0; i < personaggi.size(); i++) {
					if(personaggi.get(i).getPosX() == x && personaggi.get(i).getPosY() == y) {
						personaggi.get(i).setDirezione(direzione);
						return personaggi.get(i);
					}
				}
			}
		}
		
		return null;
	}
	
	public boolean muovi(Giocatore giocatore, int nuovaX, int nuovaY) {
		if(siPuoCamminareSullaCasella(nuovaX, nuovaY)) {
			giocatore.setPosX(nuovaX);
			giocatore.setPosY(nuovaY);
			getCasella(nuovaX, nuovaY).azioneSeCalpestata(this, giocatore);
			return true;
		}
		return false;
	}
	
	public Casella getCasella(int x, int y) {
		//trasformo le coordinate da globali a quelle dei chunk
		int chunkX = x / 16, chunkY = y / 16;
		int casellaX = x % 16, casellaY = y % 16;
		IntPosizione chunkPos = new IntPosizione(chunkX, chunkY);
		
		if(mappa.containsKey(chunkPos) && casellaX >= 0 && casellaY >= 0){
			Chunk chunk = mappa.get(chunkPos);
			
			return chunk.getCasella(casellaX, casellaY);
		}
		
		return null;
	}
	
	public HashMap<IntPosizione, Chunk> getMappa(){
		return mappa;
	}
	
	public Chunk getChunk(int chunkX, int chunkY) {
		IntPosizione chunkPos = new IntPosizione(chunkX, chunkY);
		
		if(mappa.containsKey(chunkPos)){
			Chunk chunk = mappa.get(chunkPos);
			
			return chunk;
		}
		
		return null;
	}

	public boolean isInBattaglia() {
		return inBattaglia;
	}

	public void setInBattaglia(boolean inBattaglia) {
		this.inBattaglia = inBattaglia;
	}

	public Mostro getMostro_randomico() {
		return mostro_randomico;
	}

	public void setMostro_randomico(Mostro mostro_randomico) {
		this.mostro_randomico = mostro_randomico;
	}
	
	
}

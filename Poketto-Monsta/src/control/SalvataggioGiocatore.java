package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Giocatore;

/**
 * Classe con il compito di salvare e caricare il salvataggio  dalla memoria
 * 
 * @author Alessio Gasparini
 */

public class SalvataggioGiocatore {
	
	/**
	 * Questo metodo è usato per salvare un istanza della classe Giocatore su un file
	 * 
	 * @param giocatore il giocatore che si vuole salvare su file
	 */
	public static void salva(Giocatore giocatore) {
		try {
			FileOutputStream fileOut = new FileOutputStream("src/resources/salvataggio/giocatore.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut); 
	        out.writeObject(giocatore);
	        out.close();
	        fileOut.close();
		} catch (IOException i) {
	        i.printStackTrace();
	    }
	}
	
	/**
	 * Questo metodo è usato per caricare un istanza della classe Giocatore precedentemente caricata su un file,
	 * ritorna null se il salvataggio non viene trovato 
	 * 
	 * @return il giocatore caricato dal file
	 */
	public static Giocatore carica() {
		Giocatore giocatore = null;
		
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("src/resources/salvataggio/giocatore.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			giocatore = (Giocatore) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
//			giocatore = new Giocatore("Gigi",19,19,PuntiCardinali.Sud);
		}
		
		return giocatore;
	}
}

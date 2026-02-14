package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Chunk;
import model.Caselle.Casella;

/**
 * Classe con il compito di salvare e caricare i chunk dalla memoria
 * 
 * @author Alessio Gasparini
 */

public class GestioneChunkFile {
	
	/**
	 * Questo metodo è usato per caricare il chunk alle coordinate richieste dalla memoria,
	 *  se non è presente ritorna un chunk di default
	 * 
	 * @param chunkX le coordinate x del chunk
	 * @param chunkY le coordinate y del chunk
	 * @return chunk alle cordinate richieste o un chunk di default se il chunk non è presente tra i file
	 * @see Chunk
	 */
	public static Chunk caricaChunk(int chunkX, int chunkY) {
		 Chunk chunk = null;
	      try {
	    	 //e
	         FileInputStream fileIn = new FileInputStream("src/resources/chunks/chunk"+chunkX+"-"+chunkY+".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         chunk = (Chunk) in.readObject();
	         in.close();
	         fileIn.close();
	         //s
	      } catch (IOException i) {
	         chunk = new Chunk(defaultChunk(), "Fuori dalla mappa", null);
	      } catch (ClassNotFoundException e) {
			e.printStackTrace();
	      }
	      
	      return chunk;
	}
	
	/**
	 * Questo metodo salva un chunk in memoria alle coordinate date
	 * 
	 * @param chunkX le coordinate x a cui salvare il chunk
	 * @param chunkY le coordinate y a cui salvare il chunk
	 * @param chunk il chunk da salvare
	 * @see Chunk
	 */
	public static void salvaChunk(int chunkX, int chunkY, Chunk chunk) {
		 try {
	         FileOutputStream fileOut = new FileOutputStream("src/resources/chunks/chunk"+chunkX+"-"+chunkY+".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut); 
	         out.writeObject(chunk);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	/**
	 * Questo metodo crea la matrice delle caselle del chunk di default e la ritorna
	 * 
	 * @return la matrice di caselle del chunk di default
	 * @see Chunk
	 */
	private static Casella[][] defaultChunk(){
		Casella[][] matrix = new Casella[16][16];
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 16; y++) {
				matrix[x][y] = new Casella(x+12, y, true);
			}
		}
		
		return matrix;
	}
}

package control;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe con il compito di caricare l'immagine dello SpriteSheet della mappa dalla memoria
 * e ritornare le immagini che lo compongono quando richieste
 * 
 * @author Alessio Gasparini
 */

//questa classe caricherà l'immagine e ritornerà una singola casella ricenvendo in input x e y 
public class SpriteSheetMappa {
	//La dimensione di ogni cella in pixel
	public static final int PIXEL_SIZE = 16;
	//Il numero di celle per colonna
	public static final int N_CELLS = 50;
	private static BufferedImage spriteSheet;
	//Le immagini vengono separate in un array per permettere un accesso più facilitato
	private static Image[][] array = new Image[N_CELLS][N_CELLS];
	
	/**
	 * Questo metodo carica l'immagine dello spriteSheet da un file e lo separa nelle varie immagini che lo compongono, salvandole in una matrice
	 */
	public static void init() {
		try {
			//Legge l'immagine da file
			spriteSheet = ImageIO.read(new File("src/resources/spriteSheetMappa.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Separa le immagini e le inserisce nell'array nell'array
		for(int x = 0; x < N_CELLS; x++) {
			for(int y = 0; y < N_CELLS; y++) {
				array[x][y] = spriteSheet.getSubimage(x*PIXEL_SIZE, y*PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
			}
		}
	}
	
	/**
	 * Questo metodo ritorna l'immagine contenuta all'interno della matrice di immagini alle coordinate x e y passate al metodo
	 * @param x la coordinata x a cui è situata l'immagine nella matrice
	 * @param y la coordinata y a cui è situata l'immagine nella matrice
	 * @return l'immagine alle coordinate date
	 * @see Image
	 */
	//data x e y restituisce l'immagine in quella casella dell'array
	public static Image getImage(int x, int y) {
		return array[x][y];
	}
}

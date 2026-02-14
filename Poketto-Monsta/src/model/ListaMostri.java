package model;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class ListaMostri{
	public static final int N_MOSTRI = 7;
	private static Mostro listaMostri[] = new Mostro[N_MOSTRI];
	private static Image image_mostro[][] = new Image[N_MOSTRI][2];
	
	public static void init() {
		try {
		//riempio una matrice con tutte le immagini appartenenti a tutti i mostri
			image_mostro[0][0] = ImageIO.read(new File("src/resources/ImmaginiMostro/Swampert_frontSprite.png"));
			image_mostro[0][1] = ImageIO.read(new File("src/resources/ImmaginiMostro/Swampert_backSprite.png"));
			
			image_mostro[1][0] = ImageIO.read(new File("src/resources/ImmaginiMostro/hariyama_frontSprite.png"));
			image_mostro[1][1] = ImageIO.read(new File("src/resources/ImmaginiMostro/hariyama_backSprite.png"));
			
			image_mostro[2][0] = ImageIO.read(new File("src/resources/ImmaginiMostro/Narancina_frontSprite.png"));
			image_mostro[2][1] = ImageIO.read(new File("src/resources/ImmaginiMostro/Narancina_backSprite.png"));
			
			image_mostro[3][0] = ImageIO.read(new File("src/resources/ImmaginiMostro/nutria_FrontSprite.png"));
			image_mostro[3][1] = ImageIO.read(new File("src/resources/ImmaginiMostro/nutria_backSprite.png"));
			
			image_mostro[4][0]= ImageIO.read(new File("src/resources/ImmaginiMostro/shadowent_frontSprite.png"));
			image_mostro[4][1]= ImageIO.read(new File("src/resources/ImmaginiMostro/shadowent_backSprite.png"));
			
			image_mostro[5][0]= ImageIO.read(new File("src/resources/ImmaginiMostro/holorse_frontSprite.png"));
			image_mostro[5][1]= ImageIO.read(new File("src/resources/ImmaginiMostro/holorse_backSprite.png"));
			
			image_mostro[6][0]= ImageIO.read(new File("src/resources/ImmaginiMostro/shellref_frontSprite.png"));
			image_mostro[6][1]= ImageIO.read(new File("src/resources/ImmaginiMostro/shellref_backSprite.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// riempio il vettore con i mostri
		listaMostri[0] = new Mostro("Swampert", 0, 150, 80, 80, 5, 0);
		listaMostri[1] = new Mostro("Hariyama", 1, 130, 110, 70, 5, 0);
		listaMostri[2] = new Mostro("Narancina", 2, 150, 80, 80, 5, 0);
		listaMostri[3] = new Mostro("Nutria", 3, 160, 110, 90, 5, 0);
		listaMostri[4]= new Mostro("Shadowent", 4, 120, 130, 70, 5, 0);
		listaMostri[5]= new Mostro("Holorse", 5, 120, 130, 70, 5, 0);
		listaMostri[6]= new Mostro("Shellref", 6, 200, 70, 120, 5, 0);
	}
	
	public static Mostro getMostroNuovo(int numero) {
		Object obj = (Object) listaMostri[numero];
		
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return (Mostro) obj;
	}
	
	public static Image[] getImmagini(int numero) {
		return image_mostro[numero];
	}
}

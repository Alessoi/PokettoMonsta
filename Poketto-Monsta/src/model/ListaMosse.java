package model;


public class ListaMosse{
	public static final int N_MOSSE = 7;
	private static Mossa listaMosse[] = new Mossa[N_MOSSE];
	
	public static void init() {
	//riempio un vettore con tutte le  opzioni mosse
		listaMosse[0] = new Mossa("Azione", 40, 100, "Un semplice attaco fisico che danneggia \nl'avversario", true);
		listaMosse[1] = new Mossa("Pistolacqua", 45, 95, "Un getto d'acqua dalla moderata \npotenza", true);
		listaMosse[2] = new Mossa("Terremoto", 70, 80, "Crea un potente sisma ma che non \nsempre va a segno", true);
		listaMosse[3] = new Mossa("Frustata", 50, 90, "Una poderosa frustata data con una\nliana", true);
		listaMosse[4] = new Mossa("Fiammata", 55, 85, "Una calorosa fiammata", true);
		listaMosse[5] = new Mossa("Sassata", 60, 90, "Lancia un sasso con tutte le tue forze", true);
		listaMosse[6] = new Mossa("Azzardo", 150, 40, "Una mossa devastante ma molto rischiosa \nda usare, fallisce spesso", true);
		
	}

	public static Mossa[] getListaMosse() {
		return listaMosse;
	}

	public static void setListaMosse(Mossa[] listaMosse) {
		ListaMosse.listaMosse = listaMosse;
	}
}

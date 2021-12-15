package miniprojet;

public class Membre {

	private String nom;
	private int id;
	static int c;

	public Membre(String nom) {
		this.nom = nom;
		this.id = c++;

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getC() {
		return c;
	}

	public static void setC(int c) {
		Membre.c = c;
	}

	public Membre(String nom, int id) {
		super();
		this.nom = nom;
		this.id = id;
	}

}

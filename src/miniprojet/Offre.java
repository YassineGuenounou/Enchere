package miniprojet;

public class Offre {

	private double prix;
	private Membre m;
	private Enchere e;

	public Offre(double prix, Membre m, Enchere e) {
		super();
		this.prix = prix;
		this.m = m;
		this.e = e;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Membre getM() {
		return m;
	}

	public void setM(Membre m) {
		this.m = m;
	}

	public Enchere getE() {
		return e;
	}

	public void setE(Enchere e) {
		this.e = e;
	}

	public Offre() {
		super();
	}
	public String info() {
		return prix+"##"+m.getNom()+"##"+e.getId()+"##";
	}

}

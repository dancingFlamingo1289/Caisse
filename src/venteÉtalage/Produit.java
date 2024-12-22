package venteÉtalage ;

public class Produit {
	private String upc ;
	private String nom ;
	private int qte ;
	private float prix ;

	public Produit(String nom, String upc, int qte, float prix) {
		this.upc = upc ;
		this.nom = nom ;
		this.prix = prix ;
		this.qte = qte ;
	}

	public String getUPC() {
		return this.upc ;
	}

	public String getNom() {
		return this.nom ;
	}

	public int getQte() {
		return this.qte ;
	}

	public float getPrix() {
		return this.prix ;
	}

	public void setQte(int nouvQte) {
		this.qte = nouvQte ;
	}

	public void setPrix(float nouvPrix) {
		this.prix = nouvPrix ;
	}

	public void setUPC(String nouvUpc) {
		this.upc = nouvUpc ;
	}

	public String toString() {
		return "Nom : " + this.nom + "\t\tUPC : " + this.upc + "\t\tQte restante : " + 
				this.qte + "\t\t Prix : " + this.prix ;
	}

	public String toStringClient() {
		return this.nom + "\t" + this.upc + "\t" + this.prix + "$" ;
	}
	
	public String toStringÀSupprimer() {
		return this.nom + "\t" + this.upc + "\t-" + this.prix + "$" ;
	}
}

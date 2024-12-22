package structure ;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Employé {
	private String nom, prénom ;
	private float salaire ;
	public ArrayList<Employé> listeEmployés = new ArrayList<Employé>() ;

	public Employé(String nom, String prénom) {
		this.nom = nom ;
		this.prénom = prénom ;
		this.salaire = 15.25f ;
	}

	public Employé(String nom, String prénom, float salaire) {
		this.nom = nom ;
		this.prénom = prénom ;
		this.salaire = salaire ;
	}

	public String getNom() {
		return this.nom ;
	}

	public String getPrénom() {
		return this.prénom ;
	}

	public float getSalaire() {
		return this.salaire ;
	}

	public void setSalaire(float nouvSalaire) {
		this.salaire = nouvSalaire ;
	}

	public String toString() {
		return "Nom, prénom : " + this.nom + ", " + this.prénom + 
				"\nTaux horaire : " + this.salaire + "$/h" ;
	}

	public static void écrireLettreDémission(String nom) throws IOException {
		PrintWriter lettreDémission = 
				new PrintWriter(new FileWriter("lettreDémission_" + nom + ".txt")) ;
		lettreDémission.println("Madame / Monsieur" + nom + ",\n") ;
		lettreDémission.println("Par la présente, nous sommes au regret de vous informer "
				+ "que nous avons décidé de résilier avec effet immédiat (pour motif grave "
				+ "conformément à l'article L. 124-10 du Code du travail) votre contrat de "
				+ "travail.") ;
		lettreDémission.close() ;
	}
}

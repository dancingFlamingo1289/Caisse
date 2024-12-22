package structure ;

import gestionCaisse.GestionCaisses ;

public class Gestionnaire extends Caissier {
	private boolean estGSC ;
	private float salaire ;

	public Gestionnaire(Caissier nouveauGestionnaire) {
		super(nouveauGestionnaire.getNom(), nouveauGestionnaire.getPrénom()) ;
		this.salaire = 15.70f ;
	}
	
	public Gestionnaire(Caissier nouveauGestionnaire, float nouvSalaire) {
		super(nouveauGestionnaire.getNom(), nouveauGestionnaire.getPrénom()) ;
		this.salaire = nouvSalaire ;
	}

	public boolean estGestionnaire(Caissier autre) {
		return GestionCaisses.listeGSC.contains(autre) == true ;
	}
	
	public boolean getEstGSC() {
		return estGSC ;
	}

	public void setEstGSC(boolean estGSC) {
		this.estGSC = estGSC ;
	}

	public boolean estGSC(int id, int mDeP) {
		boolean trouvé = false ;
		int i = 0 ;

		while (trouvé == false) {
			if ((GestionCaisses.listeGSC.get(i).getIDCaisse() + "").equals(id + "") 
					&& (GestionCaisses.listeGSC.get(i).getMotDePasse() + "").equals(mDeP + "")) {
				trouvé = true ;
			}

			i++ ;
		}
		
		return trouvé ;
	}
	
	public boolean estGSC(String nom, String prénom) {
		boolean found = false ;
		int i = 0 ;

		while (found == false) {
			if (GestionCaisses.listeGSC.get(i).getNom().equals(nom) 
					&& GestionCaisses.listeGSC.get(i).getPrénom().equals(prénom)) {
				found = true ;
			}

			i++ ;
		}

		return found ;
	}

	public void rétrograder(String nom, String prénom) {
		Gestionnaire toFind ;
		int i = 0 ;

		if (GestionCaisses.listeGSC.get(i).getNom().equals(nom) && 
				GestionCaisses.listeGSC.get(i).getPrénom().equals(prénom) && 
				i != GestionCaisses.listeGSC.size()) {
			toFind = GestionCaisses.listeGSC.get(i) ;

			GestionCaisses.listeGSC.remove(toFind) ;
		}
	}

	public String toString() {
		return "Nom, prénom : " + super.getNom() + ", " + super.getPrénom() + "\n" + 
				"Gestionnaire\t" + super.getIDCaisse() + ", " + super.getMotDePasse().toString() ;
	}
}
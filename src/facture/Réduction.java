package facture;

import java.util.Stack ;

public class Réduction {
	public static Stack<Réduction> pileRéductions = new Stack<Réduction>() ;
	float valRéduction ;

	public Réduction(float val) {
		this.valRéduction = val ;
	}

	public void ajouterRéduction(Réduction réduction) {
		pileRéductions.add(réduction) ;
	}

	public float getValRéduction() {
		return this.valRéduction ;
	}
}
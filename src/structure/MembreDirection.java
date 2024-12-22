package structure ;

import java.util.ArrayList ;

import gestionCaisse.GestionCaisses;

public class MembreDirection extends Gestionnaire {
	public static ArrayList<MembreDirection> listeDirection = new ArrayList<MembreDirection>() ;

	public MembreDirection(Caissier nouveauMembreDir) {
		super(nouveauMembreDir) ;
	}

	public boolean estMembreDir(Caissier autre) {
		return estGSC(autre) && GestionCaisses.listeGSC.contains(autre) ;
	}
	
	
}

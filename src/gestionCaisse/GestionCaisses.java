package gestionCaisse ;

import java.io.FileWriter ;
import java.io.IOException ;
import java.io.PrintWriter ;
import java.util.ArrayList ;
import structure.Caissier ;
import structure.Gestionnaire ;

public class GestionCaisses {
	public static ArrayList<Caissier> listeCaissiers = new ArrayList<Caissier>() ;
	public static ArrayList<Gestionnaire> listeGSC = new ArrayList<Gestionnaire>() ;
	
	public static void ajouterCaissier(String nom, String prénom) {
		Caissier caissier = new Caissier(nom, prénom) ;
		
		listeCaissiers.add(caissier) ;
		System.out.println(caissier.toString()) ;
 	}
	
	public static void promouvoirGSC(Caissier nouveau) {
		Gestionnaire nouveauGestionnaire = new Gestionnaire(nouveau) ;
		
		listeGSC.add(nouveauGestionnaire) ;
 	}
	
	public static void imprimerListeCaissiers() throws IOException {
		PrintWriter docListeCaissiers = new PrintWriter(new FileWriter("listeCaissiers.txt")) ;
		
		for (int i = 0 ; i < listeCaissiers.size() ; i++) {
			docListeCaissiers.append(listeCaissiers.get(i).toString() + "\n") ;
		}
		docListeCaissiers.close() ;
	}
	
	public static void imprimerListeGSC() throws IOException {
		PrintWriter docListeGSC = new PrintWriter(new FileWriter("listeGSC.txt")) ;
		
		for (int i = 0 ; i < listeGSC.size() ; i++) {
			docListeGSC.println(listeGSC.get(i).toString()) ;
		}
		
		docListeGSC.close() ;
	}
}
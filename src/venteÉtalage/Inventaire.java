package venteÉtalage ;

import java.io.File;
import java.io.FileWriter ;
import java.io.IOException ;
import java.io.PrintWriter ;
import java.util.ArrayList ;
import javax.swing.JOptionPane ;

public class Inventaire {
	public static ArrayList<Produit> listeProduits = new ArrayList<Produit>() ;
	
	public static boolean ajouterProduit(String nom, String upc, int qte, float prix) {
		if (upc.length() != 7 || qte < 0 || prix < 0.20) {
			JOptionPane.showMessageDialog(null, "Erreur système".toUpperCase()) ;
			return false ;
		} else {
			listeProduits.add(new Produit(nom, upc, qte, prix)) ;
			return true ;
		}
	}
	
	public static boolean supprimerProduit(String upc) {
		boolean trouvé = false ;
		int i = 0 ;
		
		while (trouvé == false && !listeProduits.isEmpty()) {
			if (listeProduits.get(i).getUPC().equals(upc)) {
				listeProduits.remove(i) ;
				trouvé = true ;
			}
			i++ ;
		}
		
		return trouvé ;
	}
	
	public static void acheterProduit(String upc) {
		boolean trouvé = false ;
		int i = 0 ;
		
		while (trouvé == false && !listeProduits.isEmpty()) {
			if (listeProduits.get(i).getUPC().equals(upc)) {
				int qte = listeProduits.get(i).getQte() ;
				listeProduits.get(i).setQte(qte - 1) ;
				trouvé = true ;
			}
			i++ ;
		}
	}
	
	public static void imprimerListeProduits() throws IOException {
		File folder = new File("/Bureau/Workspace_v2/Caisse/Liste de produits");
		if (!folder.exists()) {
            boolean created = folder.mkdirs() ;
            if (created == true) {
                System.out.println("Le dossier a été créé avec succès.") ;
            } else {
                System.err.println("Impossible de créer le dossier spécifié.") ;
            }
        }
		
		// Créer et écrire dans le fichier à l'aide de PrintWriter
        File fichier = new File(folder, "ListeProduits.txt") ;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fichier));
            int i = 0 ;
    		
    		writer.println("***Liste des produits disponibles***".toUpperCase()) ;
    		
    		while (!listeProduits.isEmpty()) {
    			writer.println(listeProduits.get(i).toString()) ;
    		}
    		
    		writer.println("***Fin de la liste***".toUpperCase()) ;
    		writer.close() ;
            JOptionPane.showMessageDialog(null, "Le fichier a été créé et écrit avec succès.") ;
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de la création et de l'écriture du fichier : " + e.getMessage());
        }
	}
}
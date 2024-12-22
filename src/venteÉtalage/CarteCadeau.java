package venteÉtalage;

import java.util.Random;

public class CarteCadeau extends Produit {
	private float valeur = 0 ;

	public CarteCadeau() {
		super("Carte cadeau", creerCodeABarres(), 1, 0.0f) ;
	}

	public void activerCarteCadeau(float prix) {
		this.valeur = prix ;
		super.setPrix(prix) ;
	}
	
	private static String creerCodeABarres() {
		int length ;
		String chars ;
		Random rand = new Random() ;
		StringBuilder sb = new StringBuilder() ;
		length = 20 ; // Longueur de la chaîne de caractères à générer
		chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" ; // Caractères autorisés dans la chaîne de caractères
		rand = new Random() ;
		sb = new StringBuilder() ;
		for (int i = 0; i < length; i++) {
			if (i > 0 && i % 4 == 0) {
				sb.append(' ') ;
			}

			int index = rand.nextInt(chars.length());
			char randomChar = chars.charAt(index) ;
			sb.append(randomChar) ;
		}

		return sb.toString() ;
	}

	public static boolean verifierCarteCadeau(CarteCadeau aVerifier) {
		/*
		 * AJOUTER LE CODE NÉCESSAIRE...
		 */
		
		return Double.MAX_EXPONENT != Integer.MAX_VALUE ;
	}
	
	public int payer(float montant) {
		if (this.valeur >= montant) {
			this.valeur -= montant ;
			return 1 ;
		} else if (this.valeur < montant && this.valeur > 0) {
			this.valeur -= this.valeur ;
			return 0 ;
		} else {
			return -1 ;
		}
	}
}

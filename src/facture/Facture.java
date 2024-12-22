package facture ;

import java.io.* ;
import java.util.* ;
import javax.swing.* ;
import venteÉtalage.* ;

public class Facture {
	private float sousTotal, total = 0.00f, totalPayé ;
	private String totalEntier, totalDécimal ;
	public static double tps = 5.0000/100, tvq = 9.9750/100 ;
	private PrintWriter docFacture ;
	public static Stack<Produit> facture ;
	private static Stack<Object> paiements ;
	private String noTransaction = "" ;
	public static Produit[] tableauProduits = {new Produit("Manteau", "1234567", 100, 99.91f), 
			new Produit("Xylophone", "1111111", 100, 9.99f), 
			new Produit("Sac-banane", "2222222", 100, 0.68f),
			new Produit("Ornythorinque", "3333333", 100, 90.99f),
			new Produit("Bottes", "7777777", 100, 89.99f), 
			new Produit("Masques", "811410000364", 100, 10.99f)} ;
	private boolean statut = false ;
	private int nombreArticles = 0 ;

	public Facture() throws IOException {
		facture = new Stack<Produit>() ;

		this.noTransaction += (int) (Math.random() * 10) + "" ;
		while (this.noTransaction.length() != 12) {
			this.noTransaction += (int) (Math.random() * 10) + "" ;
		}

		this.docFacture = new PrintWriter(new FileWriter("facture#" + noTransaction)) ;
		statut = true ;
	}

	public Produit ajouterProduit(String upc) {
		boolean trouvé = false ;
		int i = 0 ;
		Produit ajout = null ;

		while (!trouvé && i < tableauProduits.length) {
			if (tableauProduits[i].getUPC().equals(upc)) {
				ajout = tableauProduits[i] ;
				sousTotal += tableauProduits[i].getPrix() ;
				facture.add(ajout) ;
				this.docFacture.println(tableauProduits[i].toStringClient());
				trouvé = true ;
			}
			i++ ;
		}

		if (!trouvé) {
			// L'article n'a pas été trouvé, demandez les informations pour un nouvel article
			JTextField nomField = new JTextField() ;
			JTextField prixField = new JTextField() ;

			Object[] obj = {"Descpription : ", nomField, "Prix : ", prixField} ;

			int result = JOptionPane.showConfirmDialog(null, obj, "Nouvel Article",
					JOptionPane.OK_CANCEL_OPTION) ;

			if (result == JOptionPane.OK_OPTION) {
				String nom = nomField.getText();
				String prixStr = prixField.getText();

				float prix = Float.parseFloat(prixStr) ; // Vous pouvez ajouter une validation ici

				// Créez un nouvel article avec les informations fournies
				ajout = new Produit(nom, upc, 1, prix) ;

				// Ajoutez le nouvel article à la facture et mettez à jour le sous-total
				facture.add(ajout);
				sousTotal += prix;

				// Ajoutez la ligne au reçu
				this.docFacture.println(ajout.toStringClient()) ;
			}
		}

		nombreArticles++ ;

		return ajout;
	}

	public boolean quantité(int qte, String upc) {
		boolean trouvé = false ;
		int i = -1 ;

		while (!trouvé) {
			i++ ;
			if (tableauProduits[i].getUPC().equals(upc)) {
				getFacture().add(tableauProduits[i]) ;
				Inventaire.acheterProduit(tableauProduits[i].getUPC()) ;
				trouvé = true ;
			}
		}

		if (trouvé) {
			sousTotal += tableauProduits[i].getPrix() * qte ;
		} else {
			String description = JOptionPane.showInputDialog(null, 
					"Description".toUpperCase()) ;
			float prix = Float.parseFloat(JOptionPane.showInputDialog(null, 
					"Entrer montant.".toUpperCase())) ;
			while (upc.length() != 7) {
				upc = "0" + upc ;
			}
			getFacture().add(new Produit(description, upc, 1, prix * qte)) ;
			sousTotal += prix * qte ;
		}

		return trouvé ;
	}

	public Produit afficherProduit(int position) {
		return facture.get(position) ;
	}

	public boolean supprimerProduit(String upc) {
		boolean trouvé = false ;
		int i = -1 ;

		while (trouvé == false && !getFacture().isEmpty()) {
			i++ ;
			if (getFacture().get(i).getUPC().equals(upc)) {
				getFacture().remove(Inventaire.listeProduits.get(i)) ;
				trouvé = true ;
			}
		}
		return trouvé ;
	}

	public Produit supprimerDernier() {
		Produit dernier = facture.pop() ;
		this.sousTotal -= dernier.getPrix() ;

		return dernier ;
	}



	public boolean payer(float montantÀpayer, ModePaiement mDePaiement) throws IOException {
		switch (mDePaiement) {
		case Comptant :
			// Logique de paiement en espèces (comptant)
			this.total -= montantÀpayer ;
			this.totalPayé += montantÀpayer ;
			this.docFacture.append((mDePaiement + "").toUpperCase() + " : " + montantÀpayer + "$") ;
			break ;
		case Débit :
			// Logique de paiement par carte de débit
			// Par exemple, vérifier la carte, débiter le montant, etc.
			break ;
		case Crédit :
			// Logique de paiement par carte de crédit
			// Par exemple, vérifier la carte, autoriser la transaction, etc.
			break ;
		case Carte_cadeau :
			// Logique de paiement par carte-cadeau
			Object[] message = {"Code à barre : ", new JTextField()} ;
			
			CarteCadeau carte = new CarteCadeau() ;
			// Par exemple, vérifier la carte-cadeau, déduire le montant, etc.
			break ;
		}

		// Vous pouvez retourner vrai si le paiement a réussi, sinon faux
		// En fonction de la logique de paiement spécifique à chaque cas.

		return this.total >= 0.00 ;
	}

	public void imprimerFacture() throws IOException {
		if (this.total == 0.0) {
			this.docFacture.println("Merci !") ;
			this.docFacture.close() ;
			statut = false ;
		} 
	}
	public float getSousTotal() {
		return this.sousTotal ;
	}

	public void ajouterRéduction(float réduction) {
		Réduction.pileRéductions.add(new Réduction(réduction)) ;
	}

	public float évaluerTotal() {
		int i = 1 ;
		double prixTps = this.sousTotal * tps, prixTvq = this.sousTotal * tvq ;

		while (!Réduction.pileRéductions.isEmpty() && i < Réduction.pileRéductions.size()) {
			Réduction réduc = Réduction.pileRéductions.pop() ;
			this.sousTotal = this.sousTotal - réduc.getValRéduction() ;
			this.docFacture.println("Coupon : -" + réduc.getValRéduction() + "$") ;
			i++ ;
		}

		this.total = (float) (sousTotal + prixTps + prixTvq) ;
		this.total = (float) (Math.round(this.total * 100.0) / 100.0) ;
		int totalEntier = (int) this.sousTotal ;
		int totalPartiel = 10*((int) (this.sousTotal - totalEntier)) ;
		this.docFacture.println("Sous-total : " + totalEntier + "." + totalPartiel + "$") ;
		this.docFacture.println("TPS 5.0000% : " + Math.round((tps * this.sousTotal) 
				* 100.0) / 100.0 + "$") ;
		this.docFacture.println("TVQ 9.9750% : " + Math.round((tvq * this.sousTotal) 
				* 100.0) / 100.0 + "$") ;
		this.docFacture.println("Total : " + this.sousTotal * ((tps + tvq)/100) + "$") ;

		return this.total ;
	}

	public void conclureVente() {
		this.docFacture.close() ;
	}

	public String getNoTransaction() {
		return this.noTransaction ;
	}

	public boolean getStatut() {
		return this.statut ;
	}

	public int getNbArticles() {
		return facture.size() ;
	}

	public void annulerFacture() {
		this.statut = false ;

		this.docFacture.println("** Facture annulée **") ;
		this.docFacture.close() ;
	}

	public Stack<Produit> getFacture() {
		return facture ;
	}

	public enum ModePaiement {
		Comptant, Débit, Crédit, Carte_cadeau ;
	}
}

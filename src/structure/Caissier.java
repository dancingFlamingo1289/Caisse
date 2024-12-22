package structure;

import java.io.IOException;

import gestionCaisse.GestionCaisses;

public class Caissier extends Employé {
    private int idCaisse;
    private char[] motDePasse = new char[4];
    private long nbConnexions;

    public Caissier(String nom, String prénom) {
        super(nom, prénom) ;
        this.idCaisse = generateRandomIDCaisse() ;
        this.motDePasse = generateRandomPassword() ;
        this.nbConnexions = 0 ;
    }

    public Caissier(String nom, String prénom, float salaire) {
        super(nom, prénom) ;
        this.idCaisse = generateRandomIDCaisse() ;
        this.motDePasse = generateRandomPassword() ;
        this.nbConnexions = 0 ;
    }

    public Caissier(Gestionnaire g) {
        super(g.getNom(), g.getPrénom()) ;
        this.idCaisse = g.getIDCaisse() ;
        this.motDePasse = g.getMotDePasse() ;

        g = null ;
    }

    public int getIDCaisse() {
        return this.idCaisse;
    }

    public char[] getMotDePasse() {
        return this.motDePasse;
    }

    public long getNbConnexions() {
        return this.nbConnexions ;
    }
    
    public void incrementerNbConnexions() {
    	this.nbConnexions++ ;
    }

    public String toString() {
        return "Nom, prénom : " + super.getNom() + ", " + super.getPrénom() + "\n" +
                "Caissier/ière\t" + this.idCaisse + ", " + new String(this.motDePasse);
    }

    public boolean estCaissier(int id, char[] mDeP) {
        boolean trouvé = false;
        int i = 0;

        while (!trouvé && i < GestionCaisses.listeCaissiers.size()) {
            Caissier caissier = GestionCaisses.listeCaissiers.get(i);

            if (caissier.getIDCaisse() == id &&
                comparePasswords(caissier.getMotDePasse(), mDeP) == 1) {
                trouvé = true ;
            }

            i++;
        }

        return trouvé;
    }

    /**
     * ...
     * @param password1 : First password.
     * @param password2 : Second password.
     * @return <b>1</b> if the passwords are the same. <b>0</b> if the passwords are different.
     */
    private static int comparePasswords(char[] password1, char[] password2) {
        if (password1.length != password2.length) {
            return 0 ;
        }

        for (int i = 0; i < password1.length; i++) {
            if (password1[i] != password2[i]) {
                return 0 ;
            }
        }

        return 1 ;
    }

    public Caissier licencier(String nom, String prénom) throws IOException {
        boolean trouvé = false;
        int i = 0;
        Caissier àTrouver = null;

        while (!trouvé && i < GestionCaisses.listeCaissiers.size()) {
            Caissier caissier = GestionCaisses.listeCaissiers.get(i);

            if (caissier.getNom().equals(nom) && caissier.getPrénom().equals(prénom)) {
                àTrouver = caissier;

                if (àTrouver instanceof Gestionnaire) {
                    ((Gestionnaire) àTrouver).rétrograder(àTrouver.getNom(), àTrouver.getPrénom());
                } else {
                    GestionCaisses.listeCaissiers.remove(àTrouver);
                }

                écrireLettreDémission(nom);
                trouvé = true;
            }

            i++;
        }

        return àTrouver;
    }

    private int generateRandomIDCaisse() {
        return (int) (Math.random() * 10000);
    }

    private char[] generateRandomPassword() {
        char[] password = new char[4] ;
        
        for (int i = 0 ; i < password.length ; i++) {
            password[i] = (char) ('0' + (int) (Math.random() * 10)) ;
        }
        
        return password ;
    }
}

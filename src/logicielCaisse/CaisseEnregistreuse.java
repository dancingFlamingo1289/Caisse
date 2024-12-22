package logicielCaisse ;

import java.awt.EventQueue ;
import javax.swing.JFrame ;
import javax.swing.JOptionPane ;
import javax.swing.JPanel ;
import javax.swing.JTabbedPane;
import javax.swing.JTextField ;
import facture.Facture ;
import facture.Facture.ModePaiement;
import venteÉtalage.Produit ;
import java.awt.Font ;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton ;
import javax.swing.JColorChooser;
import javax.swing.AbstractAction ;
import java.awt.event.ActionEvent ;
import java.util.LinkedList;
import java.util.Stack ;
import javax.swing.Action ;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener ;
import java.io.FileWriter;
import java.io.IOException ;
import java.io.PrintWriter;
import javax.swing.JTextArea ;
import javax.swing.JLabel ;
import javax.swing.JInternalFrame ;
import javax.swing.JLayeredPane ;
import javax.swing.SwingConstants ;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.w3c.dom.Node;
import java.awt.Window.Type ;
import java.awt.Toolkit ;
import java.awt.SystemColor ;
import java.awt.Color ;
import javax.swing.JPasswordField ;
import javax.swing.JRadioButton;
import javax.swing.JTextPane ;
import javax.swing.JEditorPane ;
import javax.swing.JScrollPane ;
import java.awt.event.KeyAdapter ;
import java.awt.event.KeyEvent ;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JTable ;
import structure.* ;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import java.awt.Button;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class CaisseEnregistreuse extends JFrame {
	private Stack<String> pileÉcran = new Stack<String>() ;
	private Stack<String> pileID = new Stack<String>(),
			pilePIN = new Stack<String>() ;
	private JFrame frmCaisse ;
	private Stack<Produit> listeFacture ;
	private static boolean connecté = false ;
	private static Facture facture ;
	private final Produit[] listeProduits = {new Produit("Montre", "1111111", 12, 123.99f), 
			new Produit("Chaise", "1234567", 15, 59.05f)} ;
	private JTextField txtID ;
	private JPasswordField passwordField ;
	private JEditorPane opérationsPerso ;
	private JTextField txtTotal ;
	private LinkedList<structure.Caissier> listeCaissiers = 
			new LinkedList<structure.Caissier>() ;
	private LinkedList<structure.Gestionnaire> listeGestionnaires = 
			new LinkedList<structure.Gestionnaire>() ;
	private LinkedList<Facture> listeFactures = new LinkedList<Facture>() ;
	private JTextArea txtaFactureFinale, txtaEvolutionFacture ;
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JButton btnSauvegarder;
	private JSpinner spnSalaire;
	private JPanel panAjoutCaissier;
	private JButton btnAjoutCaissier;
	private JTextField ecran2;
	private JPanel numPad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaisseEnregistreuse window = new CaisseEnregistreuse() ;
					window.frmCaisse.setVisible(true) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}) ;
	}

	/**
	 * Create the application.
	 */
	public CaisseEnregistreuse() {
		initialize() ;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setForeground(SystemColor.controlHighlight);
		setBackground(SystemColor.controlHighlight);
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/elias/Desktop/Workspace_v2/Caisse/Liste de produits/Walmart-Logo.png"));
		setType(Type.POPUP);
		setTitle("Caisse enregistreuse");
		setBounds(100, 100, 858, 625) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 852, 597);
		tabbedPane.setBackground(SystemColor.textHighlight);
		tabbedPane.setForeground(SystemColor.controlHighlight);
		getContentPane().add(tabbedPane);

		// Créer un panel pour l'onglet "Accueil"
		JPanel accueilPanel = new JPanel();
		accueilPanel.setForeground(Color.WHITE);
		accueilPanel.setBackground(SystemColor.controlHighlight);
		accueilPanel.setLayout(null);
		JLabel label_1 = new JLabel("Bienvenue sur la page d'accueil !");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(458, 5, 203, 16);
		accueilPanel.add(label_1);
		tabbedPane.addTab("Accueil", accueilPanel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(6, 27, 446, 518);
		accueilPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID caisse :");
		lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 343, 144, 16);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("PIN :");
		lblNewLabel_2.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(20, 371, 144, 16);
		panel_2.add(lblNewLabel_2);

		txtID = new JTextField();
		txtID.setFont(new Font("Courier New", Font.PLAIN, 17));
		txtID.setHorizontalAlignment(SwingConstants.RIGHT);
		txtID.setBounds(176, 339, 252, 26);
		panel_2.add(txtID);
		txtID.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Courier New", Font.PLAIN, 17));
		passwordField.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordField.setBounds(176, 367, 252, 26);
		panel_2.add(passwordField);

		JLabel lblErreur = new JLabel("");
		lblErreur.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreur.setForeground(Color.RED);
		lblErreur.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblErreur.setBounds(20, 399, 408, 113);
		panel_2.add(lblErreur);

		JPanel numPadAccueil = numPad ;
		numPadAccueil.setBackground(new Color(30, 144, 255));
		numPadAccueil.setLayout(null);
		numPadAccueil.setBounds(459, 84, 366, 378);
		accueilPanel.add(numPadAccueil);

		JButton btnOne = new JButton("1");
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "1" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnOne.setBounds(0, 6, 83, 83);
		numPadAccueil.add(btnOne);

		JButton btnTwo = new JButton("2");
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "2" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnTwo.setBounds(95, 6, 83, 83);
		numPadAccueil.add(btnTwo);

		JButton btnThree = new JButton("3");
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "3" ;
				if (txtID.getText().length() < 4) {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else if (passwordField.getPassword().length < 4) {
					passwordField.setText(passwordField.getText() + ajout) ;
					pilePIN.push(ajout) ;
				}
			}
		});
		btnThree.setBounds(184, 6, 83, 83);
		numPadAccueil.add(btnThree);

		JButton btnFour = new JButton("4");
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "4" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnFour.setBounds(0, 101, 83, 83);
		numPadAccueil.add(btnFour);

		JButton btnFive = new JButton("5");
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "5" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnFive.setBounds(95, 101, 83, 83);
		numPadAccueil.add(btnFive);

		JButton btnSix = new JButton("6");
		btnSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "6" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnSix.setBounds(184, 101, 83, 83);
		numPadAccueil.add(btnSix);

		JButton btnSeven = new JButton("7");
		btnSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "7" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnSeven.setBounds(0, 196, 83, 83);
		numPadAccueil.add(btnSeven);

		JButton btnEight = new JButton("8");
		btnEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "8" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnEight.setBounds(95, 196, 83, 83);
		numPadAccueil.add(btnEight);

		JButton btnNine = new JButton("9");
		btnNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "9" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnNine.setBounds(184, 196, 83, 83);
		numPadAccueil.add(btnNine);

		JButton btnZero = new JButton("0");
		btnZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "0" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push(ajout) ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push(ajout) ;
					}
				}
			}
		});
		btnZero.setBounds(0, 291, 178, 83);
		numPadAccueil.add(btnZero);

		JButton btnDoubleZero = new JButton("00");
		btnDoubleZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajout = "00" ;
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText(txtID.getText() + ajout) ;
					pileID.push("0") ;
					pileID.push("0") ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText(passwordField.getText() + ajout) ;
						pilePIN.push("0") ;
						pilePIN.push("0") ;
					}
				}
			}
		});
		btnDoubleZero.setBounds(184, 291, 83, 83);
		numPadAccueil.add(btnDoubleZero);

		JButton btnDeletePrevious = new JButton("⬅") ;
		btnDeletePrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtID.getText().length() <= 4) {
					pileID.pop() ;
					txtID.setText("") ;
					for (int i = 0 ; i < pileID.size() ; i++)
						txtID.setText(txtID.getText() + pileID.get(i)) ;
				} else if (passwordField.getPassword().length < 4) {
					pilePIN.pop() ;
					passwordField.setText("") ;
					for (int i = 0 ; i < pileID.size() ; i++)
						passwordField.setText(passwordField.getText() + pilePIN.get(i)) ;
				}
			}
		});
		btnDeletePrevious.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnDeletePrevious.setBounds(279, 100, 81, 83);
		numPadAccueil.add(btnDeletePrevious) ;

		JButton btnEnter = new JButton("Entrer");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;

				while (!connecté && i < listeCaissiers.size()) {
					if (Integer.parseInt(txtID.getText()) == listeCaissiers.get(i).getIDCaisse() &&
							passwordField.getPassword().equals(listeCaissiers.get(i).getMotDePasse())) {
						connecté = true ;

						txtaEvolutionFacture.append("** Connexion " + 
								listeCaissiers.get(i).getNom() + ", " + 
								listeCaissiers.get(i).getPrénom() + " **\n") ;

						txtID.setText("") ;
						passwordField.setText("") ;
						pilePIN.clear() ;
						pileID.clear() ;
						frmCaisse.repaint() ;
					}

					if (connecté == true) {
						lblErreur.setForeground(Color.GREEN);
						lblErreur.setText("Accès accordé. Bienvenue " + 
								listeCaissiers.get(i).getPrénom() + " !") ;
					} else {
						lblErreur.setForeground(Color.RED);
						lblErreur.setText("⚠ Il y a une erreur. ⚠") ;
					}
				}
			}
		}) ;
		btnEnter.setBounds(279, 196, 81, 178);
		numPadAccueil.add(btnEnter);

		JButton btnDelete = new JButton("DEL");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtID.getText().length() != 4 && txtID.getText() != "") {
					txtID.setText("") ;
				} else {
					if (passwordField.getText().length() != 4 && passwordField.getText() != "") {
						passwordField.setText("") ;
					}
				}
			}
		});
		btnDelete.setBounds(279, 6, 81, 83);
		numPadAccueil.add(btnDelete);

		if (connecté) {
			// Créer un panel pour l'onglet "Caisse enregistreuse"
			JPanel caissePanel = new JPanel() ;
			caissePanel.setBackground(SystemColor.controlHighlight);
			caissePanel.setLayout(null);
			tabbedPane.add("Caisse enregistreuse", caissePanel) ;

			JPanel panel = new JPanel();
			panel.setBackground(new Color(30, 144, 255));
			panel.setLayout(null);
			panel.setBounds(6, 77, 819, 474);
			caissePanel.add(panel);

			numPad = new JPanel();
			numPad.setBackground(new Color(30, 144, 255));
			numPad.setLayout(null);
			numPad.setBounds(236, 6, 267, 461);
			panel.add(numPad);

			JButton button1 = new JButton("1");
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button1, ecran2) ;
					pileÉcran.add("1") ;
				}
			});
			button1.setBounds(0, 89, 83, 83);
			numPad.add(button1);

			JButton button2 = new JButton("2");
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button2, ecran2) ;
					pileÉcran.add("2") ;
				}
			});
			button2.setBounds(95, 89, 83, 83);
			numPad.add(button2);

			JButton button3 = new JButton("3");
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button3, ecran2) ;
					pileÉcran.add("3") ;
				}
			});
			button3.setBounds(184, 89, 83, 83);
			numPad.add(button3);

			JButton button4 = new JButton("4");
			button4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button4, ecran2) ;
					pileÉcran.add("4") ;
				}
			});
			button4.setBounds(0, 184, 83, 83) ;
			numPad.add(button4) ;

			JButton button5 = new JButton("5") ;
			button5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button5, ecran2) ;
					pileÉcran.add("5") ;
				}
			});
			button5.setBounds(95, 184, 83, 83);
			numPad.add(button5);

			JButton button6 = new JButton("6");
			button6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button6, ecran2) ;
					pileÉcran.add("6") ;
				}
			});
			button6.setBounds(184, 184, 83, 83);
			numPad.add(button6);

			JButton button7 = new JButton("7");
			button7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button7, ecran2) ;
					pileÉcran.add("7") ;
				}
			});
			button7.setBounds(0, 279, 83, 83);
			numPad.add(button7);

			JButton button8 = new JButton("8");
			button8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button8, ecran2) ;
					pileÉcran.add("8") ;
				}
			});
			button8.setBounds(95, 279, 83, 83);
			numPad.add(button8);

			JButton button9 = new JButton("9");
			button9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button9, ecran2) ;
					pileÉcran.add("9") ;
				}
			});
			button9.setBounds(184, 279, 83, 83);
			numPad.add(button9) ;

			JButton buttonPoint = new JButton(".");
			buttonPoint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(buttonPoint, ecran2) ;
					pileÉcran.add(".") ;
				}
			});
			buttonPoint.setBounds(0, 374, 83, 83);
			numPad.add(buttonPoint) ;

			JButton button0 = new JButton("0");
			button0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button0, ecran2) ;
					pileÉcran.add("0") ;
				}
			});
			button0.setBounds(95, 374, 83, 83);
			numPad.add(button0);

			JButton button00 = new JButton("00");
			button00.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clavier.keyButtonPressed(button4, ecran2) ;
					pileÉcran.add("0") ;
					pileÉcran.add("0") ;
				}
			});
			button00.setBounds(184, 374, 83, 83);
			numPad.add(button00);

			JButton buttonDel = new JButton("DEL");
			buttonDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ecran2.setText("") ;
				}
			});
			buttonDel.setBounds(48, 0, 83, 83);
			numPad.add(buttonDel);

			JButton btnQte = new JButton("QTE");
			btnQte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int qté = Integer.parseInt(ecran2.getText()) ;

					if (connecté && facture != null) {

					}
				}
			});
			btnQte.setBounds(143, 0, 83, 83);
			numPad.add(btnQte);

			JButton connexion = new JButton("Déconnexion");
			connexion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (facture == null) {
						connecté = false ;
						JOptionPane.showMessageDialog(null, "Déconnexion");
						txtID.setText("");
						passwordField.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Il faut d'abord terminer la "
								+ "présente transaction.") ;
					}
				}
			});
			connexion.setBounds(6, 6, 103, 83);
			panel.add(connexion);

			JButton nouvelleFacture = new JButton("Commencer");
			nouvelleFacture.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (connecté == true) {
						try {
							facture = new Facture() ;
						} catch (IOException e1) {
							e1.printStackTrace() ;
						}
						listeFacture = new Stack<Produit>() ;
						ecran2.setText("") ;
						txtaEvolutionFacture.append("Facture" + facture.getNoTransaction() + "\n");
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez vous connecter pour créer "
								+ "une facture.") ;
					}
				}
			});
			nouvelleFacture.setBounds(121, 289, 103, 83);
			panel.add(nouvelleFacture);

			JButton corriger = new JButton("Corriger");
			corriger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (connecté == true) {
						Produit dernier = facture.supprimerDernier() ;
						txtaEvolutionFacture.append("\t**Entrée annulée**\n" + 
								dernier.toStringÀSupprimer() + "\n") ;
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez vous connecter.") ;
					}
				}
			});
			corriger.setBounds(121, 99, 103, 83);
			panel.add(corriger);

			JButton escompte = new JButton("Escompte");
			escompte.setBounds(121, 6, 103, 83);
			panel.add(escompte);

			JButton CodeAction = new JButton("Code Action");
			CodeAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int codeAction = Integer.parseInt(ecran2.getText()) ;

					switch (codeAction) {
					case 1 :
						break ;
					case 2 :
						JOptionPane.showMessageDialog(null, "Malheureusement, cette fonction"
								+ " n'a pas encore été implémentée.") ;
						break ;
					case 3 :
						String idSuperviseur = JOptionPane.showInputDialog(null, 
								"ID superviseur".toUpperCase()),
						mDePsuperviseur = JOptionPane.showInputDialog(null, 
								"Mot de passe superviseur".toUpperCase()) ;

						if (idSuperviseur.length() == mDePsuperviseur.length()) {
							JOptionPane.showMessageDialog(null, "Bob veut du fromage.") ;
						}
						break ;
					case 4 :
						break ;
					case 6 :
						break ;
					case 7 :
						break ;
					case 9 :
						break ;
					case 10 :
						PrintWriter doc ;
						try {
							doc = new PrintWriter(new FileWriter("Rendement du caissier")) ;
							doc.println("**** Rendement du caissier ****".toUpperCase()) ;
							doc.println(JOptionPane.showInputDialog(null, "Nom ?".toUpperCase().toUpperCase())) ;
							doc.println("Lectures optiques par heures \t".toUpperCase() + 2) ;
							doc.println("Pourcentage Lu \t".toUpperCase()) ;
							JOptionPane.showMessageDialog(null, "Succès !") ;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break ;
					case 13 :
						break ;
					case 14 :
						break ;
					default :
						JOptionPane.showMessageDialog(null, "Malheureusement, cette fonction"
								+ " n'a pas encore été implémentée.") ;
					}
				}
			});
			CodeAction.setBounds(121, 194, 103, 83);
			panel.add(CodeAction);

			JButton coupon = new JButton("Coupon");
			coupon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					float réduction = Float.parseFloat(ecran2.getText()) ;

					txtaEvolutionFacture.append("Réduction : -" + réduction + "$\n");

					facture.ajouterRéduction(réduction) ;
				}
			});
			coupon.setBounds(6, 99, 103, 83);
			panel.add(coupon);

			JButton buttonDel_4_3 = new JButton("Annuler");
			buttonDel_4_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			buttonDel_4_3.setBounds(6, 194, 103, 83);
			panel.add(buttonDel_4_3);

			JButton buttonEntrer = new JButton("Entrer");
			buttonEntrer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Produit àTrouver = null ;

					if (connecté && facture != null) {
						if (ecran2.getText().equals("")) {
							Produit p = facture.getFacture().peek() ;

							txtaEvolutionFacture.append(p.toStringClient() + "\n");

							facture.ajouterProduit(p.getUPC()) ;
						} else {
							String upc = ecran2.getText(), ajout = "" ;

							àTrouver = facture.ajouterProduit(upc) ;
							ajout = àTrouver.toStringClient() ;

							txtaEvolutionFacture.append(ajout + "\n") ;
							ecran2.setText("") ;
						}
					}

					if (facture == null) {
						JOptionPane.showMessageDialog(null, "Il faut d'abord commencer une facture.") ;
					} 
					if (connecté == false) {
						JOptionPane.showMessageDialog(null, "Il faut d'abord se connecter.") ;
					}
				}
			}) ;
			buttonEntrer.setBounds(6, 289, 103, 177);
			panel.add(buttonEntrer);

			JButton pasDeVente = new JButton("Voir article");
			pasDeVente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Produit àTrouver = null ;
					String ajout = "" ;

					if (connecté == true) {
						String upc = ecran2.getText() ;
						boolean trouvé = false ;

						for (int i = 0 ; i < Facture.tableauProduits.length ; i++) { 
							àTrouver = Facture.tableauProduits[i] ;
							if (àTrouver.getUPC().equals(Facture.tableauProduits[i].getUPC())) {
								trouvé = true ;
								ajout = àTrouver.toStringClient() ;
								break ;
							}
						}

						if (trouvé == false) {
							ajout = "Erreur404. Produit inconnu.".toUpperCase() ;
						}

						ecran2.setText(ajout) ;
					}
				}
			});
			pasDeVente.setBounds(121, 384, 103, 83);
			panel.add(pasDeVente);

			JScrollPane spEvolutionFacture = new JScrollPane();
			spEvolutionFacture.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			spEvolutionFacture.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spEvolutionFacture.setBounds(515, 6, 298, 462);
			panel.add(spEvolutionFacture);

			ecran2 = new JTextField();
			ecran2.setBounds(6, 31, 819, 41);
			caissePanel.add(ecran2);
			ecran2.setColumns(10);

			JLabel lblNewLabel = new JLabel("Entrer un article.");
			lblNewLabel.setForeground(UIManager.getColor("desktop"));
			lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 27));
			lblNewLabel.setBounds(12, 6, 820, 23);
			caissePanel.add(lblNewLabel);

			// Créer un panel pour l'onglet "Total"
			JPanel totalPanel = new JPanel();
			totalPanel.setBackground(SystemColor.controlHighlight);
			totalPanel.setLayout(null);
			JLabel label = new JLabel("Ceci est la page de paiement.");
			label.setBounds(327, 5, 184, 16);
			label.setForeground(Color.WHITE);
			totalPanel.add(label);
			tabbedPane.addTab("Total", totalPanel) ;

			txtTotal = new JTextField();
			txtTotal.setBounds(6, 33, 645, 64);
			txtTotal.setText("Total :");
			txtTotal.setHorizontalAlignment(SwingConstants.LEFT);
			txtTotal.setForeground(Color.BLACK);
			txtTotal.setFont(new Font("Courier New", Font.PLAIN, 27));
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
			totalPanel.add(txtTotal) ;

			JButton buttonDel_5_4_1 = new JButton("Total");
			buttonDel_5_4_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (connecté == true) {
						float total = facture.évaluerTotal() ;

						float sousTotal = (float) facture.getSousTotal() ;

						txtaEvolutionFacture.append("Sous-total : " + sousTotal + "$\n" + 
								"TPS 5.0000% : " + Math.round((Facture.tps * sousTotal) 
										* 100.0) / 100.0 + "$\n" + "TVQ 9.9750% : " + 
										Math.round((Facture.tvq * sousTotal) * 100.0) +"$\n" + 
										"Total : " + facture.évaluerTotal() + "$\n") ;

						for (int i = 0 ; i < facture.taille() ; i++) {
							txtaFactureFinale.append(facture.afficherProduit(i).toStringClient() + "\n") ;
						}
						txtaFactureFinale.append("Sous-total : " + facture.getSousTotal() + "$\n" + 
								"Total : " + total + "$\n") ;

						txtTotal.setText("Total : " + total + "$") ;
					}
				}
			});
			buttonDel_5_4_1.setBounds(656, 34, 169, 69);
			totalPanel.add(buttonDel_5_4_1);

			JPanel panel_5 = new JPanel();
			panel_5.setBackground(SystemColor.controlHighlight);
			panel_5.setBounds(6, 109, 826, 436);
			totalPanel.add(panel_5);
			panel_5.setLayout(null);

			JScrollPane spFactureFinale = new JScrollPane();
			spFactureFinale.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			spFactureFinale.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spFactureFinale.setBounds(0, 0, 379, 436);
			panel_5.add(spFactureFinale);

			txtaFactureFinale = new JTextArea();
			txtaFactureFinale.setLineWrap(true);
			txtaFactureFinale.setWrapStyleWord(true);
			spFactureFinale.setViewportView(txtaFactureFinale);

			JButton btnComptant = new JButton("Comptant");
			btnComptant.setBounds(391, 0, 103, 83);
			panel_5.add(btnComptant);

			JButton btnCarteCadeau = new JButton("Carte cadeau");
			btnCarteCadeau.setBounds(506, 0, 103, 83);
			panel_5.add(btnCarteCadeau);

			JButton btnChèque = new JButton("Chèque");
			btnChèque.setBounds(506, 93, 103, 83);
			panel_5.add(btnChèque);

			JButton btnDébitCrédit = new JButton("Débit/Crédit");
			btnDébitCrédit.setBounds(391, 93, 103, 83);
			panel_5.add(btnDébitCrédit);

			JButton btnNewButton = new JButton("New button");
			btnNewButton.setBounds(391, 278, 103, 83);
			panel_5.add(btnNewButton);

			JButton btnNewButton_1 = new JButton("New button");
			btnNewButton_1.setBounds(506, 278, 103, 83);
			panel_5.add(btnNewButton_1);

			JButton btnNewButton_ = new JButton("Diviser paiements");
			btnNewButton_.setBounds(391, 185, 103, 83);
			panel_5.add(btnNewButton_);

			JButton btnNewButton_1_1 = new JButton("Imprimer");
			btnNewButton_1_1.setBounds(506, 185, 103, 83);
			panel_5.add(btnNewButton_1_1);

			JButton btnConclureVente = new JButton("Conclure la vente");
			btnConclureVente.setBounds(391, 362, 218, 69);
			panel_5.add(btnConclureVente);

			// Créer un panel pour l'onglet "Gestion de la caisse"
			JPanel gestionPanel = new JPanel();
			gestionPanel.setBackground(SystemColor.controlHighlight);
			gestionPanel.setLayout(null);
			JLabel label1 = new JLabel("Ceci est la page de gestion de la caisse.");
			label1.setBounds(287, 6, 257, 16);
			label1.setForeground(Color.WHITE);
			gestionPanel.add(label1);
			tabbedPane.addTab("Gestion de la caisse", gestionPanel);

			JPanel panel_3 = new JPanel();
			panel_3.setBounds(6, 24, 597, 521);
			gestionPanel.add(panel_3);
			panel_3.setLayout(null);

			JLabel lblNewLabel_3 = new JLabel("Caissiers");
			lblNewLabel_3.setBounds(6, 6, 162, 16);
			panel_3.add(lblNewLabel_3) ;

			JButton btnSupprCaissier = new JButton("Licencier un caissier");
			btnSupprCaissier.setBounds(6, 86, 162, 45);
			btnSupprCaissier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField txtPrénom = new JTextField(), txtNom = new JTextField() ;
					Object[] obj = {"Prénom : ", txtPrénom, "Nom :", txtNom} ;
					int option = JOptionPane.showConfirmDialog(null, obj, "Licencier un caissier", JOptionPane.OK_CANCEL_OPTION) ;
					int i ;

					if (option == JOptionPane.OK_OPTION) {
						String prénom = txtPrénom.getText(), nom = txtNom.getText() ;

						boolean trouvé = false ;
						Caissier àTrouver = null ;
						for (i = 0 ; i < listeCaissiers.size(); i++) {
							if (prénom.equals(listeCaissiers.get(i).getPrénom()) && nom.equals(listeCaissiers.get(i).getNom())) {
								àTrouver = listeCaissiers.get(i) ;
								try {
									àTrouver.licencier(nom, prénom) ;
									Document doc = opérationsPerso.getDocument() ;
									doc.insertString(doc.getLength(), "Caissier retiré\n" + àTrouver.toString() + "\n", null) ;
									listeCaissiers.remove(àTrouver) ;
								} catch (Exception e1) {
									e1.printStackTrace() ;
								}
							}
						}

						if (listeCaissiers.isEmpty()) {
							Document doc = opérationsPerso.getDocument() ;
							try {
								doc.insertString(doc.getLength(), "Liste des caIssiers vide\n", null) ;
							} catch (Exception e1) {
								e1.printStackTrace() ;
							}
						} else {
							if (àTrouver == null) {
								try {
									Document doc = opérationsPerso.getDocument() ;
									doc.insertString(doc.getLength(), "Caissier inconnu\n" + txtPrénom.getText() + ", " + txtNom.getText() + "\n", null) ;
								} catch (Exception e1) {
									e1.printStackTrace() ;
								}
							} 
						}
					}
				}
			});
			panel_3.add(btnSupprCaissier);

			JButton btnNewButton_2_2 = new JButton("Promouvoir un caissier");
			btnNewButton_2_2.setBounds(6, 164, 162, 45);
			btnNewButton_2_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField txtPrénom = new JTextField(), txtNom = new JTextField(),
							txtSalaire = new JTextField() ;
					Object[] obj = {"Prénom : ", txtPrénom, "Nom :", txtNom, 
							"Salaire : ", txtSalaire} ;
					int option = JOptionPane.showConfirmDialog(null, obj, "Promouvoir un caissier", JOptionPane.OK_CANCEL_OPTION) ;

					if (option == JOptionPane.OK_OPTION) {
						String prénom = txtPrénom.getText(), nom = txtNom.getText() ;

						boolean trouvé = false ;
						Caissier àPromouvoir ;
						Gestionnaire promu ;

						for (int i = 0 ; i < listeCaissiers.size(); i++) {
							if (prénom.equals(listeCaissiers.get(i).getPrénom()) && nom.equals(listeCaissiers.get(i).getNom())) {
								àPromouvoir = listeCaissiers.get(i) ;
								try {
									if (txtSalaire.getText().equals("")) {
										promu = new Gestionnaire(àPromouvoir) ;
									} else {
										promu = new Gestionnaire(àPromouvoir, Float.parseFloat(txtSalaire.getText())) ;
									}

									Document doc = opérationsPerso.getDocument() ;
									doc.insertString(doc.getLength(), "Caissier promu\n" + promu.toString() + "\n", null) ;
									listeCaissiers.remove(àPromouvoir) ;
									listeCaissiers.add(promu) ;
									listeGestionnaires.add(promu) ;
								} catch (Exception e1) {
									e1.printStackTrace() ;
								}
							}
						}
					}
				}
			});

			JLabel lblNewLabel_4 = new JLabel("Gestionnaire");
			lblNewLabel_4.setBounds(6, 143, 162, 16);
			panel_3.add(lblNewLabel_4);
			panel_3.add(btnNewButton_2_2);

			JButton btnNewButton_2_2_1 = new JButton("Rétrograder") ;
			btnNewButton_2_2_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField txtPrénom = new JTextField(), txtNom = new JTextField(),
							txtSalaire = new JTextField() ;
					Object[] obj = {"Prénom : ", txtPrénom, "Nom :", txtNom, 
							"Salaire : ", txtSalaire} ;
					int option = JOptionPane.showConfirmDialog(null, obj, "Promouvoir un caissier", JOptionPane.OK_CANCEL_OPTION) ;

					if (option == JOptionPane.OK_OPTION) {
						String prénom = txtPrénom.getText(), nom = txtNom.getText() ;

						boolean trouvé = false ;
						Gestionnaire àRétrograder ;
						Caissier rétrogradé ;

						for (int i = 0 ; i < listeGestionnaires.size(); i++) {
							if (prénom.equals(listeGestionnaires.get(i).getPrénom()) && nom.equals(listeGestionnaires.get(i).getNom())) {
								àRétrograder = listeGestionnaires.get(i) ;
								try {
									if (txtSalaire.getText().equals("")) {
										rétrogradé = new Caissier(àRétrograder) ;
									} /*else {
									rétrogradé = new Caissier(àRétrograder, Float.parseFloat(txtSalaire.getText())) ;
								}*/

									Document doc = opérationsPerso.getDocument() ;
									doc.insertString(doc.getLength(), "Caissier promu\n" + promu.toString() + "\n", null) ;
									listeGestionnaires.remove(àRétrograder) ;
									listeCaissiers.add(rétrogradé) ;
								} catch (Exception e1) {
									e1.printStackTrace() ;
								}
							}
						}
					}
				}
			});
			btnNewButton_2_2_1.setBounds(6, 221, 162, 45);
			panel_3.add(btnNewButton_2_2_1);

			JButton btnNewButton_2_2_2 = new JButton("Licencier");
			btnNewButton_2_2_2.setBounds(6, 278, 162, 45);
			panel_3.add(btnNewButton_2_2_2);

			JLabel lblNewLabel_5 = new JLabel("Gérants de la plateforme");
			lblNewLabel_5.setBounds(6, 328, 162, 16);
			panel_3.add(lblNewLabel_5);

			JButton btnNewButton_2_2_3 = new JButton("Promouvoir un caissier");
			btnNewButton_2_2_3.setBounds(6, 356, 162, 45);
			panel_3.add(btnNewButton_2_2_3);

			JButton btnNewButton_2_2_1_1 = new JButton("Rétrograder");
			btnNewButton_2_2_1_1.setBounds(6, 413, 162, 45);
			panel_3.add(btnNewButton_2_2_1_1);

			JButton btnNewButton_2_2_2_1 = new JButton("Licencier");
			btnNewButton_2_2_2_1.setBounds(6, 470, 162, 45);
			panel_3.add(btnNewButton_2_2_2_1);

			btnAjoutCaissier = new JButton("Ajouter un caissier");
			btnAjoutCaissier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panAjoutCaissier.setVisible(true) ;
				}
			});
			btnAjoutCaissier.setBounds(6, 34, 162, 45);
			panel_3.add(btnAjoutCaissier);

			panAjoutCaissier = new JPanel();
			panAjoutCaissier.setBounds(180, 6, 210, 146);
			panel_3.add(panAjoutCaissier);
			panAjoutCaissier.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ajouter un caissier", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panAjoutCaissier.setLayout(null);
			panAjoutCaissier.setVisible(false) ;

			JLabel lblPrenom = new JLabel("Prénom :");
			lblPrenom.setBounds(6, 23, 61, 16);
			panAjoutCaissier.add(lblPrenom);

			txtPrenom = new JTextField();
			txtPrenom.setBounds(79, 18, 125, 26);
			panAjoutCaissier.add(txtPrenom);
			txtPrenom.setColumns(10);

			JLabel lblNom = new JLabel("Nom :");
			lblNom.setBounds(6, 56, 61, 16);
			panAjoutCaissier.add(lblNom);

			txtNom = new JTextField();
			txtNom.setBounds(79, 51, 125, 26);
			panAjoutCaissier.add(txtNom);
			txtNom.setColumns(10);

			JLabel lblPrenom_1 = new JLabel("Salaire :");
			lblPrenom_1.setBounds(6, 89, 61, 16);
			panAjoutCaissier.add(lblPrenom_1);

			spnSalaire = new JSpinner();
			spnSalaire.setBounds(79, 84, 125, 26);
			spnSalaire.setModel(new SpinnerNumberModel(0.00, 0.00, 1000.0, 0.25)) ;
			panAjoutCaissier.add(spnSalaire);

			btnSauvegarder = new JButton("Sauvegarder");
			btnSauvegarder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Caissier nouvCaissier ;

					if (spnSalaire.getValue().equals(0)) {
						nouvCaissier = new Caissier(txtPrenom.getText(), txtNom.getText()) ;
					} else {
						nouvCaissier = new Caissier(txtPrenom.getText(), txtNom.getText(), (int) spnSalaire.getValue()) ;
					}
					listeCaissiers.add(nouvCaissier) ;

					btnSauvegarder.setVisible(false) ;
				}
			});
			btnSauvegarder.setBounds(49, 111, 117, 29);
			panAjoutCaissier.add(btnSauvegarder);

			JScrollPane spOperationsPersos = new JScrollPane();
			spOperationsPersos.setBounds(615, 34, 210, 505);
			gestionPanel.add(spOperationsPersos);
			spOperationsPersos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			spOperationsPersos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			opérationsPerso = new JEditorPane();
			spOperationsPersos.setViewportView(opérationsPerso);

			JPanel panParametres = new JPanel();
			tabbedPane.addTab("Paramètres", null, panParametres, null);
			panParametres.setLayout(null);

			JPanel panel_6 = new JPanel();
			panel_6.setBorder(new TitledBorder(null, "Modifier le th\u00E8me", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_6.setBounds(6, 6, 293, 230);
			panParametres.add(panel_6);

			JPanel panel_6_1 = new JPanel();
			panel_6_1.setBorder(new TitledBorder(null, "Modifier le th\u00E8me", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_6_1.setBounds(6, 248, 293, 230);
			panParametres.add(panel_6_1);

			JPanel panModifyBillHeading = new JPanel();
			panModifyBillHeading.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Modifier l'ent\u00EAte de la facture", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panModifyBillHeading.setBounds(311, 6, 293, 230);
			panParametres.add(panModifyBillHeading);
			panModifyBillHeading.setLayout(null);

			JScrollPane spBillHeading = new JScrollPane();
			spBillHeading.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			spBillHeading.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			spBillHeading.setBounds(6, 22, 281, 159);
			panModifyBillHeading.add(spBillHeading);

			JTextArea txtaBillHeading = new JTextArea();
			spBillHeading.setViewportView(txtaBillHeading);

			JButton btnSauvegarderMEnP = new JButton("Sauvegarder");
			btnSauvegarderMEnP.setBounds(6, 193, 117, 29);
			panModifyBillHeading.add(btnSauvegarderMEnP);

			JButton btnCacherModMEnS = new JButton("Cacher");
			btnCacherModMEnS.setBounds(170, 193, 117, 29);
			panModifyBillHeading.add(btnCacherModMEnS);

			JPanel panel_6_3 = new JPanel();
			panel_6_3.setBorder(new TitledBorder(null, "Modifier le th\u00E8me", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_6_3.setBounds(616, 6, 209, 230);
			panParametres.add(panel_6_3);

			// Créer un panel pour l'onglet "Paramètres"
			JPanel parametresPanel = new JPanel();
			parametresPanel.setBackground(SystemColor.controlHighlight);
			GridBagLayout gbl_parametresPanel = new GridBagLayout();
			gbl_parametresPanel.columnWidths = new int[]{1107, 0};
			gbl_parametresPanel.rowHeights = new int[]{16, 512, 0};
			gbl_parametresPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_parametresPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			parametresPanel.setLayout(gbl_parametresPanel);
			JLabel label2 = new JLabel("Ceci est la page des paramètres.");
			label2.setForeground(Color.WHITE);
			GridBagConstraints gbc_label2 = new GridBagConstraints();
			gbc_label2.anchor = GridBagConstraints.NORTH;
			gbc_label2.insets = new Insets(0, 0, 5, 0);
			gbc_label2.gridx = 0;
			gbc_label2.gridy = 0;
			parametresPanel.add(label2, gbc_label2);

			JPanel panel_4 = new JPanel();
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_4.gridx = 0;
			gbc_panel_4.gridy = 1;
			parametresPanel.add(panel_4, gbc_panel_4);

			JButton btnNewButton_2 = new JButton("Modifier la couleur");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color couleurActuelle = frmCaisse.getContentPane().getBackground();
					Color nouvelleCouleur = JColorChooser.showDialog(frmCaisse, "Choisir une couleur", couleurActuelle);

					if (nouvelleCouleur != null) {
						frmCaisse.getContentPane().setBackground(nouvelleCouleur);
					}
				}
			});

			JButton btnNewButton_6 = new JButton("Changer de thème");
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object[] objet = {new JButton("Jambon")} ;
					int choix = JOptionPane.showInternalConfirmDialog(null, objet, 
							null, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE) ;
				}
			});

			JButton btnNewButton_3 = new JButton("Changer la langue");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] langues = {"Français", "English", "Espanol", "Deutsch"} ;
					JComboBox comboBoxLangues = new JComboBox(langues) ;

					int choix = JOptionPane.showConfirmDialog(null, comboBoxLangues, "Changer la langue", 
							JOptionPane.OK_CANCEL_OPTION) ;

					if (choix == JOptionPane.OK_OPTION) {
						String langueChoisie = (String) comboBoxLangues.getSelectedItem() ;
						switch (langueChoisie) {
						case "Français" :
							frmCaisse.setTitle("Caisse enregistreuse") ;
							label1.setText("") ;

							break ;
						case "English" :
							frmCaisse.setTitle("Cash register") ;

							break ;
						}
					}
				}
			});

			JButton btnNewButton_8 = new JButton("Afficher les rapports de ventes par caissier");
			btnNewButton_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JButton btnNewButton_4 = new JButton("New button");

			JButton btnNewButton_5 = new JButton("New button");

			JButton btnNewButton_7 = new JButton("New button");

			JButton btnNewButton_10 = new JButton("New button");

			JButton btnNewButton_9 = new JButton("New button");

			JButton btnNewButton_12 = new JButton("New button");

			JButton btnNewButton_11 = new JButton("New button");

			JButton btnNewButton_14 = new JButton("New button");

			JButton btnNewButton_13 = new JButton("New button");

			JButton btnNewButton_16 = new JButton("New button");

			JButton btnNewButton_15 = new JButton("New button");

			JButton btnNewButton_2_1 = new JButton("New button");

			JButton btnNewButton_6_1 = new JButton("New button");

			JButton btnNewButton_3_1 = new JButton("New button");

			JButton btnNewButton_8_1 = new JButton("New button");

			JButton btnNewButton_4_1 = new JButton("New button");

			JButton btnNewButton_5_1 = new JButton("New button");

			JButton btnNewButton_7_1 = new JButton("New button");

			JButton btnNewButton_10_1 = new JButton("New button");

			JButton btnNewButton_9_1 = new JButton("New button");

			JButton btnNewButton_12_1 = new JButton("New button");

			JButton btnNewButton_11_1 = new JButton("New button");

			JButton btnNewButton_14_1 = new JButton("New button");

			JButton btnNewButton_13_1 = new JButton("New button");

			JButton btnNewButton_16_1 = new JButton("New button");

			JButton btnNewButton_15_1 = new JButton("New button");

			JButton btnNewButton_17_1 = new JButton("New button");

			JButton btnNewButton_18 = new JButton("New button");

			JButton btnNewButton_17 = new JButton("New button");

			JButton btnNewButton_20 = new JButton("New button");

			JButton btnNewButton_19 = new JButton("New button") ;
		}
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
		if (pageIndex != 0) {
			return Printable.NO_SUCH_PAGE ;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		txtaFactureFinale.printAll(g2d) ;

		return Printable.PAGE_EXISTS;
	}
}
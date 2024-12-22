package venteÉtalage ;

import java.awt.EventQueue ;
import javax.swing.JFrame ;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InterfaceInventaire {

	private JFrame frame ;
	private JTextField nomAjout ;
	private JTextField upcAjout ;
	private JTextField upcÀvoir ;
	private JTextField prixAff ;
	private JTextField àSupprimer ;
	private JTextField qteAjout ;
	private JTextField prixAjout ;
	private JTextField upcModif ;
	private JTextField nouvPrix ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceInventaire window = new InterfaceInventaire() ;
					window.frame.setVisible(true) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceInventaire() {
		initialize() ;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame() ;
		frame.setBounds(100, 100, 600, 374);
		frame.getContentPane().setLayout(null) ;

		JPanel panel = new JPanel() ;
		panel.setBounds(6, 6, 286, 210) ;
		frame.getContentPane().add(panel) ;
		panel.setLayout(null) ;

		JLabel lblNewLabel = new JLabel("Ajouter un produit dans la gamme") ;
		lblNewLabel.setBounds(6, 6, 274, 16) ;
		panel.add(lblNewLabel) ;

		nomAjout = new JTextField();
		nomAjout.setBounds(49, 34, 231, 26);
		panel.add(nomAjout);
		nomAjout.setColumns(10);

		upcAjout = new JTextField();
		upcAjout.setBounds(49, 72, 231, 26);
		panel.add(upcAjout);
		upcAjout.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(6, 34, 61, 16);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("UPC") ;
		lblNewLabel_2.setBounds(6, 77, 61, 16) ;
		panel.add(lblNewLabel_2) ;

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean cond ;
				cond = Inventaire.ajouterProduit(nomAjout.getText(), upcAjout.getText(), 
						Integer.parseInt(qteAjout.getText()), 
						Float.parseFloat(prixAjout.getText())) ;

				if (cond == true) {
					JOptionPane.showMessageDialog(null, "Alexa") ;
				} else {
					JOptionPane.showMessageDialog(null, "Bob") ;
				}
			}
		});
		btnNewButton.setBounds(84, 175, 117, 29);
		panel.add(btnNewButton);

		JLabel lblNewLabel_6 = new JLabel("Qte");
		lblNewLabel_6.setBounds(6, 110, 61, 16);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Prix");
		lblNewLabel_7.setBounds(6, 138, 43, 16);
		panel.add(lblNewLabel_7);

		qteAjout = new JTextField();
		qteAjout.setColumns(10);
		qteAjout.setBounds(49, 105, 231, 26);
		panel.add(qteAjout);

		prixAjout = new JTextField();
		prixAjout.setColumns(10);
		prixAjout.setBounds(49, 138, 231, 26);
		panel.add(prixAjout);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(304, 6, 286, 143);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Voir");
		lblNewLabel_3.setBounds(6, 6, 61, 16);
		panel_2.add(lblNewLabel_3);

		upcÀvoir = new JTextField();
		upcÀvoir.setBounds(0, 25, 280, 26);
		panel_2.add(upcÀvoir);
		upcÀvoir.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Prix :");
		lblNewLabel_4.setBounds(6, 63, 137, 16);
		panel_2.add(lblNewLabel_4);

		prixAff = new JTextField();
		prixAff.setEditable(false);
		prixAff.setColumns(10);
		prixAff.setBounds(0, 83, 280, 26);
		panel_2.add(prixAff);

		JButton btnNewButton_2 = new JButton("Voir");
		btnNewButton_2.setBounds(79, 109, 117, 29);
		panel_2.add(btnNewButton_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(304, 161, 286, 134);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Modifier le prix");
		lblNewLabel_8.setBounds(6, 6, 274, 16);
		panel_3.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("UPC");
		lblNewLabel_9.setBounds(6, 39, 61, 16);
		panel_3.add(lblNewLabel_9);

		upcModif = new JTextField();
		upcModif.setColumns(10);
		upcModif.setBounds(49, 34, 231, 26);
		panel_3.add(upcModif);

		JLabel lblNewLabel_10 = new JLabel("Prix");
		lblNewLabel_10.setBounds(6, 72, 61, 16);
		panel_3.add(lblNewLabel_10);

		nouvPrix = new JTextField();
		nouvPrix.setColumns(10);
		nouvPrix.setBounds(49, 67, 231, 26);
		panel_3.add(nouvPrix);

		JButton btnNewButton_3 = new JButton("Modifier");
		btnNewButton_3.setBounds(82, 99, 117, 29);
		panel_3.add(btnNewButton_3);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(6, 228, 286, 106);
		frame.getContentPane().add(panel_3_1);
		panel_3_1.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Supprimer un produit");
		lblNewLabel_5.setBounds(6, 6, 274, 16);
		panel_3_1.add(lblNewLabel_5);

		àSupprimer = new JTextField();
		àSupprimer.setBounds(6, 34, 274, 26);
		panel_3_1.add(àSupprimer);
		àSupprimer.setColumns(10);

		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setBounds(74, 72, 117, 29);
		panel_3_1.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Quitter");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Inventaire.imprimerListeProduits() ;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0) ;
			}
		});
		btnNewButton_4.setBounds(389, 307, 117, 29);
		frame.getContentPane().add(btnNewButton_4);
	}
}

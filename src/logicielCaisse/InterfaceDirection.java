package logicielCaisse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import gestionCaisse.GestionCaisses;
import structure.Caissier;

public class InterfaceDirection {

	private JFrame frame ;
	private JTextField personneÀlicencier;
	private final Action action = new SwingAction();
	private JTextField caissier;
	private JTextField gestionnaire;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDirection window = new InterfaceDirection();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceDirection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 277, 93);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Licencier");
		lblNewLabel.setBounds(6, 6, 61, 16);
		panel.add(lblNewLabel);
		
		personneÀlicencier = new JTextField();
		personneÀlicencier.setFont(new Font("Courier New", Font.PLAIN, 20));
		personneÀlicencier.setBounds(6, 27, 265, 26);
		panel.add(personneÀlicencier);
		personneÀlicencier.setColumns(10);
		
		JButton btnNewButton = new JButton("Licencier");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(6, 58, 265, 29);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 111, 277, 93);
		panel_1.setLayout(null);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Ajouter Caissier");
		lblNewLabel_1.setBounds(6, 6, 265, 16);
		panel_1.add(lblNewLabel_1);
		
		caissier = new JTextField();
		caissier.setFont(new Font("Courier New", Font.PLAIN, 20));
		caissier.setColumns(10);
		caissier.setBounds(6, 27, 265, 26);
		panel_1.add(caissier);
		
		JButton btnNewButton_1 = new JButton("Ajouter Caissier");
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(6, 58, 265, 29);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 216, 277, 93);
		panel_2.setLayout(null);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Promouvoir au poste de Gestionnaire");
		lblNewLabel_2.setBounds(6, 6, 265, 16);
		panel_2.add(lblNewLabel_2);
		
		gestionnaire = new JTextField();
		gestionnaire.setFont(new Font("Courier New", Font.PLAIN, 20));
		gestionnaire.setColumns(10);
		gestionnaire.setBounds(6, 27, 265, 26);
		panel_2.add(gestionnaire);
		
		JButton btnNewButton_2 = new JButton("Promouvoir");
		btnNewButton_2.setBounds(76, 58, 117, 29);
		panel_2.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(295, 6, 277, 93);
		panel_3.setLayout(null);
		frame.getContentPane().add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Licencier");
		lblNewLabel_3.setBounds(6, 6, 61, 16);
		panel_3.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(6, 27, 265, 26);
		panel_3.add(textField_2);
		
		JButton btnNewButton_3 = new JButton("Licencier");
		btnNewButton_3.setBounds(76, 58, 117, 29);
		panel_3.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(295, 111, 277, 93);
		panel_4.setLayout(null);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("Licencier");
		lblNewLabel_4.setBounds(6, 6, 61, 16);
		panel_4.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(6, 27, 265, 26);
		panel_4.add(textField_3);
		
		JButton btnNewButton_4 = new JButton("Licencier");
		btnNewButton_4.setBounds(76, 58, 117, 29);
		panel_4.add(btnNewButton_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(295, 216, 277, 93);
		panel_5.setLayout(null);
		frame.getContentPane().add(panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("Licencier");
		lblNewLabel_5.setBounds(6, 6, 61, 16);
		panel_5.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(6, 27, 265, 26);
		panel_5.add(textField_4);
		
		JButton btnNewButton_5 = new JButton("Licencier");
		btnNewButton_5.setBounds(76, 58, 117, 29);
		panel_5.add(btnNewButton_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(6, 426, 277, 93);
		panel_6.setLayout(null);
		frame.getContentPane().add(panel_6);
		
		JLabel lblNewLabel_6 = new JLabel("Licencier");
		lblNewLabel_6.setBounds(6, 6, 61, 16);
		panel_6.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_5.setColumns(10);
		textField_5.setBounds(6, 27, 265, 26);
		panel_6.add(textField_5);
		
		JButton btnNewButton_6 = new JButton("Licencier");
		btnNewButton_6.setBounds(76, 58, 117, 29);
		panel_6.add(btnNewButton_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(295, 321, 277, 93);
		panel_7.setLayout(null);
		frame.getContentPane().add(panel_7);
		
		JLabel lblNewLabel_7 = new JLabel("Licencier");
		lblNewLabel_7.setBounds(6, 6, 61, 16);
		panel_7.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_6.setColumns(10);
		textField_6.setBounds(6, 27, 265, 26);
		panel_7.add(textField_6);
		
		JButton btnNewButton_7 = new JButton("Licencier");
		btnNewButton_7.setBounds(76, 58, 117, 29);
		panel_7.add(btnNewButton_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(6, 321, 277, 93);
		panel_8.setLayout(null);
		frame.getContentPane().add(panel_8);
		
		JLabel lblNewLabel_8 = new JLabel("Ajouter Membre de direction");
		lblNewLabel_8.setBounds(6, 6, 265, 16);
		panel_8.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_7.setColumns(10);
		textField_7.setBounds(6, 27, 265, 26);
		panel_8.add(textField_7);
		
		JButton btnNewButton_8 = new JButton("Licencier");
		btnNewButton_8.setBounds(76, 58, 117, 29);
		panel_8.add(btnNewButton_8);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setLayout(null);
		panel_7_1.setBounds(295, 426, 277, 93);
		frame.getContentPane().add(panel_7_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("Licencier");
		lblNewLabel_7_1.setBounds(6, 6, 61, 16);
		panel_7_1.add(lblNewLabel_7_1);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Courier New", Font.PLAIN, 20));
		textField_8.setColumns(10);
		textField_8.setBounds(6, 27, 265, 26);
		panel_7_1.add(textField_8);
		
		JButton btnNewButton_7_1 = new JButton("Licencier");
		btnNewButton_7_1.setBounds(76, 58, 117, 29);
		panel_7_1.add(btnNewButton_7_1);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Licencier");
		}
		public void actionPerformed(ActionEvent e) {
			int posVirgule = personneÀlicencier.getText().indexOf(",") ;
			String nom = personneÀlicencier.getText().substring(0, posVirgule) ;
			String prénom = personneÀlicencier.getText().substring(posVirgule + 2) ;
			Caissier àVirer = new Caissier(nom, prénom) ;
			try {
				àVirer.licencier(nom, prénom) ;
			} catch (IOException e1) {
				e1.printStackTrace() ;
			}
			
			System.out.println("Employé licencié.") ;
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Ajouter Caissier") ;
		}
		public void actionPerformed(ActionEvent e) {
			int posVirgule = caissier.getText().indexOf(",") ;
			String nom = caissier.getText().substring(0, posVirgule) ;
			String prénom = caissier.getText().substring(posVirgule + 1) ;
			GestionCaisses.ajouterCaissier(nom, prénom) ;
			System.out.println("Caissier ajouté.") ;
		}
	}
}

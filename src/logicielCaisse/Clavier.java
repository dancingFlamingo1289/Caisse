package logicielCaisse ;

import java.awt.Dimension ;
import javax.swing.JPanel ;
import javax.swing.JTextField;
import javax.swing.JButton ;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clavier extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Clavier() {
		setPreferredSize(new Dimension(270, 465)) ;
		setLayout(null) ;
		
		JButton btnOne = new JButton("1") ;
		btnOne.setBounds(0, 95, 83, 83) ;
		add(btnOne) ;
		
		JButton btnTwo = new JButton("2") ;
		btnTwo.setBounds(95, 95, 83, 83) ;
		add(btnTwo) ;
		
		JButton btnThree = new JButton("3") ;
		btnThree.setBounds(184, 95, 83, 83) ;
		add(btnThree) ;
		
		JButton btnFour = new JButton("4") ;
		btnFour.setBounds(0, 190, 83, 83) ;
		add(btnFour) ;
		
		JButton btnFive = new JButton("5") ;
		btnFive.setBounds(95, 190, 83, 83) ;
		add(btnFive) ;
		
		JButton btnSix = new JButton("6") ;
		btnSix.setBounds(184, 190, 83, 83) ;
		add(btnSix) ;
		
		JButton btnSeven = new JButton("7") ;
		btnSeven.setBounds(0, 285, 83, 83) ;
		add(btnSeven) ;
		
		JButton btnEight = new JButton("8") ;
		btnEight.setBounds(95, 285, 83, 83) ;
		add(btnEight) ;
		
		JButton btnNine = new JButton("9") ;
		btnNine.setBounds(184, 285, 83, 83) ;
		add(btnNine) ;
		
		JButton btnDot = new JButton(".") ;
		btnDot.setBounds(0, 380, 83, 83) ;
		add(btnDot) ;
		
		JButton btnZero = new JButton("0") ;
		btnZero.setBounds(95, 380, 83, 83) ;
		add(btnZero) ;
		
		JButton btnDoubleZero = new JButton("00") ;
		btnDoubleZero.setBounds(184, 380, 83, 83) ;
		add(btnDoubleZero) ;
		
		JButton buttonDel = new JButton("DEL") ;
		buttonDel.setBounds(48, 6, 83, 83) ;
		add(buttonDel) ;
		
		JButton btnQte = new JButton("QTE") ;
		btnQte.setBounds(143, 6, 83, 83) ;
		add(btnQte) ;
	}
	
	public static void keyButtonPressed(JButton key, JTextField txtField) {
		key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() + Integer.parseInt(key.getText())) ;
			}
		});
	}
}

package logicielCaisse;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

import outils.OutilsImage;

public class ZoneImage extends JPanel {
	private Image img = null ;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g) ;
		Graphics2D g2d = (Graphics2D) g ;
		g2d.drawImage(img, 0, 0, null) ;
	}
	
	/**
	 * Create the panel.
	 */
	public ZoneImage() {
		img = OutilsImage.lireImage("Walmart-Logo.png");
	}
}

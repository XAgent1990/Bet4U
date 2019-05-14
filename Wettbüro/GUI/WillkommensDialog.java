package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Für ueb06
 * Enthält Willkommensgrafik mit Einkaufswagen als Komponente
 * @author Scheben
 *
 */
public class WillkommensDialog extends JDialog {
	public WillkommensDialog (JFrame owner, String text) {
		super(owner);
		setTitle(text);
		setModal(true);
		setSize(200, 150);
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
 
		// Grafik einbinden
		add("Center", new WillkommensGrafik());
		
		setVisible(true);
	}
}

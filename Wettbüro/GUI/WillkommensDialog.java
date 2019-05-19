package GUI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		setSize(332, 246);
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
 
		// Grafik einbinden
		add("Center", new JLabel(new ImageIcon("images/bet4u.png")));
		
		setVisible(true);
	}
}

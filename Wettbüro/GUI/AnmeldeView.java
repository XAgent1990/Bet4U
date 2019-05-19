package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 * * V6.0
 *    1) Zur verdeckten Eingabe des Passworts jetzt JPasswordField statt
 *       JTextField
 *    2) Implementierung von getPwd muss geändert werden von getText()
 *       auf getPassword() und Umwandlung des jetzt gelieferten char[] in
 *       einen String
 * 
 * V5.0 Änderungen siehe PKA-ml05-WebshopGUI-NurEINController
 * 
 * @author Scheben
 *
 */

@SuppressWarnings("serial")
public class AnmeldeView extends JDialog {

	// Außerhalb des Konstruktors benoetigte Textfelder als Attribute
	private JTextField textfeldBenutzername;
	
	// V6.0 JPasswordFiels statt JTextField
	private JPasswordField textfeldPasswort; 
	
	// V5.0 als Attribut wegen Methode focusToNextComponent
	JButton weiter = new JButton("weiter"); 

	// V5.0: Zusätzlicher Parameter controller
	public AnmeldeView(Window owner, Controller controller) {
		super(owner, "Anmelden");
		setModal(true);

		setSize(300, 170);
		setMinimumSize(new Dimension(300, 170)); // Damit Fenster nicht zu klein
												 // werden kann

		setLocationRelativeTo(owner); // In Mitte des Owners positionieren

		setLayout(new GridLayout(5, 1));

		JLabel labelBenutzername = new JLabel("Geben Sie Ihren Benutzernamen ein");
		JLabel labelPasswort = new JLabel("Geben Sie Ihr Passwort ein");
		textfeldBenutzername = new JTextField();
		textfeldPasswort = new JPasswordField(); // V6.0

		add(labelBenutzername);
		add(textfeldBenutzername);
		add(labelPasswort);
		add(textfeldPasswort);
		add(weiter);
		
		iniAnmeldeDialog();
		
     	// V5.0: Beobachter registrieren, hier Controllercode in 
		// innerer Klasse AnmeldeListener von Controller
		Controller.AnmeldeListener al = controller.new AnmeldeListener();
		textfeldBenutzername.addKeyListener(al);
		textfeldPasswort.addKeyListener(al);
		weiter.addKeyListener(al);
		weiter.addActionListener(al);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// V5.0 setVisible -> Methode anmelden im Controller. 
		// Begründung siehe V5.0
		// setVisible(true);
	}

	public String getBenutzername() {
		return textfeldBenutzername.getText();
	}

	// V6.0: Anpassung an JPasswordFiled. getText() ist depricated;
	// statt dessen getPassword(); getPassword() liefert char[]
	public String getPwd() {
		return String.valueOf(textfeldPasswort.getPassword());
	}

	private void iniAnmeldeDialog() {
		textfeldBenutzername.setText("");
		textfeldPasswort.setText("");
		textfeldBenutzername.requestFocus();
	}
	
	/**
	 * V5.0: Setzt bei Wahl von ENTER den
	 *       Cursor auf die nächste Komponente
	 *       Wird von Beobachter-Code verwendet 
	 */
	void focusToNextComponent() {
		Component c = this.getFocusOwner();
		if (c == textfeldBenutzername)
			textfeldPasswort.requestFocus();
		else
			if (c == textfeldPasswort)
				weiter.requestFocus();
	}
	
	/**
	 * V5.0 Wird von Beobachtercode in innerer Klasse AnmeldeListener
	 * von Controller benötigt. Er muss wissen, ob der KeyEvent am Button 
	 * oder einem der Textfelder aufgetreten ist, da Aktionen von Quelle 
	 * des Events abhängen.
	 * 
	 * @param o
	 * @return true, wenn o der weiter-Button ist, sonst false
	 */
	boolean isWeiterButton(Object o) {
		return o == weiter;
	}	
}

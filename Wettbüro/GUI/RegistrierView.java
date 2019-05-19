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
public class RegistrierView extends JDialog {

	private JTextField tf_Benutzername = new JTextField();
	private JTextField tf_EmailAdresse = new JTextField();
	
	private JPasswordField tf_Passwort = new JPasswordField();
	private JPasswordField tf_passwortWiederholung = new JPasswordField(); 
	
	// V5.0 als Attribut wegen Methode focusToNextComponent
	JButton registrieren = new JButton("Jetzt registrieren"); 

	// V5.0: Zusätzlicher Parameter controller
	public RegistrierView(Window owner, Controller controller) {
		super(owner, "Registrieren");
		setModal(true);

		setSize(300, 300);
		setMinimumSize(new Dimension(300, 200)); // Damit Fenster nicht zu klein
												 // werden kann

		setLocationRelativeTo(owner); // In Mitte des Owners positionieren

		setLayout(new GridLayout(9, 1));

		JLabel labelBenutzername = new JLabel("Geben Sie Ihren Benutzeramen ein");
		JLabel labelEmailAdresse = new JLabel("Geben Sie Ihre Emailadresse ein");
		JLabel labelPasswort = new JLabel("Geben Sie Ihr Passwort ein");
		JLabel lPasswortWiederholung = new JLabel("Passwort wiederholen");


		add(labelBenutzername);
		add(tf_Benutzername);
		
		add(labelEmailAdresse);
		add(tf_EmailAdresse);
		
		add(labelPasswort);
		add(tf_Passwort);
		
		add(lPasswortWiederholung);
		add(tf_passwortWiederholung);
		
		add(registrieren);
		
		iniRegistrierDialog();
		
     	// V5.0: Beobachter registrieren, hier Controllercode in 
		// innerer Klasse AnmeldeListener von Controller
		Controller.RegistrierListener al = controller.new RegistrierListener();
		tf_Benutzername.addKeyListener(al);
		tf_EmailAdresse.addKeyListener(al);
		tf_Passwort.addKeyListener(al);
		tf_passwortWiederholung.addKeyListener(al);
		registrieren.addKeyListener(al);
		registrieren.addActionListener(al);
		this.addWindowListener(al);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		// V5.0 setVisible -> Methode anmelden im Controller. 
		// Begründung siehe V5.0
		// setVisible(true);
	}

	public String getBenutzername() {
		return tf_Benutzername.getText();
	}
	
	public String getEmail() {
		return tf_EmailAdresse.getText();
	}

	// V6.0: Anpassung an JPasswordFiled. getText() ist depricated;
	// statt dessen getPassword(); getPassword() liefert char[]
	public String getPwd() {
		return String.valueOf(tf_Passwort.getPassword());
	}

	private void iniRegistrierDialog() {
		tf_Benutzername.setText("");
		tf_EmailAdresse.setText("");
		tf_Passwort.setText("");
		tf_passwortWiederholung.setText("");
		tf_Benutzername.requestFocus();
	}
	
	/**
	 * V5.0: Setzt bei Wahl von ENTER den
	 *       Cursor auf die nächste Komponente
	 *       Wird von Beobachter-Code verwendet 
	 */
	void focusToNextComponent() {
		Component c = this.getFocusOwner();
		if (c == tf_Benutzername)
			tf_EmailAdresse.requestFocus();
		if (c == tf_EmailAdresse)
			tf_Passwort.requestFocus();
		if (c == tf_Passwort)
			tf_passwortWiederholung.requestFocus();
		if (c == tf_passwortWiederholung)
				registrieren.requestFocus();
	}
	
	private boolean textFieldOK(JTextField tf) {
		return (tf.getText() != null && !tf.getText().equals(""));
	}
	
	boolean textFieldsOK() {
		boolean OK = true;
		OK = OK & textFieldOK(tf_Benutzername) & textFieldOK(tf_EmailAdresse)
				& textFieldOK(tf_Passwort) & textFieldOK(tf_passwortWiederholung);
		return OK;
	}
	
	boolean passwordsOK() {
		return String.valueOf(tf_Passwort.getPassword()).equals(String.valueOf(tf_passwortWiederholung.getPassword()));
	}
	
	/**
	 * @param o
	 * @return true, wenn o der Registrier-Button ist, sonst false
	 */
	boolean isRegistrierButton(Object o) {
		return o == registrieren;
	}
}

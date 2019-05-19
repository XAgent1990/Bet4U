package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * V8.0 In registriereListener() auch WindowListener für
 *      MainView registriert
 * 
 * V6.0: Buutons inkl. Icons (Icon-Verzeichnis: images)
 * 
 * V5.0
 * 
 * 1) Beobachter-Code für das Hauptfenster ist in innerer Klasse 
 *    MainViewListener des Controllers zu finden.
 *    Ein solches Beobachter-Objekt wird an allen Buttons registriert
 *    (Methode registriereListener())
 * 2) Zusätzliche Methode enableButtons() zur Aktivierung der Buttons nach 
 *    erfolgreicher Anmeldung
 * 3) Sichtbarheit von iniButtons() wegen Aufruf aus Controller geändert
 * 
 * @author Scheben
 *
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private JButton registrieren;
	private JButton anmelden;
	private JButton abmelden;
	private JButton meineWetten;

	private JPanel buttonPanel; // Zur Aufnahme der Buttons

	public MainView(Controller controller)  {
		super("Webshop-Anwendung");
		
		setSize(700, 350);
		setMinimumSize(new Dimension(600, 300));
		
		fuelleButtonPanel();
		add(buttonPanel, BorderLayout.NORTH);

		// V5.0: Beobachter registrieren
		registriereListener(controller);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);  
	}
	
	private void fuelleButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 5));
		
		registrieren = new JButton("registrieren");
		buttonPanel.add(registrieren);
	
		anmelden = new JButton("anmelden");
		buttonPanel.add(anmelden);

		abmelden = new JButton("abmelden");
		buttonPanel.add(abmelden);

		meineWetten = new JButton("meine Wetten");
		buttonPanel.add(meineWetten);

		iniButtons();
	}

	// V5.0: private -> Paketglobal, da von Controller benötigt
	void iniButtons() {
		registrieren.setEnabled(true);
		anmelden.setEnabled(true);
		abmelden.setEnabled(false);
		meineWetten.setEnabled(false);
	}
	
	// V5.0: Zur Aktivierung der Buttons nach erfolgreicher 
	// Anmeldung
	void enableButtons() {
		registrieren.setEnabled(false);
		anmelden.setEnabled(false);
		abmelden.setEnabled(true);
		meineWetten.setEnabled(true);
	}
	
	/*********** Verbindung zwischen View und Controller ***********/

	// Beobachter an den Buttons registrieren
	private void registriereListener(Controller controller) {
		Controller.MainViewListener hl = controller.new MainViewListener();
		registrieren.addActionListener(hl);
		anmelden.addActionListener(hl);
		abmelden.addActionListener(hl);
		meineWetten.addActionListener(hl);
		this.addWindowListener(hl);
	}
	
}

package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import Fachlogik.Wettb�ro;
import Fachlogik.Teamverwaltung.*;
import Fachlogik.Wettkampfverwaltung.*;
import Fachlogik.Wettverwaltung.*;
import Fachlogik.Userverwaltung.*;

public class Controller
{
	private Wettb�ro wettb�ro;
	private MainView mainwindow;

	private AnmeldeView anmeldeView;
	private RegistrierView registrierView;
	
	private AuswahlView auswahlView;
	private WettView wettView;
	private WettenView wettenView;

	private boolean registrationAborted;

	public Controller(Wettb�ro wettb�ro)
	{
		this.wettb�ro = wettb�ro;
	}

	public void start()
	{
		mainwindow = new MainView(this);
		try
		{
			wettb�ro.laden();
			String text = "Willkommen bei " + wettb�ro.getBetreiber() + "!";
			new WillkommensDialog(mainwindow, text);
		} catch (ClassNotFoundException | IOException | BenutzerBereitsVorhandenException e)
		{
			new HinweisView(mainwindow, e.getMessage());
		}
	}

	private void ausfuehren(String befehl)
	{
		switch (befehl)
		{
		case "registrieren":
			if (registrieren())
			{
				mainwindow.enableButtons();
				browse();
			}
			break;
		case "anmelden":
			if (anmelden())
			{
				mainwindow.enableButtons();
				browse();
			}
			break;
		case "abmelden":
			abmelden();
			mainwindow.iniButtons();
			break;
		case "zurKasse":
			zurKasse();
			break;
		}
	}

	private boolean anmelden()
	{
		anmeldeView = new AnmeldeView(mainwindow, this);
		anmeldeView.setVisible(true);
		boolean anmeldenErfolgreich = false;
		String name = anmeldeView.getBenutzername();
		String pwd = anmeldeView.getPwd();
		User user = wettb�ro.getUser(name, pwd);
		if(user == null)
		{
			new HinweisView(mainwindow, "Ung�ltige Anmeldeinformationen. Benutzername oder Passwort falsch.");
		}
		else
		{
			anmeldenErfolgreich = true;
			wettb�ro.anmelden(user);
		}
		return anmeldenErfolgreich;
	}

	private boolean registrieren()
	{
		registrationAborted = false;
		registrierView = new RegistrierView(mainwindow, this);
		registrierView.setVisible(true);
		boolean anmeldenErfolgreich = false;
		if (!registrationAborted)
		{
			try
			{
				String name = registrierView.getBenutzername();
				String mail = registrierView.getEmail();
				String pwd = registrierView.getPwd();
				Benutzer user = new Benutzer(mail, name, pwd);
				wettb�ro.addBenutzer(user);
				wettb�ro.anmelden(user);
				anmeldenErfolgreich = true;
			} catch (BenutzerBereitsVorhandenException | UngueltigerBenutzerException e)
			{
				new HinweisView(mainwindow, e.getMessage());
			}
		}
		return anmeldenErfolgreich;
	}

	private void browse()
	{
		auswahlView = new AuswahlView(mainwindow, this, wettb�ro.getWettkampfliste());
		mainwindow.add(auswahlView);
	}

	private void zurKasse()
	{
		if (auswahlView.auswahlVorhanden())
		{
			wettView = new WettView(mainwindow, this, auswahlView.getSelectedWettkampf());
			wettView.setVisible(true);

		} else
			new HinweisView(mainwindow, "Keinen Wettkampf ausgew�hlt!");
	}

	private void abmelden()
	{
		auswahlView.dispose();
		mainwindow.remove(auswahlView);
		new HinweisView(mainwindow,
				"Auf Wiedersehen " + wettb�ro.getUserName() + "\nWir freuen uns auf Ihren n�chsten Besuch");
		wettb�ro.abmelden();
	}

	/*****************************************************************/
	/****************** Innere Listener-Klassen **********************/
	/*****************************************************************/
	class AnmeldeListener extends KeyAdapter implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			anmeldeView.setVisible(false);
			anmeldeView.dispose();
		}

		public void keyPressed(KeyEvent evt)
		{
			if (evt.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if (anmeldeView.isWeiterButton(evt.getSource()))
				{
					anmeldeView.setVisible(false);
					anmeldeView.dispose();
				} else
					anmeldeView.focusToNextComponent();
			}
		}
	}

	class RegistrierListener extends WindowAdapter implements KeyListener, ActionListener
	{
		public void windowClosing(WindowEvent evt)
		{
			registrationAborted = true;
			registrierView.setVisible(false);
			registrierView.dispose();
		}

		public void actionPerformed(ActionEvent arg0)
		{
			if (!registrierView.textFieldsOK())
			{
				new HinweisView(registrierView, "Eins der Textfelder ist leer!");
			} else if (!registrierView.passwordsOK())
			{
				new HinweisView(registrierView, "Eingegebene Passw�rter stimmen nicht �berein!");
			} else
			{
				registrierView.setVisible(false);
				registrierView.dispose();
			}
		}

		public void keyPressed(KeyEvent evt)
		{
			if (evt.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if (registrierView.isRegistrierButton(evt.getSource()))
				{
					if (!registrierView.textFieldsOK())
					{
						new HinweisView(registrierView, "Eins der Textfelder ist leer!");
					} else if (!registrierView.passwordsOK())
					{
						new HinweisView(registrierView, "Eingegebene Passw�rter stimmen nicht �berein!");
					} else
					{
						registrierView.setVisible(false);
						registrierView.dispose();
					}
				} else
					registrierView.focusToNextComponent();
			}
		}

		public void keyReleased(KeyEvent arg0)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{
		}
	}

	class AuswahlListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getActionCommand().equals("comboBoxChanged"))
			{
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				String wettkampftyp = (String) cb.getSelectedItem();
				auswahlView.filter(wettkampftyp);
			} else
			{
				Wettkampf wettkampf = auswahlView.getSelectedWettkampf();
				wettb�ro.ausgew�hlt(wettkampf);
				auswahlView.clearSelection();
			}

		}
	}

	class WettViewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			wettView.setVisible(false);
			wettView.dispose();
		}
	}

	class MainViewListener implements ActionListener, WindowListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String befehl = e.getActionCommand();
			ausfuehren(befehl);
		}

		public void windowActivated(WindowEvent e)
		{
		}

		public void windowClosed(WindowEvent e)
		{
		}

		public void windowClosing(WindowEvent e)
		{
			try
			{
				wettb�ro.speichern();
			} catch (IOException e1)
			{
				e1.toString();
			}
		}

		public void windowDeactivated(WindowEvent e)
		{
		}

		public void windowDeiconified(WindowEvent e)
		{
		}

		public void windowIconified(WindowEvent e)
		{
		}

		public void windowOpened(WindowEvent e)
		{
		}
	}
}

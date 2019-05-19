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

import Fachlogik.Wettbüro;
import Fachlogik.Teamverwaltung.*;
import Fachlogik.Wettkampfverwaltung.*;
import Fachlogik.Wettverwaltung.*;
import Fachlogik.Userverwaltung.*;

public class Controller
{
	private Wettbüro wettbüro;
	private MainView mainwindow;

	private AnmeldeView anmeldeView;
	private RegistrierView registrierView;
	
	private AuswahlView auswahlView;
	private WettView wettView;
	private WettenView wettenView;

	private boolean registrationAborted;

	public Controller(Wettbüro wettbüro)
	{
		this.wettbüro = wettbüro;
	}

	public void start()
	{
		mainwindow = new MainView(this);
		try
		{
			wettbüro.laden();
			String text = "Willkommen bei " + wettbüro.getBetreiber() + "!";
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
		User user = wettbüro.getUser(name, pwd);
		if(user == null)
		{
			new HinweisView(mainwindow, "Ungültige Anmeldeinformationen. Benutzername oder Passwort falsch.");
		}
		else
		{
			anmeldenErfolgreich = true;
			wettbüro.anmelden(user);
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
				wettbüro.addBenutzer(user);
				wettbüro.anmelden(user);
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
		auswahlView = new AuswahlView(mainwindow, this, wettbüro.getWettkampfliste());
		mainwindow.add(auswahlView);
	}

	private void zurKasse()
	{
		if (auswahlView.auswahlVorhanden())
		{
			wettView = new WettView(mainwindow, this, auswahlView.getSelectedWettkampf());
			wettView.setVisible(true);

		} else
			new HinweisView(mainwindow, "Keinen Wettkampf ausgewählt!");
	}

	private void abmelden()
	{
		auswahlView.dispose();
		mainwindow.remove(auswahlView);
		new HinweisView(mainwindow,
				"Auf Wiedersehen " + wettbüro.getUserName() + "\nWir freuen uns auf Ihren nächsten Besuch");
		wettbüro.abmelden();
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
				new HinweisView(registrierView, "Eingegebene Passwörter stimmen nicht überein!");
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
						new HinweisView(registrierView, "Eingegebene Passwörter stimmen nicht überein!");
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
				wettbüro.ausgewählt(wettkampf);
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
				wettbüro.speichern();
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

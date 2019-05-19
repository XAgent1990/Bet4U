package Fachlogik.Userverwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Datenhaltung.IUserDAO;

public class Userverwaltung {

	private Set<User> benutzerListe;
	private Set<User> adminListe;
	private IUserDAO dao;

	public Userverwaltung(IUserDAO dao)
	{
		benutzerListe = new HashSet<User>();
		adminListe = new HashSet<User>();
		this.dao = dao;
	}

	public void laden() throws IOException, BenutzerBereitsVorhandenException
	{
		benutzerListe.clear();
		try
		{
			List<Benutzer> liste = dao.laden();
			for (Benutzer b : liste)
				this.addBenutzer(b);

		} catch (BenutzerBereitsVorhandenException ex)
		{
			throw new BenutzerBereitsVorhandenException(
					"Fehler beim Laden der Benutzerdaten. Es gibt zwei Benutzer mit derselben ID!");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void speichern() throws IOException
	{
		List<Benutzer> liste = new ArrayList<>();
		for (User b : benutzerListe)
			liste.add((Benutzer)b);
		dao.speichern(liste);
	}

	public boolean istLeer()
	{
		return benutzerListe.isEmpty();
	}

	public List<Benutzer> getBenutzerliste()
	{
		ArrayList<Benutzer> benutzerLi = new ArrayList<Benutzer>();
		for (User benutzer : benutzerListe)
		{
			benutzerLi.add((Benutzer)benutzer);
		}
		return benutzerLi;
	}

	public List<Admin> getAdminliste()
	{
		ArrayList<Admin> adminLi = new ArrayList<Admin>();
		for (User admin : adminListe)
		{
			adminLi.add((Admin)admin);
		}
		return adminLi;
	}

	public User getUser(String name, String pwd)
	{
		for(User u : adminListe)
		{
			if(((Admin)u).getName().equals(name) && ((Admin)u).getPwd().equals(pwd))
				return u;
		}
		for(User u : benutzerListe)
		{
			if(((Benutzer)u).getName().equals(name) && ((Benutzer)u).getPasswort().equals(pwd))
				return u;
		}
		return null;
	}

	public void addBenutzer(Benutzer benutzer) throws BenutzerBereitsVorhandenException
	{
		if (benutzer == null)
		{
			return;
		}
		for(User u : adminListe)
		{
			if(((Admin)u).getName().equals(benutzer.getName()))
				throw new BenutzerBereitsVorhandenException("Benutzername bereits vergeben!\n"
						+ "Bitte wählen sie einen anderen.");
		}
		for(User u : benutzerListe)
		{
			if(((Benutzer)u).getName().equals(benutzer.getName()))
				throw new BenutzerBereitsVorhandenException("Benutzername bereits vergeben!\n"
						+ "Bitte wählen sie einen anderen.");
		}
		if (!benutzerListe.add(benutzer))
		{
			String str = "Benutzer kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Benutzer mit derselben ID existiert:\n" + benutzer.toString();
			throw new BenutzerBereitsVorhandenException(str);
		}
	}

	public boolean istGueltig(Benutzer benutzer)
	{
		for(User u : benutzerListe)
		{
			if(benutzer.match(u))
				return true;
		}
		return false;
	}

	public void addAdmin(Admin admin) throws AdminBereitsVorhandenException
	{
		if (admin == null)
		{
			return;
		}
		if (!adminListe.add(admin))
		{
			String str = "Admin kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Admin mit derselben ID existiert:\n" + admin.toString();
			throw new AdminBereitsVorhandenException(str);
		}
	}
}

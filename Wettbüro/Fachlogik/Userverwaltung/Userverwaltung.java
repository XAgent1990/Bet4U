package Fachlogik.Userverwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import Datenhaltung.IUserDAO;

public class Userverwaltung {

	private SortedSet<Benutzer> benutzerListe;
	private IUserDAO dao;

	public Userverwaltung(IUserDAO dao)
	{
		benutzerListe = new TreeSet<Benutzer>();
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
		for (Benutzer b : benutzerListe)
			liste.add(b);
		dao.speichern(liste);
	}

	public boolean istLeer()
	{
		return benutzerListe.isEmpty();
	}

	public List<Benutzer> getBenutzerliste()
	{
		ArrayList<Benutzer> benutzerLi = new ArrayList<Benutzer>();
		for (Benutzer benutzer : benutzerListe)
		{
			benutzerLi.add(benutzer);
		}
		return benutzerLi;
	}

	public void addBenutzer(Benutzer benutzer) throws BenutzerBereitsVorhandenException
	{
		if (benutzer == null || !(benutzer instanceof Benutzer))
			return;
		if (!benutzerListe.add(benutzer))
		{
			String str = "Benutzer kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Benutzer mit derselben ID existiert:\n" + benutzer.toString();
			throw new BenutzerBereitsVorhandenException(str);
		}
	}

	public boolean istGueltig(Benutzer benutzer)
	{
		for(Benutzer u : benutzerListe)
		{
			if(benutzer.match(u))
				return true;
		}
		return false;
	}
}

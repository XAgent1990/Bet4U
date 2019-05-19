package Fachlogik.Userverwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Datenhaltung.IUserDAO;

public class Userverwaltung {

	private Set<Benutzer> benutzerListe;
	private IUserDAO dao;

	public Userverwaltung(IUserDAO dao)
	{
		benutzerListe = new HashSet<Benutzer>();
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
		System.out.println("Füge " + benutzer + " hinzu");
		if (benutzer == null || !(benutzer instanceof Benutzer))
		{
			return;
		}
		if (!benutzerListe.add(benutzer))
		{
			String str = "Benutzer kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Benutzer mit derselben ID existiert:\n" + benutzer.toString();
			throw new BenutzerBereitsVorhandenException(str);
		}
		System.out.println(benutzer + " hinzugefügt");
	}

	public boolean istGueltig(Benutzer benutzer)
	{
		System.out.println("check");
		for(Benutzer u : benutzerListe)
		{
			System.out.println(u);
			if(benutzer.match(u))
				return true;
		}
		return false;
	}
}

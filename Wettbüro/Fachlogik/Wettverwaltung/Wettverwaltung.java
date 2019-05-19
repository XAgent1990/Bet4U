package Fachlogik.Wettverwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import Datenhaltung.IWetteDAO;

public class Wettverwaltung
{

	private SortedSet<Wette> wetteListe;
	private IWetteDAO dao;

	public Wettverwaltung(IWetteDAO dao)
	{
		wetteListe = new TreeSet<Wette>();
		this.dao = dao;
	}

	public void laden() throws IOException, WetteBereitsVorhandenException
	{
		wetteListe.clear();
		try
		{
			List<Wette> liste = dao.laden();
			for (Wette w : liste)
				this.addWette(w);

		} catch (WetteBereitsVorhandenException | ClassNotFoundException ex)
		{
			throw new WetteBereitsVorhandenException(
					"Fehler beim Laden der Wettedaten. Es gibt zwei Wette mit derselben ID!");
		}
	}

	public void speichern() throws IOException
	{
		List<Wette> liste = new ArrayList<>();
		for (Wette a : wetteListe)
			liste.add(a);
		dao.speichern(liste);
	}

	public boolean istLeer()
	{
		return wetteListe.isEmpty();
	}

	public List<Wette> getWetteliste()
	{
		ArrayList<Wette> wetteLi = new ArrayList<Wette>();
		for (Wette wette : wetteListe)
		{
			wetteLi.add(wette);
		}
		return wetteLi;
	}

	public List<Wette> getWetteliste(String typ)
	{
		ArrayList<Wette> wetteLi = new ArrayList<Wette>();
		for (Wette wette : wetteListe)
		{
			if (wette.getTyp().equals(typ))
				wetteLi.add(wette);
		}
		return wetteLi;
	}

	public void addWette(Wette wette) throws WetteBereitsVorhandenException
	{
		if (!wetteListe.add(wette))
		{
			String str = "Wette kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Wette mit derselben ID existiert:\n" + wette.toString();
			throw new WetteBereitsVorhandenException(str);
		}
	}

}

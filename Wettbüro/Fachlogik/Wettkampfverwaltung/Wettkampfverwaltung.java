package Fachlogik.Wettkampfverwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import Datenhaltung.IWettkampfDAO;
import Fachlogik.Teamverwaltung.Team;

public class Wettkampfverwaltung
{
	private SortedSet<Wettkampf> wettkampfListe;
	private IWettkampfDAO dao;

	public Wettkampfverwaltung(IWettkampfDAO dao)
	{
		wettkampfListe = new TreeSet<Wettkampf>();
		this.dao = dao;
	}

	public void laden() throws IOException, WettkampfBereitsVorhandenException
	{
		wettkampfListe.clear();
		try
		{
			List<Wettkampf> liste = dao.laden();
			for (Wettkampf w : liste)
				this.addWettkampf(w);

		} catch (WettkampfBereitsVorhandenException ex)
		{
			throw new WettkampfBereitsVorhandenException(
					"Fehler beim Laden der Wettkampfdaten. Es gibt zwei Wettkampf mit derselben ID!");
		}
	}

	public void speichern() throws IOException
	{
		List<Wettkampf> liste = new ArrayList<>();
		for (Wettkampf a : wettkampfListe)
			liste.add(a);
		dao.speichern(liste);
	}

	public boolean istLeer()
	{
		return wettkampfListe.isEmpty();
	}

	public List<Wettkampf> getWettkampfliste()
	{
		ArrayList<Wettkampf> wettkampfLi = new ArrayList<Wettkampf>();
		for (Wettkampf wettkampf : wettkampfListe)
		{
			wettkampfLi.add(wettkampf);
		}
		return wettkampfLi;
	}

	public List<Wettkampf> getWettkampfliste(String typ)
	{
		ArrayList<Wettkampf> wettkampfLi = new ArrayList<Wettkampf>();
		for (Wettkampf wettkampf : wettkampfListe)
		{
			if (wettkampf.getTyp().equals(typ))
				wettkampfLi.add(wettkampf);
		}
		return wettkampfLi;
	}

	public List<Wettkampf> getWettkampfliste(Team team)
	{
		ArrayList<Wettkampf> wettkampfLi = new ArrayList<Wettkampf>();
		for (Wettkampf wettkampf : wettkampfListe)
		{
			if (wettkampf.teamTakesPart(team))
				wettkampfLi.add(wettkampf);
		}
		return wettkampfLi;
	}

	public List<Wettkampf> getWettkampfliste(String typ, Team team)
	{
		ArrayList<Wettkampf> wettkampfLi = new ArrayList<Wettkampf>();
		for (Wettkampf wettkampf : wettkampfListe)
		{
			if (wettkampf.teamTakesPart(team) && wettkampf.getTyp().equals(typ))
				wettkampfLi.add(wettkampf);
		}
		return wettkampfLi;
	}

	public void addWettkampf(Wettkampf wettkampf) throws WettkampfBereitsVorhandenException
	{
		if (!wettkampfListe.add(wettkampf))
		{
			String str = "Wettkampf kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Wettkampf mit derselben ID existiert:\n" + wettkampf.toString();
			throw new WettkampfBereitsVorhandenException(str);
		}
	}
}

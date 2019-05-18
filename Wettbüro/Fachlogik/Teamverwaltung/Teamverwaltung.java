package Fachlogik.Teamverwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import Datenhaltung.ITeamDAO;

public class Teamverwaltung {

	private SortedSet<Team> teamListe;
	private ITeamDAO dao;

	public Teamverwaltung(ITeamDAO dao)
	{
		teamListe = new TreeSet<Team>();
		this.dao = dao;
	}

	public void laden() throws IOException, TeamBereitsVorhandenException
	{
		teamListe.clear();
		try
		{
			List<Team> liste = dao.laden();
			for (Team b : liste)
				this.addTeam(b);

		} catch (TeamBereitsVorhandenException ex)
		{
			throw new TeamBereitsVorhandenException(
					"Fehler beim Laden der Teamdaten. Es gibt zwei Team mit derselben ID!");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void speichern() throws IOException
	{
		List<Team> liste = new ArrayList<>();
		for (Team b : teamListe)
			liste.add(b);
		dao.speichern(liste);
	}

	public boolean istLeer()
	{
		return teamListe.isEmpty();
	}

	public List<Team> getTeamliste()
	{
		ArrayList<Team> teamLi = new ArrayList<Team>();
		for (Team team : teamListe)
		{
			teamLi.add(team);
		}
		return teamLi;
	}

	public void addTeam(Team team) throws TeamBereitsVorhandenException
	{
		if (team == null || !(team instanceof Team))
			return;
		if (!teamListe.add(team))
		{
			String str = "Team kann nicht hinzugefügt werden,\n"
					+ "da bereits ein Team mit derselben ID existiert:\n" + team.toString();
			throw new TeamBereitsVorhandenException(str);
		}
	}

	public boolean istGueltig(Team team)
	{
		for(Team u : teamListe)
		{
			if(team.match(u))
				return true;
		}
		return false;
	}
}

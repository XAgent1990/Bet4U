package Fachlogik.Wettkampfverwaltung;

import Fachlogik.Teamverwaltung.Team;

public class Spiel extends Wettkampf {

	public Spiel(int id, String typ, String bezeichnung) throws UngueltigeBezeichnungException
	{
		super(id, typ, bezeichnung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Wettkampf arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Team[] getTeams()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean teamTakesPart(Team team)
	{
		// TODO Auto-generated method stub
		return false;
	}

}

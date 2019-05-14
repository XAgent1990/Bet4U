package Fachlogik.Wettkampfverwaltung;

import Fachlogik.Teamverwaltung.Team;

public abstract class Wettkampf implements Comparable<Wettkampf>
{
	private static int letzteID = 0;
	private int id;
	private String typ;
	private String bezeichnung;
	
	public abstract Team[] getTeams();
	public abstract boolean teamTakesPart(Team team);

	public Wettkampf(int id, String typ, String bezeichnung) throws UngueltigeBezeichnungException
	{
		this.setID(id);
		if (letzteID < id)
			letzteID = id;
		this.setTyp(typ);
		setBezeichnung(bezeichnung);
	}

	public Wettkampf(String typ, String bezeichnung) throws UngueltigeBezeichnungException
	{
		this(++letzteID, typ, bezeichnung);
	}

	public int getID()
	{
		return this.id;
	}

	public void setID(int id)
	{
		this.id = id;
	}

	public String getTyp()
	{
		return typ;
	}

	public void setTyp(String typ)
	{
		this.typ = typ;
	}

	public String getBezeichnung()
	{
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) throws UngueltigeBezeichnungException
	{
		if (bezeichnung == null || bezeichnung.equals(""))
			throw new UngueltigeBezeichnungException("Für Wettkampf Nr. " + id + " wurde keine Bezeichnung angegeben");
		else
			this.bezeichnung = bezeichnung;
	}
	
	public String toString()
	{
		return typ + " " + bezeichnung;
	}
}

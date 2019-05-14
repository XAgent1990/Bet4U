package Fachlogik.Wettverwaltung;

import Fachlogik.Wettkampfverwaltung.UngueltigeBezeichnungException;
import Fachlogik.Wettkampfverwaltung.Wettkampf;

public abstract class Wette
{
	private static int letzteID = 0;
	private int id;
	private String typ;
	private String bezeichnung;
	private Wettkampf wettkampf;
	
	public Wette(int id, String typ, String bezeichnung, Wettkampf wettkampf) throws UngueltigeBezeichnungException
	{
		this.setID(id);
		if (letzteID < id)
			letzteID = id;
		this.setTyp(typ);
		setBezeichnung(bezeichnung);
	}

	public Wette(String typ, String bezeichnung, Wettkampf wettkampf) throws UngueltigeBezeichnungException
	{
		this(++letzteID, typ, bezeichnung, wettkampf);
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
			throw new UngueltigeBezeichnungException("Für Wette Nr. " + id + " wurde keine Bezeichnung angegeben");
		else
			this.bezeichnung = bezeichnung;
	}
	
	public Wettkampf getWettkampf()
	{
		return wettkampf;
	}
	
	public void setWettkampf(Wettkampf wettkampf) throws UngueltigerWettkampfException
	{
		if (bezeichnung == null || bezeichnung.equals(""))
			throw new UngueltigerWettkampfException("Für Wette Nr. " + id + " wurde keine Bezeichnung angegeben");
		else
			this.wettkampf = wettkampf;
	}
}

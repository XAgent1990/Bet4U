package Fachlogik;

import java.io.IOException;
import java.util.List;

import Fachlogik.Teamverwaltung.*;
import Fachlogik.Wettkampfverwaltung.*;
import Fachlogik.Wettverwaltung.*;
import Fachlogik.Userverwaltung.*;

public class Wettbüro
{
	private String betreiber;
	private Wettkampf wettkampf = null;

	/**
	 * Aktuell am Webshop angemeldeter Kunde
	 */
	private User aktuellerUser;

	/**
	 * Referenz auf die Userverwaltung mit den Methoden laden(), speichern() ...
	 */
	private Userverwaltung userverwaltung;

	/**
	 * Referenz auf die Wettverwaltung mit den Methoden laden(), speichern() ...
	 */
	private Wettverwaltung wettverwaltung;

	/**
	 * Referenz auf die Wettkampfverwaltung mit den Methoden laden(), speichern()
	 * ...
	 */
	private Wettkampfverwaltung wettkampfverwaltung;

	public Wettbüro(Userverwaltung userverwaltung, Wettverwaltung wettverwaltung,
			Wettkampfverwaltung wettkampfverwaltung, String betreiber)
	{
		this.userverwaltung = userverwaltung;
		this.wettverwaltung = wettverwaltung;
		this.wettkampfverwaltung = wettkampfverwaltung;
		this.betreiber = betreiber;
	}

	public void laden() throws IOException, BenutzerBereitsVorhandenException, ClassNotFoundException
	{
		String str = null;
		try
		{
			wettverwaltung.laden();
			wettkampfverwaltung.laden();
		} catch (IOException | WettkampfBereitsVorhandenException | WetteBereitsVorhandenException ex)
		{
			str = ex.getMessage();
		} finally
		{
			userverwaltung.laden();
			if (str != null)
				throw new IOException(str);
		}
	}

	public void speichern() throws IOException
	{
		userverwaltung.speichern();
	}

	public String getBetreiber()
	{
		return betreiber;
	}

	public void anmelden(User user)
	{
		this.aktuellerUser = user;
	}

	public void abmelden()
	{
		aktuellerUser = null;
	}

	public String getUserName()
	{
		return aktuellerUser.getName();
	}

	public boolean istGueltig(User user)
	{
		if(user instanceof Benutzer)
			return userverwaltung.istGueltig((Benutzer)user);
		else
			return false;
	}

	public void addBenutzer(Benutzer b) throws BenutzerBereitsVorhandenException
	{
		userverwaltung.addBenutzer(b);
	}

	public List<Wettkampf> getWettkampfliste()
	{
		return wettkampfverwaltung.getWettkampfliste();
	}

	public List<Wettkampf> getWettkampfliste(Team team)
	{
		return wettkampfverwaltung.getWettkampfliste(team);
	}

	public List<Wettkampf> getWettkampfliste(String wettkampftyp)
	{
		return wettkampfverwaltung.getWettkampfliste(wettkampftyp);
	}

	public List<Wettkampf> getWettkampfliste(String wettkampftyp, Team team)
	{
		return wettkampfverwaltung.getWettkampfliste(wettkampftyp, team);
	}

	public Wettkampf getWettkampf()
	{
		return wettkampf;
	}

	public void ausgewählt(Wettkampf wettkampf)
	{
		this.wettkampf = wettkampf;
	}
}

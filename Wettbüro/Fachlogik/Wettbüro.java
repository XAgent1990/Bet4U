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
	 * Referenz auf die Wettkampfverwaltung mit den Methoden laden(), speichern() ...
	 */
	private Wettkampfverwaltung wettkampfverwaltung;

	/**
	 * Referenz auf die Wettkampfverwaltung mit den Methoden laden(), speichern() ...
	 */
	private Teamverwaltung teamverwaltung;

	public Wettbüro(Userverwaltung userverwaltung, Wettverwaltung wettverwaltung,
			Wettkampfverwaltung wettkampfverwaltung, Teamverwaltung teamverwaltung, String betreiber)
	{
		this.userverwaltung = userverwaltung;
		this.wettverwaltung = wettverwaltung;
		this.wettkampfverwaltung = wettkampfverwaltung;
		this.teamverwaltung = teamverwaltung;
		this.betreiber = betreiber;
		try
		{
			this.addAdmin(new Admin("admin", "admin"));
		} catch (AdminBereitsVorhandenException e)
		{
			System.out.println("Error: Admin konnte nicht hinzugefügt werden!");
		}
	}

	public void laden() throws IOException, BenutzerBereitsVorhandenException, ClassNotFoundException
	{
		String str = null;
		try
		{
			wettverwaltung.laden();
			wettkampfverwaltung.laden();
			teamverwaltung.laden();
		} catch (IOException | WettkampfBereitsVorhandenException | WetteBereitsVorhandenException | TeamBereitsVorhandenException ex)
		{
			str = ex.getMessage();
		} finally
		{
			userverwaltung.laden();
			if (str != null)
				throw new IOException(str);
			System.out.println("Adminaccounts:");
			for(Admin a : userverwaltung.getAdminliste())
				System.out.println("Benutzername: " + a.getName() + "\nPasswort: " + a.getPwd());
		}
	}

	public void speichern() throws IOException
	{
		wettverwaltung.speichern();
		wettkampfverwaltung.speichern();
		teamverwaltung.speichern();
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

	public User getUser(String name, String pwd)
	{
		return userverwaltung.getUser(name, pwd);
	}

	public String getUserName()
	{
		return aktuellerUser.getName();
	}

	public void addBenutzer(Benutzer b) throws BenutzerBereitsVorhandenException
	{
		userverwaltung.addBenutzer(b);
	}

	public void addAdmin(Admin a) throws AdminBereitsVorhandenException
	{
		userverwaltung.addAdmin(a);
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

package Fachlogik.Userverwaltung;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Benutzer extends User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6447027857926278414L;

	private static final String EMAIL_PATTERN = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2})";

	private String emailAdresse = null;
	private String benutzername = null;
	private String passwort = null;
	private String vorname = null;
	private String nachname = null;

	public Benutzer(String emailAdresse,String benutzername, String passwort) throws UngueltigerBenutzerException {
		setEmailAdresse(emailAdresse);
		setBenutzername(benutzername);
		setPasswort(passwort);
	}

	public String getEmailAdresse()
	{
		return emailAdresse;
	}

	public void setEmailAdresse(String emailAdresse) throws UngueltigerBenutzerException
	{
		// Hier wird geprüft, ob der Aufbau der Emailadresse dem regulären
		// Ausdruck in EMAIL_PATTERN entspricht
		if (!Pattern.matches(EMAIL_PATTERN, emailAdresse))
			throw new UngueltigerBenutzerException("Ungueltige Emailadresse " + emailAdresse);
		this.emailAdresse = emailAdresse;
	}

	public String getPasswort()
	{
		return passwort;
	}

	public void setPasswort(String passwort) throws UngueltigerBenutzerException
	{
		if (passwort.equals(""))
			throw new UngueltigerBenutzerException("Ungueltiges Passwort");
		this.passwort = passwort;
	}

	/**
	 * Prüft, ob das aktuelle Benutzer-Objekt dieselbe Emailadresse und dasselbe
	 * Passwort besitzt, wie das übergebene Benutzer-Objekt k. Falls ja wird true
	 * zurückgegeben, ansonsten false
	 * 
	 * @param k
	 */
	public boolean match(Benutzer b)
	{
		String st = emailAdresse + passwort;
		String kst = b.emailAdresse + b.passwort;
		return st.equals(kst);
	}

	// Um zu gewährleisten, dass keine 2 Benutzers mit derselben
	// Emailadresse gespeichert werden
	public boolean equals(Object k)
	{
		if (k == null)
			return false;
		if (k instanceof Benutzer)
		{
			String st = emailAdresse;
			String kst = ((Benutzer) k).emailAdresse;
			return st.equals(kst);
		} else
			return false;
	}

	// Benutzers werden in HashSet gespeichert,
	// also hashCode nötig. Dieser muss für inhaltsgleiche
	// Objekte denselben Wert ergeben
	public int hashCode()
	{
		String st = emailAdresse;
		return st.hashCode();
	}

	public String toString()
	{
		return "Emailadresse = " + emailAdresse + ", Passwort = " + passwort;
	}

	public String getVorname()
	{
		return vorname;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public String getNachname()
	{
		return nachname;
	}

	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}

	public String getName()
	{
		return benutzername;
	}

	public void setBenutzername(String benutzername)
	{
		this.benutzername = benutzername;
	}

}
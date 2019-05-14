package Fachlogik.Userverwaltung;

@SuppressWarnings("serial")
public class BenutzerBereitsVorhandenException extends Exception
{

	public BenutzerBereitsVorhandenException()
	{
		super();
	}

	public BenutzerBereitsVorhandenException(String str)
	{
		super(str);
	}

}

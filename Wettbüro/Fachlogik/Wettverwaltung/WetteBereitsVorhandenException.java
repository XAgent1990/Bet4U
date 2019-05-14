package Fachlogik.Wettverwaltung;

@SuppressWarnings("serial")
public class WetteBereitsVorhandenException extends Exception
{
	public WetteBereitsVorhandenException()
	{
		super();
	}

	public WetteBereitsVorhandenException(String str)
	{
		super(str);
	}
}

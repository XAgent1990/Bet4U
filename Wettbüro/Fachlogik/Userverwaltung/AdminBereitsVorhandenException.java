package Fachlogik.Userverwaltung;

@SuppressWarnings("serial")
public class AdminBereitsVorhandenException extends Exception
{
	public AdminBereitsVorhandenException()
	{
		super();
	}

	public AdminBereitsVorhandenException(String str)
	{
		super(str);
	}
}

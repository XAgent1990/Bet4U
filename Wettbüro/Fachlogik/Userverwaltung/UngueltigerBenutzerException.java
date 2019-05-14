package Fachlogik.Userverwaltung;

public class UngueltigerBenutzerException extends Exception
{
	public UngueltigerBenutzerException()
	{
		super();
	}

	public UngueltigerBenutzerException(String message)
	{
		super(message);
	}

}

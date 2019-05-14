package Fachlogik.Wettverwaltung;

@SuppressWarnings("serial")
public class UngueltigerWettkampfException extends Exception
{
	public UngueltigerWettkampfException()
	{
		super();
	}

	public UngueltigerWettkampfException(String str)
	{
		super(str);
	}
}

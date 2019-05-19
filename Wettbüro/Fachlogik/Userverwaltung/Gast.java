package Fachlogik.Userverwaltung;

public class Gast extends User
{
	private final String name = "Gast";
	
	@Override
	public String getName()
	{
		return name;
	}
}

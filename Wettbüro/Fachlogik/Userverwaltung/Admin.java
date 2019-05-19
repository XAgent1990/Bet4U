package Fachlogik.Userverwaltung;

public class Admin extends User
{
	private final String name = "Admin";
	
	@Override
	public String getName()
	{
		return name;
	}
}

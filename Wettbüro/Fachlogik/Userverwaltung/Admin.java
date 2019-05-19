package Fachlogik.Userverwaltung;

public class Admin extends User
{
	private String name;
	private String pwd;
	
	public Admin(String name, String pwd)
	{
		this.name = name;
		this.pwd = pwd;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	public String getPwd()
	{
		return pwd;
	}
}

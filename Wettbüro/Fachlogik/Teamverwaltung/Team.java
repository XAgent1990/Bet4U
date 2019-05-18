package Fachlogik.Teamverwaltung;

import java.io.Serializable;

public class Team implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8724350087476711257L;
	private String name;
	private String shortName;

	public Team(String name, String shortName)
	{
		this.name = name;
		this.shortName = shortName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public boolean match(Team t)
	{
		String st = name + "|" + shortName;
		String kst = t.name + "|" + t.shortName;
		return st.equals(kst);
	}
}

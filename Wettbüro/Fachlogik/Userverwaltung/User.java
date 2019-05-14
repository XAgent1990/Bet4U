package Fachlogik.Userverwaltung;

public abstract class User
{
	public User()
	{
	}

	public abstract String getName();
	
	public boolean match(User b)
	{
		if (b == null)
			return false;
		if(b instanceof Benutzer)
		{
			String st = ((Benutzer)this).getEmailAdresse() + ((Benutzer)this).getPasswort();
			String kst = ((Benutzer) b).getEmailAdresse() + ((Benutzer) b).getPasswort();
			return st.equals(kst);
		}
		else
			return false;
	}

	// Um zu gewährleisten, dass keine 2 Benutzer mit derselben
	// Emailadresse gespeichert werden
	public boolean equals(Object b)
	{
		if (b == null)
			return false;
		if (b instanceof Benutzer)
		{
			String st = ((Benutzer)this).getEmailAdresse();
			String kst = ((Benutzer) b).getEmailAdresse();
			return st.equals(kst);
		} else
			return false;
	}

	public String toString()
	{
		return "";
	}
}
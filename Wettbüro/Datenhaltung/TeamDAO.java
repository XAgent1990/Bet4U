package Datenhaltung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Fachlogik.Teamverwaltung.*;

public class TeamDAO implements ITeamDAO
{
	public List<Team> laden() throws IOException, ClassNotFoundException
	{
		List<Team> liste = new ArrayList<>();
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Team.ser"));
		Team t;
		t = (Team) os.readObject();
		while (t != null)
		{
			liste.add(t);
			System.out.println(
					"Deserialisiert:\nEmail: " + t.getName() + '\n');
			t = (Team) os.readObject();
		}
		os.close();
		return liste;
	}

	public void speichern(List<Team> teamListe) throws FileNotFoundException, IOException
	{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Team.ser"));
		Iterator<Team> it = teamListe.iterator();
		while (it.hasNext())
			os.writeObject(it.next());
		os.close();
	}
}

package Datenhaltung;

import java.io.EOFException;
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
import Fachlogik.Wettverwaltung.Wette;

public class TeamDAO implements ITeamDAO
{
	@Override
	public List<Team> laden() throws IOException, ClassNotFoundException
	{
		List<Team> liste = new ArrayList<>();
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Team.ser"));
		Team t;
		t = (Team)os.readObject();
		while (t != null)
		{
			liste.add(t);
			System.out.println("Deserialisiert: Hier noch Team Infos eingeben");
			try
			{
				t = (Team)os.readObject();
			} catch (EOFException e)
			{
				t = null;
			}
		}
		os.close();
		return liste;
	}

	@Override
	public void speichern(List<Team> wetteListe) throws FileNotFoundException, IOException
	{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Team.ser"));
		Iterator<Team> it = wetteListe.iterator();
		while (it.hasNext())
			os.writeObject(it.next());
		os.close();
	}
}

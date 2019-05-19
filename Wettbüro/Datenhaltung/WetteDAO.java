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

import Fachlogik.Userverwaltung.Benutzer;
import Fachlogik.Wettverwaltung.Wette;

public class WetteDAO implements IWetteDAO
{

	@Override
	public List<Wette> laden() throws IOException, ClassNotFoundException
	{
		List<Wette> liste = new ArrayList<>();
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Wette.ser"));
		Wette w;
		w = (Wette)os.readObject();
		while (w != null)
		{
			liste.add(w);
			System.out.println("Deserialisiert: Hier noch Wette Infos eingeben");
			try
			{
				w = (Wette)os.readObject();
			} catch (EOFException e)
			{
				System.out.println("EOF!");
				w = null;
			}
		}
		os.close();
		return liste;
	}

	@Override
	public void speichern(List<Wette> wetteListe) throws FileNotFoundException, IOException
	{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Wette.ser"));
		Iterator<Wette> it = wetteListe.iterator();
		while (it.hasNext())
			os.writeObject(it.next());
		os.close();
	}

}

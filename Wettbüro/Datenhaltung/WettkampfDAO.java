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

import Fachlogik.Wettkampfverwaltung.Wettkampf;
import Fachlogik.Wettverwaltung.Wette;

public class WettkampfDAO implements IWettkampfDAO {

	@Override
	public List<Wettkampf> laden() throws IOException, ClassNotFoundException
	{
		List<Wettkampf> liste = new ArrayList<>();
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Wettkampf.ser"));
		Wettkampf w;
		w = (Wettkampf)os.readObject();
		while (w != null)
		{
			liste.add(w);
			System.out.println("Deserialisiert: Hier noch Wettkampf Infos eingeben");
			try
			{
				w = (Wettkampf)os.readObject();
			} catch (EOFException e)
			{
				w = null;
			}
		}
		os.close();
		return liste;
	}

	@Override
	public void speichern(List<Wettkampf> wetteListe) throws FileNotFoundException, IOException
	{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Wettkampf.ser"));
		Iterator<Wettkampf> it = wetteListe.iterator();
		while (it.hasNext())
			os.writeObject(it.next());
		os.close();
	}

}

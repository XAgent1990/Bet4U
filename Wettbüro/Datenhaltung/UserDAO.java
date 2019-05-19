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

import Fachlogik.Userverwaltung.*;

public class UserDAO implements IUserDAO
{
	public List<Benutzer> laden() throws IOException, ClassNotFoundException
	{
		List<Benutzer> liste = new ArrayList<>();
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Benutzer.ser"));
		Benutzer b;
		b = (Benutzer)os.readObject();
		while (b != null)
		{
			liste.add(b);
			System.out.println(
					"Deserialisiert:\nEmail: " + b.getEmailAdresse() + "\nPasswort: " + b.getPasswort() + '\n');
			try
			{
				b = (Benutzer)os.readObject();
			} catch (EOFException e)
			{
				System.out.println("EOF!");
				b = null;
			}
		}
		os.close();
		return liste;
	}

	@Override
	public void speichern(List<Benutzer> benutzerListe) throws FileNotFoundException, IOException
	{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Benutzer.ser"));
		Iterator<Benutzer> it = benutzerListe.iterator();
		while (it.hasNext())
			os.writeObject(it.next());
		os.close();
	}
}

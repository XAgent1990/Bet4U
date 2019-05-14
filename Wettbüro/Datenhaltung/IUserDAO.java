package Datenhaltung;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Fachlogik.Userverwaltung.*;

public interface IUserDAO {

	List<Benutzer> laden() throws IOException, ClassNotFoundException;

	void speichern(List<Benutzer> benutzerListe) throws FileNotFoundException, IOException;

}

package Datenhaltung;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Fachlogik.Wettverwaltung.Wette;

public interface IWetteDAO
{

	List<Wette> laden() throws IOException, ClassNotFoundException;

	void speichern(List<Wette> liste) throws FileNotFoundException, IOException;

}

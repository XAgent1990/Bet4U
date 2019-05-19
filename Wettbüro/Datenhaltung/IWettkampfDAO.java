package Datenhaltung;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Fachlogik.Wettkampfverwaltung.Wettkampf;

public interface IWettkampfDAO {

	void speichern(List<Wettkampf> liste) throws FileNotFoundException, IOException;

	List<Wettkampf> laden() throws IOException, ClassNotFoundException;

}

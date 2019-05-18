package Datenhaltung;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Fachlogik.Teamverwaltung.*;

public interface ITeamDAO {

	List<Team> laden() throws IOException, ClassNotFoundException;

	void speichern(List<Team> teamListe) throws FileNotFoundException, IOException;

}

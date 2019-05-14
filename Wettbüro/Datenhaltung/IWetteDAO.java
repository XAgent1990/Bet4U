package Datenhaltung;

import java.util.List;

import Fachlogik.Wettverwaltung.Wette;

public interface IWetteDAO
{

	List<Wette> laden();

	void speichern(List<Wette> liste);

}

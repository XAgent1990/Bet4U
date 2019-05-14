package Datenhaltung;

import java.util.List;

import Fachlogik.Wettkampfverwaltung.Wettkampf;

public interface IWettkampfDAO {

	void speichern(List<Wettkampf> liste);

	List<Wettkampf> laden();

}

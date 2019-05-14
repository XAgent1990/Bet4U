package Fachlogik.Wettverwaltung;

import Fachlogik.Wettkampfverwaltung.UngueltigeBezeichnungException;
import Fachlogik.Wettkampfverwaltung.Wettkampf;

public class Punktewette extends Wette {

	public Punktewette(int id, String typ, String bezeichnung, Wettkampf wettkampf)
			throws UngueltigeBezeichnungException
	{
		super(id, typ, bezeichnung, wettkampf);
		// TODO Auto-generated constructor stub
	}

}

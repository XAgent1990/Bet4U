import Datenhaltung.*;
import Fachlogik.Wettbüro;
import Fachlogik.Teamverwaltung.*;
import Fachlogik.Wettkampfverwaltung.*;
import Fachlogik.Wettverwaltung.*;
import Fachlogik.Userverwaltung.*;
import GUI.Controller;

public class Main {

	public static void main(String[] args) {
		Wettkampfverwaltung wettkampfverwaltung = new Wettkampfverwaltung(
				new WettkampfDAO());
		Wettverwaltung wettverwaltung = new Wettverwaltung(
				new WetteDAO());
		Teamverwaltung teamverwaltung = new Teamverwaltung(
				new TeamDAO());
		Userverwaltung userverwaltung = new Userverwaltung(
				new UserDAO());
		Wettbüro wettbüro = new Wettbüro(userverwaltung, wettverwaltung, wettkampfverwaltung, teamverwaltung, "Bet4U");
		Controller controller = new Controller(wettbüro);
		controller.start();
	}
}

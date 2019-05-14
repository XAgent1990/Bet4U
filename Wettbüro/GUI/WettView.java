package GUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;

import Fachlogik.Wettkampfverwaltung.Wettkampf;

@SuppressWarnings("serial")
public class WettView extends JDialog
{
	public WettView(Window owner, Controller controller, Wettkampf wettkampf)
	{
		super(owner, "Übersicht zum Wetten zu " + wettkampf);
		setModal(true);
		setSize(700, 200);
		setMinimumSize(new Dimension(700, 200));

		Point p = owner.getLocationOnScreen();
		setLocation(p.x + 100, p.y + 100);

		JButton setzen = new JButton("Wette setzen");
		add("East", setzen);

		setzen.addActionListener(controller.new WettViewListener());

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}

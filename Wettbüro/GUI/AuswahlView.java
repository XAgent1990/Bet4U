package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

import Fachlogik.Wettkampfverwaltung.*;
import GUI.ModelsAndFilters.*;

@SuppressWarnings("serial")
public class AuswahlView extends JInternalFrame
{
	private MyTableModel tabellenModell;
	private JTable wettkampfTabelle;
	private TableRowSorter<MyTableModel> sorter;

	// Kombobox zur Auswahl des Wettkampftypen
	private JComboBox<String> typAuswahl;

	private java.util.List<Wettkampf> wettkampfliste;

	public AuswahlView(Window owner, Controller controller, java.util.List<Wettkampf> wettkampfliste)
	{
		super("Wettkampfliste", false, // resizable
				false, // closable
				false, // maximizable
				false);// iconifiable

		setSize(400, 300);
		setMinimumSize(new Dimension(400, 250));
		setLayout(new BorderLayout());

		// V6.0
		this.wettkampfliste = wettkampfliste;

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton wetten = new JButton("Wetten");
		buttonPanel.add(wetten);
		add(buttonPanel, BorderLayout.SOUTH);

		/*********************************************************************/
		/** Sortierbare Tabelle statt JList, ComboBox für Kategorien-Filter **/
		/*********************************************************************/
		JPanel panelCenter = new JPanel(new BorderLayout());
		panelCenter.setBorder(new LineBorder(Color.BLACK, 2));

		String[] spaltenUeberschriften = { "Typ", "Bezeichnung"};
		// zunächst 0 Zeilen
		tabellenModell = new MyTableModel(spaltenUeberschriften, 0);
		wettkampfTabelle = new JTable(tabellenModell);
		wettkampfTabelle.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fuelleTabelleMitWettkämpfen(wettkampfliste);

		// Sorter erzeugen und Tabelle zuordnen
		sorter = new TableRowSorter<MyTableModel>(tabellenModell);
		wettkampfTabelle.setRowSorter(sorter);

		// Filter setzen (Siehe GUI.ModelsAndFilters)
		sorter.setRowFilter(new TypFilter(""));

		JPanel typen = new JPanel();
		// Combobox zur Auswahl des Typen
		typAuswahl = new JComboBox<String>();
		typAuswahl.addItem("");
		typAuswahl.addItem("Tunier");
		typAuswahl.addItem("Spiel");
		typen.add(new JLabel("Auswahl des Typen"));
		typen.add(typAuswahl);

		// Tabelle --> JScrollPanel, da zweiteilig (Header, Inhalt)
		panelCenter.add(new JScrollPane(wettkampfTabelle), BorderLayout.CENTER);
		panelCenter.add(typen, BorderLayout.SOUTH);

		add(panelCenter, BorderLayout.CENTER);

		/**************************************************************************/

		// Beobachter für Combobox
		typAuswahl.addActionListener(controller.new AuswahlListener());

		setVisible(true);
	}
	
	private void fuelleTabelleMitWettkämpfen(java.util.List<Wettkampf> wettkampfliste)
	{
		Object[] zeile = new Object[2];
		for (Wettkampf wettkampf : wettkampfliste)
		{
			zeile[0] = wettkampf.getTyp();
			zeile[1] = wettkampf.getBezeichnung();
			tabellenModell.addRow(zeile);
		}
		System.out.println("Testweise Platzhalterwettkämpfe");
		zeile[0] = "Spiel";
		zeile[1] = "Platzhalterteam A vs. Platzhalterteam B";
		tabellenModell.addRow(zeile);
		zeile[0] = "Tunier";
		zeile[1] = "Platzhalterweltmeisterschaft";
		tabellenModell.addRow(zeile);
	}
	
	private int getListSelection()
	{
		int selected = wettkampfTabelle.getSelectedRow();
		if(selected != -1)
			selected = wettkampfTabelle.convertRowIndexToModel(selected);
		return selected;
	}

	Wettkampf getSelectedWettkampf()
	{
		int selected = getListSelection();
		if(!(selected >= 0))
			return null;
		else
			return wettkampfliste.get(selected);
	}
	
	void clearSelection()
	{
		wettkampfTabelle.clearSelection();
	}
	
	void filter(String typ)
	{
		sorter.setRowFilter(new TypFilter(typ));
	}

	public boolean auswahlVorhanden()
	{
		return getSelectedWettkampf() != null;
	}
}

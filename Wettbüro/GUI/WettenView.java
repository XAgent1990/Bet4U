package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;

import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

import Fachlogik.Wettverwaltung.*;

@SuppressWarnings("serial")
public class WettenView extends JDialog
{

	private DefaultListModel<SubEntry<Wette, Integer>> listenModell;
	private JList<SubEntry<Wette, Integer>> anzeigeListe;

	public WettenView(Window owner, Controller controller, SortedMap<Wette, Integer> wettenMap)
	{
		super(owner, "Übersicht über Ihre Wetten");
		setModal(true);
		setSize(700, 200);
		setMinimumSize(new Dimension(700, 200));

		Point p = owner.getLocationOnScreen();
		setLocation(p.x + 100, p.y + 100);

		JButton wetten = new JButton("Wetten");
		add("East", wetten);

		listenModell = new DefaultListModel<SubEntry<Wette, Integer>>();
		anzeigeListe = new JList<SubEntry<Wette, Integer>>(listenModell);
		anzeigeListe.setEnabled(false);
		add(anzeigeListe, BorderLayout.CENTER);
		fuelleWettenInListe(wettenMap);

		wetten.addActionListener(controller.new WettViewListener());

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void fuelleWettenInListe(SortedMap<Wette, Integer> artikelMap)
	{
		listenModell.removeAllElements();
		Set<Entry<Wette, Integer>> es = artikelMap.entrySet();
		for (Entry<Wette, Integer> e : es)
		{
			SubEntry<Wette, Integer> se = new SubEntry<Wette, Integer>(e.getKey(), e.getValue());
			listenModell.addElement(se);
		}
	}

	private class SubEntry<K, V> implements Entry<K, V>
	{
		private K key;
		private V value;

		public SubEntry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}

		public K getKey()
		{
			return key;
		}

		public V getValue()
		{
			return value;
		}

		public V setValue(V value)
		{
			V oldvalue = this.value;
			this.value = value;
			return oldvalue;
		}

		public String toString()
		{
			return key.toString();
		}
	}
}

package GUI.ModelsAndFilters;

import javax.swing.table.DefaultTableModel;

/**
 * VL06: MyTableModel für Darstellung der Artikel in Form einer Tabelle
 * 
 * MyTableModel überschreibt Methode getColumnClass.
 * Dadurch kann ein RowSorter auf den Typen der Spalten arbeiten.
 * Implementieren diese Typen die Comparable-Schnittstelle, werden die
 * Elemente in einer Spalte entsprechend der compareTo-Methode
 * sortiert.
 * Ohne Überschreiben gibt getColumnClass Object.class zurück.
 * Dann wird zum Sortieren auf die toString-Methode der Objekte
 * zurückgegriffen
 *  
**/
@SuppressWarnings("serial")
public class MyTableModel extends DefaultTableModel {
	public MyTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}
	
	MyTableModel(Object[][] rows, String[] columns) {
		super(rows, columns);
	}
	
	public Class<?> getColumnClass(int column) {
		if (getRowCount() > 0 && column >= 0 && column < getColumnCount())
			return getValueAt(0, column).getClass();
		else
			return Object.class;
	}
}

package GUI.ModelsAndFilters;

import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;

public class TypFilter extends RowFilter<MyTableModel, Integer> {

	private String typ;

	public TypFilter(String typ) {
		this.typ = typ;
	}

	public boolean include(Entry<? extends MyTableModel, ? extends Integer> entry) {
		String typ = (String) entry.getValue(0);
		if (this.typ == "")
			return true;
		else
			return typ == this.typ;
	}
}

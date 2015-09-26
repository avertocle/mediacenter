package mc.gui.library;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import mc.gui.ColorConsts;

public class TableCollections extends JTable {

	private static final long serialVersionUID = 1L;

	TableModelCollections tmodel;

	public TableCollections(TableModelCollections tmodel) {
		super(tmodel);
		this.tmodel = tmodel;
		setTableProperties();
	}

	private void setTableProperties() {
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setFillsViewportHeight(true);
		this.setShowGrid(false);

		this.setRowHeight(40);
		this.setIntercellSpacing(new Dimension(5, 10));

		this.setBackground(ColorConsts.Table_Collections.clrBg);
		this.setForeground(ColorConsts.Table_Collections.clrFg);
		this.setFont(ColorConsts.Table_Collections.font);

		fixColumnWidth(TableModelCollections.colNumCollection, 330);
		fixColumnWidth(TableModelCollections.colNumUse, 35);
	}

	private void fixColumnWidth(int colNo, int width) {
		this.getColumnModel().getColumn(colNo).setMaxWidth(width);
		this.getColumnModel().getColumn(colNo).setPreferredWidth(width);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
			c.setForeground(ColorConsts.Table_Collections.clrCellFg);
			c.setBackground(ColorConsts.Table_Collections.clrCellBg);
		return c;
	}
}

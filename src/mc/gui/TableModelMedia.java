package mc.gui;

import javax.swing.table.DefaultTableModel;

public class TableModelMedia extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private static String[] colnames = {"Name"};
	
	public TableModelMedia(){
		super(colnames, 0);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
}

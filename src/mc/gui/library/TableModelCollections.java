package mc.gui.library;

import javax.swing.table.DefaultTableModel;

public class TableModelCollections extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] colnames = {"Collection", "Use"};
	
	public static final int colNumCollection= 0;
	public static final int colNumUse = 1;
	
	public TableModelCollections() {
		super(colnames, 0);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		switch(column){
		case colNumCollection:
			return false;
		case colNumUse:
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		switch(column){
		case colNumCollection:
			return String.class;
		case colNumUse:
			return Boolean.class;
		default:
			return String.class;
		}
	}
}

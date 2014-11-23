package mc.gui.media;

import javax.swing.table.DefaultTableModel;

public class TableModelMedia extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private static String[] colnames = {"File", "Name", "Year", "Director"};
	
	public static int colNumFile = 0;
	public static int colNumName = 1;
	public static int colNumYear = 2;
	public static int colNumDirector = 3;
	
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

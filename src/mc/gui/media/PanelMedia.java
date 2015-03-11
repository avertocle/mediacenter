package mc.gui.media;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import mc.event.g2c.RootEventG2C;
import mc.event.g2g.RootEventG2G;
import mc.gui.ColorConsts;
import mc.utils.Logger;

public class PanelMedia extends JPanel {
 
	private static final long serialVersionUID = 1L;

	private JTextField tx_tableFilterText;
	private TableModelMedia tmodelMedia;
	private TableRowSorter<TableModelMedia> tableSorter;
	private TableMedia tableMedia;
	private JScrollPane sp_tableMedia;
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private MapMediaTable mapMediaTable;
	
	public PanelMedia(ConcurrentLinkedQueue<RootEventG2C> gcToPc, ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue){
		super();
		this.gcToPc = gcToPc;
		mapMediaTable = new MapMediaTable();
		makeUIElements();
		makePanel();
	}
	
	public void reloadWholeLibrary(List<Object[]> list) {
		clearEverything();
		int index = 0;
		for(Object[] rowData : list){
			index = addToTableModel(new Object[]{rowData[2], rowData[3], rowData[4], rowData[5], rowData[6]});
			mapMediaTable.storeMedia(index, (Integer)(rowData[0]), (String)(rowData[1]));
		}
	}
	
	private void clearEverything() {
		tmodelMedia.setRowCount(0);
		mapMediaTable.clearAll();
	}

	private int addToTableModel(Object[] objects) {
		tmodelMedia.addRow(objects);
		return tmodelMedia.getRowCount() - 1;
	}

	/************* Methods ****************************************/
	
	private void makeUIElements() 
	{
		tmodelMedia = new TableModelMedia();
		tableSorter = new TableRowSorter<TableModelMedia>(tmodelMedia);
		tableMedia = new TableMedia(mapMediaTable, tmodelMedia, tableSorter, gcToPc);
		sp_tableMedia = new JScrollPane(tableMedia);
		sp_tableMedia.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		makeFilterField();
	}
	
	private void makePanel() 
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints lc = new GridBagConstraints();
		lc.fill = GridBagConstraints.BOTH;
		lc.gridwidth = 3;
		lc.gridx = lc.gridy = 0;
		this.add(tx_tableFilterText, lc);

		lc.gridy++;	lc.weightx = 1; lc.weighty = 1;
		this.add(sp_tableMedia, lc);

		this.setBackground(ColorConsts.clrPanelBg);
	}
	
	private void makeFilterField(){
		tx_tableFilterText = new JTextField(50);
	    //Whenever filterText changes, invoke newFilter.
		tx_tableFilterText.getDocument().addDocumentListener(
	            new DocumentListener() {
	                public void changedUpdate(DocumentEvent e) {
	                	newMovieFilter();
	                }
	                public void insertUpdate(DocumentEvent e) {
	                	newMovieFilter();
	                }
	                public void removeUpdate(DocumentEvent e) {
	                	newMovieFilter();
	                }
	            });
	}

    private void newMovieFilter() 
    {
		RowFilter<TableModelMedia, Object> rf = null;
		String filterValue = "";
		filterValue = (tx_tableFilterText.getText());
		
		try 
		{	
			filterValue = "(?i)" + filterValue;
			rf = RowFilter.regexFilter(filterValue);
			tableSorter.setRowFilter(rf);
			return;
		} 
		catch (Exception e) 
		{	
			tableSorter.setRowFilter(null);
			Logger.logException(e);	
			return;
		}
	
	}
    
//    private <M, I> RowFilter<M, I> getYearFilter(int year_s, int year_e, int col){
//    	List<RowFilter<M,I>> rfl = new ArrayList<RowFilter<M,I>>();
//    	rfl.add((RowFilter<M, I>) RowFilter.numberFilter(ComparisonType.AFTER, year_s-1, col));
//    	rfl.add((RowFilter<M, I>) RowFilter.numberFilter(ComparisonType.BEFORE, year_e+1, col));
//    	return RowFilter.andFilter(rfl);
//    }
//    
//    private <M, I> RowFilter<M, I> getYearFilter(int year_s, int year_e){
//    
//    }
//    private <M, I> RowFilter<M, I> getYearFilter(int year_s, int year_e){
//
//    }
}

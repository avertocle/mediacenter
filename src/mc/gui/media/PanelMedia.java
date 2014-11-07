package mc.gui.media;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import mc.event.g2c.RootEventG2C;
import mc.event.g2g.RootEventG2G;
import mc.gui.ColorConsts;

public class PanelMedia extends JPanel {
 
	private static final long serialVersionUID = 1L;

	private TableModelMedia tmodelMedia;
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
			index = addToTableModel(new Object[]{rowData[0]});
			mapMediaTable.storeMedia(index, (Integer)(rowData[1]), (String)(rowData[2]));
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
		tableMedia = new TableMedia(mapMediaTable, tmodelMedia, gcToPc);
		sp_tableMedia = new JScrollPane(tableMedia);
		sp_tableMedia.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
	
	private void makePanel() 
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints lc = new GridBagConstraints();
		lc.fill = GridBagConstraints.BOTH;
		lc.gridwidth = 3;
		lc.gridx = lc.gridy = 0;
		lc.weightx = 1; lc.weighty = 1;
		this.add(sp_tableMedia, lc);
		this.setBackground(ColorConsts.clrPanelBg);
	}

}

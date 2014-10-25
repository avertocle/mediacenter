package mc.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import mc.event.g2c.RootEventG2C;

public class PanelMedia extends JPanel {
 
	private static final long serialVersionUID = 1L;

	private TableModelMedia tmodelMedia;
	private TableMedia tableMedia;
	private JScrollPane sp_tableMedia;
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	
	public PanelMedia(ConcurrentLinkedQueue<RootEventG2C> gcToPc){
		super();
		this.gcToPc = gcToPc;
		makeUIElements();
		makePanel();
	}
	
	public void reloadWholeLibrary(List<Object[]> list) {
		for(Object[] rowData : list){
			tmodelMedia.addRow(rowData);
		}
	}
	
	/************* Methods ****************************************/
	
	private void makeUIElements() 
	{
		tmodelMedia = new TableModelMedia();
		tableMedia = new TableMedia(tmodelMedia, gcToPc);
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

package mc.gui.library;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.event.g2g.RootEventG2G;
import mc.event.g2g.RootEventG2G.EventTypeG2G;
import mc.gui.ColorConsts;
import mc.gui.GuiLibrary;
import mc.utils.Logger;

public class PanelCollectionMgmt extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue;

	private JButton bt_add;
	private JButton bt_close;
	private JTabbedPane tb_collection;
	private List<PanelCollection> panelCollections;
	
	private static final String Acmd_Add = "Acmd_Add";
	private static final String Acmd_Close = "Acmd_Close";

	public PanelCollectionMgmt(ConcurrentLinkedQueue<RootEventG2C> gcToPc, ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue) {
		this.gcToPc = gcToPc;
		this.guiInternalQueue = guiInternalQueue;
		makeUIElements();
		makePanel();
	}

	public void refresh(List<PanelCollection> collections) {
		panelCollections.clear();
		panelCollections.addAll(collections);
		int tabCount = tb_collection.getTabCount();
		for(int i=0; i<tabCount; i++){
			tb_collection.removeTabAt(0);
		}
		for(PanelCollection panelCollection : panelCollections){
			tb_collection.addTab(panelCollection.getCollectionName(), panelCollection);
			tb_collection.revalidate();
		}
		this.revalidate();
	}

	private void makeUIElements() {

		bt_add = GuiLibrary.makeButton("Add New Collection", new BCL(), 0, Acmd_Add, -1);
		bt_close = GuiLibrary.makeButton("Close", new BCL(), 0, Acmd_Close, -1);
		panelCollections = new ArrayList<>();
		tb_collection = new JTabbedPane();
		tb_collection.addTab("xxx", new JPanel());
	}

	private void makePanel() {
		this.setBackground(ColorConsts.clrPanelBg);
		this.setLayout(new GridBagLayout());
		GridBagConstraints lc = new GridBagConstraints();
		lc.anchor = GridBagConstraints.LINE_START;
		lc.fill = GridBagConstraints.BOTH;
		lc.weightx = lc.weighty = 10;
		lc.gridx = lc.gridy = 0;
		lc.insets = new Insets(10, 0, 0, 0);
		lc.gridwidth = 8;
		this.add(tb_collection, lc);

		lc.fill = GridBagConstraints.NONE;
		lc.weightx = lc.weighty = 1;
		lc.gridx = 0;
		lc.gridy++;
		lc.insets = new Insets(20, 10, 0, 10);
		lc.gridwidth = 1;
		this.add(bt_add, lc);
		lc.gridx++;
		this.add(bt_close, lc);
		lc.gridx++;
		lc.fill = GridBagConstraints.HORIZONTAL;
		lc.gridwidth = 6;
		this.add(new JLabel(""), lc);
	}

	class BCL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Logger.logFrequentEvent(e.toString());

			String acmd = e.getActionCommand();

			switch (acmd) {
				case Acmd_Add: {
					String choice = JOptionPane.showInputDialog(null, "Enter a collection name", "Add Collection", JOptionPane.OK_CANCEL_OPTION);
					if(choice != null && !choice.trim().isEmpty()){
						gcToPc.add(new RootEventG2C(EventTypeG2C.AddCollection, choice));
					}
					return;
				}
				case Acmd_Close: {
					guiInternalQueue.add(new RootEventG2G(EventTypeG2G.CloseConsole_CollectionMgmt, null));
					break;
				}
				default:
					return;
			}
		}

	}
}

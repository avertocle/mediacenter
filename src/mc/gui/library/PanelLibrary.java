package mc.gui.library;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.event.g2g.RootEventG2G;
import mc.event.g2g.RootEventG2G.EventTypeG2G;
import mc.gui.ColorConsts;
import mc.gui.GuiLibrary;
import mc.utils.Logger;

public class PanelLibrary extends JPanel {

	private static final long serialVersionUID = 1L;
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue;

	private JButton bt_add;
	private JButton bt_rescan;
	private JButton bt_close;
	private TableModelCollections tmodel;
	private TableCollections table;

	private static final String Acmd_Add = "Acmd_Add";
	private static final String Acmd_Rescan = "Acmd_Rescan";
	private static final String Acmd_Close = "Acmd_Close";

	public PanelLibrary(ConcurrentLinkedQueue<RootEventG2C> gcToPc, ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue) {
		this.gcToPc = gcToPc;
		this.guiInternalQueue = guiInternalQueue;
		makeUIElements();
		makePanel();
	}

	public void refresh(List<String> dirList) {
		tmodel.setRowCount(0);
		for (String d : dirList) {
			tmodel.addRow(new Object[] { d, true });
		}
	}

	private void makeUIElements() {

		bt_add = GuiLibrary.makeButton("Add", new BCL(), 0, Acmd_Add, -1);
		bt_rescan = GuiLibrary.makeButton("Rescan", new BCL(), 0, Acmd_Rescan, -1);
		bt_close = GuiLibrary.makeButton("Close", new BCL(), 0, Acmd_Close, -1);

		tmodel = new TableModelCollections();
		table = new TableCollections(tmodel);
	}

	private void makePanel() {
		this.setBackground(ColorConsts.clrPanelBg);
		this.setLayout(new GridBagLayout());
		GridBagConstraints lc = new GridBagConstraints();
		lc.anchor = GridBagConstraints.LINE_START;
		lc.fill = GridBagConstraints.BOTH;
		lc.weightx = lc.weighty = 1;
		lc.gridx = lc.gridy = 0;
		lc.insets = new Insets(10, 0, 0, 0);
		lc.gridwidth = 3;
		this.add(table, lc);

		lc.fill = GridBagConstraints.HORIZONTAL;
		lc.gridx = 0;
		lc.gridy++;
		lc.insets = new Insets(20, 10, 0, 10);
		lc.gridwidth = 1;
		this.add(bt_add, lc);
		lc.gridx++;
		this.add(bt_rescan, lc);
		lc.gridx++;
		this.add(bt_close, lc);

	}

	class BCL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Logger.logFrequentEvent(e.toString());

			String acmd = e.getActionCommand();

			switch (acmd) {
				case Acmd_Add: {
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int choice = fc.showOpenDialog(PanelLibrary.this);
					if (choice == JFileChooser.APPROVE_OPTION) {
						File dir = fc.getSelectedFile();
						gcToPc.add(new RootEventG2C(EventTypeG2C.AddDirToLib, dir));
					}
					return;
				}
				case Acmd_Rescan: {
					List<String> list = new ArrayList<String>();
					int rows = tmodel.getRowCount();
					for (int i = 0; i < rows; i++) {
						if ((boolean) tmodel.getValueAt(i, TableModelCollections.colNumUse)) {
							list.add((String) tmodel.getValueAt(i, TableModelCollections.colNumCollection));
						}
					}
					gcToPc.add(new RootEventG2C(EventTypeG2C.RescanCollection, list));
					break;
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

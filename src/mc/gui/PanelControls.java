package mc.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JPanel;

import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.event.g2g.RootEventG2G;
import mc.utils.Logger;

public class PanelControls extends JPanel {
 
	private static final long serialVersionUID = 1L;

	private JButton bt_manageCollections;
	private JButton bt_rescanCollections;
	
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	
	private static final String Acmd_ManageCollections = "Acmd_ManageCollections";
	private static final String Acmd_RescanCollections = "Acmd_RescanCollections";
	
	public PanelControls(ConcurrentLinkedQueue<RootEventG2C> gcToPc, ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue) {
		this.gcToPc = gcToPc;
		makeUIElements();
		makePanel();
	}

	private void makeUIElements() {
		bt_manageCollections = GuiLibrary.makeButton("Manage Collections", new BCL(), 0, PanelControls.Acmd_ManageCollections, -1);
		bt_rescanCollections = GuiLibrary.makeButton("Rescan Collections", new BCL(), 0, PanelControls.Acmd_RescanCollections, -1);
	}

	private void makePanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints lc = new GridBagConstraints();
		lc.fill = GridBagConstraints.NONE;
		lc.gridx = lc.gridy = 0;
		this.add(bt_manageCollections, lc);		lc.gridx++;
		this.add(bt_rescanCollections, lc);
	}
	
	class BCL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Logger.logFrequentEvent(e.toString());
			String acmd = e.getActionCommand();
			if(acmd.equals(Acmd_ManageCollections)){
				gcToPc.add(new RootEventG2C(EventTypeG2C.ModelRequest_DirList, null));
			}
			if(acmd.equals(Acmd_RescanCollections)){
				gcToPc.add(new RootEventG2C(EventTypeG2C.RescanCollection, null));
			}
			else{
				return;
			}
		}
		
	}

}

package mc.gui.library;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import mc.event.g2c.RootEventG2C;
import mc.event.g2g.RootEventG2G;

public class DialogLibrary extends JDialog {

	private static final long serialVersionUID = 1L;

	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue;
	private PanelCollectionMgmt panelCollectionMgmt;
	private JFrame parentframe;

	public DialogLibrary(ConcurrentLinkedQueue<RootEventG2C> gcToPc,
			ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue, JFrame parentframe) {
		this.gcToPc = gcToPc;
		this.guiInternalQueue = guiInternalQueue;
		this.parentframe = parentframe;
		makeUIElements();
	}

	public void showDialog(Map<String, List<String>> collectionMap) {
		List<PanelCollection> panelCollections = getPanelsFromMap(collectionMap);
		panelCollectionMgmt.refresh(panelCollections);
		
		this.setSize(new Dimension(800, 500));
		this.getContentPane().add(new JScrollPane(panelCollectionMgmt));
		if (parentframe != null) {
			this.setLocationRelativeTo(parentframe);
		}
		else {
			this.setLocationByPlatform(true);
		}
		this.setVisible(true);
	}

	public void refreshDialog(Map<String, List<String>> collectionMap) {
		List<PanelCollection> panelCollections = getPanelsFromMap(collectionMap);
		panelCollectionMgmt.refresh(panelCollections);
	}
	
	public List<PanelCollection> getPanelsFromMap(Map<String, List<String>> collectionMap){
		PanelCollection panelCollection;
		List<PanelCollection> panelCollections = new ArrayList<>();
		for(Entry<String, List<String>> entry : collectionMap.entrySet()){
			panelCollection = new PanelCollection(gcToPc, guiInternalQueue, entry.getKey());
			panelCollection.refresh(entry.getValue());
			panelCollections.add(panelCollection);
		}
		return panelCollections;
	}

	private void makeUIElements() {
		panelCollectionMgmt = new PanelCollectionMgmt(gcToPc, guiInternalQueue);
		panelCollectionMgmt.setBackground(Color.pink);
	}

//	public static void main(String args[]) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				DialogLibrary dialogLibrary = new DialogLibrary(null, null, new JFrame());
//				dialogLibrary.showDialog(Arrays.asList(new String[]{"aaaaaaaa", "bbbbbbbb", "cccccccc"}));
//			}
//		});
//	}
}

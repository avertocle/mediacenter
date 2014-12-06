package mc.gui.library;

import java.awt.Dimension;
import java.util.List;
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
	private PanelLibrary panelLibrary;
	private JFrame parentframe;

	public DialogLibrary(ConcurrentLinkedQueue<RootEventG2C> gcToPc,
			ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue, JFrame parentframe) {
		this.gcToPc = gcToPc;
		this.guiInternalQueue = guiInternalQueue;
		this.parentframe = parentframe;
		makeUIElements();
	}

	public void showDialog(List<String> dirList) {
		panelLibrary.refresh(dirList);
		panelLibrary.revalidate();
		this.setSize(new Dimension(400, 300));
		this.getContentPane().add(new JScrollPane(panelLibrary));
		if (parentframe != null) {
			this.setLocationRelativeTo(parentframe);
		}
		else {
			this.setLocationByPlatform(true);
		}
		this.setVisible(true);
	}

	public void refreshDialog(List<String> dirList) {
		panelLibrary.refresh(dirList);
		panelLibrary.revalidate();
	}

	private void makeUIElements() {
		panelLibrary = new PanelLibrary(gcToPc, guiInternalQueue);
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

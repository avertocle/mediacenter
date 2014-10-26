package mc.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mc.constants.GeneralConstants;
import mc.event.g2c.RootEventG2C;

public class MainPageGui {

	public boolean isGuiInitialised = false;

	/*********************************************************************
	 * /* Variables /
	 *********************************************************************/

	private JFrame frame;

	private PanelMedia panelMedia;
	private PanelControls panelControls;

	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;

	public MainPageGui(ConcurrentLinkedQueue<RootEventG2C> gcToPc) {
		this.gcToPc = gcToPc;
	}

	/*********************************************************************
	 * /* Methods /
	 *********************************************************************/

	public void start() {
		GuiLibrary.setUILookAndFeelToNimbus();
		// makeComponentSizeAndLocations();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				makeUIElements();
				drawFrame();
				isGuiInitialised = true;
			}
		});
	}

	public void stop() {
		frame.dispose();
		return;
	}

	public void terminate() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.dispose();
			}
		});
	}

	/*********************************************************************
	 * /* Control Methods /
	 *********************************************************************/

	@SuppressWarnings("unchecked")
	public void reloadWholeLibrary(Object data) {
		List<Object[]> list = (List<Object[]>) (data);
		panelMedia.reloadWholeLibrary(list);
	}

	public void removeMedia(Object data) {
		// TODO Auto-generated method stub

	}

	public void addMedia(Object data) {
		// TODO Auto-generated method stub

	}

	/*********************************************************************
	 * /* Internal Methods /
	 *********************************************************************/

	private void makeUIElements() {
		panelControls = new PanelControls(gcToPc);
		panelMedia = new PanelMedia(gcToPc);
	}

	private void drawFrame() {
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints lc = new GridBagConstraints();

		lc.fill = GridBagConstraints.BOTH;
		lc.insets = new Insets(0, 0, 0, 0);

		lc.gridx = lc.gridy = 0;
		lc.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(panelControls, lc);

		lc.weightx = 1;
		lc.weighty = 1;
		lc.gridy++;
		lc.fill = GridBagConstraints.BOTH;
		mainPanel.add(panelMedia, lc);

		/* Making Frame */

		frame = new JFrame(GeneralConstants.frameHeading);

		frame.getContentPane().setLayout(new GridBagLayout());
		lc = new GridBagConstraints();
		lc.weightx = lc.weighty = 1;
		lc.gridx = lc.gridy = 0;
		lc.fill = GridBagConstraints.BOTH;
		frame.getContentPane().add(mainPanel, lc);

		Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(frameSize);

		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

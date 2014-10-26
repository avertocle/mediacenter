package mc.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import mc.utils.Logger;

/*
 * LIBRARY_VERSION : 2014_28_06_04_11
 */

public class GuiLibrary 
{
	
	private static Color[] clrButtonBg = {
		ColorConsts.clrButtonBg,			
		ColorConsts.clrButtonSpl1Bg,
		ColorConsts.clrButtonSpl2Bg,
		ColorConsts.clrButtonSpl3Bg
	};
	
	private static Color[] clrButtonFg = {
		ColorConsts.clrButtonFg,	
		ColorConsts.clrButtonSpl1Fg,
		ColorConsts.clrButtonSpl2Fg,
		ColorConsts.clrButtonSpl3Fg
	};

	public static void setUILookAndFeelToNimbus()
	{
		
		//UIManager.put("nimbusBase", Color.pink);
		UIManager.put("nimbusBlueGrey", Color.black);
		//UIManager.put("control", Color.yellow);
		
		try 
	 	{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} 
		catch(Exception e){	Logger.logException(e);		}
	}
	
	/*********************************************************************
	/* Buttons
	/*********************************************************************/
	
	public static JButton makeButton(String text, ActionListener al, int splFlag, String actionCmd, int mnemonic)
	{
		JButton bt = makeButton(text, al, splFlag);
		if(actionCmd != null && !actionCmd.isEmpty())
		{	bt.setActionCommand(actionCmd);	}
		
		if(mnemonic > 0)
		{	bt.setMnemonic(mnemonic);	}

		return bt;
	}
	
	public static JButton makeButton(String text, ActionListener al, int splFlag)
	{
		JButton bt = new JButton(text);
		bt.addActionListener(al);
		
		if(splFlag >= 0 && splFlag < clrButtonBg.length)
		{
			bt.setBackground(clrButtonBg[splFlag]);
			bt.setForeground(clrButtonFg[splFlag]);
		}
		
		return bt;
	}

	/*********************************************************************
	/* TextFields
	/*********************************************************************/
	
	public static JTextField makeTextField(int columns, String name)
	{
		JTextField tx = new JTextField();
		if(columns>0)
		{	tx.setColumns(columns);	}
		
		if(name !=null && !name.isEmpty())
		{	tx.setName(name);	}
		
		return tx;
	}
	
	/*********************************************************************
	/* Label
	/*********************************************************************/

	public static JLabel makelabel(String text, Font font, Color clrBg, Color clrFg, boolean opacity)
	{
		if(text == null) { text = "";	}
		JLabel lbl = new JLabel(text);
		
		if(clrBg !=null){	lbl.setBackground(clrBg);	}
		if(clrFg !=null){	lbl.setForeground(clrFg);	}
		if(font !=null) {	lbl.setFont(font);			}
		lbl.setOpaque(opacity);
		
		return lbl;
	}

	/*********************************************************************
	/* Internal Frames
	/*********************************************************************/
	
	public static void prepareIframe(JInternalFrame iframe, JPanel panel, int sizeX, int sizeY, Point location, boolean visible)
	{
		iframe.add(panel);
		iframe.setSize(sizeX, sizeY);
		iframe.setLocation(location);
		iframe.setVisible(visible);
		iframe.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	}
	
	public static void prepareIframe(JInternalFrame iframe, JTabbedPane panel, int sizeX, int sizeY, Point location, boolean visible)
	{
		iframe.add(panel);
		iframe.setSize(sizeX, sizeY);
		iframe.setLocation(location);
		iframe.setVisible(visible);
		iframe.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	}
	
	/*********************************************************************
	/* JFrame
	/*********************************************************************/

	
	public static JFrame makeJFrame(String heading, Dimension frameSize, int frameExtendedState, int defaultClose)
	{
		Dimension loc = Toolkit.getDefaultToolkit().getScreenSize();
		
		JFrame frame = new JFrame(heading);
		frame.setSize(frameSize);
		if(frameExtendedState > 0)
		{	frame.setExtendedState(frame.getExtendedState() | frameExtendedState);	}
		frame.setDefaultCloseOperation(defaultClose);
		frame.setLocation(loc.width/3, loc.height/3);
		return frame;
	}

	public static JFrame makeJFrame(String heading, Dimension frameSize, int frameExtendedState, int defaultClose, Point location, Image iconImage)
	{
		JFrame frame = makeJFrame(heading, frameSize, frameExtendedState, defaultClose);
		frame.setLocation(location);
		frame.setIconImage(iconImage);
		return frame;
	}

	/*********************************************************************
	/* Menu Item 
	/*********************************************************************/
	
	public static JMenuItem makeMenuItem(String text, String actionCommand, ActionListener al)
	{
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.setActionCommand(actionCommand);
		menuItem.addActionListener(al);
		return menuItem;
	}


	/*********************************************************************
	/* Border
	/*********************************************************************/
	
	public static Border getLineMarginBorder(Color clrBorder, int thickness, boolean rounded, int[] margin)
	{
		Border tborder = BorderFactory.createLineBorder(clrBorder, thickness, rounded);
		Border mborder = new EmptyBorder(margin[0], margin[1], margin[2], margin[3]);
		CompoundBorder cb = new CompoundBorder(tborder, mborder);
		return cb;
	}
	
	public static CompoundBorder getTittledBorder(String name, Color clrTittle, Color clrBorder)
	{
		int[] margin = new int[]{2,2,2,2};
		TitledBorder tborder;
		Border blackline = BorderFactory.createLineBorder(clrBorder, 1, true);
		tborder = BorderFactory.createTitledBorder(blackline, name);
		tborder.setTitleJustification(TitledBorder.LEFT);
		tborder.setTitleColor(clrTittle);
		
		Border mborder = new EmptyBorder(margin[0], margin[1], margin[2], margin[3]);
		CompoundBorder cb = new CompoundBorder(tborder, mborder);
		return cb;
	}	
	
}
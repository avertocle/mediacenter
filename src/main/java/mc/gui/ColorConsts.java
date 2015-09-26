package mc.gui;

import java.awt.Color;
import java.awt.Font;

public class ColorConsts 
{
	/**********************************************************************
	/*  General component and fonts 
	/**********************************************************************/
	
	public static Color clrUiDebugPink = Color.pink;
	public static Color clrUiDebugRed = Color.red;
	public static Color clrUiDebugGren = Color.green;
	public static Color clrUiDebugYellow = Color.yellow;
	
	public static Color clrFrameBg = Color.black;
	
	/* Panel */
	public static Color clrPanelBg = Color.black;
	public static Color clrPanelFg = Color.white;
	
	/* Button */
	public static Color clrButtonBg = new  Color(15, 50, 45);	 	// ShadeOf Green
	public static Color clrButtonFg = Color.white;				
	public static Font fontButton = new Font("SansSerif", Font.PLAIN, 12);

	public static Color clrButtonSpl1Bg = new Color(0, 27, 51);			// ShadeOf Green
	public static Color clrButtonSpl1Fg = Color.white;			
	
	public static Color clrButtonSpl2Bg = new Color(95,129,95);		// ShadeOf Brown
	public static Color clrButtonSpl2Fg = Color.white;			
	
	public static Color clrButtonSpl3Bg = new Color(140, 0, 0);			// ShadeOf Red
	public static Color clrButtonSpl3Fg = Color.white;

	/******** Message Panel *****************************************************/
	
	public static class Console_ManageCollections{
		public static Color clrPanelBg = ColorConsts.clrPanelBg;
		public static Color clrDirLabelBg = new Color(5, 60, 55);
		public static Color clrDirLabelFg = Color.white;
		public static Font fontDirLabel = new Font("SansSerif", Font.PLAIN, 15);
	}
	
	public static class Table_Media{
		
		public static Color clrBg = new Color(12,12,12);
		public static Color clrFg = Color.white;
		public static Font font = new Font("SansSerif", Font.PLAIN, 15);
		
		public static Color clrHeaderBg = new Color(30, 54, 33);
		public static Color clrHeaderFg = new Color(238, 238, 231);
		public static Font fontHeader = new Font("SansSerif", Font.BOLD, 11);
		
		public static Color clrCellBg = new Color(134, 220, 121);			// pastel green
		public static Color clrCellFg = new Color(255, 100, 100);			// pastel red
	}
	
	public static class Table_Collections{
		
		public static Color clrBg = new Color(12,12,12);
		public static Color clrFg = Color.white;
		public static Font font = new Font("SansSerif", Font.PLAIN, 15);

		public static Color clrCellBg = new Color(0, 80, 107);
		public static Color clrCellFg = Color.WHITE;			
	}
	
}


package mc.gui;

import java.awt.Color;
import java.awt.Font;

public class ColorConsts 
{
	// BG :: BackGround
	// FG :: ForeGround
	
	/**********************************************************************
	/*  LOGIN  
	/**********************************************************************/
	
	public static Color clrLoginPanelBg = Color.black;
	public static Color clrLoginFrameBg = Color.black;
	public static Color clrLoginButtonBg = new Color(0, 27, 51);
	public static Color clrLoginButtonFg = Color.white;
	
	/**********************************************************************
	/*  General component and fonts 
	/**********************************************************************/
	
	public static Color clrUiDebugPink = Color.pink;
	public static Color clrUiDebugRed = Color.red;
	public static Color clrUiDebugGren = Color.green;
	public static Color clrUiDebugYellow = Color.yellow;
	
	public static Color clrFrameBg = Color.black;
	
	/* Panel */
	public static Color clrPanelBg = new Color(20,20,20);
	public static Color clrPanelFg = Color.white;
	
	/* Label */
	public static Color clrLabelBg = Color.black;
	public static Color clrLabelFg = Color.white;
	public static Font fontLabel = new Font("SansSerif", Font.PLAIN, 12);
	
	/* Textfield */
	//public static Color clrTextboxDisabledBg = new Color(205, 205, 205);
	//public static Color clrTextboxDisabledFg = Color.black;
	
	//public static Color clrTextboxBg = new Color(205, 205, 205);
	//public static Color clrTextboxFg = Color.black;
	
	/* Combobox */
	//public static Color clrComboboxBg = new Color(205, 205, 205);
	//public static Color clrComboboxFg = Color.black;
	public static Font fontCombobox = new Font("SansSerif", Font.PLAIN, 10);
	
	/* Tabbed Pane */
//	public static Color clrTabbedPaneSelectedTab = Color.blue;
//	public static Color clrTabbedPaneBG = Color.black;
//	public static Color clrTabbedPaneFG = Color.white;
//	public static Font fontTabbedPane = new Font("SansSerif", Font.PLAIN, 15);
	
	/* Button */
	public static Color clrButtonBg = new Color(0, 27, 51);				// ShadeOf Blue
	public static Color clrButtonFg = Color.white;				
	public static Font fontButton = new Font("SansSerif", Font.PLAIN, 12);

	public static Color clrButtonSpl1Bg = new Color(95,129,95);			// ShadeOf Green
	public static Color clrButtonSpl1Fg = Color.white;			
	
	public static Color clrButtonSpl2Bg = new Color(102, 51, 0);		// ShadeOf Brown
	public static Color clrButtonSpl2Fg = Color.white;			
	
	public static Color clrButtonSpl3Bg = new Color(140, 0, 0);			// ShadeOf Red
	public static Color clrButtonSpl3Fg = Color.white;

	/******** Message Panel *****************************************************/
	
	public static Color clrLabelMsgBg = Color.black;
	public static Color clrLabelMsgNormalFg = new Color(51, 130, 204);
	public static Color clrLabelMsgErrorFg = Color.red;
	public static Font fontLabelMsg = new Font("SansSerif", Font.PLAIN, 15);
	
	/******** Menubar Colors **********************************/
	
//	public static Color clrMenubarBg = Color.black;
//	public static Color clrMenubarFg = Color.white;
//	public static Color clrMenuItemsBg = Color.black;
//	public static Color clrMenuItemsFg = Color.white;
//	
//	public static Font fontMenuBar = new Font("SansSerif", Font.PLAIN, 12);
//	public static Font fontMenuItem = new Font("SansSerif", Font.PLAIN, 12);
	
	/******** Confirmation Dialog Colors **********************************/
	
	public static Color clrConfirmDiagHeadingBg = Color.black;
	public static Color clrConfirmDiagHeadingFg = Color.cyan;
	
	public static Color clrConfirmDiagMessageBg = Color.black;
	public static Color clrConfirmDiagMessageFg = Color.yellow;;
	 
	public static Color clrConfirmDiagButtonBg = Color.red;
	public static Color clrConfirmDiagButtonFg = Color.white;
	
	public static Color clrConfirmDiagBg = Color.black;
	public static Color clrConfirmDiagFg = Color.white;
	
	/******** Add Strategy Panel *********************************/
	public static class AddStratPanelColors
	{
		public static Color clrErrorLabelAddStratBg =  Color.black;
		public static Color clrErrorLabelAddStratFg = Color.white;
		public static Font fontErrorLabelAddStrat = new Font("SansSerif", Font.ITALIC, 15);
		
		public static Color clrAddStratLegLabelsBg = Color.black;
		public static Color clrAddStratLegLabelFg = Color.white;
		public static Font fontAddStratLegLabels = new Font("SansSerif", Font.PLAIN, 12);

		public static Color clrAddStratCommonLabelsBg = Color.black;
		public static Color clrAddStratCommonLabelsfg = Color.white;
		public static Font fontAddStratCommonLabels = new Font("SansSerif", Font.PLAIN, 12);
		
		public static Color clrAddStratHeadingBg = Color.black;
		public static Color clrAddStratHeadingFg = new Color(51, 130, 204);
		public static Font fontAddStratHeading = new Font("SansSerif", Font.PLAIN, 20);
	}
	
	/******** Broadcast Panel Colors ***************************/
	public static class BcPanelColors
	{
		public static int sizeTableCellRow = 50;

		//public static Color clrPanelBroadcastBg = clrPanelBg;		
		public static Color clrPanelBroadcastBg = new Color(15,42,55);
		
		public static Color clrValueFg = Color.blue;
		public static Color clrValueFgInc = Color.green;
		public static Color clrValueFgDec = Color.red;
		public static Color clrValueFgSame = Color.white;
		public static Font fontMarketStatusValue = new Font("SansSerif", Font.PLAIN, 15);

		public static Color clrIndexNameBg = clrPanelBroadcastBg;
		public static Color clrIndexNameFg = Color.white;
		public static Font fontIndexName = new Font("SansSerif", Font.BOLD, 20);
		
		public static Color clrIndexValueBg = clrPanelBroadcastBg;
		public static Font fontIndexValue = new Font("SansSerif", Font.PLAIN, 30);
		
		public static Color clrIndexDeltaBg = clrPanelBroadcastBg;
		public static Font fontIndexDelta = new Font("SansSerif", Font.PLAIN, 15);
		
		public static Color clrStatNameBg = clrPanelBroadcastBg;		
		public static Color clrStatNameFg = new Color(153,0,153);
		public static Font fontStatName = new Font("SansSerif", Font.PLAIN, 15);

		public static Color clrStatValueBg = clrPanelBroadcastBg;
		public static Font fontStatValue = new Font("SansSerif", Font.PLAIN, 15);

	}
	
	/******** Posiiton Table Colors ***************************/
	public static class PosTableColors 
	{
		public static int sizeTableCellRow = 25;
		
		public static Color clrPosTableBg = new Color(20,20,20);
		public static Color clrPosTableFg = Color.white;
		public static Font fontPosTable = new Font("SansSerif", Font.PLAIN, 13);
		
		public static Color clrPosTableHeaderBg = Color.black;
		public static Color clrPosTableHeaderFg = Color.white;
		public static Font fontPosTableHeader = new Font("SansSerif", Font.PLAIN, 15);
		
		public static Color clrTableCellNormalBg = new Color(50,50,50, 100);
		public static Color clrTableCellNormalFg = new Color(51, 130, 204);

		public static Color clrTableCellPausedBg = new Color(14,192,201);
		public static Color clrTableCellPausedFg = Color.black;
		
		public static Color clrTableCellRMSBg = new Color(200,200,200);
		public static Color clrTableCellRMSFg = Color.black;
		
		public static Color clrTableCellActiveBg = new Color(255,124,25);
		public static Color clrTableCellActiveFg = Color.black;
		
		public static Color clrTableCellSendBg = Color.yellow;
		public static Color clrTableCellSendFg = Color.black;
		
		public static Color clrTableCellATPBg = clrTableCellNormalBg;			//	shade of 
		public static Color clrTableCellATPFg = Color.yellow;					//	shade of 
		
		public static Color clrTableCellStrikeBg = new Color(29, 41, 144, 150);		//	shade of blue
		public static Color clrTableCellStrikeFg = Color.white;					//	shade of 
		
		/** Editable cells **/
		public static Color clrTableCellSoqBg = new Color(255,165,0);
		public static Color clrTableCellSoqFg = Color.black;
		
		public static Color clrTableCellQtyBg = new Color(255,255,0);
		public static Color clrTableCellQtyFg = Color.black;;
		
		public static Color clrTableCellPriceBg = new Color(255,255,0);
		public static Color clrTableCellPriceFg = Color.black;
		
		public static Font fontEditableCells = new Font("SansSerif", Font.BOLD, 13);
		
		/** Selection **/

		public static Color clrTableCellSelectedBg = new Color(180, 200, 255);
		public static Color clrTableCellSelecteFg = new Color(180, 200, 255);

		
		public static Color clrTableCellActivateBg = clrTableCellNormalBg;
		
		public static Color clrTableCellCMPBg = clrTableCellNormalBg;	
		public static Color clrTableCellCMPBFg = new Color(255,0,128);		//new Color(128, 128, 0);
		public static Color clrTableCellCMPSFg = new Color(255,0,128);		//new Color(255, 165, 0);
		public static Font  fontCMPCells = new Font("SansSerif", Font.PLAIN, 13);
		
		public static Color clrTableCellCostFg = new Color(255,102,0);		//new Color(255, 165, 0);
	
		public static Color clrTableCellPosCompletedBg =  Color.green;
		public static Color clrTableCellPosCompletedFg =  Color.black;
		
		public static Color clrTableCellStateFg =new Color(230, 230, 204);
		public static Font fontStateCells = new Font("SansSerif", Font.ITALIC, 11);

	}

	/******** Gui-Logs Table Colors ***************************/
	public static class LogTableColors
	{
		public static Color clrBg = new Color(20,20,20);;
		public static Color clrFg = Color.white;
		public static Font fontTable = new Font("SansSerif", Font.PLAIN, 12);
		public static int rowHeight = 17;
		
		public static Color clrHeaderBg = Color.black;
		public static Color clrHeaderFg = Color.white;
		public static Font fontHeader = new Font("SansSerif", Font.PLAIN, 13);
		
		public static Color clrCellBg = Color.black;
		public static Color clrCellFg = Color.white;
		
		public static Color clrCellErrorBg = Color.black;
		public static Color clrCellErrorFg = Color.red;
		
		public static Color clrCellBuyTradeBg = Color.black;
		public static Color clrCellBuyTradeFg = Color.green;
		
		public static Color clrCellSellTradeBg = Color.black;
		public static Color clrCellSellTradeFg = Color.red;
		

	}
	
	/******** Trade Table Colors ******************************/
	public static class TradeTableColors
	{
		
		public static Color clrTrTableBg = new Color(20,20,20);;
		public static Color clrTrTableFg = Color.white;
		public static Font fontTrTable = new Font("SansSerif", Font.PLAIN, 11);
		
		public static Color clrTrTableHeaderBg = new Color(30, 54, 33);
		public static Color clrTrTableHeaderFg = new Color(238, 238, 231);
		public static Font fontTrTableHeader = new Font("SansSerif", Font.BOLD, 11);
		
		public static Color clrTrTableCellBuyFg = new Color(134, 220, 121);			// pastel green
		public static Color clrTrTableCellSellFg = new Color(255, 100, 100);			// pastel red
		public static Color clrTrTableCellBg = Color.black;
	
	}
	
	public static class LogoColors
	{
		public static Color clrLogoHeading1Fg = new Color(0, 82, 103);
		public static Color clrLogoHeading2Fg = new Color(0, 112, 224);
		public static Font fontLogoHeading1 = new Font("SansSerif", Font.PLAIN, 40);
		public static Font fontLogoHeading2 = new Font("SansSerif", Font.BOLD, 80);
	}
	
}


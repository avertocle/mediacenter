package mc.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;

public class TableMedia extends JTable {
	
	private static final long serialVersionUID = 1L;
	private TableModelMedia tmodel;
	
	ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	
	public TableMedia(TableModelMedia tmodelMedia, ConcurrentLinkedQueue<RootEventG2C> gcToPc) {
		super(tmodelMedia);
		this.tmodel = tmodelMedia;
		this.gcToPc = gcToPc;
		setTableProperties();
	}
	
	private void setTableProperties()
	{
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setFillsViewportHeight(true);
		this.setShowGrid(true);

		//this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setBackground(ColorConsts.TradeTableColors.clrTrTableBg);
		this.setForeground(ColorConsts.TradeTableColors.clrTrTableFg);
		
		this.getTableHeader().setBackground(ColorConsts.TradeTableColors.clrTrTableHeaderBg);
		this.getTableHeader().setForeground(ColorConsts.TradeTableColors.clrTrTableHeaderFg);
		
		this.getTableHeader().setFont(ColorConsts.TradeTableColors.fontTrTableHeader);
		this.setFont(ColorConsts.TradeTableColors.fontTrTable);
		
		this.addMouseListener(new MCL());
		
//		this.addComponentListener(new ComponentAdapter() 
//		{
//		   	public void componentResized(ComponentEvent ev) 
//		   	{
//		   		try	{	scrollRectToVisible(getCellRect(getRowCount()-1, 0, true));		}
//		   		catch (Exception e)	{ Logger.logExceptionMinimal(e);	}
//		   	}
//		});
//		
//		restrictColumnWidth(TableModelTrades.colNumPGID, 50, 50);
//		restrictColumnWidth(TableModelTrades.colNumTradeType, 0, 0);
//		restrictColumnWidth(TableModelTrades.colNumLTQ, 50, 50);
//		restrictColumnWidth(TableModelTrades.colNumQLeft, 50, 50);
		
	}
	
	class MCL implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(TableMedia.this) && e.getClickCount() == 2) {
		         int row = TableMedia.this.getSelectedRow();
		         int column = TableMedia.this.getSelectedColumn();
		         String fileAbsPath = (String) (tmodel.getValueAt(row, column));
		         gcToPc.add(new RootEventG2C(EventTypeG2C.PlayMedia, fileAbsPath));
		         // do some action
		     }
		   }

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
//	private void restrictColumnWidth(int col, int max, int pref)
//	{
//		this.getColumnModel().getColumn(col).setMaxWidth(max);
//		this.getColumnModel().getColumn(col).setPreferredWidth(pref);
//	}
//	
//	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
//	{
//		Component c = super.prepareRenderer(renderer, row, column);
//		
//		char val = 'x';
//		try
//		{
//			val =  (tmodel.getValueAt(row, TableModelTrades.colNumTradeType)).toString().charAt(0);
//		}
//		catch(Exception e){}
//		setCellColor(c, val);
//		return c;
//	}
//	
//	private void setCellColor(Component c, char val)
//	{
//	   switch(val)
//	   {
//	   case 'B':
//		   c.setForeground(ColorConsts.TradeTableColors.clrTrTableCellBuyFg);
//		   c.setBackground(ColorConsts.TradeTableColors.clrTrTableCellBg);  
//		   break;
//	   case 'S':
//		   c.setForeground(ColorConsts.TradeTableColors.clrTrTableCellSellFg);
//		   c.setBackground(ColorConsts.TradeTableColors.clrTrTableCellBg);  
//		   break;
//	   default:
//		   c.setForeground(Color.white);
//		   c.setBackground(Color.black);  
//		   break;
//	   }
//	}
	
}

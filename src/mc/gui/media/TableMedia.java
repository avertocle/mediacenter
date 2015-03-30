package mc.gui.media;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.gui.ColorConsts;
import mc.utils.Logger;

public class TableMedia extends JTable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private TableModelMedia tmodel;

	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private MapMediaTable mapMediaTable;

	private TableRowSorter<TableModelMedia> tsorter;
	
	public TableMedia(MapMediaTable mapMediaTable, TableModelMedia tmodelMedia,
			TableRowSorter<TableModelMedia> tsorter, ConcurrentLinkedQueue<RootEventG2C> gcToPc) {
		super(tmodelMedia);
		this.mapMediaTable = mapMediaTable;
		this.tmodel = tmodelMedia;
		this.tsorter = tsorter;
		this.gcToPc = gcToPc;
		setTableProperties();
	}

	private void setTableProperties() {
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setCellSelectionEnabled(true);
		this.setFillsViewportHeight(true);
		this.setShowGrid(true);

		this.setRowHeight(30);

		this.setBackground(ColorConsts.Table_Media.clrBg);
		this.setForeground(ColorConsts.Table_Media.clrFg);
		this.setFont(ColorConsts.Table_Media.font);

		this.getTableHeader().setBackground(ColorConsts.Table_Media.clrHeaderBg);
		this.getTableHeader().setForeground(ColorConsts.Table_Media.clrHeaderFg);
		this.getTableHeader().setFont(ColorConsts.Table_Media.fontHeader);

		this.addMouseListener(new MCL());
		
		fixColumnWidth(TableModelMedia.colNumYear, 100);
		fixColumnWidth(TableModelMedia.colNumDirector, 100);
		fixColumnWidth(TableModelMedia.colNumSize, 100);
		
		this.setRowSorter(tsorter);
		
	}

	private void fixColumnWidth(int colNo, int width){
		this.getColumnModel().getColumn(colNo).setMaxWidth(width);
		this.getColumnModel().getColumn(colNo).setPreferredWidth(width);
	}

	class MCL implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Logger.logFrequentEvent(e.toString());
			if (e.getSource().equals(TableMedia.this) && e.getClickCount() == 2) {
				int row_v = TableMedia.this.getSelectedRow();
				int row = convertRowIndexToModel(row_v);
				int col_v = TableMedia.this.getSelectedColumn();
				String fileAbsPath = mapMediaTable.getMediaPathByPosition(row);
				if(col_v == TableModelMedia.colNumName){
					gcToPc.add(new RootEventG2C(EventTypeG2C.PlayMedia, fileAbsPath));
				}
				else if(col_v == TableModelMedia.colNumFile){
					gcToPc.add(new RootEventG2C(EventTypeG2C.OpenContainingFolder, fileAbsPath));
				}
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

	// private void restrictColumnWidth(int col, int max, int pref)
	// {
	// this.getColumnModel().getColumn(col).setMaxWidth(max);
	// this.getColumnModel().getColumn(col).setPreferredWidth(pref);
	// }
	//
	// public Component prepareRenderer(TableCellRenderer renderer, int row, int
	// column)
	// {
	// Component c = super.prepareRenderer(renderer, row, column);
	//
	// char val = 'x';
	// try
	// {
	// val = (tmodel.getValueAt(row,
	// TableModelTrades.colNumTradeType)).toString().charAt(0);
	// }
	// catch(Exception e){}
	// setCellColor(c, val);
	// return c;
	// }
	//
	// private void setCellColor(Component c, char val)
	// {
	// switch(val)
	// {
	// case 'B':
	// c.setForeground(ColorConsts.TradeTableColors.clrTrTableCellBuyFg);
	// c.setBackground(ColorConsts.TradeTableColors.clrTrTableCellBg);
	// break;
	// case 'S':
	// c.setForeground(ColorConsts.TradeTableColors.clrTrTableCellSellFg);
	// c.setBackground(ColorConsts.TradeTableColors.clrTrTableCellBg);
	// break;
	// default:
	// c.setForeground(Color.white);
	// c.setBackground(Color.black);
	// break;
	// }
	// }

}

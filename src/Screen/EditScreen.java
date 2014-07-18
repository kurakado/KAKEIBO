package Screen;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import Table.NotOnlyStringTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import File.SaveFileWriter;
import Screen.Listeners.Window.AbstructWindowAdapter;

@SuppressWarnings("serial")
public class EditScreen extends Frame {
	
	private Integer row_fig = 50;	//行数
//	private Integer column_fig = 7;	//列数
	private String name=null;		//ユーザ名
	private String group=null;		//グループ名
	private DefaultTableModel tableModel;
	private /*Extends*/JTable table;
	//
	String[] bunrui = {"食料品"};
	String[] shohin = {"小豆"};
	Integer[] price = {300};
	Boolean[] tax_flg = {true};
	Integer[] tax = {8};
	Integer[] allprice = {324};
	String[] shop = {"SEIYU"};
	private Object[][] tabledata = {{bunrui[0],shohin[0],price[0],tax_flg[0],tax[0],allprice[0],shop[0]}};
	//分類，商品名,料金,外税,税率(%),小計,店			
	private String[] columnNames = {"分類", "商品名", "料金", "外税","税率(%)","小計","店"};
	
	EditScreen(String nm,String grp){
		//Frame作成。タイトルセット
        super("EditScreen");
        
        //メンバ変数セット
		name=nm;
		group=grp;
		
        //サイズのセット。横，縦
        this.setSize(900, 500);
        //レイアウトセット
        
        //ウィンドウの設定
        this.addWindowListener(new AbstructWindowAdapter(this));

        //ラベル
        Label l1 = new Label(name,Label.CENTER);
        Label l2 = new Label(group,Label.CENTER);
        
        //ラベルをパネルにセット
        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(1,2));
        p1.add(l1);
        p1.add(l2);
        
        
        
        //テーブル
        //ファイル読み込んで自動でデータ生成するようにしたい。
        tableModel = new NotOnlyStringTableModel(columnNames, 0);
        table = new /*Extends*/JTable(tableModel){
    		//0行目の背景色を薄灰色に変更
    		@Override
    		public Component prepareRenderer(TableCellRenderer tcr, int row,int column){
    			Component c = super.prepareRenderer(tcr, row, column);
    			if(row==0){
    				c.setBackground(Color.LIGHT_GRAY);
    			}else{
    				c.setBackground(getBackground());
    			}
    			return c;
    		}
    		
    		@Override
    		public void editingStopped(ChangeEvent e){
    			super.editingStopped(e);
    			for(int i=0;i<row_fig;i++){
//        			System.out.println("debug::"+i+":"+table.getValueAt(i,2)+table.getValueAt(i,3)+table.getValueAt(i,4));
    				if( (!(table.getValueAt(i,2)==null))  ){
    					if(table.getValueAt(i,3)==null){
    						table.setValueAt(Integer.parseInt(table.getValueAt(i,2).toString()), i, 5);
    					}else if( Boolean.parseBoolean(table.getValueAt(i,3).toString())==true && (!(table.getValueAt(i,4)==null))){
    						table.setValueAt(Integer.parseInt(table.getValueAt(i,2).toString())*(1+0.01*Integer.parseInt(table.getValueAt(i,4).toString())), i, 5);
    					}else if( Boolean.parseBoolean(table.getValueAt(i,3).toString())==false ){
    						table.setValueAt(Integer.parseInt(table.getValueAt(i,2).toString()), i, 5);
    					}else{
//    						System.out.println("error:: please input cell("+i+",4).:EditScreen");
    					}
    				}
    			}
    			
    		}
        };
        table.setRowHeight(50);
        table.getTableHeader().setReorderingAllowed(false);
       	tableModel.addRow(tabledata[0]);
       	tableModel.setRowCount(row_fig);
       	

        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(750, 770));

        //ボタン
        Button b1 = new Button("戻る");
        b1.addActionListener(new ReturnToMenuActionListener());
        Button b2 = new Button("リセット");
        b2.addActionListener(new ResetTableActionListener());
        Button b3 = new Button("保存");
        b3.addActionListener(new SaveTableActionListener());
        Button b4 = new Button("行追加");
        b4.addActionListener(new AddRowTableActionListener());
        
        //パネル
        Panel p2 = new Panel();
        p2.setLayout(new GridLayout(2, 1));
        Panel p3 = new Panel();
        p3.setLayout(new GridLayout(1, 2));
        Panel p4 = new Panel();
        p4.setLayout(new GridLayout(1, 3));
        
        p2.add(p4);
        p2.add(p3);
        
        //ボタンをパネルにセット
        p3.add(b4);
        p4.add(b1);
        p4.add(b2);
        p4.add(b3);

        
        //レイアウト
        setLayout(new BorderLayout());
        add("North",p1);
        add("Center",sp);
        add("South",p2);
        
        
        
        //画面上に表示
        this.setVisible(true);
	}
		


//	//セルにリスナーセットする為の拡張クラス
//	public class ExtendsJTable extends JTable {
//		public ExtendsJTable(DefaultTableModel tableModel) {
//			super(tableModel);
//			DefaultTableColumnModel colModel = (DefaultTableColumnModel)getColumnModel();
//			//3番目のカラムを取得する
//			TableColumn col0 = colModel.getColumn( 3 );
//			//3番目のカラムにExtendsCellEditorをセットする
//			col0.setCellEditor( new ExtendsCellEditor( new JTextField() ) );
//		}
//
//		
//		/**
//		* CellEditor
//		*/
//		class ExtendsCellEditor extends DefaultCellEditor{
//			ExtendsCellEditor( JTextField tf ){
//				super( tf );
//				setClickCountToStart( 1 );
//				//MyEditorListenerを追加
//				addCellEditorListener( new ExtendsEditorListener() );
//			}
//		}
//
//		/**
//		* CellEditorListener
//		*/
//		class ExtendsEditorListener implements CellEditorListener{
//
//			//セルの編集が終わった時
//			public void editingStopped( ChangeEvent e ){
//				System.out.println("debug::EditScreen:" + this.getClass().getName()+"::editingStopped");
//			}
//
//			public void editingCanceled( ChangeEvent e ){
//				System.out.println("debug::EditScreen:" + this.getClass().getName()+"::editingCanceled");
//
//			}
//		}
//
//	}
	

	//戻るボタン用内部クラス
	private class ReturnToMenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new MenuScreen(name,group);
			dispose();	//このボタンが存在するフレームを閉じる
		}
	}
	
	//リセットボタン
	private class ResetTableActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new EditScreen(name,group);
			dispose();	//このボタンが存在するフレームを閉じる
		}
	}
	
	//保存ボタン。戻るボタンを継承
	private class SaveTableActionListener extends ReturnToMenuActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=1;i<=row_fig-1;i++){
				if(tableModel.getValueAt(i,1)==null){
					tableModel.removeRow(i);
					row_fig=row_fig-1;
					i=i-1;
				}
			}
			//データ保存
			new SaveFileWriter(name,table,new File(group+".csv"));
			//戻るボタンの動作
			super.actionPerformed(e);
		}
	}
	
	//行追加ボタン
	private class AddRowTableActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			row_fig=row_fig+1;
			tableModel.setRowCount(row_fig);
			//dispose();
		}
	}
}
package Screen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Screen.Listeners.Window.AbstructWindowAdapter;
import Table.NotOnlyStringTableModel;

@SuppressWarnings("serial")
public class DataToTableScreen extends Frame {
	protected ArrayList<String> list;
	protected ArrayList<String> detail = new ArrayList<String>();
	protected ArrayList<ArrayList <String>> detail_list = new ArrayList<ArrayList <String>>();
	//分類，商品名,料金,外税,税率(%),小計,店
	private Object[] tabledata = {"食料品","小豆",300,true,8,324,"SEIYU","user","0"};
	private String[] columnNames = {"分類", "商品名", "料金", "外税","税率(%)","小計","店","編集者","日時"};
	private DefaultTableModel tableModel;
	private JTable table ;

		
	public DataToTableScreen(){
		this( new ArrayList<String>(), null, null);
	}
	
	public DataToTableScreen(ArrayList<String> h_list){
		this( h_list, null, null);
	}
	
	public DataToTableScreen(ArrayList<String> h_list,DefaultTableModel h_defaultmodel){
		this( h_list, h_defaultmodel, null);
	}
	
	public DataToTableScreen(ArrayList<String> h_list,DefaultTableModel h_defaultmodel,JTable h_table){
		super("DataToTableScreen");
		//サイズのセット。横，縦
        this.setSize(900, 500);
        
        //レイアウトセット
//        setLayout(new BorderLayout());
        setLayout(new GridLayout(1, 1));

        //ウィンドウの設定
        this.addWindowListener(new AbstructWindowAdapter(this));
        
        //DefaultTableModel
		if(h_defaultmodel==null){
			tableModel= new NotOnlyStringTableModel(columnNames, 0);   
		}
		
		//JTable
		if(h_table==null){
			table=new JTable(tableModel){
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
			};
		}
		list=h_list;
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
			int locate = 0;
			int locate_next = list.get(i).indexOf(",");
			while(!(locate==-1 || locate_next==-1)){
				detail.add(list.get(i).substring(locate,locate_next).replace(",",""));
				locate=locate_next;
				locate_next = list.get(i).indexOf(",",locate+1);
			}
			detail.add(list.get(i).substring(locate).replace(",",""));
			detail_list.add(detail);
		}
		tableModel.addRow(tabledata);
		tableModel.setRowCount(detail_list.size()+3);
		make_table();
		table.setValueAt("aa",1,0);
		JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(750, 770));
		add(sp);
		//画面上に表示
        this.setVisible(true);
	}
	
	public void make_table(){
		//テーブル
        //ファイル読み込んで自動でデータ生成するようにしたい。
        table.setRowHeight(50);
        table.getTableHeader().setReorderingAllowed(false);
//        System.out.println(detail.get(0));
        int index=0;
        for(int i=0;i<detail_list.size();i++){     	
        	for(int j=0;j<detail.size()/detail_list.size();j++){
        		System.out.println("index"+ index +":("+detail.get(index)+"):"+(i+1)+":"+j);
        		table.setValueAt(detail.get(index),i+1,j);
        		index++;
        	}
        }
	}
}

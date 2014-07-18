package Screen;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import File.SaveFileReader;
import Screen.Listeners.Button.ShutDownActionListener;
import Screen.Listeners.Window.AbstructWindowAdapter;

@SuppressWarnings("serial")
public class MenuScreen  extends Frame {
	private String m_name,m_group;
	
	public MenuScreen(){
		this("please enter your name.");
	}
	
	public MenuScreen(String name){
		this(name,"-");
	}
	
	public MenuScreen(String name,String grp){
		super("Menu");
		if (name==null){
			name="please enter your name.";
		}
		
		m_name=name;
		m_group=grp;
		
        //サイズのセット。横，縦
        this.setSize(750, 300);
        this.addWindowListener(new AbstructWindowAdapter(this));
        
        //テキストボックス
        TextField t1 = new TextField(m_name,Label.CENTER);
        
        //ラベル
        Label l1 = new Label("名前を入力してください。",Label.LEFT);
        Label l2 = new Label("グループ名を選択してください。",Label.LEFT);
        l2.setPreferredSize(new Dimension(25, 200));
                
        //チョイス
        Choice c1 = new Choice();
        c1.addItemListener(new SelectChoiceItemListener(c1));
        c1.add("-");
        String[] contents = new File(".").list();
        for (int i=0; i < contents.length; i++) {
        	if(!(contents[i].indexOf(".csv")==-1)){
        		c1.add( contents[i].substring(0,contents[i].indexOf(".csv")));
        	}
        }
        c1.select(m_group);	//デフォルト選択設定
        
        //閉じるボタン
        Button b1 = new Button("閉じる");
        b1.addActionListener(new ShutDownActionListener(this));
        //データ入力画面へ進むボタン
        Button b2 = new Button("データ入力画面へ進む");
        b2.addActionListener(new MenuToEditActionListener());
        //全個別データの表示ボタン
        Button b3 = new Button("個別データの全表示");
        b3.addActionListener(new IndividualDataDisplayActionListener());
        //集計データの表示ボタン
        Button b4 = new Button("集計データの表示");
        b4.addActionListener(new AggregateDataDisplayActionListener());
        //データファイルのマージボタン
        Button b5 = new Button("データファイルのマージ");
        b5.addActionListener(new DataMargeActionListener());

        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(1, 3));
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        
        Panel p2 = new Panel();
        p2.setLayout(new GridLayout(1, 2));
        p2.add(b1);
        p2.add(b2);        
        
        this.setLayout(new GridLayout(6, 1));
        add(l1);
        add(t1);
        add(l2);
        add(c1);
        add(p1);
        add(p2);
        
        
        
        
        //画面上に表示
        this.setVisible(true);
	}
	
	//データファイルのマージボタン用内部クラス
	private class DataMargeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
	//集計データの表示ボタン用内部クラス
	private class AggregateDataDisplayActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
	//個別データの全表示ボタン用内部クラス
	private class IndividualDataDisplayActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				SaveFileReader sfr = new SaveFileReader(new File(m_group+".csv"));
				ArrayList<String> allText = sfr.getTextAll();
				new DataToTableScreen(allText){
					
					public void make_table(){
						System.out.println("start override");
						for(int j=0;j<detail.size();j++){
							System.out.println(j % (detail.size()/detail_list.size() )+":"+detail.get(j));
							if( (j % (detail.size()/detail_list.size() )==3) & (detail.get(j)=="null") ){
								detail.set(j, "false");
								System.out.println("null to false:"+detail.get(j));
							}
						}
						super.make_table();
					}

				};
			} catch (FileNotFoundException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
	}
	//データ入力画面へ進むボタン用内部クラス
	private class MenuToEditActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			new EditScreen(m_name,m_group);
			dispose();	//このボタンが存在するフレームを閉じる
		}
	}
	
	private class SelectChoiceItemListener implements ItemListener{
		private Choice ch;
		
		public SelectChoiceItemListener(Choice choice){
			ch=choice;
		}
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			m_group=ch.getSelectedItem();
			System.out.println(m_group);
		}
		
	}
//	//戻るボタン用内部クラス
//	private class ReturnToLoginActionListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			new LoginScreen(m_name,m_group);
//			dispose();	//このボタンが存在するフレームを閉じる
//		}
//	}
}

package Screen;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Screen.Listeners.Button.ShutDownActionListener;
import Screen.Listeners.Window.AbstructWindowAdapter;

@SuppressWarnings("serial")
public class LoginScreen extends Frame  {
	//参考：http://www.tohoho-web.com/java/layout.htm
	//GridBagLayout を用いることで、画面を n×m の升目に分割し、(0, 0) の座標から幅 1、高さ 3 の部品を配置することができます。



	public LoginScreen(){
		this("please enter your name.");
	}
	public LoginScreen(String name){
		this(name,"-");
	}
	public LoginScreen(String name,String group){
		//Frame作成。タイトルセット
		super("LoginScreen");
		if (name==null){
			name="please enter your name.";
		}
        //サイズのセット。横，縦
        this.setSize(400, 200);        
        
        //ウィンドウの設定
        this.addWindowListener(new AbstructWindowAdapter(this));
        
        //ラベル
        Label l1 = new Label("名前を入力してください。");
        Label l2 = new Label("グループ名を選択してください。");
        
        
        //テキストボックス
        TextField t1 = new TextField(name);

        //チョイス
        Choice c1 = new Choice();
        	//グループ名をファイルから自動取得したい。そこの実装は後で。
        c1.add("-");
        c1.add("グループ1");
        c1.add("グループ2");
        c1.select(group);

        //ボタン
        Button b1 = new Button("閉じる");
        b1.addActionListener(new ShutDownActionListener(this));
        Button b2 = new Button("進む");
        b2.addActionListener(new LoginToMenuActionListener(c1,t1));
        //Button b3 = new Button("ファイルマージ");
        
        //パネル
        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(1, 2));
//        Panel p2 = new Panel();
//        p2.setLayout(new GridLayout(1, 2));
        
        //パネルにセット
        p1.add(b1);
        p1.add(b2);
//        p2.add(l2);
//        p2.add(b3);
        
        //レイアウト
        setLayout(new GridLayout(5, 1));
        add(l1);
        add(t1);
        add(l2);
        add(c1);
        add(p1);
        
        //画面上に表示
        this.setVisible(true);
//        show();  //windowsでは推奨されないらしい
	}
	
	private class LoginToMenuActionListener implements ActionListener {
		private Choice c;
		private TextField t;
		
		LoginToMenuActionListener(Choice choice,TextField textField){
			c=choice;
			t=textField;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			new MenuScreen(t.getText(),c.getSelectedItem());
			dispose();	//このボタンが存在するフレームを閉じる
		}
	}	
}



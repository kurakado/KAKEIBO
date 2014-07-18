package Screen;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import Screen.Listeners.Button.GenerateScreenActionListener;
import Screen.Listeners.Button.ShutDownActionListener;
import Screen.Listeners.Window.AbstructWindowAdapter;


@SuppressWarnings("serial")
public class TestScreen extends Frame {
	
	//参考：http://www.tohoho-web.com/java/layout.htm
	//GridBagLayout を用いることで、画面を n×m の升目に分割し、(0, 0) の座標から幅 1、高さ 3 の部品を配置することができます。
	GridBagLayout gbl = new GridBagLayout();

    void add(Component b, int x, int y, int w, int h) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(b, gbc);
        add(b);
    }
	
	


	public TestScreen() {
        super("FrameTest");
        //サイズのセット。横，縦
        this.setSize(300, 300);
        setLayout(gbl);
        
        
        this.addWindowListener(new AbstructWindowAdapter(this));
        Label l1 = new Label("名前を入力してください");
//        Label l2 = new Label("グループ名を選択してください");
        Button b1 = new Button("Close");
        Button b2 = new Button("Open");
        TextField text1 = new TextField("please enter your name.");
        b1.addActionListener(new ShutDownActionListener(this));
        b2.addActionListener(new GenerateScreenActionListener( this.getClass() ));
        
        //パネル
        Panel pn1 = new Panel();
        pn1.setLayout(new GridLayout(1, 2));
        
        pn1.add(b1);
        pn1.add(b2);
        add(pn1, 0,0,3,1);
        add(l1,0,1,3,1);
        add(text1,0,2,3,1);
        //画面上に表示
        this.setVisible(true);
//        show();  //windowsでは推奨されないらしい
    }


	
//	class ShutDownActionListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            System.exit(0);
//        }
//    }
	

}

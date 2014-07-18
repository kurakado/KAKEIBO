package Screen.Listeners.Window;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AbstructWindowAdapter extends WindowAdapter{
	
	//このウィンドウが存在するフレーム
	Frame this_frame;
	
	public AbstructWindowAdapter(Frame frame){
		super();
		this_frame=frame;
	}
    public void windowClosing(WindowEvent e)  {
//        System.exit(0);	//Java仮想マシンの終了をしたい訳ではない
		this_frame.dispose();	//このボタンが存在するフレームを閉じる
    }
}

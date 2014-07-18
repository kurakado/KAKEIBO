package Screen.Listeners.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShutDownActionListener implements ActionListener{
	
	//このボタンが存在するフレーム
	Frame this_frame;
	
	public ShutDownActionListener(Frame frame){
		super();
		this_frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//        System.exit(0);	//Java仮想マシンの終了をしたい訳ではない
		this_frame.dispose();	//このボタンが存在するフレームを閉じる
	}

}

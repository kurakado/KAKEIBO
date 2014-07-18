package Screen.Listeners.Button;



import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateScreenActionListener implements ActionListener{
	public GenerateScreenActionListener(Class<? extends Frame> cl){
		class_ = cl;
	}
	Class<? extends Frame> class_;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Class<?> cla = Class.forName(class_.getName());
			cla.newInstance();
//			System.out.println("debug::GenerateScreenActionListener:" + class_.getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	
}

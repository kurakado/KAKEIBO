import java.io.File;
import java.io.IOException;

import File.ConfigFileReader;
import Screen.MenuScreen;




public class Main {
	public static void main(String [] args) {
		try {
			ConfigFileReader config_file = new ConfigFileReader(new File("config.txt"));
			String name = config_file.getConfig("\"DEFAULT_USER_NAME\"");
			String group= config_file.getConfig("\"DEFAULT_GROUP_NAME\"");
			new MenuScreen(name,group);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}

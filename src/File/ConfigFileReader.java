package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigFileReader extends AbstructFileReader {

	public ConfigFileReader(File fl) throws FileNotFoundException, IOException {
		super(fl);
	}

	public String getConfig(String str){
		String conf=null;
		for(int i=0;i<list.size();i++){
			if(!(list.get(i).indexOf(str)==-1)){
				conf=list.get(i).substring(list.get(i).indexOf("=")+1).replace(" ", "");
				break;
			}
		}
		return conf;
	}
}

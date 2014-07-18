package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AbstructFileReader {
	private File file;
	protected ArrayList<String> list = new ArrayList<String>();
	
	public AbstructFileReader(File fl) throws FileNotFoundException ,IOException {
		file=fl;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = br.readLine();
		while(str != null){
//			System.out.println(line+":"+str+"::"+this.getClass().getName());
			list.add(str);
//			System.out.println(file_inter[line]+"::"+this.getClass().getName());
		    str = br.readLine();
		}
		br.close();
	}
	
	public File getFile(){
		return file;
	}
	public ArrayList<String> getTextAll(){
		return list;
	}
}

package File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JTable;

public class SaveFileWriter{
	private int row_fig;
	private int column_fig;
	private String name;
	
	public SaveFileWriter(String nm,JTable table){
		this(nm,table,new File("data.csv"));
		name=nm;
	}
	
	public SaveFileWriter(String nm,JTable table,File file){
		System.out.println("SaveFileWriter!start!");
		name=nm;
		try {
			PrintWriter pw = new PrintWriter(
								new BufferedWriter(
										new FileWriter(file,true)
										)
								);
			row_fig=table.getRowCount();
			column_fig=table.getColumnCount();
			
			for(int i=1;i<=row_fig-1;i++){
				String str="";
				for(int j=0;j<=column_fig-1;j++){
					str=str+table.getValueAt(i,j)+",";
				}
				str=str+name+",";
				Date date1 = new Date();
				str=str+date1.getTime();
				pw.println(str);
			}
			pw.close();
		} catch (IOException  e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			System.out.println("SaveFileWriter!end!");
		}
	}


}

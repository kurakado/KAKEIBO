package Table;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public
class NotOnlyStringTableModel extends DefaultTableModel{
	public NotOnlyStringTableModel(String[] columnNames, int rowNum){
		super(columnNames, rowNum);
	}

	
	public Class<?> getColumnClass(int col){
		return getValueAt(0, col).getClass();
	}
}
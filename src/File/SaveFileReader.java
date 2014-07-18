package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveFileReader extends AbstructFileReader {

	public SaveFileReader(File fl) throws FileNotFoundException, IOException {
		super(fl);
	}
}

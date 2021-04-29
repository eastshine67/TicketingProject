package salesReport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class FileWriting{
	private FileWriter fw;
	private boolean isFileExist;
	
	public void FileChecking() {
		try {
			File file = new File("sales_report.csv");
			if(file.exists() == false) {
				isFileExist = false;
			} else {
				isFileExist = true;
			}
			fw = new FileWriter("sales_report.csv", true);					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileClose() {
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
		}
	}
	
	public void headerWrite() throws IOException {
		if (isFileExist == false) {
			String head = "¿œ¿⁄," + "√— ∏≈√‚" + "\n";
			fw.write(head);
		}		
	}

	public void dataWrite(String date, String sales) throws IOException {
		
			FileChecking();
			headerWrite();		
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String result = sdf.format(date) + "," + sales + "\n";
			fw.write(result);
			fileClose();
	}
}
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
	
	public void headerWrite1() throws IOException {
		if (isFileExist == false) {
			String head = "구분," + "주간권," + "야간권," + "\n";
			fw.write(head);
		}		
	}
	
	public void headerWrite2() throws IOException {
		if (isFileExist == false) {
			String head = "일자," + "총 매출" + "\n";
			fw.write(head);
		}		
	}

	public void dataWrite1(String agetype, int day, int night) throws IOException {
		
		FileChecking();
		headerWrite1();		
		String result = agetype + "," + day + "," + night + "\n";
		fw.write(result);
		fileClose();
}
	
	
	public void dataWrite2(String date, String sales) throws IOException {
		
			FileChecking();
			headerWrite2();		
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String result = sdf.format(date) + "," + sales + "\n";
			fw.write(result);
			fileClose();
	}
}
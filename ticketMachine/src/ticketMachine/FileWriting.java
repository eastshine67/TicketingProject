package ticketMachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileWriting{
	private FileWriter fw;
	private boolean isFileExist;
	Variables v = new Variables();
	Print p = new Print();
	
	public void FileChecking() {
		try {
			File file = new File("C:\\Users\\이동엽\\Desktop\\ticketing.csv");
			if(file.exists() == false) {
				isFileExist = false;
			} else {
				isFileExist = true;
			}
			fw = new FileWriter("C:\\Users\\이동엽\\Desktop\\ticketing.csv", true);					
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
			p.printError();
		}
	}
	
	public void headerWrite() throws IOException {
		if (isFileExist == false) {
			String head = "날짜," + "권종," + "연령구분," + "수량," + "가격," + "우대사항" + "\n";
			fw.write(head);
		}		
	}

	public void dataWrite(String typePrint, String agePrint, String quantityPrint, String finalTotalPricePrint, String concessionPrint) throws IOException {
		
			FileChecking();
			headerWrite();		
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String result = sdf.format(c.getTime()) + "," + typePrint  + "," + agePrint + "," + quantityPrint + "," + finalTotalPricePrint + "," + concessionPrint + "\n";
			fw.write(result);
			fileClose();
			
	}
}

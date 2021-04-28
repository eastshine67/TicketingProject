package salesReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SalesReport {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\ÀÌµ¿¿±\\Desktop\\ticketing.csv");	
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		File f1 = new File("C:\\Users\\ÀÌµ¿¿±\\Desktop\\report.csv");
		
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1));
		
		String readtext;
		
		int cnt = 0;
		
		int wcnt = 0;
		
		while((readtext = br.readLine()) != null) {
		
			StringBuffer s = new StringBuffer();
			
			String [] field = readtext.split(",");
			
	
			
			for (int i = 1; i < field.length; i++) {
				s.append("," + field[i]);		
			}
				
				
			bw1.write(s.toString());
				
			bw1.newLine();
				
			wcnt++;	
			cnt++;
			
		}
		br.close();
		
		bw1.close();
		


		
		
		
		
		
		
	}

}
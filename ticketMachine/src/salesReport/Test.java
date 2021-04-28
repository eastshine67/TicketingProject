package salesReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\이동엽\\Desktop\\ticketing.csv");
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String readtxt;
		
		if((readtxt = br.readLine()) == null) {
		
			System.out.printf("빈 파일입니다\n");
			
			return;
		}
		String[] field_name = readtxt.split(",");
		
		
		int lineCnt = 0;
		System.out.printf("================================ report.csv =================================\n\n");
		System.out.printf("%s%9s%11s%9s%9s%13s", "날짜", "권종", "연령구분", "수량", "가격", "우대사항" + "\n\n");
		
		while ((readtxt = br.readLine()) != null) {
		
			String [] field = readtxt.split(",");
			
			
			for (int j = 0; j < field_name.length; j++) {
	
				if (j == 0 && j % 6 == 0) {
					field[j] = field[j].replaceAll("-", "");
//					System.out.println();
					System.out.printf("\n%-11s", field[j]);
				} else {
					System.out.printf("%-11s", field[j]);
				}
				
			}
		}
		System.out.printf("\n-----------------------------------------------------------------------------\n");
		
		System.out.printf("============================= 권종 별 판매현황 ==============================\n\n");
		
		br.close();
		
	}

}

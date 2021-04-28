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
		Variables v = new Variables();
		String readtxt;
		
		if((readtxt = br.readLine()) == null) {
		
			System.out.printf("빈 파일입니다\n");
			
			return;
		}
		String[] field_name = readtxt.split(",");
		
		System.out.printf("================================ report.csv =================================\n\n");
		System.out.printf("%s%9s%11s%9s%9s%13s", "날짜", "권종", "연령구분", "수량", "가격", "우대사항" + "\n\n");
		
		while ((readtxt = br.readLine()) != null) {
		
			String [] field = readtxt.split(",");

			for (int i = 0; i < field_name.length; i++) {
	
				if (i == 0 && i % 6 == 0) {
					field[i] = field[i].replaceAll("-", "");
					System.out.printf("\n%-11s", field[i]);
				} else {
					System.out.printf("%-11s", field[i]);
				}
				
				if (field[i].equals("주간권")) {
					if (field[i].equals("대인")) v.dayAdultCount++;
					if (field[i].equals("청소년")) v.dayTeenCount++;
					if (field[i].equals("소인")) v.dayChildCount++;
					if (field[i].equals("경로")) v.dayElderlyCount++;
					v.dayCount++;
					v.tmp1 = field[i+3];
					v.dayPrice = Integer.parseInt(v.tmp1);
				}
				
				if (field[i].equals("야간권")) {
					if (field[i].equals("대인")) v.nightAdultCount++;
					if (field[i].equals("청소년")) v.nightTeenCount++;
					if (field[i].equals("소인")) v.nightChildCount++;
					if (field[i].equals("경로")) v.nightElderlyCount++;
					v.nightCount++;
					v.tmp2 = field[i+3];
					v.nightPrice = Integer.parseInt(v.tmp2);
				}
							
				
				
			}
		}
		System.out.printf("\n-----------------------------------------------------------------------------\n");
		System.out.printf("============================= 권종 별 판매현황 ==============================\n\n");
		System.out.printf("\n주간권 총 %d매", v.dayCount);
		System.out.printf("\n소인 %d매, 청소년 %d매, 대인 %d매, 경로 %d매", v.dayChildCount, v.dayTeenCount, v.dayAdultCount, v.dayElderlyCount);
		System.out.printf("\n주간권 매출 : %d원\n", v.dayPrice);
		System.out.printf("\n야간권 총 %d매", v.nightCount);
		System.out.printf("\n소인 %d매, 청소년 %d매, 대인 %d매, 경로 %d매", v.nightChildCount, v.nightTeenCount, v.nightAdultCount, v.nightElderlyCount);
		System.out.printf("\n야간권 매출 : %d원\n", v.nightPrice);
		
		
		System.out.printf("============================= 일자별 매출 현황 ==============================\n\n");

		
		
		
		
		
		br.close();
		
	}

}

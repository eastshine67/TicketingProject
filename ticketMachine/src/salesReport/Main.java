package salesReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("ticketing.csv");
		BufferedReader br = new BufferedReader(new FileReader(f));
		Variables v = new Variables();
		String readtxt;
		FileWriting fw = new FileWriting();
		
		if((readtxt = br.readLine()) == null) {
		
			System.out.printf("빈 파일입니다\n");
			
			return;
		}
		String[] field_name = readtxt.split(",");
		
		System.out.printf("================================ report.csv =================================\n\n");
		System.out.printf("%s%9s%11s%7s%8s%12s", "날짜", "권종", "연령구분", "수량", "가격", "우대사항" + "\n\n");
		ArrayList<String[]> test = new ArrayList<String[]>();
		
		while ((readtxt = br.readLine()) != null) {
		
			String [] field = readtxt.split(",");
			
			
			
			
			test.add(field); // arrayList
			
			
			for (int i = 0; i < field_name.length; i++) {
				
				if (i == 0) {
					field[i] = field[i].replaceAll("-", ""); // 날짜값 사이에 dash 제거	
					System.out.printf("\n%s%3s", field[i], "");
					
				} else {
					
					if (i % 1 == 0 && field[i].length() == 3) { // '청소년'은 3자이므로 줄 맞추기
						System.out.printf("%-9s", field[i]); // strA[i] 권종, 나이, X, 갯수, 금액, 우대
					} else {
						System.out.printf("%-10s", field[i]); // strA[i] 권종, 나이, X, 갯수, 금액, 우대
					}
				}
				
				if (field[i].equals("주간권")) {
					if (field[i+1].equals("유아")) {
						v.dayToddlerCount = Integer.parseInt(field[i+2]);
						if (v.dayToddlerCount == 0) v.dayToddlerCount++;
						
					} else if (field[i+1].equals("소인")) {
						v.dayChildCount = Integer.parseInt(field[i+2]);
						if (v.dayChildCount == 0) v.dayChildCount++;
					
					} else if (field[i+1].equals("청소년")) {
						v.dayTeenCount = Integer.parseInt(field[i+2]);
						if (v.dayTeenCount == 0) v.dayTeenCount++;
						
					} else if (field[i+1].equals("대인")) {
						v.dayAdultCount = Integer.parseInt(field[i+2]);
						if (v.dayAdultCount == 0) v.dayAdultCount++;
					} else if (field[i+1].equals("경로")) {
						v.dayElderlyCount = Integer.parseInt(field[i+2]);
						if (v.dayElderlyCount == 0) v.dayElderlyCount++;
					}
					v.dayCount = v.dayToddlerCount + v.dayChildCount + v.dayTeenCount + v.dayAdultCount + v.dayElderlyCount;
					v.tmp1 = field[i+3];
					v.dayPrice = Integer.parseInt(v.tmp1);
					v.dayPriceSum = v.dayPriceSum + v.dayPrice;
				}
				
				if (field[i].equals("야간권")) {
					if (field[i+1].equals("유아")) {
						v.nightToddlerCount = Integer.parseInt(field[i+2]);
						if (v.nightToddlerCount == 0) v.nightToddlerCount++;
						
					} else if (field[i+1].equals("소인")) {
						v.nightChildCount = Integer.parseInt(field[i+2]);
						if (v.nightChildCount == 0) v.nightChildCount++;
					
					} else if (field[i+1].equals("청소년")) {
						v.nightTeenCount = Integer.parseInt(field[i+2]);
						if (v.nightTeenCount == 0) v.nightTeenCount++;
						
					} else if (field[i+1].equals("대인")) {
						v.nightAdultCount = Integer.parseInt(field[i+2]);
						if (v.nightAdultCount == 0) v.nightAdultCount++;
					} else if (field[i+1].equals("경로")) {
						v.nightElderlyCount = Integer.parseInt(field[i+2]);
						if (v.nightElderlyCount == 0) v.nightElderlyCount++;
					}
					v.nightCount = v.nightToddlerCount + v.nightChildCount + v.nightTeenCount + v.nightAdultCount + v.nightElderlyCount;
					v.tmp2 = field[i+3];
					v.nightPrice = Integer.parseInt(v.tmp2);
					v.nightPriceSum = v.nightPriceSum + v.nightPrice;
				}
				
				
				if (field[i].equals("*우대적용 없음")) {
					v.noConcessionCount = Integer.parseInt(field[i-1]);
					if (v.noConcessionCount == 0) v.noConcessionCount++;
						
				} else if (field[i].equals("*장애인 우대적용")) {
					v.disabledCount = Integer.parseInt(field[i-1]);
					if (v.disabledCount == 0) v.disabledCount++;
					
				} else if (field[i].equals("*국가유공자 우대적용")) {
					v.nationalMeritCount = Integer.parseInt(field[i-1]);
					if (v.nationalMeritCount == 0) v.nationalMeritCount++;
					
				} else if (field[i].equals("*다자녀 우대적용")) {
					v.multichildCount = Integer.parseInt(field[i-1]);
					if (v.multichildCount == 0) v.multichildCount++;
				} else if (field[i].equals("*임산부 우대적용")) {
					v.disabledCount = Integer.parseInt(field[i-1]);
					if (v.disabledCount == 0) v.disabledCount++;
				}
			}
		}
		
		
		String[] testField;
		String[] testField2;
		int sumPrice;
		ArrayList<String> incomePerDay = new ArrayList<String>();
		testField = test.get(0);
		sumPrice = Integer.parseInt(testField[4]);
		
		for (int i = 0; i < test.size(); i++) {
			testField = test.get(i);
			
			if (i == test.size() -1) {
				incomePerDay.add(testField[0]+","+sumPrice);
				
			} else {
				testField2 = test.get(i+1);
				
				if (testField[0].equals(testField2[0])) {
					sumPrice += Integer.parseInt(testField2[4]);
					
				} else {
					incomePerDay.add(testField[0]+","+sumPrice);
					sumPrice = Integer.parseInt(testField2[4]);
				}	
			}	
		}
		
		System.out.printf("\n-----------------------------------------------------------------------------\n");
		System.out.printf("============================= 권종 별 판매현황 ==============================\n\n");
		System.out.printf("\n주간권 총 %d매", v.dayCount);
		System.out.printf("\n소인 %d매, 청소년 %d매, 대인 %d매, 경로 %d매", v.dayChildCount, v.dayTeenCount, v.dayAdultCount, v.dayElderlyCount);
		System.out.printf("\n주간권 매출 : %d원\n", v.dayPriceSum);
		System.out.printf("\n야간권 총 %d매", v.nightCount);
		System.out.printf("\n소인 %d매, 청소년 %d매, 대인 %d매, 경로 %d매", v.nightChildCount, v.nightTeenCount, v.nightAdultCount, v.nightElderlyCount);
		System.out.printf("\n야간권 매출 : %d원\n", v.nightPriceSum);
		System.out.printf("============================= 일자별 매출 현황 ==============================\n\n");
		String[] dateNprice;
		
		for(int i = 0; i < incomePerDay.size(); i++ ) {

			dateNprice = incomePerDay.get(i).split(",");
			
			System.out.printf("%s년 %s월 %s일 : 총 매출 %10s원\n", dateNprice[0].substring(0,4), dateNprice[0].substring(4,6), dateNprice[0].substring(6), dateNprice[1]);
			
			fw.dataWrite(dateNprice[0], dateNprice[1]);
		}
		
		System.out.printf("-----------------------------------------------------------------------------\n\n");
		System.out.printf("============================= 우대권 판매 현황 ==============================\n\n");
		System.out.printf("총 판매 티켓 수 : %s\t\n", v.dayCount+v.nightCount);
		System.out.printf("우대 없음 : %s\t\n", v.noConcessionCount);
		System.out.printf("장애인 : %s\t\n", v.disabledCount);
		System.out.printf("국가유공자 : %s\t\n", v.nationalMeritCount);
		System.out.printf("다자녀 : %s\t\n", v.multichildCount);
		System.out.printf("임산부 : %s\t\n", v.pregnantCount);
		
	
		br.close();
		
	
	}
}

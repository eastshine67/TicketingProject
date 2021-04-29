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
		
			System.out.printf("�� �����Դϴ�\n");
			
			return;
		}
		String[] field_name = readtxt.split(",");
		
		System.out.printf("================================ report.csv =================================\n\n");
		System.out.printf("%s%9s%11s%7s%8s%12s", "��¥", "����", "���ɱ���", "����", "����", "������" + "\n\n");
		ArrayList<String[]> test = new ArrayList<String[]>();
		
		while ((readtxt = br.readLine()) != null) {
		
			String [] field = readtxt.split(",");
			
			
			
			
			test.add(field); // arrayList
			
			
			for (int i = 0; i < field_name.length; i++) {
				
				if (i == 0) {
					field[i] = field[i].replaceAll("-", ""); // ��¥�� ���̿� dash ����	
					System.out.printf("\n%s%3s", field[i], "");
					
				} else {
					
					if (i % 1 == 0 && field[i].length() == 3) { // 'û�ҳ�'�� 3���̹Ƿ� �� ���߱�
						System.out.printf("%-9s", field[i]); // strA[i] ����, ����, X, ����, �ݾ�, ���
					} else {
						System.out.printf("%-10s", field[i]); // strA[i] ����, ����, X, ����, �ݾ�, ���
					}
				}
				
				if (field[i].equals("�ְ���")) {
					if (field[i+1].equals("����")) {
						v.dayToddlerCount = Integer.parseInt(field[i+2]);
						if (v.dayToddlerCount == 0) v.dayToddlerCount++;
						
					} else if (field[i+1].equals("����")) {
						v.dayChildCount = Integer.parseInt(field[i+2]);
						if (v.dayChildCount == 0) v.dayChildCount++;
					
					} else if (field[i+1].equals("û�ҳ�")) {
						v.dayTeenCount = Integer.parseInt(field[i+2]);
						if (v.dayTeenCount == 0) v.dayTeenCount++;
						
					} else if (field[i+1].equals("����")) {
						v.dayAdultCount = Integer.parseInt(field[i+2]);
						if (v.dayAdultCount == 0) v.dayAdultCount++;
					} else if (field[i+1].equals("���")) {
						v.dayElderlyCount = Integer.parseInt(field[i+2]);
						if (v.dayElderlyCount == 0) v.dayElderlyCount++;
					}
					v.dayCount = v.dayToddlerCount + v.dayChildCount + v.dayTeenCount + v.dayAdultCount + v.dayElderlyCount;
					v.tmp1 = field[i+3];
					v.dayPrice = Integer.parseInt(v.tmp1);
					v.dayPriceSum = v.dayPriceSum + v.dayPrice;
				}
				
				if (field[i].equals("�߰���")) {
					if (field[i+1].equals("����")) {
						v.nightToddlerCount = Integer.parseInt(field[i+2]);
						if (v.nightToddlerCount == 0) v.nightToddlerCount++;
						
					} else if (field[i+1].equals("����")) {
						v.nightChildCount = Integer.parseInt(field[i+2]);
						if (v.nightChildCount == 0) v.nightChildCount++;
					
					} else if (field[i+1].equals("û�ҳ�")) {
						v.nightTeenCount = Integer.parseInt(field[i+2]);
						if (v.nightTeenCount == 0) v.nightTeenCount++;
						
					} else if (field[i+1].equals("����")) {
						v.nightAdultCount = Integer.parseInt(field[i+2]);
						if (v.nightAdultCount == 0) v.nightAdultCount++;
					} else if (field[i+1].equals("���")) {
						v.nightElderlyCount = Integer.parseInt(field[i+2]);
						if (v.nightElderlyCount == 0) v.nightElderlyCount++;
					}
					v.nightCount = v.nightToddlerCount + v.nightChildCount + v.nightTeenCount + v.nightAdultCount + v.nightElderlyCount;
					v.tmp2 = field[i+3];
					v.nightPrice = Integer.parseInt(v.tmp2);
					v.nightPriceSum = v.nightPriceSum + v.nightPrice;
				}
				
				
				if (field[i].equals("*������� ����")) {
					v.noConcessionCount = Integer.parseInt(field[i-1]);
					if (v.noConcessionCount == 0) v.noConcessionCount++;
						
				} else if (field[i].equals("*����� �������")) {
					v.disabledCount = Integer.parseInt(field[i-1]);
					if (v.disabledCount == 0) v.disabledCount++;
					
				} else if (field[i].equals("*���������� �������")) {
					v.nationalMeritCount = Integer.parseInt(field[i-1]);
					if (v.nationalMeritCount == 0) v.nationalMeritCount++;
					
				} else if (field[i].equals("*���ڳ� �������")) {
					v.multichildCount = Integer.parseInt(field[i-1]);
					if (v.multichildCount == 0) v.multichildCount++;
				} else if (field[i].equals("*�ӻ�� �������")) {
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
		System.out.printf("============================= ���� �� �Ǹ���Ȳ ==============================\n\n");
		System.out.printf("\n�ְ��� �� %d��", v.dayCount);
		System.out.printf("\n���� %d��, û�ҳ� %d��, ���� %d��, ��� %d��", v.dayChildCount, v.dayTeenCount, v.dayAdultCount, v.dayElderlyCount);
		System.out.printf("\n�ְ��� ���� : %d��\n", v.dayPriceSum);
		System.out.printf("\n�߰��� �� %d��", v.nightCount);
		System.out.printf("\n���� %d��, û�ҳ� %d��, ���� %d��, ��� %d��", v.nightChildCount, v.nightTeenCount, v.nightAdultCount, v.nightElderlyCount);
		System.out.printf("\n�߰��� ���� : %d��\n", v.nightPriceSum);
		System.out.printf("============================= ���ں� ���� ��Ȳ ==============================\n\n");
		String[] dateNprice;
		
		for(int i = 0; i < incomePerDay.size(); i++ ) {

			dateNprice = incomePerDay.get(i).split(",");
			
			System.out.printf("%s�� %s�� %s�� : �� ���� %10s��\n", dateNprice[0].substring(0,4), dateNprice[0].substring(4,6), dateNprice[0].substring(6), dateNprice[1]);
			
			fw.dataWrite(dateNprice[0], dateNprice[1]);
		}
		
		System.out.printf("-----------------------------------------------------------------------------\n\n");
		System.out.printf("============================= ���� �Ǹ� ��Ȳ ==============================\n\n");
		System.out.printf("�� �Ǹ� Ƽ�� �� : %s\t\n", v.dayCount+v.nightCount);
		System.out.printf("��� ���� : %s\t\n", v.noConcessionCount);
		System.out.printf("����� : %s\t\n", v.disabledCount);
		System.out.printf("���������� : %s\t\n", v.nationalMeritCount);
		System.out.printf("���ڳ� : %s\t\n", v.multichildCount);
		System.out.printf("�ӻ�� : %s\t\n", v.pregnantCount);
		
	
		br.close();
		
	
	}
}

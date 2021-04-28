package salesReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\�̵���\\Desktop\\ticketing.csv");
		BufferedReader br = new BufferedReader(new FileReader(f));
		Variables v = new Variables();
		String readtxt;
		
		if((readtxt = br.readLine()) == null) {
		
			System.out.printf("�� �����Դϴ�\n");
			
			return;
		}
		String[] field_name = readtxt.split(",");
		
		System.out.printf("================================ report.csv =================================\n\n");
		System.out.printf("%s%9s%11s%9s%9s%13s", "��¥", "����", "���ɱ���", "����", "����", "������" + "\n\n");
		
		while ((readtxt = br.readLine()) != null) {
		
			String [] field = readtxt.split(",");

			for (int i = 0; i < field_name.length; i++) {
	
				if (i == 0 && i % 6 == 0) {
					field[i] = field[i].replaceAll("-", "");
					System.out.printf("\n%-11s", field[i]);
				} else {
					System.out.printf("%-11s", field[i]);
				}
				
				if (field[i].equals("�ְ���")) {
					if (field[i].equals("����")) v.dayAdultCount++;
					if (field[i].equals("û�ҳ�")) v.dayTeenCount++;
					if (field[i].equals("����")) v.dayChildCount++;
					if (field[i].equals("���")) v.dayElderlyCount++;
					v.dayCount++;
					v.tmp1 = field[i+3];
					v.dayPrice = Integer.parseInt(v.tmp1);
				}
				
				if (field[i].equals("�߰���")) {
					if (field[i].equals("����")) v.nightAdultCount++;
					if (field[i].equals("û�ҳ�")) v.nightTeenCount++;
					if (field[i].equals("����")) v.nightChildCount++;
					if (field[i].equals("���")) v.nightElderlyCount++;
					v.nightCount++;
					v.tmp2 = field[i+3];
					v.nightPrice = Integer.parseInt(v.tmp2);
				}
							
				
				
			}
		}
		System.out.printf("\n-----------------------------------------------------------------------------\n");
		System.out.printf("============================= ���� �� �Ǹ���Ȳ ==============================\n\n");
		System.out.printf("\n�ְ��� �� %d��", v.dayCount);
		System.out.printf("\n���� %d��, û�ҳ� %d��, ���� %d��, ��� %d��", v.dayChildCount, v.dayTeenCount, v.dayAdultCount, v.dayElderlyCount);
		System.out.printf("\n�ְ��� ���� : %d��\n", v.dayPrice);
		System.out.printf("\n�߰��� �� %d��", v.nightCount);
		System.out.printf("\n���� %d��, û�ҳ� %d��, ���� %d��, ��� %d��", v.nightChildCount, v.nightTeenCount, v.nightAdultCount, v.nightElderlyCount);
		System.out.printf("\n�߰��� ���� : %d��\n", v.nightPrice);
		
		
		System.out.printf("============================= ���ں� ���� ��Ȳ ==============================\n\n");

		
		
		
		
		
		br.close();
		
	}

}

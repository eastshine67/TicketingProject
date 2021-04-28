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
		
		String readtxt;
		
		if((readtxt = br.readLine()) == null) {
		
			System.out.printf("�� �����Դϴ�\n");
			
			return;
		}
		String[] field_name = readtxt.split(",");
		
		
		int lineCnt = 0;
		System.out.printf("================================ report.csv =================================\n\n");
		System.out.printf("%s%9s%11s%9s%9s%13s", "��¥", "����", "���ɱ���", "����", "����", "������" + "\n\n");
		
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
		
		System.out.printf("============================= ���� �� �Ǹ���Ȳ ==============================\n\n");
		
		br.close();
		
	}

}

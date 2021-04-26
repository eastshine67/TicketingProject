package ticketMachine;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class ProcessingMachine {
	Variables v = new Variables ();
	
	public void ticketMachine() throws IOException {	
		Scanner scanner = new Scanner(System.in);
		UserInput uinput = new UserInput();
	
		do {
			v.type = uinput.inputType(); // ������ �Է��ϼ���
			
			if (v.type == ConstantValues.TICKET_TYPE_DAY) {
				v.type = 1;
				v.typePrint = "�ְ���";
			} else if (v.type == ConstantValues.TICKET_TYPE_NIGHT) {
				v.type = 2;
				v.typePrint = "�߰���";
			} else {
				System.out.println("Invalid Value");
				break;
			}
			
			v.regiNum = uinput.inputRegiNumber(); // �ֹι�ȣ�� �Է��ϼ���
			if(v.regiNum.length() != 13) { // �߸��� �ֹι�ȣ�� ��� break
				System.out.println("�߸��� �ֹι�ȣ �Դϴ�.");
				break;}
			ageCal(v.regiNum); // �ֹι�ȣ�� Ȱ���Ͽ� ���̸� �ڵ� ��� v.age�� ���� ����
			agePrice(v.age); // ���̿� ������ �ش��ϴ� ���� v.price�� ����
			v.quantity = uinput.inputQuantity(); // ��� �ֹ��Ͻðڽ��ϱ�?
			System.out.println(quantityChecker(v.quantity)); // �ֹ� ���� üũ �� ����
			if (quantityChecker(v.quantity).length() > 0) break; // �ֹ� ������ 10�� �ʰ� 1�� �̸��� ��� break
			
			v.concession = uinput.inputConcession(); // �������� �����ϼ���
			concessionCal(v.concession); // v.concessionRate�� �ش� ������ ����
			
			v.finalPrice = (int)Math.round(v.concessionRate * (double)v.price);
			v.finalTotalPrice = v.finalPrice * v.quantity;
			v.purchaseList.add(v.typePrint); // �ֹ��� Ƽ�� ����
			v.purchaseList.add(v.agePrint);
			v.purchaseList.add("X");
			String printQ = Integer.toString(v.quantity);
			v.purchaseList.add(printQ);
			String printFTP = Integer.toString(v.finalTotalPrice);
			v.purchaseList.add(printFTP);
			v.sum += v.finalTotalPrice;
			v.purchaseList.add(v.concessionPrint);
			

			System.out.printf("%s\n", v.purchaseList);
			System.out.printf("\n����� �Ѿ��� %d �� �Դϴ�\n�����մϴ�\n\n", v.sum);
			v.purchaseList.add("\n");
			v.conti = uinput.inputContinue(); // ��� �߱��Ͻðڽ��ϱ�?
			
			if(v.conti == 2) {
				System.out.println("Ƽ�� �߱��� �����մϴ�. �����մϴ�.");
				v.programExit = uinput.inputExit();		
			}
			
		} while (v.programExit != ConstantValues.PROGRAM_EXIT);
		scanner.close();
	}
	
	public void ageCal(String regiNum) throws IOException {
		
		String strBirthYear = regiNum.substring(0,2);
		
		if (strBirthYear.subSequence(0, 1).equals("0") && 
				(regiNum.substring(6,7).equals("3") || regiNum.substring(6,7).equals("4"))) { // ������� 2000�� ������ ���
			v.intBirthYear = Integer.parseInt("20" + strBirthYear);
		} else { // ������� 2000�� ������ ���
			v.intBirthYear = Integer.parseInt("19" + strBirthYear);
		}
		String strBirthMonth = regiNum.substring(2,4);
		v.intBirthMonth = Integer.parseInt(strBirthMonth.trim());
		String strBirthDay = regiNum.substring(4,6);
		v.intBirthDay = Integer.parseInt(strBirthDay.trim());
		v.age = getAge(v.intBirthYear, v.intBirthMonth, v.intBirthDay);
	}
	
	public int getAge(int birthYear, int birthMonth, int birthDay) {
		
	        Calendar current = Calendar.getInstance();
	        int currentYear  = current.get(Calendar.YEAR);
	        int currentMonth = current.get(Calendar.MONTH) + 1;
	        int currentDay   = current.get(Calendar.DAY_OF_MONTH);
	        int age = currentYear - birthYear;
	        if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)  
	            age--;
	       
	        return age;
	}
	
	public void agePrice(int age) {
		
		if (v.type == 1) {
			if (age <= ConstantValues.MAX_BABY) {
				v.price = ConstantValues.BABY_PRICE;
			} else if (age > ConstantValues.MAX_BABY && age <= ConstantValues.MAX_CHILD) {
				v.price = ConstantValues.CHILD_DAY_PRICE;
				v.agePrint = "����";
			} else if (age > ConstantValues.MAX_CHILD && age <= ConstantValues.MAX_TEEN) {		
				v.price = ConstantValues.TEEN_DAY_PRICE;
				v.agePrint = "û�ҳ�";
			} else if (age > ConstantValues.MAX_TEEN && age <= ConstantValues.MAX_ADULT) {
				v.price = ConstantValues.ADULT_DAY_PRICE;
				v.agePrint = "����";
			} else {
				v.price = ConstantValues.ELDERLY_DAY_PRICE;
				v.agePrint = "���";
			}
			
		} else {
			if (age >= ConstantValues.MIN_BABY && age <= ConstantValues.MAX_BABY) {
				v.price = ConstantValues.BABY_PRICE;
			} else if (age >= ConstantValues.MIN_CHILD && age <= ConstantValues.MAX_CHILD) {
				v.price = ConstantValues.CHILD_NIGHT_PRICE;
				v.agePrint = "����";
			} else if (age >= ConstantValues.MIN_TEEN && age <= ConstantValues.MAX_TEEN) {		
				v.price = ConstantValues.TEEN_NIGHT_PRICE;
				v.agePrint = "û�ҳ�";
			} else if (age >= ConstantValues.MIN_ADULT && age <= ConstantValues.MAX_ADULT) {
				v.price = ConstantValues.ADULT_NIGHT_PRICE;
				v.agePrint = "����";
			} else {
				v.price = ConstantValues.ELDERLY_NIGHT_PRICE;
				v.agePrint = "���";
			}	
		}
	}
	
	public String quantityChecker(int quantity) {
		if (quantity > ConstantValues.MAX_QUANTITY) {
			return "10�� �̸����� �Է��ϼ���";
			
		} else if (quantity < ConstantValues.MIN_QUANTITY) {
			return "1�� �̻� �Է��ϼ���";
			
		} else {
			v.quantity = quantity;
			return "";
		}
	}
	
	public void concessionCal(int concession) {
		
		if (concession == ConstantValues.CONCESSION_NON) {
			v.concessionRate = 1;
			v.concessionPrint = "*������� ����";
		} else if (concession == ConstantValues.CONCESSION_DISABLED) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_DISABLED;
			v.concessionPrint = "*����� �������";
		} else if (concession == ConstantValues.CONCESSION_NATIONALMERIT) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_NATIONALMERIT;
			v.concessionPrint = "*���������� �������";
		} else if (concession == ConstantValues.CONCESSION_MULTICHILD) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_MULTICHILD;
			v.concessionPrint = "*���ڳ� �������";
		} else if (concession == ConstantValues.CONCESSION_PREGNANT) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_PREGNANT;
			v.concessionPrint = "*�ӻ�� �������";
		} else {
			System.out.println("Invalid Value");
		}
	}
	
}
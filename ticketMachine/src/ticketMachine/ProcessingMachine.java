package ticketMachine;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class ProcessingMachine {
	Variables v = new Variables ();
	Print p = new Print();
	public void ticketMachine() throws IOException {	
		Scanner scanner = new Scanner(System.in);
		UserInput uinput = new UserInput();
		FileWriting fw = new FileWriting();
		
		do {
			try {
				v.type = uinput.inputType(); // ������ �Է��ϼ���
				if (v.type == ConstantValues.TICKET_TYPE_DAY) {
					v.type = 1;
					v.typePrint = "�ְ���";
				} else if (v.type == ConstantValues.TICKET_TYPE_NIGHT) {
					v.type = 2;
					v.typePrint = "�߰���";
				} else {
					break;
				}
				v.regiNum = uinput.inputRegiNumber(); // �ֹι�ȣ�� �Է��ϼ���
				if (regiNumChecker(v.regiNum) == "error") break;  // �߸��� �ֹι�ȣ�� ��� break
				ageCal(v.regiNum); // �ֹι�ȣ�� Ȱ���Ͽ� ���̸� �ڵ� ��� v.age�� ���� ����
				ageSort(v.age); // ���̿� ������ �ش��ϴ� ���� v.price�� ����
				v.quantity = uinput.inputQuantity(); // ��� �ֹ��Ͻðڽ��ϱ�?
				quantityChecker(v.quantity); // �ֹ� ���� üũ �� ����
				if (quantityChecker(v.quantity) == "error") break; // �ֹ� ������ 10�� �ʰ� 1�� �̸��� ��� break
				v.concession = uinput.inputConcession(); // �������� �����ϼ���
				concessionCal(v.concession); // v.concessionRate�� �ش� ������ ����
				v.finalPrice = (int)Math.round(v.concessionRate * (double)v.price);
				v.finalTotalPrice = v.finalPrice * v.quantity;
				v.purchaseList.add(v.typePrint); // �ֹ��� Ƽ�� ����
				v.purchaseList.add(v.agePrint); // ���� ���� ����
				v.purchaseList.add("X"); // Xǥ�� ����
				v.quantityPrint = Integer.toString(v.quantity);
				v.purchaseList.add(v.quantityPrint); // ���� ����
				v.finalTotalPricePrint = Integer.toString(v.finalTotalPrice);
				v.purchaseList.add(v.finalTotalPricePrint); // ���� ����
				v.sum += v.finalTotalPrice;
				v.purchaseList.add(v.concessionPrint); // ������ ����
				p.printPrice(v.sum); // ����� �Ѿ��� *****�� �Դϴ�
				fw.dataWrite(v.typePrint, v.agePrint, v.quantityPrint, v.finalTotalPricePrint,  v.concessionPrint); // ���ų��� csv ���Ϸ� ����
				v.conti = uinput.inputContinue(); // ��� �߱��Ͻðڽ��ϱ�?
				if(v.conti == 2) {
					p.printClose();
					v.str = String.join(",", v.purchaseList);
					v.strA = v.str.split(",");
					p.printPurchaseList(v.strA);
					v.programExit = uinput.inputExit();
				}
				
			} catch (Exception e) {
				System.out.println("error");
			}
			
		} while (v.programExit != ConstantValues.PROGRAM_EXIT);
		scanner.close();
		fw.fileClose();
	}
	
	public void ageCal(String regiNum) throws IOException {
		
		String strBirthYear = regiNum.substring(0,2);
		if ((strBirthYear.subSequence(0, 1).equals("0") || strBirthYear.subSequence(0, 1).equals("1") || strBirthYear.subSequence(0, 1).equals("2")) 
				&& (regiNum.substring(6,7).equals("3") || regiNum.substring(6,7).equals("4"))) { // ������� 2000�� ������ ���
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
	
	public void ageSort(int age) {
		
		for (int i = 0; i < ConstantValues.AGE_RANGE.length; i++) {

			if (age <= ConstantValues.AGE_RANGE[0]) v.price = ConstantValues.BABY_PRICE;
			if (age >= ConstantValues.AGE_RANGE[1] && age <= ConstantValues.AGE_RANGE[2]) v.agePrint = "����";
			if (age >= ConstantValues.AGE_RANGE[3] && age <= ConstantValues.AGE_RANGE[4]) v.agePrint = "û�ҳ�";
			if (age >= ConstantValues.AGE_RANGE[5] && age <= ConstantValues.AGE_RANGE[6]) v.agePrint = "����";
			if (age > ConstantValues.AGE_RANGE[6]) v.agePrint = "���";
			agePrice();
		}
	}
	
	public void agePrice() {
		
		if (v.agePrint == "����" && v.type == 1) v.price = ConstantValues.CHILD_DAY_PRICE;
		if (v.agePrint == "����" && v.type == 2) v.price = ConstantValues.CHILD_NIGHT_PRICE;
		if (v.agePrint == "û�ҳ�" && v.type == 1) v.price = ConstantValues.TEEN_DAY_PRICE;
		if (v.agePrint == "û�ҳ�" && v.type == 2) v.price = ConstantValues.TEEN_NIGHT_PRICE;
		if (v.agePrint == "����" && v.type == 1) v.price = ConstantValues.ADULT_DAY_PRICE;
		if (v.agePrint == "����" && v.type == 2) v.price = ConstantValues.ADULT_NIGHT_PRICE;
		if (v.agePrint == "���" && v.type == 1) v.price = ConstantValues.ELDERLY_DAY_PRICE;
		if (v.agePrint == "���" && v.type == 2) v.price = ConstantValues.ELDERLY_NIGHT_PRICE;
	}
	
	public String regiNumChecker(String regiNum) {
		if (v.regiNum.length() != 13) {
			p.printErrorRegiNumber();
			return "error";
		
		} else {
			return "";
		}
	}
	
	public String quantityChecker(int quantity) {
		if (quantity > ConstantValues.MAX_QUANTITY) {
			p.printOverQuantity();
			return "error";
			
		} else if (quantity < ConstantValues.MIN_QUANTITY) {
			p.printUnderQuantity();
			return "error";
		
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
		}
	}
}
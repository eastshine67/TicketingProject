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
				v.type = uinput.inputType(); // 권종을 입력하세요
				if (v.type == ConstantValues.TICKET_TYPE_DAY) {
					v.type = 1;
					v.typePrint = "주간권";
				} else if (v.type == ConstantValues.TICKET_TYPE_NIGHT) {
					v.type = 2;
					v.typePrint = "야간권";
				} else {
					break;
				}
				v.regiNum = uinput.inputRegiNumber(); // 주민번호를 입력하세요
				if (regiNumChecker(v.regiNum) == "error") break;  // 잘못된 주민번호일 경우 break
				ageCal(v.regiNum); // 주민번호를 활용하여 나이를 자동 계산 v.age에 나이 저장
				ageSort(v.age); // 나이와 권종에 해당하는 가격 v.price에 설정
				v.quantity = uinput.inputQuantity(); // 몇개를 주문하시겠습니까?
				quantityChecker(v.quantity); // 주문 갯수 체크 및 저장
				if (quantityChecker(v.quantity) == "error") break; // 주문 갯수가 10개 초과 1개 미만일 경우 break
				v.concession = uinput.inputConcession(); // 우대사항을 선택하세요
				concessionCal(v.concession); // v.concessionRate에 해당 할인율 저장
				v.finalPrice = (int)Math.round(v.concessionRate * (double)v.price);
				v.finalTotalPrice = v.finalPrice * v.quantity;
				v.purchaseList.add(v.typePrint); // 주문한 티켓 저장
				v.purchaseList.add(v.agePrint); // 연령 구분 저장
				v.purchaseList.add("X"); // X표지 저장
				v.quantityPrint = Integer.toString(v.quantity);
				v.purchaseList.add(v.quantityPrint); // 수량 저장
				v.finalTotalPricePrint = Integer.toString(v.finalTotalPrice);
				v.purchaseList.add(v.finalTotalPricePrint); // 가격 저장
				v.sum += v.finalTotalPrice;
				v.purchaseList.add(v.concessionPrint); // 우대사항 저장
				p.printPrice(v.sum); // 입장료 총액은 *****원 입니다
				fw.dataWrite(v.typePrint, v.agePrint, v.quantityPrint, v.finalTotalPricePrint,  v.concessionPrint); // 구매내역 csv 파일로 저장
				v.conti = uinput.inputContinue(); // 계속 발권하시겠습니까?
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
				&& (regiNum.substring(6,7).equals("3") || regiNum.substring(6,7).equals("4"))) { // 출생일이 2000년 이후인 경우
			v.intBirthYear = Integer.parseInt("20" + strBirthYear);
		} else { // 출생일이 2000년 이전인 경우
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
			if (age >= ConstantValues.AGE_RANGE[1] && age <= ConstantValues.AGE_RANGE[2]) v.agePrint = "소인";
			if (age >= ConstantValues.AGE_RANGE[3] && age <= ConstantValues.AGE_RANGE[4]) v.agePrint = "청소년";
			if (age >= ConstantValues.AGE_RANGE[5] && age <= ConstantValues.AGE_RANGE[6]) v.agePrint = "대인";
			if (age > ConstantValues.AGE_RANGE[6]) v.agePrint = "경로";
			agePrice();
		}
	}
	
	public void agePrice() {
		
		if (v.agePrint == "소인" && v.type == 1) v.price = ConstantValues.CHILD_DAY_PRICE;
		if (v.agePrint == "소인" && v.type == 2) v.price = ConstantValues.CHILD_NIGHT_PRICE;
		if (v.agePrint == "청소년" && v.type == 1) v.price = ConstantValues.TEEN_DAY_PRICE;
		if (v.agePrint == "청소년" && v.type == 2) v.price = ConstantValues.TEEN_NIGHT_PRICE;
		if (v.agePrint == "대인" && v.type == 1) v.price = ConstantValues.ADULT_DAY_PRICE;
		if (v.agePrint == "대인" && v.type == 2) v.price = ConstantValues.ADULT_NIGHT_PRICE;
		if (v.agePrint == "경로" && v.type == 1) v.price = ConstantValues.ELDERLY_DAY_PRICE;
		if (v.agePrint == "경로" && v.type == 2) v.price = ConstantValues.ELDERLY_NIGHT_PRICE;
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
			v.concessionPrint = "*우대적용 없음";
		} else if (concession == ConstantValues.CONCESSION_DISABLED) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_DISABLED;
			v.concessionPrint = "*장애인 우대적용";
		} else if (concession == ConstantValues.CONCESSION_NATIONALMERIT) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_NATIONALMERIT;
			v.concessionPrint = "*국가유공자 우대적용";
		} else if (concession == ConstantValues.CONCESSION_MULTICHILD) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_MULTICHILD;
			v.concessionPrint = "*다자녀 우대적용";
		} else if (concession == ConstantValues.CONCESSION_PREGNANT) {
			v.concessionRate = ConstantValues.CONCESSION_RATE_PREGNANT;
			v.concessionPrint = "*임산부 우대적용";
		}
	}
}
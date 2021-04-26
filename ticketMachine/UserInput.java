package ticketMachine;

import java.util.Scanner;

public class UserInput {
	Scanner scanner = null;
	
		public UserInput() {
			scanner = new Scanner(System.in);
		}
		
		public int inputType() {
			System.out.println("권종을 입력하세요.");
			System.out.println("1. 주간권");
			System.out.println("2. 야간권");
			int type;
			type = scanner.nextInt();
		
			return type;
		}
	
		public String inputRegiNumber() {
			System.out.println("주민번호를 입력하세요.");
			String regiNum;
			regiNum = scanner.next();
		
			return regiNum;
		}
		
		public int inputQuantity() {
			System.out.println("몇개를 주문하시겠습니까? (최대 10개)");
			int quantity;
			quantity = scanner.nextInt();
		
			return quantity;
		}
		
		public int inputConcession() {
			System.out.println("우대사항을 선택하세요.");
			System.out.println("1. 없음 (나이 우대는 자동처리)");
			System.out.println("2. 장애인");
			System.out.println("3. 국가유공자");
			System.out.println("4. 다자녀");
			System.out.println("5. 임산부");
			int type;
			type = scanner.nextInt();
		
			return type;
		}
		
		
		public int inputContinue() {
			System.out.println("계속 발권 하시겠습니까?");
			System.out.println("1. 티켓 발권");
			System.out.println("2. 종료");

			int con;
			con = scanner.nextInt();
		
			return con;
		}
		
		public int inputExit() {
			System.out.println("계속 진행(1: 새로운 주문, 2: 프로그램 종료)");

			int exit;
			exit = scanner.nextInt();
		
			return exit;
		}
}

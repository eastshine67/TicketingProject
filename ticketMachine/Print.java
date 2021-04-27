package ticketMachine;

public class Print {
	Variables v = new Variables();
	
	public void printType() {
		System.out.println("권종을 입력하세요.");
		System.out.println("1. 주간권");
		System.out.println("2. 야간권");
	}
	
	public void printRegiNumber() {
		System.out.println("주민번호를 입력하세요.");	
	}
	
	public void printErrorRegiNumber() {
		System.out.println("잘못된 주민번호 입니다.");
	}
	
	public void printQuantity() {
		System.out.println("몇개를 주문하시겠습니까? (최대 10개)");
	}
	
	public void printOverQuantity() {
		System.out.println("10개 미만으로 입력하세요");
	}
	
	public void printUnderQuantity() {
		System.out.println("1개 이상 입력하세요");
	}
	
	public void printConcession() {
		System.out.println("우대사항을 선택하세요.");
		System.out.println("1. 없음 (나이 우대는 자동처리)");
		System.out.println("2. 장애인");
		System.out.println("3. 국가유공자");
		System.out.println("4. 다자녀");
		System.out.println("5. 임산부");
	}
	
	public void printPrice(int i) {
		System.out.printf("\n입장료 총액은 %d 원 입니다\n감사합니다\n\n", i);
		v.purchaseList.add("\n");
	}
	
	public void printContinue() {
		System.out.println("계속 발권 하시겠습니까?");
		System.out.println("1. 티켓 발권");
		System.out.println("2. 종료");
	}
	
	public void printClose() {
		System.out.println("티켓 발권을 종료합니다. 감사합니다.\n");
	}
	
	public void printExit() {
		System.out.println("계속 진행(1: 새로운 주문, 2: 프로그램 종료)");
	}
	
	public void printPurchaseList(String [] strA) {
		for(int i = 0; i < strA.length; i++) {
			if(i % 6 == 0 && i != 0) System.out.println();
			
			if(i % 1 == 0 && strA[i].length() == 3) { // '청소년'은 3자이므로 줄 맞추기
				System.out.printf("%-7.7s", strA[i]); // strA[i] 권종, 나이, X, 갯수, 금액, 우대
			} else {
				System.out.printf("%-8.8s", strA[i]); // strA[i] 권종, 나이, X, 갯수, 금액, 우대
			}
		}
		System.out.printf("\n\n");
	}
}

package ticketMachine;

import java.util.Scanner;

public class UserInput {
	Scanner scanner = null;
	Print p = new Print();
		public UserInput() {
			scanner = new Scanner(System.in);
		}
		
		public int inputType() {
			p.printType();
			int type;
			type = scanner.nextInt();
		
			return type;
		}
	
		public String inputRegiNumber() {
			p.printRegiNumber();
			String regiNum;
			regiNum = scanner.next();
		
			return regiNum;
		}
		
		public int inputQuantity() {
			p.printQuantity();
			int quantity;
			quantity = scanner.nextInt();
		
			return quantity;
		}
		
		public int inputConcession() {
			p.printConcession();
			int type;
			type = scanner.nextInt();
		
			return type;
		}
		
		
		public int inputContinue() {
			p.printContinue();
			int con;
			con = scanner.nextInt();
		
			return con;
		}
		
		public int inputExit() {
			p.printExit();
			int exit;
			exit = scanner.nextInt();
			
			return exit;
		}
}

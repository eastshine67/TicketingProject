package ticketMachine;

import java.util.Scanner;

public class UserInput {
	Scanner scanner = null;
	
		public UserInput() {
			scanner = new Scanner(System.in);
		}
		
		public int inputType() {
			System.out.println("������ �Է��ϼ���.");
			System.out.println("1. �ְ���");
			System.out.println("2. �߰���");
			int type;
			type = scanner.nextInt();
		
			return type;
		}
	
		public String inputRegiNumber() {
			System.out.println("�ֹι�ȣ�� �Է��ϼ���.");
			String regiNum;
			regiNum = scanner.next();
		
			return regiNum;
		}
		
		public int inputQuantity() {
			System.out.println("��� �ֹ��Ͻðڽ��ϱ�? (�ִ� 10��)");
			int quantity;
			quantity = scanner.nextInt();
		
			return quantity;
		}
		
		public int inputConcession() {
			System.out.println("�������� �����ϼ���.");
			System.out.println("1. ���� (���� ���� �ڵ�ó��)");
			System.out.println("2. �����");
			System.out.println("3. ����������");
			System.out.println("4. ���ڳ�");
			System.out.println("5. �ӻ��");
			int type;
			type = scanner.nextInt();
		
			return type;
		}
		
		
		public int inputContinue() {
			System.out.println("��� �߱� �Ͻðڽ��ϱ�?");
			System.out.println("1. Ƽ�� �߱�");
			System.out.println("2. ����");

			int con;
			con = scanner.nextInt();
		
			return con;
		}
		
		public int inputExit() {
			System.out.println("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����)");

			int exit;
			exit = scanner.nextInt();
		
			return exit;
		}
}

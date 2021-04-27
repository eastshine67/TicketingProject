package ticketMachine;

public class Print {
	Variables v = new Variables();
	
	public void printType() {
		System.out.println("������ �Է��ϼ���.");
		System.out.println("1. �ְ���");
		System.out.println("2. �߰���");
	}
	
	public void printRegiNumber() {
		System.out.println("�ֹι�ȣ�� �Է��ϼ���.");	
	}
	
	public void printErrorRegiNumber() {
		System.out.println("�߸��� �ֹι�ȣ �Դϴ�.");
	}
	
	public void printQuantity() {
		System.out.println("��� �ֹ��Ͻðڽ��ϱ�? (�ִ� 10��)");
	}
	
	public void printOverQuantity() {
		System.out.println("10�� �̸����� �Է��ϼ���");
	}
	
	public void printUnderQuantity() {
		System.out.println("1�� �̻� �Է��ϼ���");
	}
	
	public void printConcession() {
		System.out.println("�������� �����ϼ���.");
		System.out.println("1. ���� (���� ���� �ڵ�ó��)");
		System.out.println("2. �����");
		System.out.println("3. ����������");
		System.out.println("4. ���ڳ�");
		System.out.println("5. �ӻ��");
	}
	
	public void printPrice(int i) {
		System.out.printf("\n����� �Ѿ��� %d �� �Դϴ�\n�����մϴ�\n\n", i);
		v.purchaseList.add("\n");
	}
	
	public void printContinue() {
		System.out.println("��� �߱� �Ͻðڽ��ϱ�?");
		System.out.println("1. Ƽ�� �߱�");
		System.out.println("2. ����");
	}
	
	public void printClose() {
		System.out.println("Ƽ�� �߱��� �����մϴ�. �����մϴ�.\n");
	}
	
	public void printExit() {
		System.out.println("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����)");
	}
	
	public void printPurchaseList(String [] strA) {
		for(int i = 0; i < strA.length; i++) {
			if(i % 6 == 0 && i != 0) System.out.println();
			
			if(i % 1 == 0 && strA[i].length() == 3) { // 'û�ҳ�'�� 3���̹Ƿ� �� ���߱�
				System.out.printf("%-7.7s", strA[i]); // strA[i] ����, ����, X, ����, �ݾ�, ���
			} else {
				System.out.printf("%-8.8s", strA[i]); // strA[i] ����, ����, X, ����, �ݾ�, ���
			}
		}
		System.out.printf("\n\n");
	}
}

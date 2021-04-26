package ticketMachine;

import java.util.ArrayList;

public class Variables {
	int type; // day or night
	int conti; // continue or exit
	String regiNum; // resident registration number
	int quantity; // quantity of the ticket
	int concession; // type of concession
	double concessionRate;
	int programExit; // final exit
	int intBirthYear;
	int intBirthMonth;
	int intBirthDay;
	int age; // current user age
	int price; // price without concession
	int finalPrice; // price with concession
	int finalTotalPrice; // volume price with concession
	ArrayList<String> purchaseList = new ArrayList<String>(); // purchase list
	String typePrint; // type in the purchase list
	String agePrint; // age categorized in the purchase list
	String concessionPrint; // concession type in the purchase list
	int sum; // total price
	
}

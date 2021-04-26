package ticketMachine;

public class ConstantValues {

	final static int TICKET_TYPE_DAY = 1;
	final static int TICKET_TYPE_NIGHT = 2;
	final static int CONCESSION_NON = 1;
	final static int CONCESSION_DISABLED = 2;
	final static int CONCESSION_NATIONALMERIT = 3;
	final static int CONCESSION_MULTICHILD = 4;
	final static int CONCESSION_PREGNANT = 5;
	final static double CONCESSION_RATE_DISABLED = 0.6; // price * CONCESSION_RATE_DISABLED
	final static double CONCESSION_RATE_NATIONALMERIT = 0.5; // price * CONCESSION_RATE_NATIONALMERIT
	final static double CONCESSION_RATE_MULTICHILD = 0.8; // price * CONCESSION_RATE_MULTICHILD
	final static double CONCESSION_RATE_PREGNANT = 0.85; // price * CONCESSION_RATE_PREGNANT
	final static int PROGRAM_CONTINUE = 1;
	final static int PROGRAM_EXIT = 2;
	final static int BABY_PRICE = 0;
	final static int ADULT_DAY_PRICE = 56000;
	final static int ADULT_NIGHT_PRICE = 46000;
	final static int TEEN_DAY_PRICE = 47000;
	final static int TEEN_NIGHT_PRICE = 40000;
	final static int CHILD_DAY_PRICE = 44000;
	final static int CHILD_NIGHT_PRICE = 37000;
	final static int ELDERLY_DAY_PRICE = 44000;
	final static int ELDERLY_NIGHT_PRICE = 37000;
	final static int MIN_BABY = 1;
	final static int MAX_BABY = 2;
	final static int MIN_CHILD = 3;
	final static int MAX_CHILD = 12;
	final static int MIN_TEEN = 13;
	final static int MAX_TEEN = 18;	
	final static int MIN_ADULT = 19;
	final static int MAX_ADULT = 64;
	final static int MIN_QUANTITY = 1;
	final static int MAX_QUANTITY = 10;
}

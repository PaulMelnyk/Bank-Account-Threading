/**
 * @author PaulMelnyk
 * studentID: 14008771
 * @version 1.0
 * @serial Networking Assignment
 */
public class Card extends Thread {
	public Account a1; //allows access to a1
	int withAmount, depAmount; //variables to store the random variables
	int localBal; //used to print value for threads
	int value; //stores current transaction value, for printing.
	
	public Card(Account a1) {
		this.a1 = a1; //initiates an account inside of card
	}

	public void run() {
		localBal = 0; //starts the balance of user when new thread starts
		for (int i = 0; i < 20; i++) {
						
			if (Math.random() > 0.5) {
				value = (int) (Math.random() * 10);//stores the random value for withdraw
				a1.withdraw(value,getId()); //withdraws the given amount
				localBal +=value; //adds to the users hand, as value goes up when withdraw occurs
			} 
			else {
				value = (int) (Math.random() * 10); //stores the random value for deposit
				a1.deposit(value,getId());//deposits the given amount
				localBal -=value; //minuses users hand, as value goes down when deposit occurs
			}
			try {
				sleep(200); 
			} 
			catch (InterruptedException e) { //catches error
				e.printStackTrace();
			}

		}
		String totalPrint = String.format("%5s (%d) %15s %20s %15d","END",getId(),"--","--",localBal); //creates a format for end of thread
		System.out.println(totalPrint);
	}

}

import java.util.ArrayList; //allows arraylists to be imported
/**
 * @author PaulMelnyk
 * studentID: 14008771
 * @version 1.0
 * @serial Networking Assignment
 */
public class Account {

	int balance; //stores balance var
	int transId=1; //count for transaction array
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> end = new ArrayList<String>();
	static String balPrint; // prints beginning balance for threads
	static String title = String.format("%15s %15s %15s %15s", "Transaction", "Withdraw", "Deposit", "Balance"); //prints title
	static String tPrint; //prints each transaction
	
	public Account(int bal) {
		balance = bal; 
		balPrint = String.format("%63d",bal); //prints initial balance
	}

	public synchronized void withdraw(int withAmount,long id) { 
		while(balance<withAmount){ //checks to see if enough money in account so withdraw doesnt make negative value remain
			try {
				wait(); //if not enough in account, withdraw will not go through
			} 
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		balance=balance-withAmount;
		tPrint = String.format("%5d (%d) %15d %20s %15d",transId,id,withAmount,"--",balance);
			transId+=1; //used to number transactions
		list.add(tPrint); //adds the withdraw to the arraylist
	}

	public synchronized void deposit(int depAmount,long id) {
		balance = balance + depAmount;
		notify(); //will notify the wait, to say there is enough in account now to withdraw
			tPrint = String.format("%5d (%d) %15s %20d %15d",transId,id,"--",depAmount,balance); //creates a format for deposit
			transId+=1; // used to number transactions
			list.add(tPrint); //adds the deposit to arraylist
	}
	
	public void printStatement() {
		
		System.out.println(title); // heading print
		System.out.println(balPrint); //prints inital val
		
		for(int i=0;i<list.size();i++){ //loops to print all the transactions
			System.out.println(list.get(i)); //prints the val at i in the arraylist
		}
		System.out.println(String.format("%36s", "COMPLETE")); //prints a completion statement when all values in arraylist have been printed
		}	
	
}

/**
 * @author PaulMelnyk
 * studentID: 14008771
 * @version 1.0
 * @serial Networking Assignment
 */
public class Main {

	public static void main(String[] args) throws InterruptedException 
	{
		if (args.length != 2) { //checks to see if args are input
			System.out.println("Error, Values for 'Account Balance' and 'Number Of Cards' not supplied"); //prints if args not there
			System.exit(0); //exits the program
		}
		
		else {
			int bal = Integer.parseInt(args[0]); // sets bal as arg 0
			int card = Integer.parseInt(args[1]); // sets card as arg 1
			
			Account a1 = new Account(bal); //creates account with balance
			Card[] cArray = new Card[card]; //creates array for card passing in arg for length
			
			for(int i=0; i<cArray.length;i++){
				cArray[i] = new Card(a1); //adds new card from instance account to i 
				cArray[i].start(); //starts the thread
			}
			
			for(int i=0; i<cArray.length;i++){
				try{
					cArray[i].join(); //prevents thread from running until current executes
				} 
				catch (InterruptedException e){
					e.printStackTrace();
				}	
			}
			
			a1.printStatement(); //prints all values

		}
	}

}

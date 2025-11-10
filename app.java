package IBS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class app {
	public app(){
		isr = new InputStreamReader(System.in);
		buff = new BufferedReader(isr);
		myOperations = new BankOperations(buff);
	}
	Customer myObject;
	InputStreamReader isr;
	BufferedReader  buff;
	BankOperations myOperations;
	String operations, choice;
	public static void main(String[] args){
		app myObj = new app();
		myObj.startMenu();
			
//		System.out.println("Welcome to Indian Banking System...... IBS\n Please select your bank\n1. ICICI\n2. HDFC\n3. HSBC\n4. AXIS\n5. SBI");
//		System.out.println("Select your bank:");
//		myObj.myObject = new Customer();
//		myObj.myOperations = new BankOperations(myObj.buff);
//		try {
//			myObj.choice = myObj.buff.readLine();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		myObj.myObject = new Customer("name", "phone");
//		myObj.myObject.setCustName("name1");
//		myObj.myObject.setCustPhone("phone1");
//		System.out.println("Enter your name:");
//		String name = myObj.buff.readLine();
//		switch(Integer.parseInt(myObj.choice)) {
//		case 1: System.out.println("ICICI selected!"); break;
//		case 2: System.out.println("HDFC selected!"); break;
//		case 3: System.out.println("HSBC selected!"); break;
//		case 4: System.out.println("AXIS selected!"); break;
//		case 5: System.out.println("SBI selected!"); break;
//		default: System.out.println("No bank selected!"); break;
//		}
//		
//		System.out.println(name);
//		
//		
//		System.out.println("Please select your operation\n1. Create account\n2. Deposit\n3. Withdraw\n4. Open FD\n5. Apply loan");
//		try {
//			myObj.operations = myObj.buff.readLine();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		switch(Integer.parseInt(myObj.operations)) {
//		case 1: myObj.myOperations.createAccount(); break;
//		case 2: myObj.myOperations.depositMoney(); break;
//		case 3: myObj.myOperations.withdrawMoney(); break;
//		case 4: myObj.myOperations.openFD(); break;
//		case 5: myObj.myOperations.applyLoan(); break;
//		default: System.out.println("No operation selected!"); break;
//		}
//		System.out.println("bank operations Restored");
	}
	
	void startMenu() {
		System.out.println("--------------------------------------");
		System.out.println("Welcome to INDIAN BANKING SYSTEM (IBS)");
		System.out.println("--------------------------------------");
		
		boolean exit = false;
		
		while(!exit) {
			try {
				System.out.println("Select your bank:");
				System.out.println("1. ICICI\n2. HDFC\n3. HSBC\n4. AXIS\n5. SBI\n6. Exit");
				
				choice = buff.readLine();
				
				int bankChoice = Integer.parseInt(choice);
				
				if(bankChoice == 6) {
					System.out.println("Thank u for using INDIAN BANKING SYSTEM");
					break;
				}
				
				String bankName = switch(bankChoice) {
				case 1-> "ICICI"; 
				case 2-> "HDFC"; 
				case 3-> "HSBC"; 
				case 4-> "AXIS"; 
				case 5-> "SBI"; 
				default-> null;
				};
				
				if(bankName == null) {
					System.out.println("Invalid bank choice");
					continue;
				}
				System.out.println("\n" + bankName + " Bank Selected!");
				boolean backToBankMenu = false;
				while(!backToBankMenu) {
					System.out.println("Please select your operation\n1. Create account\n2. Deposit\n3. Withdraw\n4. Open FD\n5. Apply loan\n6. Go back to bank selection");
					System.out.println("Enter your choice : ");
					operations = buff.readLine();
					  
					int op = Integer.parseInt(operations);
					
					switch(op) {
					case 1-> myOperations.createAccount(bankName); 
					case 2-> myOperations.depositMoney(bankName); 
					case 3-> myOperations.withdrawMoney(bankName); 
					case 4-> myOperations.openFD(bankName); 
					case 5-> myOperations.applyLoan(bankName); 
					case 6-> backToBankMenu = true; 
					default-> System.out.println("Invalid operation choice"); 
					}
				}
			}catch(IOException e) {
				System.out.println("Input error : " + e.getMessage());
			}
			catch(NumberFormatException e) {
				System.out.println("Please enter valid input");
			}
		}
		
	}
}

package IBS;

import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;

public class BankOperations {
	BufferedReader buff;
	Map<String, List<Customer>> bankCustomer = new HashMap<>();
	BankOperations() {
		super();
	}
	
	public BankOperations(BufferedReader buff){
		this.buff = buff;
		
		bankCustomer.put("ICICI", new ArrayList<>());
		bankCustomer.put("HDFC", new ArrayList<>());
		bankCustomer.put("HSBC", new ArrayList<>());
		bankCustomer.put("AXIS", new ArrayList<>());
		bankCustomer.put("SBI", new ArrayList<>());
	}
	void createAccount(String bankName) {
		System.out.println("Create account selected!");
		
		try {
			System.out.print("Enter your name : ");
			String name = buff.readLine();
			
			System.out.print("Enter mobile number : ");
			String phone = buff.readLine();
			
			System.out.print("Enter initial deposit : ");
			Double initialDeposit = Double.parseDouble(buff.readLine());
			
			if(initialDeposit < 5000) {
				System.out.println("!!Account creation failed amount is not 5000!!");
				return;
			}
			String accountNumber = "AC" + (int)(Math.random() * 900000 + 100000);
			
			Customer newCustomer = new Customer(name, phone);
			newCustomer.setAccountNumber(accountNumber);
			newCustomer.setBalance(initialDeposit);
			newCustomer.setBankName(bankName);
			bankCustomer.get(bankName).add(newCustomer);
			
			System.out.println("Account created successfully");
			System.out.println("---------------------------------");
			System.out.println("Bank Name : " + bankName);
			System.out.println("Account Number : " + accountNumber);
			System.out.println("Customer Name : " + name);
			System.out.println("Phone Number : " + phone);
			System.out.println("Opening amount : " + initialDeposit);
			System.out.println("----------------------------------");
		}catch(IOException e) {
			System.out.println("Error creating account : " + e.getMessage());
		} catch(NumberFormatException e) {
			System.out.println("Invalid amount entered");
		}
	}
	void depositMoney(String bankName) {
		System.out.println("Deposit selected!");
		try {
			System.out.print("Enter account number : ");
			String accNo = buff.readLine();
			
			Customer found = findCustomer(bankName, accNo);
			
			if(found == null) {
				System.out.println("Account not found");
				return;
			}
			
			System.out.print("Enter deposit amount : ");
			double amt = Double.parseDouble(buff.readLine());
			
			if(amt <= 0) {
				System.out.println("Invalid amount");
				return;
			}
			found.setBalance(found.getBalance() + amt);
			System.out.println("Deposit Successful");
			found.displayCustomerInfo();
	
		} catch(IOException e) {
			System.out.println("Error reading input : " + e.getMessage());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid amount entered.");
		}
	}
	void withdrawMoney(String bankName) {
		System.out.println("Withdraw selected!");
		try {
			System.out.println("Enter account number : ");
			String accNo = buff.readLine();
			
			Customer found = findCustomer(bankName, accNo);
			
			if(found == null) {
				System.out.println("Account not found");
				return;
			}
			
			int failedAttempt = 0;
			boolean success = false;
			
			while(failedAttempt < 3 && !success) {
				System.out.print("Enter Withdraw amount : ");
				double amount = Double.parseDouble(buff.readLine());
				if(amount <= 0) {
					System.out.println("Invalid amount");
					failedAttempt++;
					continue;
				}
				
				double newBalance = found.getBalance() - amount;
				
				if(newBalance < 5000) {
					failedAttempt++;
					System.out.println("Minimum balance Rs 5000 must be maintained");
				}
				
				if(failedAttempt == 3) {
					System.out.println("Account temporarily blocked after 3 failed attempt");
					return;
				}
				else {
					found.setBalance(newBalance);
					System.out.println("Withdraw Successful");
					found.displayCustomerInfo();
					success = true;
				}
			}
		} catch(IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	void openFD(String bankName) {
		System.out.println("OpenFD selected!");
		try {
			System.out.println("Enter account number : ");
			String accNo = buff.readLine();
			
			Customer found = findCustomer(bankName, accNo);
			if(found == null) {
				System.out.println("Account not found");
				return;
			}
			
			System.out.println("Enter FD Amount : ");
			double fdAmount = Double.parseDouble(buff.readLine());
			
			System.out.println("Enter FD Duration(In years) : ");
			int years = Integer.parseInt(buff.readLine());
			
			double interest = fdAmount * 0.10 * years;
			double maturity = fdAmount + interest;
			
			System.out.println("FD Created Successfully");
			
			System.out.println("Principal amount : " + fdAmount);
			System.out.println("Interest (10%) : " + interest);
			System.out.println("Maturity amount : " + maturity);
			System.out.println("Duration : " + years + "Years");
			
		}catch(IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}
	void applyLoan(String bankName) {
		System.out.println("Apply Loan selected!");
		try {
			System.out.println("Enter account number : ");
			String accNo = buff.readLine();
			
			Customer found = findCustomer(bankName, accNo);
			if(found == null) {
				System.out.println("Account not found");
				return;
			}
			System.out.println("Enter loan amount : ");
			double amount = Double.parseDouble(buff.readLine());
			
			System.out.println("Enter Tenure : ");
			int years = Integer.parseInt(buff.readLine());
			
			double interestRate = 0.12;
			
			double totalInterest = amount * interestRate * years;
			double totalPayable = amount + totalInterest;
			
			System.out.println("Loan Approved");
			
			System.out.println("Loan amount : " + amount);
			System.out.println("Interest : " + totalInterest);
			System.out.println("Total Payable amount : " + totalPayable);
			
			System.out.println("Tenure : " + years + "Years");
		}catch(IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	private Customer findCustomer(String bankName, String accNo) {
		List<Customer> list = bankCustomer.get(bankName);
		
		if(list != null) {
			for(Customer c : list) {
				if(c.getAccountNumber().equals(accNo)) {
					return c;
				}
			}
		}
		return null;
	}
}

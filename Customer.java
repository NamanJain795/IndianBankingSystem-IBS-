package IBS;

public class Customer {
	String custName, custPhone;
	String accountNumber;
	double balance;
	String bankName;
	
	public Customer() {
		super();
	}
	
	public Customer(String custName, String custPhone) {
		super();
		this.custName = custName;
		this.custPhone = custPhone;
	}
	
	public String getCustname() {
		return custName;
	}
	
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public void displayCustomerInfo() {
		System.out.println("-----------------------------");
		System.out.println("Bank Name : " + bankName);
		System.out.println("Account number : " + accountNumber);
		System.out.println("Customer name : " + custName);
		System.out.println("Phone Number : " + custPhone);
		System.out.println("Current balance : " + balance);
		System.out.println("------------------------------");
	}
}

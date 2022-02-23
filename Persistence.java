package bankaccountmanagement;

import java.util.Map;

import newexception.MistakeOccuredException;

interface Persistance
{
	public int addCustomer(Customer cusObj) throws MistakeOccuredException;
	
	public long addAccount(int id,AccountDetails obj)throws MistakeOccuredException;
	
	public void updateCustomerStatus(int id,int status)throws MistakeOccuredException;
	
	public void updateAccountStatus(int id,long accountNum,int status)throws MistakeOccuredException;
	
	public void deposit(int id,long accNum,double amount) throws MistakeOccuredException;
	
	public void withDraw(int id,long accNum,double amount) throws MistakeOccuredException;
	
	public Map<Integer,Customer> getCustomer() throws MistakeOccuredException;
	
	public Map<Integer, Map<Long,AccountDetails>> getAccount() throws MistakeOccuredException;
	
}

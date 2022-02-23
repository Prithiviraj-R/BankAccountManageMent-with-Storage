package bankaccountmanagement;

import java.util.HashMap;
import java.util.Map;

import newexception.MistakeOccuredException;
import util.HelperUtil;

public class Cache 
{
	Map<Integer,Customer> customer=new HashMap<>();

	Map<Integer,Map<Long,AccountDetails>> account=new HashMap();
	

	public void putCustomer(int id,Customer obj)
	{
	     
		customer.put(id, obj); 
	     
	    System.out.println(customer);

	}

	public void putAccount(int id,long accNo,AccountDetails obj)
	{
	     
		Map<Long,AccountDetails> details=account.get(id); 
	     
		if(details==null)
	    
		{
	    	 details=new HashMap();
	    
	    	 account.put(id,details);
	     
		}
	     details.put(accNo, obj);
	    
	     System.out.println(account);

	}
	
	public void getAllCustomer()
	{
		System.out.println(customer);
	}
	
	public void getAllAccount()
	{
		System.out.println(account);
	}

	public void preload(Map<Integer,Customer> obj)
	{
		
		customer.putAll(obj);

	}

	public void preloadAccount(Map<Integer,Map<Long,AccountDetails>> map)
	{
		
		account.putAll(map);
		
	}
	public Customer getDetails(int id)
	{
		Customer details=customer.get(id);
		
		return details;
	}

	public AccountDetails getAccount(int id,long accNo)
	{
		
//		HelperUtil.numberCheck(id);
		
		Map<Long, AccountDetails> map2 = account.get(id);
		
//		HelperUtil.objectCheck(map2, "CustomerId");
		
		AccountDetails accDetails = map2.get(accNo);
		
//		HelperUtil.objectCheck(accDetails, "AccountDetails");
		
		return accDetails;
		
	}

	public void setCustomerStatus(int id,int status)
	{
		Customer details=customer.get(id);
		
		if(status==1)
		{
			details.setStatus(true);
		}
		
		else if(status==0)
		{
			details.setStatus(false);
		}
		
		System.out.println(details);
	}

	public boolean getCustomerStatus(int id) throws MistakeOccuredException
	{
		
		Customer details=customer.get(id);
		
		HelperUtil.objectCheck(details, "Customer Details ");
		
		return details.isStatus();

	}

	public void setAccountStatus(int id,long accNo,int status) throws MistakeOccuredException
	{
		Map<Long,AccountDetails> accMap=account.get(id);
		
		HelperUtil.objectCheck(accMap, "AccountDetails Map ");
		
		AccountDetails details=accMap.get(accNo);
		
		HelperUtil.objectCheck(details, "Account details ");
		
		if(status==1)
		{
			details.setStatus(true);
		}
		else if(status==0)
		{
			details.setStatus(false);
		}
		
		System.out.println(details);
	}

	public boolean getAccountStatus(int id,long accNo) throws MistakeOccuredException
	{
	    Map<Long,AccountDetails> accMap=account.get(id);
		
		AccountDetails details=accMap.get(accNo);
		
		HelperUtil.objectCheck(details, "Account details ");
		
		return details.isStatus();
	}


	public void deposit(int id,long accNo,double amount) throws MistakeOccuredException
	{
		Map<Long, AccountDetails> accDetailsMap = account.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "AccountDetails Map ");
		
		AccountDetails accInfo = accDetailsMap.get(accNo);
		
		HelperUtil.objectCheck(accInfo, "Account details ");
		
		double balance = accInfo.getBalance();
		
		double newBalance = balance + amount;
		
		accInfo.setBalance(newBalance);
	}

	public void withDraw(int id,long accNo,double amount) throws MistakeOccuredException 
	{
		Map<Long, AccountDetails> accDetailsMap = account.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "AccountDetails Map ");
		
		AccountDetails accInfo = accDetailsMap.get(accNo);
		
		HelperUtil.objectCheck(accInfo, "Account details ");
		
		double balance = accInfo.getBalance();
		
		if (balance < amount) {
		
			throw new MistakeOccuredException("Insufficient balance.");
		}
		
		double newBalance = balance - amount;
		
		accInfo.setBalance(newBalance);
	}

	public double getBalance(int id,long accNo) throws MistakeOccuredException
	{
	    Map<Long, AccountDetails> accDetailsMap = account.get(id);
		
	    HelperUtil.objectCheck(accDetailsMap, "AccountDetails Map ");
	    
		AccountDetails accInfo = accDetailsMap.get(accNo);
		
		HelperUtil.objectCheck(accInfo, "Account details ");
		
		return accInfo.getBalance();
	}
}

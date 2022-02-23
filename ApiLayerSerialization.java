package bankaccountmanagement;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import newexception.MistakeOccuredException;
import util.HelperUtil;

public class ApiLayerSerialization
{
	Persistance storageObj=null;

	Properties prop=new Properties();
	
	public ApiLayerSerialization(boolean flag)
	{
		String path="";
	    prop.setProperty("File","bankaccountmanagement.Serialize");
	    prop.setProperty("DB", "bankaccountmanagement.Database");
	    if(flag)
	    {
	    	path=prop.getProperty("File");
	    }
	    else
	    {
	    	path=prop.getProperty("DB");
	    }
	  
	    	try 
	    	{
				Class storageClass=Class.forName(path);
				Object interfaceObj=storageClass.getDeclaredConstructor().newInstance();
				storageObj=(Persistance) interfaceObj;
				
			}
	    	catch (ClassNotFoundException e) 
	    	{
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	}	
	Cache cacheObj= new Cache();
	

    //    Method to load cache
	public void preLoadCustomerMap() throws MistakeOccuredException
	{
		Map<Integer,Customer> returned=storageObj.getCustomer();
		
		cacheObj.preload(returned);
	}
    
	
	//	Method to load Cache
	public void preLoadAccountMap() throws MistakeOccuredException
	{
		Map<Integer,Map<Long,AccountDetails>> returned=storageObj.getAccount();
		
		cacheObj.preloadAccount(returned);
	}
	
	
	
	// Method to create Customer
	public void addCustomerInfo(Customer cusObj)
			throws MistakeOccuredException 
	{
		
		HelperUtil.objectCheck(cusObj);
		
		
			preLoadCustomerMap();
		
		int id=storageObj.addCustomer(cusObj);
		
		
		cacheObj.putCustomer(id, cusObj);
		
	}

//	Method to get Customer Details.
	public Customer getCustomerDetails(int id) throws MistakeOccuredException
	{
		preLoadCustomerMap();
		
		HelperUtil.numberCheck(id);

		return cacheObj.getDetails(id);
		
	}
	
    //	Method to create AccountDetails.
	public void accountToCustomerId(int id,AccountDetails accObj) throws MistakeOccuredException
	{
		
	    preLoadCustomerMap();
			
	    preLoadAccountMap();	
		
		
		long accNo=storageObj.addAccount(id, accObj);
		
	    cacheObj.putAccount(id, accNo, accObj);
	
	}

    //Method to get AccountDetails
	public AccountDetails getAccountdetails(int id,long accNo) throws MistakeOccuredException
	{
		preLoadCustomerMap();
		
		preLoadAccountMap();
	
		return cacheObj.getAccount(id,accNo);
	}
	
//	Method to Set customer status.
	public void setCustomerStatus(int id,int status) throws MistakeOccuredException
	{
        preLoadCustomerMap();
		
		preLoadAccountMap();
		
		storageObj.updateCustomerStatus(id, status);
		
		cacheObj.setCustomerStatus(id, status);
	}

//	Method to get customer status. 
	public boolean getCustomerStatus(int id) throws MistakeOccuredException
	{
        
		preLoadCustomerMap();
		
		preLoadAccountMap();

		return cacheObj.getCustomerStatus(id);
		
		
	}
	
//	Method to set AccountStatus
	public void setAccountStatus(int id,long accNo,int status) throws MistakeOccuredException
	{
        preLoadCustomerMap();
		
		preLoadAccountMap();
		
		storageObj.updateAccountStatus(id, accNo, status);
		
		cacheObj.setAccountStatus(id, accNo, status);
	
	}
	
//	Method to get Account status.
	public boolean getAccountStatus(int id,long accNo) throws MistakeOccuredException
	{
        preLoadCustomerMap();
		
		preLoadAccountMap();
	
	    return cacheObj.getAccountStatus(id, accNo);
	}
	
//	Method to deposit.
	public void deposit(int id,long accNo,double amount) throws MistakeOccuredException
	{
        preLoadCustomerMap();
		
		preLoadAccountMap();
		
		storageObj.deposit(id,accNo,amount);
		
		cacheObj.deposit(id, accNo, amount);
	}
	
//	Method to withDraw
    public void withDraw(int id,long accNo,double amount) throws MistakeOccuredException
    {
    	 preLoadCustomerMap();
 		
 		preLoadAccountMap();
 		
 		storageObj.withDraw(id,accNo,amount);
 		
 		cacheObj.withDraw(id, accNo, amount);
    }
    
//    Method to get Balance.
    public double getBalance(int id,long accNo) throws MistakeOccuredException
    {

   	    preLoadCustomerMap();
		
		preLoadAccountMap();
		
		return cacheObj.getBalance(id, accNo);
    }
    public void getAllCustomer() throws MistakeOccuredException
    {
    	preLoadCustomerMap();
    	
    	cacheObj.getAllCustomer();
    }
    
    public void getAllAccount() throws MistakeOccuredException
    {
    	preLoadAccountMap();
    	
    	cacheObj.getAllAccount();
    }
    
    
	}

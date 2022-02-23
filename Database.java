package bankaccountmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import newexception.MistakeOccuredException;
import util.HelperUtil;

public class Database implements Persistance
{
	String url="jdbc:mysql://localhost:3306/bank";
	String uName="root";
	String pass="Zithraj@18";
	private long accountId;
	private int customerId;
	
	public void createTable() 
	{
		try(Connection con=DriverManager.getConnection(url, uName, pass);
		Statement st=con.createStatement();)
		{
			String query1="create table if not exists customerInfo(customerID int not null auto_increment,name varchar(25),dob varchar(10),address varchar(25),mobileNo bigint,status tinyint,primary key(customerID));";
		    String query2="create table if not exists accountInfo(accountID int not null auto_increment,customerID int not null,branchName varchar(13),balance int,status tinyint,primary key(accountID),foreign key(customerID) references customerInfo(customerID));";
		    int count1=st.executeUpdate(query1);		  
			
			int count2=st.executeUpdate(query2);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
     

	public int insertTable(String query)throws MistakeOccuredException
	{
		int count;
		try(Connection con=DriverManager.getConnection(url, uName, pass);
    			Statement st=con.createStatement();)
		{
			st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=st.getGeneratedKeys();
			rs.next();
			int id=rs.getInt(1);
			return id;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long insertTableAcc(String query)throws MistakeOccuredException
	{
		int count;
		try(Connection con=DriverManager.getConnection(url, uName, pass);
    			Statement st=con.createStatement();)
		{
			st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=st.getGeneratedKeys();
			rs.next();
			long id=rs.getInt(1);
			System.out.println(id);
			return id;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int addCustomer(Customer cusObj)throws MistakeOccuredException
	{
		String name=cusObj.getName();
		String dob=cusObj.getDob();
		String address=cusObj.getAddress();
		boolean status=cusObj.isStatus();
		long mobile=cusObj.getPhoneNumber();
		int add=insertTable("insert into customerInfo(name,dob,address,status,mobileNo) values('"+ name +"','"+dob+"','"+ address +"',"+status+","+mobile+");");
		return add;
	}
	
	public long addAccount(int id,AccountDetails accObj)throws MistakeOccuredException
	{
		String branch=accObj.getBranch();
		int customerId=accObj.getCustomerId();
		double balance=accObj.getBalance();
		boolean status=accObj.isStatus();
		long add=insertTableAcc("insert into accountInfo(customerId,branchName,balance,status) values('"+customerId+"','"+branch+"',"+ balance +","+status+");");
		return add;
	}
	
	public int updateRecord(String query) throws MistakeOccuredException
	{
		
		try(Connection con=DriverManager.getConnection(url, uName, pass);
				Statement statement=con.createStatement();)
		{
			int number=statement.executeUpdate(query);
			return number;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new MistakeOccuredException(e);
		}
	}
	
	public void updateCustomerStatus(int id,int status)throws MistakeOccuredException
	{
         updateRecord("update customerInfo set status="+status+" where customerId="+id+";");
         System.out.println("Sucessfully Updated.");
	}
	
	public void updateAccountStatus(int id,long accNo,int status)throws MistakeOccuredException
	{
		updateRecord("update accountInfo set status="+ status +" where accountId = "+ id +" and "+"customerId="+ accNo +";");
		System.out.println("Sucessfully updated.");
	}


	@Override
	public void deposit(int id, long accNum, double amount) throws MistakeOccuredException
	{
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		
		Map<Long, AccountDetails> accDetailsMap = accountMap.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "Customer account");
		
		AccountDetails accInfo = accDetailsMap.get(accNum);
		
		HelperUtil.objectCheck(accInfo, "Account Information ");
		
		double newBalance=accInfo.getBalance()+amount;
		
		updateRecord("update accountInfo set balance ="+newBalance+" where accountId ="+accNum +" and "+" customerId="+id+";");
		
	}


	@Override
	public void withDraw(int id, long accNum, double amount) throws MistakeOccuredException 
	{
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		Map<Long, AccountDetails> accDetailsMap = accountMap.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "CustomerId");
		
		AccountDetails accInfo = accDetailsMap.get(accNum);
		
		HelperUtil.objectCheck(accInfo, "AccountDetails");
		
//		accountAccess(accInfo);
		
		
		if (accInfo.getBalance() < amount) {
		
			throw new MistakeOccuredException("Insufficient balance.");
		}
		
		double newBalance = accInfo.getBalance() - amount;
		
	    updateRecord("update accountInfo set balance="+ newBalance +" where accountId= "+id+" and "+"customerId="+ accNum +";");
	}


	@Override
	public Map<Integer, Customer> getCustomer() throws MistakeOccuredException
	{
		createTable();
		Map<Integer,Customer> customerData=new HashMap<>();
		try(Connection connection=DriverManager.getConnection(url, uName, pass);
				Statement statement = connection.createStatement())
		{
			 String sql="select * from customerInfo;";
			 ResultSet result=statement.executeQuery(sql);
			
			 while(result.next())
			 {
				 Customer customerInfo=new Customer();
				 customerInfo.setName(result.getString("Name"));
				 customerInfo.setDob(result.getString("dob"));
				 customerInfo.setAddress(result.getString("Address"));
				 customerInfo.setPhoneNumber(result.getLong("MobileNo"));
				 customerId=result.getInt("customerId");
				 customerData.put(customerId, customerInfo);
			 }

					return customerData;
		}					
				
		
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new MistakeOccuredException(e);
		}
	}


	@Override
	public Map<Integer, Map<Long, AccountDetails>> getAccount() throws MistakeOccuredException
	{
		createTable();
		Map<Integer,Map<Long,AccountDetails>> accountMap=new HashMap<>();
		try(Connection connection=DriverManager.getConnection(url, uName, pass);
				Statement statement = connection.createStatement())
		{
			String sql="select * from accountInfo;";
			ResultSet result=statement.executeQuery(sql);
			 
			while(result.next())
			{
				 AccountDetails accountInfo=new AccountDetails();
				 accountInfo.setCustomerId(result.getInt("customerId"));
				 accountInfo.setBranch(result.getString("branchName"));
				 accountInfo.setBalance(result.getDouble("balance"));
				 accountInfo.setStatus(result.getBoolean("status"));
				 accountId = result.getLong("accountId");
				 accountInfo.setAccountNumber(result.getLong("accountId"));
				 Map<Long,AccountDetails> temp=accountMap.get(accountInfo.getCustomerId());
				 if(temp==null)
				 {
					 temp=new HashMap<>();
					 accountMap.put(accountInfo.getCustomerId(), temp);
				 }
				 temp.put((long) accountId, accountInfo);
				 }
			
				return accountMap;
		   }
			catch(SQLException ex)
			{
				throw new MistakeOccuredException(ex);
			}
		
	}
 	
}	
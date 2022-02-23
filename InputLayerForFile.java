package bankaccountmanagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import newexception.MistakeOccuredException;

public class InputLayerForFile 
{

	Scanner input=new Scanner(System.in);
	
	
	
	boolean flag=input.nextBoolean();
	
	ApiLayerSerialization layerObj= new ApiLayerSerialization(flag);
	    
	    
	    public void case1() throws MistakeOccuredException {
	    	Serialize obj=new Serialize();
	    	System.out.println("Generate Customer ID");
			System.out.println("Enter the count");
			int count=input.nextInt();input.nextLine();
			Customer cusObj=null;
		    AccountDetails accObj = null;
			Map newOne=null;
		    for(int i=0;i<count;i++)
		    {
		    	cusObj=new Customer();
		    	System.out.println("Enter the name:");
		    	String name=input.nextLine();
		    	System.out.println("Enter the date of birth:");
		    	String DOB=input.nextLine();
		    	System.out.println("Enter the address:");
		    	String address=input.nextLine();
		    	System.out.println("Enter the phone number:");
		    	long phNo=input.nextLong();
		    	input.nextLine();
		    	cusObj.setName(name);
		    	cusObj.setDob(DOB);
		    	cusObj.setAddress(address);
		    	cusObj.setPhoneNumber(phNo);
		    	layerObj.addCustomerInfo(cusObj);
		    }	
	    }
	    
	    public void case2() throws MistakeOccuredException {
	    	System.out.println("Adding Account to Customer Id:");
			AccountDetails accNo1=null;
			Map accMap=null;
			System.out.println("Enter the count of Account:");
			int count1=input.nextInt();
			input.nextLine();
			for(int i=0;i<count1;i++)
			{
			accNo1=new AccountDetails();
			System.out.println("Enter the Customer Id:");
			int id=input.nextInt();input.nextLine();
			System.out.println("Enter the branch:");
			String branch=input.nextLine();
			System.out.println("Enter the AccountBalance:");
			double bal=input.nextFloat();
			input.nextLine();
			accNo1.setBranch(branch);
			accNo1.setCustomerId(id);
			accNo1.setBalance(bal);
			layerObj.accountToCustomerId(id,accNo1);
			}
	    }
	    
	    public void case3() throws MistakeOccuredException
	    {
	    	System.out.println("Get the Customer Info:");
			System.out.println("Enter the id");
			int cusId=input.nextInt();input.nextLine();
			System.out.println(layerObj.getCustomerDetails(cusId));
	    }
	    
	    public void case4() throws MistakeOccuredException
	    {
	    	System.out.println("Get the Account Info:");
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println("Enter the Account number");
			long accNo=input.nextInt();input.nextLine();
			System.out.println(layerObj.getAccountdetails(cusId1,accNo));
	    }
	    
	    public void case5() throws MistakeOccuredException
	    {
	    	System.out.println("Get the Status of Account.");;
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println("Enter the Account number");
			int accNo=input.nextInt();input.nextLine();
			System.out.println(layerObj.getAccountStatus(cusId1,accNo));
	    }
	    
	    public void case6() throws MistakeOccuredException
	    {
	    	 System.out.println("Set the Status of Account.");;
				System.out.println("Enter the id");
				int cusId1=input.nextInt();input.nextLine();
				System.out.println("Enter the Account number");
				long accNo=input.nextInt();input.nextLine();
				System.out.println("Enter the Status:Active-->1,Inactive-->0");
				int status=input.nextInt();input.nextLine(); 
				layerObj.setAccountStatus(cusId1, accNo, status);
	    }
//	    
	    public void case7() throws MistakeOccuredException
	    {
	    	System.out.println("Get the Status of Customer.");;
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println(layerObj.getCustomerStatus(cusId1));
	    }
	    
	    public void case8() throws MistakeOccuredException
	    {
	    	System.out.println("set the Status of Customer.");;
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println("Enter the Status:Active-->1,Inactive-->0");
			int status=input.nextInt();input.nextLine(); 
			layerObj.setCustomerStatus(cusId1, status);
	    }
	    
	    public void case9() throws MistakeOccuredException
	    {
	    	System.out.println("Deposit");
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println("Enter the Account number");
			long accNo=input.nextLong();input.nextLine();
			System.out.println("Enter the amount to be deposit:");
			double deposit=input.nextDouble();input.nextLine();
			layerObj.deposit(cusId1, accNo, deposit);
	    }
	    
	    public void case10() throws MistakeOccuredException
	    {
	    	System.out.println("Withdraw");
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println("Enter the Account number");
			long accNo=input.nextLong();input.nextLine();
			System.out.println("Enter the amount to be deposit:");
			double withDraw=input.nextDouble();input.nextLine();
		    layerObj.withDraw(cusId1, accNo, withDraw);
	    }
//		
	    public void case11() throws MistakeOccuredException
	    {
	    	System.out.println("Balance");
			System.out.println("Enter the id");
			int cusId1=input.nextInt();input.nextLine();
			System.out.println("Enter the Account number");
			long accNo=input.nextLong();input.nextLine();
			System.out.println(layerObj.getBalance(cusId1, accNo));
	    }
	    
	    public void case12() throws MistakeOccuredException
	    {
	    	layerObj.getAllCustomer();
	    }
	    public void case13() throws MistakeOccuredException
	    {
	    	layerObj.getAllAccount();
	    }
	    public static void main(String[] args)
		{
	    	Scanner input=new Scanner(System.in);
			InputLayerForFile runnerObj=new InputLayerForFile();
			System.out.println("1.Generate Customer Id"+"\n"+"2.Create Account in customer Id"+"\n"+"3.Get Customer info"+"\n"+"4.get Account info"+"\n"+"5.get The status of Account"+"\n"+"6.Set The status of Account"+"\n"+"7.Get the Status of Customer"
			+"\n"+"8.Set the status of customer"+"\n"+"9.Deposit"+"\n"+"10.Withdraw"+"\n"+"11.Balance"+"\n"+"12.Get all Customer details"+"\n"+"12.Get All Account.");
			System.out.println("Enter the choice:");
			int choice=Integer.parseInt(input.nextLine());
			switch(choice)
			{
			case 1:
				try
				{
					runnerObj.case1();
				}
				catch(MistakeOccuredException ex)
				{
					ex.printStackTrace();
						System.out.println(ex.getMessage());
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				break;
     		case 2:
				try
				{
				  runnerObj.case2();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				break;
			case 3:
				try
				{
				   runnerObj.case3();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				break;
			case 4:
				try
				{
				runnerObj.case4();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				break;
			case 5:
				try
				{
				    runnerObj.case5();
				}
				catch(MistakeOccuredException ex)
					{
						System.out.println(ex.getMessage());
					}
				catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				break;
			case 6:
				try
				{
				   runnerObj.case6();
				}
				catch(MistakeOccuredException ex)
					{
						System.out.println(ex.getMessage());
					}
				catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				break;
			case 7:
				try
				{
				    runnerObj.case7();
				}
				catch(MistakeOccuredException ex)
					{
						System.out.println(ex.getMessage());
					}
				catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				break;
			case 8:
				try
				{
				    runnerObj.case8();
				}
				catch(MistakeOccuredException ex)
					{
						System.out.println(ex.getMessage());
					}
				catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				break;
//			
			case 9:
				try
				{
					runnerObj.case9();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(Exception ex)
				{
		            ex.printStackTrace();			
				}
				break;
				
			case 10:
				try
				{
				    runnerObj.case10();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(Exception ex)
				{
		            ex.printStackTrace();			
				}
				break;
			case 11:
				try
				{
				    runnerObj.case11();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(Exception ex)
				{
		            ex.printStackTrace();			
				}
				break;
			case 12:
				try
				{
					runnerObj.case12();
				}
				catch(MistakeOccuredException ex)
				{
					System.out.println(ex.getMessage());
				}
				break;
				
			case 13:
				try
				{
					runnerObj.case13();
				}
				catch (MistakeOccuredException e) 
				{
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Enter the correct one.");
				break;
			}
		}
}

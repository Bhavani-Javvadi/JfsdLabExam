package CRUDOperations;

import java.util.List;
import java.util.Scanner;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Controller 
{
   public static void main(String args[])
   {
	   Controller controller=new Controller();
	   Scanner sc=new Scanner(System.in);
	   while (true) {
           System.out.println("\nChoose an operation:");
           System.out.println("1. Add Customer");
           System.out.println("2. Display Customer by ID");
           System.out.println("3. Update Customer");
           System.out.println("4. Delete Customer");
           System.out.println("5. Display All Customers using Complete Object");
           System.out.println("6. Exit");
	   System.out.print("Enter your choice: ");
	   int choice = sc.nextInt();

       switch (choice) {
           case 1:
               controller.addcustomer();
               break;
           case 2:
               controller.displaycustomerbyid();
               break;
           case 3:
               controller.updatecustomer();
               break;
           case 4:
               controller.deletecustomer();
               break;
           case 5:
               controller.displayallcustomerscompleteobj();
               break;
           case 6:
               System.out.println("Exiting...");
               System.exit(0);
               break;
           default:
               System.out.println("Invalid choice! Please choose again.");
               break;
               
         }
	   }
    }
   
   public void addcustomer()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Customers customers=new Customers();
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Customer Details: \nID : ");
	   int cid=sc.nextInt();
	   System.out.println("Enter Name: ");
	   String cname = sc.next();
	   System.out.println("Enter Email: ");
	   String cemail=sc.next();
	   System.out.println("Enter Contact Number: ");
	   String ccontactno = sc.next();
	   System.out.println("Enter Address: ");
	   String caddress=sc.next();
	   
	   customers.setId(cid);
	   customers.setName(cname);
	   customers.setEmail(cemail);
	   customers.setContact(ccontactno);
	   customers.setAddress(caddress);
	   
	   session.persist(customers); //save is deprecated
	   t.commit();
	   
	   System.out.println("customer Added Successfulyy");
	   
	   session.close();
	   sf.close();
   }
   

   public void displaycustomerbyid()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   //There is no need to create transaction object because there is no DML operation
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Customer ID:");
	   int cid=sc.nextInt();
	   
	   Customers customers=session.find(Customers.class, cid);
	    if(customers!=null)
	    {
	    	System.out.println("Customer ID:"+customers.getId());
	    	System.out.println("Customer Name:"+customers.getName());
	    	System.out.println("Customer Email:"+customers.getEmail());
	    	System.out.println("Faculty Contact No:"+customers.getContact());
	    	System.out.println("Customer Address:"+customers.getAddress());
	    }
	    else
	    {
	    	System.out.println("Customer ID Not Found");
	    }
	   
	    session.close();
	    sf.close();
	   
   }
   
   public void updatecustomer() 
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Customer ID:");
	   int cid=sc.nextInt();
	   
	    Customers customers=session.find(Customers.class, cid);
	    if(customers!=null) 
	    {
	    	System.out.println("Enter Customer Name:");
	    	String cname=sc.next();
	    	System.out.println("Enter Customer contact No:");
	    	String ccontactno=sc.next();
	    	
	    	customers.setName(cname);
	    	customers.setContact(ccontactno);
	    	
	    	
	    	t.commit();
	    	System.out.println("Customer Updated Successfully");
	    	
	    }
	    else
	    {
	    	System.out.println("Customer ID Not Found");
	    }
	    
	    session.close();
	    sf.close();
   }
   
   public void deletecustomer()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Customer ID to Delete:");
	   int cid=sc.nextInt();
	   
	    Customers customers=session.find(Customers.class, cid);
	    if(customers!=null)
	    {
	    	session.remove(customers);
	    	t.commit();
	    	System.out.println("Customer Deleted Successfully");
	    }
	    else
	    {
	    	System.out.println("Customer ID Not Found");
	    }
   }
   
   public void displayallcustomerscompleteobj() { 	 
	   
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   String hql="from Customer"; 
	   
	   Query<Customers> qry=session.createQuery(hql, Customers.class); 
	   List<Customers> customers=qry.getResultList();
	   System.out.println("Total Customers="+customers.size());
	   
	   for(Customers p:customers) 
	   {
		   System.out.println("ID:"+p.getId());
		   System.out.println("Name:"+p.getName());
		   System.out.println("Email:"+p.getEmail());
		   System.out.println("Contactno:"+p.getContact());
		   System.out.println("Address:"+p.getAddress());
	   }
	   session.close();
	   sf.close();
   }
   
}

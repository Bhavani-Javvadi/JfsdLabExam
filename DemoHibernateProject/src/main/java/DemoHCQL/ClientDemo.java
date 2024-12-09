package DemoHCQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ClientDemo {

  public static void main(String[] args) {
    
    ClientDemo operations=new ClientDemo();
   // operations.addcustomer();
   operations.restrictionsdemo();
    
  }
  
  public void addcustomer()
  {
	  
	  Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	  Customer customer = new Customer();
      customer.setName("Bhavani");
      customer.setEmail("bhavani@gmail.com");
      customer.setAge(21);
      customer.setLocation("Bvrm");

      session.persist(customer);

      t.commit();
      System.out.println("Customer Added Successfully");

      session.close();
      sf.close();
  }

  
  public void restrictionsdemo()
  {
	  Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      configuration.addAnnotatedClass(Customer.class);

      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();

      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
      Root<Customer> root = cq.from(Customer.class);

      // Example: Apply restrictions
      cq.select(root).where(cb.equal(root.get("name"), "Bhavani")); // Equal criteria

      // Fetch the results
      List<Customer> customers = session.createQuery(cq).getResultList();

      // Print the results
      System.out.println("**** Customers with gender = FEMALE ****");
      for (Customer customer : customers) {
          System.out.println(customer.toString());
      }

      session.close();
      sf.close();
  }
  
  
  
}
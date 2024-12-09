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
    operations.addcustomer();
//   operations.restrictionsdemo();
    
  }
  
  public void addcustomer()
  {
	  
	  Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	  Customer customer = new Customer();
      customer.setName("Bhavs");
      customer.setEmail("Bhavs@gmail.com");
      customer.setAge(10);
      customer.setLocation("vizag");

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

//      cq.select(root).where(cb.equal(root.get("name"), "CSIT")); // Equal
//      cq.select(root).where(cb.notEqual(root.get("email"),"csit@gmail.com")); // not Equal 
//      cq.select(root).where(cb.lessThan(root.get("age"), 22)); //less than
//      cq.select(root).where(cb.greaterThan(root.get("age"), 50)); //greater than
//      cq.select(root).where(cb.like(root.get("location"), "B%")); //starts with C
      cq.select(root).where(cb.between(root.get("age"),20,25)); // Age between 20 and 25


      List<Customer> customers = session.createQuery(cq).getResultList();

      for (Customer customer : customers) {
          System.out.println(customer.toString());
      }

      session.close();
      sf.close();
  }
  
  
  
}
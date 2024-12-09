package DemoHQL;

import java.util.List;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;


public class HQLOperations 
{
   public static void main(String[] args) 
   {
	HQLOperations operations=new HQLOperations();
	  //operations.addStudent();
	//operations.displayallstudentscompleteobj();
	//operations.displayallstudentspartialobj();
	//operations.aggregatefunctions();
	//operations.updatepositionalparams();
	//operations.updatenamedparams();
	//operations.deletepositionalparams();
	//operations.deletenamedparams();
	//operations.displaystudentbyidpositionalparams();
	//operations.displaystudentbyidnamedparams();
	//operations.displaystudents();
     operations.paginationdemo();
   }
   //adding student using persistence object(PO)
   
   public void addStudent(){
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Student student=new Student();
	   student.setId(10);
	   student.setName("klef");
	   student.setGender("male");
	   student.setAge(18);
	   student.setCgpa(6.4);
	   student.setDepartment("CSE");
	   student.setEmail("kled@gmail.com");
	   student.setContact("9860059407");
	   
	   session.persist(student); 
	   t.commit();
	   System.out.println("Student Added Successfully");
	   session.close();
	   sf.close();
   }
   
   public void displayallstudentscompleteobj() { //complete object	 
	   
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   String hql="from Student"; // select * from demo_student_table
	   
	   Query<Student> qry=session.createQuery(hql, Student.class); //object
	   List<Student> studentlist=qry.getResultList();
	   System.out.println("Total student="+studentlist.size());
	   
	   for(Student s:studentlist) 
	   {
		   System.out.println("ID:"+s.getId());
		   System.out.println("Name:"+s.getName());
		   System.out.println("Gender:"+s.getGender());
		   System.out.println("Age:"+s.getAge());
		   System.out.println("Age:"+s.getCgpa());
		   System.out.println("Department:"+s.getDepartment());
		   System.out.println("Email:"+s.getEmail());
		   System.out.println("Contact:"+s.getContact());

	   }
	   session.close();
	   sf.close();
   }
   public void displayallstudentspartialobj() { //partial object
	   
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   String hql="select s.id,s.name,s.department from Student s";
	   //s is a reference object or alias
	   
	   Query<Object[]> qry=session.createQuery(hql, Object[].class); //object array
	   List<Object[]>studentlist=qry.getResultList();
	   System.out.println("Total Student="+studentlist.size());
	   for(Object[] obj:studentlist)
	   {
		   System.out.println("Student ID:"+obj[0]);
		   System.out.println("Student Name:"+obj[1]);
		   System.out.println("Student Department:"+obj[2]);
	   }
	   session.close();
	   sf.close();
   }
   public void aggregatefunctions()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   String hql1="select count(*) from Student" ;
	   Query<Long> qry1=session.createQuery(hql1,Long.class);
	   Long count=qry1.getSingleResult();
	   System.out.println("Total Students="+count);
	   
	   String hql2="select sum(cgpa) from Student" ;
	   Query<Double> qry2=session.createQuery(hql2,Double.class);
	   double sum=qry2.getSingleResult();
	   System.out.println("Total Cgpa="+sum);
	   
	   String hql3="select avg(cgpa) from Student" ;
	   Query<Double> qry3=session.createQuery(hql3,Double.class);
	   double avgage=qry3.getSingleResult();
	   System.out.println("Total average="+avgage);
	   
	   String hql4="select min(cgpa) from Student" ;
	   Query<Double> qry4=session.createQuery(hql4,Double.class);
	   double min=qry4.getSingleResult();
	   System.out.println("Min Cgpa="+min);
	   
	   String hql5="select max(cgpa) from Student" ;
	   Query<Double> qry5=session.createQuery(hql5,Double.class);
	   double max=qry5.getSingleResult();
	   System.out.println("Max Cgpa="+max);
	   session.close();
	   sf.close();
	   
	   
   }
   public void updatepositionalparams()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Student ID:");
	   int sid=sc.nextInt();
	   System.out.println("Enter Student Name:");
	   String sname=sc.next();
	   System.out.println("Enter Student Department:");
	   String sdepartment=sc.next();
	   String hql="update Student set name=?1,department=?2 where id=?3";
	   MutationQuery qry=session.createMutationQuery(hql);
	   qry.setParameter(1, sname);
	   qry.setParameter(2, sdepartment);
	   qry.setParameter(3, sid);
	   int n=qry.executeUpdate();
	   System.out.println(n +"Student(s) Updated sucessfully");
	   sc.close();
	   session.close();
	   sf.close();
	   
	   
   }
   public void deletepositionalparams()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Student ID:");
	   int sid=sc.nextInt();
	   
	   String hql="delete from Student where id=?1";
	   MutationQuery qry=session.createMutationQuery(hql);
	   qry.setParameter(1, sid);
	   int n=qry.executeUpdate();

        t.commit();
        if(n>0) {
        	System.out.println("Student Deleted Successfully");
        }
        else {
        	System.out.println("Student ID Not Found");
        }
	   sc.close();
	   session.close();
	   sf.close();
	   
	   
   }
   public void updatenamedparams()
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Student ID:");
	   int sid=sc.nextInt();
	   System.out.println("Enter Student Name:");
	   String sname=sc.next();
	   System.out.println("Enter Student Department:");
	   String sdepartment=sc.next();
	   String hql="update Student set name=:v1,department=:v2 where id=:v3";
	   MutationQuery qry=session.createMutationQuery(hql);
	   qry.setParameter("v1", sname); //v1-parameter
	   qry.setParameter("v2", sdepartment);
	   qry.setParameter("v3", sid);
	   int n=qry.executeUpdate();
	   System.out.println(n +" Student(s) Updated sucessfully");
	   t.commit();
	   sc.close();
	   session.close();
	   sf.close();
   }
   public void deletenamedparams() {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Student ID:");
	   int sid=sc.nextInt();
	   
	   String hql="delete from Student where id=:v";
	   MutationQuery qry=session.createMutationQuery(hql);
	   qry.setParameter("v", sid);
	   int n=qry.executeUpdate();

        t.commit();
        if(n>0) {
        	System.out.println("Student Deleted Successfully");
        }
        else {
        	System.out.println("Student ID Not Found");
        }
	   sc.close();
	   session.close();
	   sf.close();
	   
   }
   
   // display student by id using positional params
   public void displaystudentbyidpositionalparams() {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Student ID:");
	   int sid=sc.nextInt();
	   
	   String hql="from Student where id=?1";    
	   Query<Student> qry=session.createQuery(hql, Student.class);
	   qry.setParameter(1, sid);
	   Student s=qry.getSingleResultOrNull();
	   if(s!=null) 
	   {
		   //we can use getter methods to print every property of Product object(p)
		   System.out.println(s.toString());
	   }
	   else {
		   System.out.println("Student ID Not Found");
	   }
	   sc.close();
	   session.close();
	   sf.close();
   }
   
// display student by id using named params
   public void displayproductbyidnamedparams() {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter Student ID:");
	   int sid=sc.nextInt();
	   
	   String hql="from Student where id=:v";    //id-property
	   Query<Student> qry=session.createQuery(hql, Student.class);
	   qry.setParameter("v", sid);
	   Student s=qry.getSingleResultOrNull();
	   if(s!=null) 
	   {
		   System.out.println(s.toString());
	   }
	   else {
		   System.out.println("Product ID Not Found");
	   }
	   sc.close();
	   session.close();
	   sf.close();
   }
   
   // HQL query to display only names of all students whose CGPA is greater than 7.
   public void displaystudents() 
   {
	   Configuration configuration=new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf=configuration.buildSessionFactory();
	   Session session=sf.openSession();
	   
	   Scanner sc=new Scanner(System.in);
	  
	   
	   System.out.println("Enter Student Cgpa:");
	   double scgpa=sc.nextDouble();
	  
	   String hql="from Student where cgpa>=?1"; 
	   
	   Query<Student> qry=session.createQuery(hql, Student.class); //object
	   qry.setParameter(1, scgpa);
	   List<Student> studentlist=qry.getResultList();
	   System.out.println("Total Students="+studentlist.size());
	   
	   
	   
	   for(Student s:studentlist) 
	   {
		   System.out.println(s.getName());
	   }
	   session.close();
	   sf.close();
   }
 //pagination
   
   public void paginationdemo()
   {
     Configuration configuration = new Configuration();
       configuration.configure("hibernate.cfg.xml");
         
         SessionFactory sf = configuration.buildSessionFactory();
         Session session = sf.openSession();
         
         String hql = "from Student"; // 
         
         Query<Student> qry = session.createQuery(hql, Student.class);
         qry.setFirstResult(4);
         qry.setMaxResults(10);
         List<Student> studentlist =  qry.getResultList();
         
         System.out.println("Total students="+studentlist.size());
         
          for( Student s : studentlist) 
          {
            System.out.println(s.toString());
          }
          
          session.close();
          sf.close();
   }
   }
   
   
   


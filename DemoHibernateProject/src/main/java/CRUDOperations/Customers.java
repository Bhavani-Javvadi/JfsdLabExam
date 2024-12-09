package CRUDOperations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_table")
public class Customers 
{
   @Id
   @Column(name="cid")
   private int id;
   @Column(name="cname",nullable=false,length=50)
   private String name;
   @Column(name="cemail",nullable=false,length=50,unique=true)
   private String email;
   @Column(name="ccontactno",nullable=false,length=20,unique=true)
   private String contact;
   @Column(name="caddress",nullable=false,length=50)
   private String address;
   
@Override
public String toString() {
	return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", address="
			+ address + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
   
   
  }

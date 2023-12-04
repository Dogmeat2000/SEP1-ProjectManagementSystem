package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class Customer implements Serializable
{
  private String firstName, lastName, email;
  private Company company;
  private Address customerAddress;

  private int phoneNumber;
  private String phoneNumberPrefix;

  public Customer(String firstName, String lastName, String email, int phoneNumber, Address customerAddress)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.customerAddress = customerAddress;
    this.phoneNumberPrefix = "";
    this.company = new Company("", new Address("", "", "", 0));
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getEmail()
  {
    return email;
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPhoneNumberPrefix()
  {
    return phoneNumberPrefix;
  }

  public void setPhoneNumberPrefix(String prefix)
  {
    this.phoneNumberPrefix = prefix;
  }

  public void setPhoneNumber(int number)
  {
    this.phoneNumber = number;
  }

  public Address getCustomerAddress()
  {
    return this.customerAddress;
  }

  public Company getCustomerCompany()
  {
    return this.company;
  }

  public void set(String firstName, String lastName, String email, int phoneNumber)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  @Override public String toString()
  {
    return "Customer{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", company="
        + company + ", customerAddress=" + customerAddress + ", phoneNumber=" + phoneNumber + ", phoneNumberPrefix='" + phoneNumberPrefix
        + '\'' + '}';
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object otherCustomer)
  {
    if (!(otherCustomer instanceof Customer))
    {
      return false;
    }
    Customer other = (Customer) otherCustomer;
    return (other.getEmail().equals(this.getEmail()) && other.getFirstName().equals(this.getFirstName()) && other.getLastName()
        .equals(this.getLastName()) && other.getPhoneNumber() == this.getPhoneNumber() && other.getCustomerAddress()
        .equals(this.getCustomerAddress()) && other.getCustomerCompany().equals(this.getCustomerCompany()));
  }

}

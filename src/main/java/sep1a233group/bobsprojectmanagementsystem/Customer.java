package sep1a233group.bobsprojectmanagementsystem;

public class Customer
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

  public void set(String firstName, String lastName, String email,
      int phoneNumber)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }





}

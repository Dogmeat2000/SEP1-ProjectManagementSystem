package sep1a233group.bobsprojectmanagementsystem;

public class Customer
{
  private String firstName, lastName, email;

  private int phoneNumber;

  public Customer(String firstName, String lastName, String email,
      int phoneNumber)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
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

  public void set(String firstName, String lastName, String email,
      int phoneNumber)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }





}

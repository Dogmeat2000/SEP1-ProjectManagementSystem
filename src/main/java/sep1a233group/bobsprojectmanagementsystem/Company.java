package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class Company implements Serializable
{
  private String name;
  private Address companyAddress;

  public Company(String name, Address companyAddress)
  {
    this.name = name;
    this.companyAddress = companyAddress;
  }
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Address getCompanyAddress()
  {
    return companyAddress;
  }

  public void setCompanyAddress(Address companyAddress)
  {
    this.companyAddress = companyAddress;
  }

  @Override public String toString()
  {
    return "Company{" + "name='" + name + '\'' + ", companyAddress=" + companyAddress + '}';
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object other)
{
  if(!(other instanceof Company))
  {
    return false;
  }
  Company otherCompany = (Company) other;
  return (otherCompany.getName().equals(this.getName()) && otherCompany.getCompanyAddress().equals(this.getCompanyAddress()));
}



}

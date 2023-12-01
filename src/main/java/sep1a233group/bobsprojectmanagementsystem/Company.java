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





}

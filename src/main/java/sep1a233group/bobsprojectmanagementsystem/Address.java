package sep1a233group.bobsprojectmanagementsystem;

public class Address
{

  private String street ,apartment ,city ,country;

  private int postalCode;

  public Address (String street, String apartment, String city, String country,
      int postalCode)
  {
    this.street = street;
    this.apartment = apartment;
    this.city = city;
    this.country = country;
    this.postalCode = postalCode;
  }

  public String getStreet()
  {
    return street;
  }

  public String getApartment()
  {
    return apartment;
  }

  public String getCity()
  {
    return city;
  }

  public String getCountry()
  {
    return country;
  }

  public int getPostalCode()
  {
    return postalCode;
  }
  public void set(String street, String apartment, String city, String country,
      int postalCode)
  {
    this.street = street;
    this.apartment = apartment;
    this.city = city;
    this.country = country;
    this.postalCode = postalCode;
  }
}

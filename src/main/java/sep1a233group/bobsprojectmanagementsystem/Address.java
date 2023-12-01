package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class Address implements Serializable
{

  private String street, streetNumber, apartment, city, country;

  private int postalCode;

  public Address(String street, String city, String country, int postalCode)
  {
    this.street = street;
    this.streetNumber = "";
    this.apartment = "";
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

  public String getStreetNumber()
  {
    return streetNumber;
  }

  public String setStreet(String street)
  {
    return this.street = street;
  }

  public String setStreetNumber(String streetNumber)
  {
    return this.streetNumber = streetNumber;
  }

  public String setApartment(String apartment)
  {
    return this.apartment = apartment;
  }

  public int setPostalCode(int postalCode)
  {
    return this.postalCode = postalCode;
  }

  public String setCity(String city)
  {
    return this.city = city;
  }

  public String setCountry(String country)
  {
    return this.country = country;
  }

  public void set(String street, String apartment, String city, String country, int postalCode)
  {
    this.street = street;
    this.apartment = apartment;
    this.city = city;
    this.country = country;
    this.postalCode = postalCode;
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object other)
  {
    if (!(other instanceof Address))
    {
      return false;
    }
    Address otherAddress = (Address) other;
    return (otherAddress.getStreet().equals(this.getStreet()) && otherAddress.getStreetNumber().equals(this.getStreetNumber()) &&
        otherAddress.getApartment().equals(this.getApartment()) && otherAddress.getCity().equals(this.getCity())
            && otherAddress.getPostalCode() == this.getPostalCode() && otherAddress.getCountry().equals(this.getCountry()));
  }

  @Override public String toString()
  {
    return "Address{" + "street='" + street + '\'' + ", streetNumber='" + streetNumber + '\'' + ", apartment='" + apartment + '\''
        + ", city='" + city + '\'' + ", country='" + country + '\'' + ", postalCode=" + postalCode + '}';
  }
}

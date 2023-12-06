package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class Address implements Serializable
{

  private String street, streetNumber, apartment, city, country;

  private int postalCode;

  public Address(String street, String city, String country, int postalCode)
  {
    set(street, "", city, country, postalCode);
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

  public void setStreet(String street)
  {
    this.street = street;
  }

  public void setStreetNumber(String streetNumber)
  {
    this.streetNumber = streetNumber;
  }

  public void setApartment(String apartment)
  {
    this.apartment = apartment;
  }

  public void setPostalCode(int postalCode)
  {
    this.postalCode = postalCode;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public void set(String street, String apartment, String city, String country, int postalCode)
  {
    setStreet(street);
    setCity(city);
    setCountry(country);
    setPostalCode(postalCode);
    setStreetNumber("");
    setApartment(apartment);
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

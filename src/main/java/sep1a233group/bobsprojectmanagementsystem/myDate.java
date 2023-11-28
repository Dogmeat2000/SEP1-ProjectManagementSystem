package sep1a233group.bobsprojectmanagementsystem;

import java.util.Calendar;
import java.util.Date;

public class myDate
{
  private int day, month, year;


  /*
  * this method is the constructor, which is used when creating a new myDate object.
  */
  public myDate(int day, int month, int year)
  {
    setDay(day);
    setMonth(month);
    setYear(year);
  }
  /*
  * the constructor with no values creates a new myDate object with the current date.
  */
  public myDate()
  {
    Calendar calendar = Calendar.getInstance();
    day = calendar.get(Calendar.DAY_OF_MONTH);
    month = calendar.get(Calendar.MONTH+1);
    year = calendar.get(Calendar.YEAR);
  }

  public int getDay() { return day; }
  public void setDay(int day) { this.day = day; }
  public int getMonth() { return month; }
  public void setMonth(int month) { this.month = month; }
  public int getYear() { return year; }
  public void setYear(int year) { this.year = year; }


  /*
   * this method calculates the amount of days between two dates
  */
  public int daysBetween(myDate date)
  {
    if (date instanceof myDate)
    {
      int counter = 0;
      while (date.day != this.day && date.month != this.month && date.year != this.year)
      {
        // Find ud af hvilken dato er forrest

      }
    }
    return 6;//Retur antal dage;
  }

  /*
  * this method is used to get the object value as a string. in this situation we want our date to return in the format (dd/mm/yyyy)
  */
  @Override public String toString()
  {

    //implement Method make format (dd/mm/yyyy)
    return super.toString();
  }

  /*
   *this method can be used if you have a date and want to create a new myDate object with the same date.
   * the method uses an existing myDate object and creates a new one identical.
   */
  public myDate copy()
  {
    return new myDate(getDay(),getMonth(),getYear());
  }

  /*
   * this method compares two myDate objects to see if they are the same date or not
   */
  @Override public boolean equals(Object obj)
  {
    if (!(obj instanceof myDate))
    {
      return false;
    }
    else
    {
      myDate other = (myDate) obj;

      return (other.getDay()==day && other.getMonth() == month && other.getYear() == year);
    }
  }


  /*
   * this method returns a myDate object with the current date
   */
  public myDate Now()
  {
    return new myDate();
  }
}

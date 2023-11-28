package sep1a233group.bobsprojectmanagementsystem;

import java.util.Calendar;
import java.util.Date;

public class myDate
{
  private int day, month, year;

  public myDate(int day, int month, int year)
  {
    setDay(day);
    setMonth(month);
    setYear(year);
  }

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

  @Override public String toString()
  {

    //implement Method make format (dd/mm/yyyy)
    return super.toString();
  }

  public myDate copy()
  {
    return new myDate(getDay(),getMonth(),getYear());
  }

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

  public myDate Now()
  {
    return new myDate();
  }
}

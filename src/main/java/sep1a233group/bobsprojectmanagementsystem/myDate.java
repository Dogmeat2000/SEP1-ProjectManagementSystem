package sep1a233group.bobsprojectmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class myDate
{
  private int day;
  private int month;
  private int year;

  public myDate(int day, int month, int year)
  {
    setDay(day);
    setMonth(month);
    setYear(year);
  }
  public myDate()
  {
    Calendar now = GregorianCalendar.getInstance();
    this.day = now.get(Calendar.DAY_OF_MONTH);
    this.month = now.get(Calendar.MONTH) + 1;
    this.year = now.get(Calendar.YEAR);
  }

  public int getDay()
  {
    return day;
  }
  public int getMonth()
  {
    return month;
  }
  public int getYear()
  {
    return year;
  }

  public void setDay(int day)
  {
    if (day < 1 || day > numberOfDaysInMonths())
    {
      if (day < 1)
      {
        day = 1;
        this.day = day;
      }
      else
      {
        day = numberOfDaysInMonths();
        this.day = day;
      }
    }
    else
    {
      this.day = day;
    }
  }

  public void setMonth(int month)
  {
    if (month < 1 || month > 12)
    {
      if (month < 1)
      {
        month = 1;
        this.month = month;
      }
      else
      {
        month = 12;
        this.month = month;
      }
    }
    else
    {
      this.month = month;
    }
  }

  public void setYear(int year)
  {
    if (year < 0)
    {
      year = -year;
      this.year = year;
    }
    else
    {
      this.year = year;
    }
  }
  public boolean isLeapYear()
  {
    if (year%4==0 && year%100!=0 || year%400==0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public int numberOfDaysInMonths()
  {
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
    {
      return 31;
    }
    else if (month == 4 || month == 6 || month == 9 || month == 11)
    {
      return 30;
    }
    else if (month == 2 && isLeapYear())
    {
      return 29;
    }
    else
    {
      return 28;
    }
  }

  public int daysbetween(myDate other)
  {
    boolean date1Bigger;
    myDate date1 = (myDate)other;
    if (this.year<date1.year)
    {
      date1Bigger = true;
    }
    else if (this.year==date1.year && this.month < date1.month)
    {
      date1Bigger = true;
    }
    else if (this.month == date1.month && this.day < date1.month)
    {
      date1Bigger = true;
    }
    else
    {
      date1Bigger = false;
    }

    myDate Counterdate = new myDate(day, month, year);
    int antaldage = 0;
    if (date1Bigger)
    {
      while (date1.day != Counterdate.day || date1.month != Counterdate.month || date1.year != Counterdate.year)
      {
        antaldage++;
        Counterdate.stepForwardOneDay();
      }
    }
    else
    {
      while (date1.day != Counterdate.day || date1.month != Counterdate.month || date1.year != Counterdate.year)
      {
        antaldage++;
        date1.stepForwardOneDay();
      }
    }
    return antaldage;
  }

  public void stepForwardOneDay()
  {
    day++;
    if (day>numberOfDaysInMonths())
    {
      day=1;
      month++;
      if (month>12)
      {
        month = 1;
        year++;
      }
    }
  }

  public boolean equals(Object obj)
  {
    if (obj instanceof myDate)
    {
      myDate otherdate = (myDate)obj;
      return (this.day == otherdate.day &&
              this.month == otherdate.month &&
              this.year == otherdate.year);
    }
    else
    {
      return false;
    }
  }

  @Override public String toString()
  {
    Date date = new Date(year-1900, month-1, day);
    SimpleDateFormat MyDate = new SimpleDateFormat("dd/MM/yyyy");

    return MyDate.format(date);
  }

  public myDate copy()
  {
    return new myDate(day, month, year);
  }


  public static myDate now()
  {
    return new myDate();
  }
}
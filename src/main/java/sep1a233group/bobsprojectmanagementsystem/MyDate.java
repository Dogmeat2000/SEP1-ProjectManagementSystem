package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  public MyDate(int day, int month, int year)
  {
    set(day, month, year);
  }
  public MyDate()
  {
    Calendar now = GregorianCalendar.getInstance();
    this.setDay(now.get(Calendar.DAY_OF_MONTH));
    this.setMonth(now.get(Calendar.MONTH) + 1);
    this.setYear(now.get(Calendar.YEAR));
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
    if (this.getYear() % 4 == 0 && this.getYear() % 100 != 0 || this.getYear() % 400 == 0)
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
    if (this.getMonth() == 1 || this.getMonth() == 3 || this.getMonth() == 5 || this.getMonth() == 7 || this.getMonth() == 8 || this.getMonth() == 10 || this.getMonth() == 12)
    {
      return 31;
    }
    else if (this.getMonth() == 4 || this.getMonth() == 6 || this.getMonth() == 9 || this.getMonth() == 11)
    {
      return 30;
    }
    else if (this.getMonth() == 2 && isLeapYear())
    {
      return 29;
    }
    else
    {
      return 28;
    }
  }

  public int daysbetween(MyDate other)
  {
    boolean date1Bigger;
    MyDate date1 = (MyDate)other;
    if (this.getYear() < date1.getYear())
    {
      date1Bigger = true;
    }
    else if (this.getYear() == date1.getYear() && this.getMonth() < date1.getMonth())
    {
      date1Bigger = true;
    }
    else if (this.getMonth() == date1.getMonth() && this.getYear() < date1.getMonth())
    {
      date1Bigger = true;
    }
    else
    {
      date1Bigger = false;
    }

    MyDate counterdate = new MyDate(this.getDay(), this.getMonth(), this.getYear());
    int antaldage = 0;
    if (date1Bigger)
    {
      while (date1.getDay() != counterdate.getDay() || date1.getMonth() != counterdate.getMonth() || date1.getYear() != counterdate.getYear())
      {
        antaldage++;
        counterdate.stepForwardOneDay();
      }
    }
    else
    {
      while (date1.getDay() != counterdate.getDay() || date1.getMonth() != counterdate.getMonth() || date1.getYear() != counterdate.getYear())
      {
        antaldage++;
        date1.stepForwardOneDay();
      }
    }
    return antaldage;
  }

  public void set(int day, int month, int year)
  {
    //Check that the input is legal&Proper:
    if (year < 0)
    {
      year = year * -1;
    }
    this.year = year;

    if (month < 1)
    {
      month = 1;
    }
    if (month > 12)
    {
      month = 12;
    }
    this.month = month;

    if (day < 1)
    {
      day = 1;
    }
    if (day > this.numberOfDaysInMonth())
    {
      day = this.numberOfDaysInMonth();
    }
    this.day = day;
  }

  public void stepForwardOneDay()
  {
    //Increment the day with 1 if the given day is less than the last day of the given month.
    if (this.getDay() < 28)
    {
      this.set(this.getDay() + 1, this.getMonth(), this.getYear());
    }
    //Increment the day with 1, but this loop catches the specifics around february and leap years.
    else if (this.getDay() == 28 && this.getMonth() == 2 && !this.isLeapYear())
    {
      this.set(1, 3, this.getYear());
    }
    else if (this.getDay() == 29 && this.getMonth() == 2 && this.isLeapYear())
    {
      this.set(1, 3, this.getYear());
    }

    //Check if the date is not the last day of the month (even months) and increment with 1.
    else if (this.getDay() < 30)
    {
      this.set(this.getDay() + 1, this.getMonth(), this.getYear());
    }
    else if ((this.getDay() == 31 && this.numberOfDaysInMonth() == 31)
        && this.getMonth() == 12)
    {//This is december... increase the year by one.
      this.set(1, 1, this.getYear() + 1);
    }
    else if ((this.getDay() == 30 && this.numberOfDaysInMonth() == 30) || (
        this.getDay() == 31 && this.numberOfDaysInMonth() == 31))
    {
      this.set(1, this.getMonth() + 1, this.getYear());
    }
    else
    {
      this.set(this.getDay() + 1, this.getMonth(),
          this.getYear()); //Add a single day, as this remaining loop will only run if the day is nr. 30, but the number of days in the month is 31!
    }
  }

  public int numberOfDaysInMonth()
  {
    if (this.getMonth() == 1 || this.getMonth() == 3 || this.getMonth() == 5
        || this.getMonth() == 7 || this.getMonth() == 8 || this.getMonth() == 10
        || this.getMonth() == 12)
    {
      return 31; //Days in uneven months.
    }
    else if (this.getMonth() == 4 || this.getMonth() == 6
        || this.getMonth() == 9 || this.getMonth() == 11)
    {
      return 30; //Days in uneven months.
    }
    else if (this.getMonth() == 2)
    {
      if (this.isLeapYear())
      {
        return 29; //Leap year
      }
      else
      {
        return 28; //not leap year.
      }
    }
    else
    {
      return 0; //Unknown error!
    }
  }

  public boolean equals(Object obj)
  {
    if (obj instanceof MyDate)
    {
      MyDate otherdate = (MyDate)obj;
      return (this.getDay() == otherdate.getDay() &&
          this.getMonth() == otherdate.getMonth() &&
          this.getYear() == otherdate.getYear());
    }
    else
    {
      return false;
    }
  }

  @Override public String toString()
  {
    java.util.Date date = new java.util.Date(this.getYear() - 1900, this.getMonth() - 1, this.getDay());
    SimpleDateFormat Date = new SimpleDateFormat("dd/MM/yyyy");

    return Date.format(date);
  }

  public MyDate copy()
  {
    return new MyDate(this.getDay(), this.getMonth(), this.getYear());
  }


  public static MyDate now()
  {
    return new MyDate();
  }
}

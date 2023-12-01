package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class HumanRessources implements Serializable
{
  private double manHoursSpent, totalManHoursNeeded;

  public HumanRessources(double manHoursSpent, double totalManHoursNeeded)
  {
    setManHoursSpent(manHoursSpent);
    setTotalManHoursNeeded(totalManHoursNeeded);
  }

  public double getManHoursSpent()
  {
    return manHoursSpent;
  }

  public void setManHoursSpent(double manHoursSpent)
  {
    this.manHoursSpent = manHoursSpent;
  }

  public double getTotalManHoursNeeded()
  {
    return totalManHoursNeeded;
  }

  public void setTotalManHoursNeeded(double totalManHoursNeeded)
  {
    this.totalManHoursNeeded = totalManHoursNeeded;
  }

  @Override public String toString()
  {
    return "HumanRessources{" + "manHoursSpent=" + manHoursSpent + ", totalManHoursNeeded=" + totalManHoursNeeded + '}';
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof HumanRessources))
    {
      return false;
    }
    HumanRessources other = (HumanRessources) obj;
    return (other.getTotalManHoursNeeded() == this.getTotalManHoursNeeded() && other.getManHoursSpent() == this.getManHoursSpent());
  }
}

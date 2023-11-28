package sep1a233group.bobsprojectmanagementsystem;

public class HumanRessources
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
}

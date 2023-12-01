package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class Finances implements Serializable
{
  private double materialExpences, totalBudget;

  public Finances(double materialExpences, double totalBudget)
  {
    setMaterialExpences(materialExpences);
    setTotalBudget(totalBudget);
  }

  public double getMaterialExpences()
  {
    return materialExpences;
  }

  public void setMaterialExpences(double materialExpences)
  {
    this.materialExpences = materialExpences;
  }

  public double getTotalBudget()
  {
    return totalBudget;
  }

  public void setTotalBudget(double totalBudget)
  {
    this.totalBudget = totalBudget;
  }
}

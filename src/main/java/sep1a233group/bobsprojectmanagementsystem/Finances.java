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

  @Override public String toString()
  {
    return "Finances{" + "materialExpences=" + materialExpences + ", totalBudget=" + totalBudget + '}';
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Finances))
    {
      return false;
    }
    Finances other = (Finances) obj;
    return (other.getMaterialExpences() == this.getMaterialExpences() && other.getTotalBudget() == this.getTotalBudget());
  }
}

package sep1a233group.bobsprojectmanagementsystem;

public class ProgressReport
{
  private Customer customer;
  private Address projectAddress;
  private Finances projectFinances;
  private MyDate projectStartDate;
  private MyDate projectEndDate;
  private HumanRessources projectRessources;

  public ProgressReport(Customer customer, Address projectAddress,
      Finances projectFinances, MyDate projectStartDate, MyDate projectEndDate,
      HumanRessources projectRessources)
  {
    setProgressReport(customer, projectAddress, projectFinances, projectStartDate, projectEndDate, projectRessources);
  }
  public void setProgressReport(Customer customer, Address projectAddress,
      Finances projectFinances, MyDate projectStartDate, MyDate projectEndDate,
      HumanRessources projectRessources)
  {
    this.customer = customer;
    this.projectAddress = projectAddress;
    this.projectFinances = projectFinances;
    this.projectStartDate = projectStartDate;
    this.projectEndDate = projectEndDate;
    this.projectRessources = projectRessources;
  }
}

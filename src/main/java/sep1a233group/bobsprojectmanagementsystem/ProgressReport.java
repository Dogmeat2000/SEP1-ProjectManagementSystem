package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public class ProgressReport implements Serializable
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

  @Override public String toString()
  {
    return "ProgressReport{" + "customer=" + customer + ", projectAddress=" + projectAddress + ", projectFinances=" + projectFinances
        + ", projectStartDate=" + projectStartDate + ", projectEndDate=" + projectEndDate + ", projectRessources=" + projectRessources
        + '}';
  }

  public ProgressReport getProgressReport()
  {
    return this;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public Address getProjectAddress()
  {
    return projectAddress;
  }

  public Finances getProjectFinances()
  {
    return projectFinances;
  }

  public MyDate getProjectStartDate()
  {
    return projectStartDate;
  }

  public MyDate getProjectEndDate()
  {
    return projectEndDate;
  }

  public HumanRessources getProjectRessources()
  {
    return projectRessources;
  }
}

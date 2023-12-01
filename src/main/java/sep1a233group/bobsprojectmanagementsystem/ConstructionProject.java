package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

public abstract class ConstructionProject implements Serializable
{
  private Address projectAddress; //What is the address of the Project?
  private Customer customer; // who is the project for?
  private PromotionalInformation projectInformation; // What information do we have about the project?
  private HumanRessources humanRessources; // how many man-hours is used for the project(current and expected to finish)?
  private Finances finances; //how much is spent on the project?. what is the budget for the project?
  private ProgressReport progressReport; // how is the project going?
  private boolean isProjectFinished; //is the project finished?
  private boolean projectConfidentiality; // is the project confidential?
  private MyDate projectStartDate; //When was the project created/initiated?
  private MyDate projectEndDate; //When was the project finished (or expected to finish?)
  private boolean isDashboardProject; //Used to identify projects that should be shown on the dashboard.

  //TODO: IMPLEMENT CLASS

  public ConstructionProject()
  {
    //TODO: Implement... ALSO CONVERT THESE TEMPORARY DATA SETS TO SETTERS AND GETTERS!
    this.projectInformation = new PromotionalInformation("");
    this.projectAddress = new Address("","","",0);
    this.customer = new Customer("","","",0,new Address("","","",0));
    setProjectConfidentiality(false);
    this.projectStartDate = MyDate.now();
    this.projectEndDate = MyDate.now();
    this.humanRessources = new HumanRessources(0,0);
    this.finances = new Finances(0,0);
    this.progressReport = new ProgressReport(getCustomer(), getProjectAddress(), getFinances(), getProjectStartDate(), getProjectEndDate(), getHumanRessources());
    isProjectFinished = false;
    setDashboardProject(false);
  }

  public boolean isDashboardProject()
  {
    return isDashboardProject;
  }

  public void setDashboardProject(boolean dashboardProject)
  {
    isDashboardProject = dashboardProject;
  }

  public Address getProjectAddress() { return projectAddress; }

  public void setProjectAddress(Address projectAddress)
  {
    this.projectAddress = projectAddress;
  }

  public Customer getCustomer() { return customer; }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  public PromotionalInformation getProjectInformation() { return projectInformation; }

  public void setProjectInformation(PromotionalInformation projectInformation)
  {
    this.projectInformation = projectInformation;
  }

  public HumanRessources getHumanRessources() { return humanRessources; }

  public void setHumanRessources(HumanRessources humanRessources)
  {
    this.humanRessources = humanRessources;
  }

  public Finances getFinances() { return finances; }

  public void setFinances(Finances finances)
  {
    this.finances = finances;
  }

  public ProgressReport generateProgressReport() { return new ProgressReport(this.customer,this.projectAddress,this.finances,this.projectStartDate,this.projectEndDate,this.humanRessources); }

  public ProgressReport getProgressReport()
  {
    return progressReport;
  }

  public boolean isProjectFinished() { return isProjectFinished; }

  public void setProjectFinished(boolean projectFinished) { isProjectFinished = projectFinished; }

  public boolean isProjectConfidential() { return projectConfidentiality; }

  public void setProjectConfidentiality(boolean projectConfidentiality) { this.projectConfidentiality = projectConfidentiality; }

  public MyDate getProjectStartDate() { return projectStartDate; }

  public void setProjectStartDate(MyDate projectStartDate)
  {
    this.projectStartDate = projectStartDate;
  }

  public MyDate getProjectEndDate() { return projectEndDate;}

  public void setProjectEndDate(MyDate projectEndDate)
  {
    this.projectEndDate = projectEndDate;
  }

  /** This abstract method is compares the given ConstructionProject object with the current ConstructionProject object and returns true if they are identical.
   * It is for instance used to validate attempts of adding projects to the system in order to avoid adding duplicates.
   * Author: K. Dashnaw
   * */
  public abstract boolean equals(Object project);

  /** Abstract method for getting the project duration in months
   * Author: K. Dashnaw
   * */
  public abstract int getProjectDuration();

  /** Abstract method for setting the project duration in months
   * Author: K. Dashnaw
   */
  public abstract void setProjectDuration(int projectDuration);

   /** This abstract method, once implemented, returns the specific type of construction project this is.
   * They can either be: Residential, Commercial, Industrial or Road projects
   * Author: K. Dashnaw
   */
  public abstract String getProjectType();

  /** This abstract method, once implemented, returns a string value representing all the project data in single line.
   * Author: K. Dashnaw
   */
  public abstract String toString();
}
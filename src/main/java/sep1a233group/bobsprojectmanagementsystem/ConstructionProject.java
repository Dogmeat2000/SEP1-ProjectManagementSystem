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

  private String projectType; //Used to contain the type of project.

  //TODO: IMPLEMENT CLASS


  public ConstructionProject()
  {
    //TODO: Implement... ALSO CONVERT THESE TEMPORARY DATA SETS TO SETTERS AND GETTERS!
    this.projectInformation = new PromotionalInformation("");
    this.projectAddress = new Address("","","",0);
    this.customer = new Customer("","","",0,new Address("","","",0));
    setProjectConfidentiality(false);
    this.projectStartDate = MyDate.now();
    setProjectEndDate(MyDate.now());
    this.humanRessources = new HumanRessources(0,0);
    this.finances = new Finances(0,0);
    this.progressReport = new ProgressReport(getCustomer(), getProjectAddress(), getFinances(), getProjectStartDate(), getProjectEndDate(), getHumanRessources());
    isProjectFinished = false;
    setDashboardProject(false);
    setProjectType("Undefined");
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

  /**
   * this method generates a progress report based on the given project and returns the progress report
   * Author: Alperen Ö. */
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

  /** <p>    </p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public String getProjectType()
  {
    return this.projectType;
  }

  public void setProjectType(String type)
  {
    projectType = type;
  }

  public void setProjectEndDate(MyDate projectEndDate)
  {
    this.projectEndDate = projectEndDate;
  }

  /** <p>This abstract method is compares the given ConstructionProject object with the current ConstructionProject object and returns true if they are identical.
   * It is for instance used to validate attempts of adding projects to the system in order to avoid adding duplicates.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public abstract boolean equals(Object project);

  /** <p>Abstract method for getting the project duration in months</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public abstract int getProjectDuration();

  /** <p>Abstract method for setting the project duration in months</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public abstract void setProjectDuration(int projectDuration);

  /** <p>This abstract method, once implemented, returns a string value representing all the project data in single line.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public abstract String toString();

  /** <p>This abstract method, once implemented, returns a copy of the selected Construction Project type.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */

  /**
   * <p>returns a new project with the same values as the given project</p>
   * */
  public abstract ConstructionProject copy();

}
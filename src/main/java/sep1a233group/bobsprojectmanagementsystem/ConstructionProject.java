package sep1a233group.bobsprojectmanagementsystem;

public abstract class ConstructionProject
{
  private Address projectAddress; //What is the address of the Project?
  private Customer customer; // who is the project for?
  private PromotionalInformation projectInformation; // What information do we have about the project?
  private HumanRessources humanRessources; // how much manhours is used for the project(current and expectied to finish)?
  private Finances finances; //how much is spent on the project?. what is the budget for the project?
  private ProgressReport progressReport; // how is the project going?
  private boolean isProjectFinished; //is the project finished?
  private boolean projectConfidentiality; // is the project confidential?
  private MyDate projectStartDate; //When was the project created/initiated?
  private MyDate projectEndDate; //When was the project finished (or expected to finish?)

  //TODO: IMPLEMENT CLASS

  public ConstructionProject(PromotionalInformation projectInformation, Address projectAddress, Customer customer, boolean projectConfidentiality, MyDate projectStartDate, MyDate projectEndDate, HumanRessources humanRessources, Finances finances,ProgressReport progressReport)
  {
    //TODO: Implement... ALSO CONVERT THESE TEMPORARY DATA SETS TO SETTERS AND GETTERS!
    this.projectInformation = projectInformation;
    this.projectAddress = projectAddress;
    this.customer = customer;
    this.projectConfidentiality = projectConfidentiality;
    this.projectStartDate = projectStartDate;
    this.projectEndDate = projectEndDate;
    this.humanRessources = humanRessources;
    this.finances = finances;
    this.progressReport = progressReport;
    isProjectFinished = false;
  }

  public Address getProjectAddress() { return projectAddress; }

  public Customer getCustomer() { return customer; }

  public PromotionalInformation getProjectInformation() { return projectInformation; }

  public HumanRessources getHumanRessources() { return humanRessources; }

  public Finances getFinances() { return finances; }

  public ProgressReport getProgressReport() { return progressReport; }

  public boolean isProjectFinished() { return isProjectFinished; }

  public void setProjectFinished(boolean projectFinished) { isProjectFinished = projectFinished; }

  public boolean isProjectConfidential() { return projectConfidentiality; }

  public void setProjectConfidentiality(boolean projectConfidentiality) { this.projectConfidentiality = projectConfidentiality; }

  public MyDate getProjectStartDate() { return projectStartDate; }

  public MyDate getProjectEndDate() { return projectEndDate;}

  /** This method is compares the given ConstructionProject object with the current ConstructionProject object and returns true if they are identical.
   * It is for instance used to validate attempts of adding projects to the system in order to avoid adding duplicates.
   * Author:
   * */
  public boolean equals(ConstructionProject project)
  {
    //TODO: Implement method.
    return false; //Dummy information for now!
  }

    /**
   * This abstract method, once implemented, returns the specific type of construction project this is.
   * They can either be: Residential, Commercial, Industrial or Road projects
   * Author: K. Dashnaw
   */
  public abstract String getProjectType();
}

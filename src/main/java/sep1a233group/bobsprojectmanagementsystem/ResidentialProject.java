package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class ResidentialProject extends ConstructionProject implements Serializable
{
  private boolean isRenovation; //False = Project is a new construction. True = Project is a renovation.
  private int numberOfKitchens, numberOfBathrooms, numberOfOtherRoomsWithPlumbing; //Project data

  private double buildingSize; //Size of building project in m^2.

  private int projectDuration; // How many months the build will take.

  /** Constructs this object the first time it is created/called. */
  public ResidentialProject()
  {
    super();

    //Note: Default values are called and set in the MainModel when project is created, where user changes to these default values are also saved.
    /*If default values successfully loaded, then set them. Else set some hard-coded values!*/
    setIsRenovation(false);
    setBuildingSize(0);
    setNumberOfKitchens(0);
    setNumberOfBathrooms(0);
    setNumberOfOtherRoomsWithPlumbing(0);
    setProjectDuration(5);
    setProjectType("Residential");
  }

  /** Returns the number of kitchens the construction build has assigned. */
  public int getNumberOfKitchens()
  {
    return numberOfKitchens;
  }

  /** Sets the number of kitchens the construction build has assigned. */
  public void setNumberOfKitchens(int numberOfKitchens)
  {
    this.numberOfKitchens = numberOfKitchens;
  }

  /** Returns the number of bathrooms the construction build has assigned. */
  public int getNumberOfBathrooms()
  {
    return numberOfBathrooms;
  }

  /** Sets the number of bathrooms the construction build has assigned. */
  public void setNumberOfBathrooms(int numberOfBathrooms)
  {
    this.numberOfBathrooms = numberOfBathrooms;
  }

  /** Returns the number of 'other rooms with plumbing' the construction build has assigned. */
  public int getNumberOfOtherRoomsWithPlumbing()
  {
    return numberOfOtherRoomsWithPlumbing;
  }

  /** Sets the number of 'other rooms with plumbing' the construction build has assigned. */
  public void setNumberOfOtherRoomsWithPlumbing(
      int numberOfOtherRoomsWithPlumbing)
  {
    this.numberOfOtherRoomsWithPlumbing = numberOfOtherRoomsWithPlumbing;
  }

  /** Returns the planned building size in m^2 (Square meters) */
  public double getBuildingSize()
  {
    return buildingSize;
  }

  /** Sets the planned building size in m^2 (Square meters) */
  public void setBuildingSize(double buildingSize)
  {
    this.buildingSize = buildingSize;
  }

  /** Returns the number of months this project is planned to take to complete. */
  public int getProjectDuration()
  {
    return projectDuration;
  }

  /** Sets the number of months this project is planned to take to complete. */
  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }

  /**
   * Returns true if the project is a renovation of an already existing construction.
   * Returns false if it is an entirely new project.
   */
  public boolean getIsRenovation()
  {
    return isRenovation;
  }

  /**
   * Sets whether this the project is a renovation of an already existing
   * construction (True) or if it is an entirely new project (False).
   */
  public void setIsRenovation(boolean isRenovation)
  {
    this.isRenovation = isRenovation;
  }

  /** Returns a string value containing all project information. Useful for debugging.
   * Author: K. Dashnaw
   * */
  public String toString()
  {
    String returnValue = this.getProjectType() + "\n";
    returnValue += " BuildingSize=" + this.getBuildingSize() + "\n";
    returnValue += " isRenovation=" + this.getIsRenovation() + "\n";
    returnValue += " NumberOfKitchens=" + this.getNumberOfKitchens() + "\n";
    returnValue += " numberOfBathrooms=" + this.getNumberOfBathrooms() + "\n";
    returnValue += " numberOfOtherRoomsWithPlumbing=" + this.getNumberOfOtherRoomsWithPlumbing() + "\n";
    returnValue += " displayOnDashboard?=" + this.isDashboardProject() + "\n";
    returnValue += " ProjectStartDate=" + this.getProjectStartDate().toString() + "\n";
    returnValue += " ProjectEndDate=" + this.getProjectEndDate().toString() + "\n";
    returnValue += " ProjectDuration=" + this.getProjectDuration() + "\n";
    returnValue += " Finances=" + this.getFinances() + "\n";
    returnValue += " HumanResources=" + this.getHumanRessources() + "\n";
    returnValue += " IsProjectFinished=" + this.isProjectFinished() + "\n";
    returnValue += " isProjectConfidential=" + this.isProjectConfidential() + "\n";
    returnValue += " Customer=" + this.getCustomer() + "\n";
    returnValue += " Customer=" + this.getProjectAddress() + "\n";
    returnValue += " Customer=" + this.getProjectInformation() + "\n";

    return returnValue;
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  @Override
  public boolean equals(Object project)
  {
    if (!(project instanceof ResidentialProject))
    {
      return false;
    }
    ResidentialProject other = (ResidentialProject) project;

    //Compare local attributes for equality:
    if (!(other.getBuildingSize() == this.getBuildingSize() && other.getProjectDuration() == this.getProjectDuration() && other.getNumberOfKitchens() == this.getNumberOfKitchens() && other.getIsRenovation() == (this.getIsRenovation())
        && other.getNumberOfBathrooms() == this.getNumberOfBathrooms() && other.getNumberOfOtherRoomsWithPlumbing() == this.getNumberOfOtherRoomsWithPlumbing()))
    {
      System.out.println("1");
      return false;
    }
    //Compare Customer and Address info:
    else if(!(other.getCustomer().getCustomerCompany().equals(this.getCustomer().getCustomerCompany()) && other.getProjectAddress().equals(this.getProjectAddress())))
    {
      System.out.println("2");
      return false;
    }
    //Compare Finances and Human resources info:
    else if(!(other.getFinances().equals(this.getFinances()) && other.getHumanRessources().equals(this.getHumanRessources()) && other.isProjectConfidential() == this.isProjectConfidential() && other.isProjectFinished() == this.isProjectFinished()))
    {
      System.out.println("3");
      return false;
    }
    //Compare remaining info
    else if(!(other.getProjectInformation().equals(this.getProjectInformation()) && other.getProjectStartDate().equals(this.getProjectStartDate()) && other.getProjectEndDate().equals(this.getProjectEndDate())))
    {
      System.out.println("4");
      return false;
    }
    else
    {
      //Objects are identical!
      return true;
    }
  }

  public ConstructionProject copy()
  {
    //Create fresh project.
    ResidentialProject copyProject = new ResidentialProject();

    //Copy attribute values: First project specific values.
    copyProject.setIsRenovation(this.getIsRenovation());
    copyProject.setNumberOfOtherRoomsWithPlumbing(this.getNumberOfOtherRoomsWithPlumbing());
    copyProject.setNumberOfBathrooms(this.getNumberOfBathrooms());
    copyProject.setNumberOfKitchens(this.getNumberOfKitchens());
    copyProject.setBuildingSize(this.getBuildingSize());

    //Copy non-unique project attributes:
    copyProject.setProjectDuration(this.getProjectDuration());
    copyProject.setDashboardProject(this.isDashboardProject());
    copyProject.setProjectConfidentiality(this.isProjectConfidential());
    copyProject.setProjectType("Residential");
    copyProject.setProjectFinished(this.isProjectFinished());

    //Copy Date information:
    copyProject.setProjectStartDate(new MyDate(this.getProjectStartDate().getDay(), this.getProjectStartDate().getMonth(), this.getProjectStartDate().getYear()));
    copyProject.setProjectEndDate(new MyDate(this.getProjectEndDate().getDay(), this.getProjectEndDate().getMonth(), this.getProjectEndDate().getYear()));

    //Copy customer information:
    copyProject.setCustomer(new Customer(this.getCustomer().getFirstName(), this.getCustomer().getLastName(), this.getCustomer().getEmail(), this.getCustomer().getPhoneNumber(), new Address(this.getCustomer().getCustomerAddress().getStreet(), this.getCustomer().getCustomerAddress().getCity(), this.getCustomer().getCustomerAddress().getCountry(), this.getCustomer().getCustomerAddress().getPostalCode())));
    copyProject.getCustomer().getCustomerAddress().setStreetNumber(this.getCustomer().getCustomerAddress().getStreetNumber());
    copyProject.getCustomer().setPhoneNumberPrefix(this.getCustomer().getPhoneNumberPrefix());
    copyProject.getCustomer().getCustomerAddress().setApartment(this.getCustomer().getCustomerAddress().getApartment());
    copyProject.getCustomer().getCustomerCompany().setName(this.getCustomer().getCustomerCompany().getName());
    copyProject.getCustomer().getCustomerCompany().setCompanyAddress(new Address(this.getCustomer().getCustomerCompany().getCompanyAddress().getStreet(),this.getCustomer().getCustomerCompany().getCompanyAddress().getCity(), this.getCustomer().getCustomerCompany().getCompanyAddress().getCountry(), this.getCustomer().getCustomerCompany().getCompanyAddress().getPostalCode()));
    copyProject.getCustomer().getCustomerCompany().getCompanyAddress().setStreetNumber(this.getCustomer().getCustomerCompany().getCompanyAddress().getStreetNumber());
    copyProject.getCustomer().getCustomerCompany().getCompanyAddress().setApartment(this.getCustomer().getCustomerCompany().getCompanyAddress().getApartment());

    //Copy project location:
    copyProject.getProjectAddress().setStreet(this.getProjectAddress().getStreet());
    copyProject.getProjectAddress().setStreetNumber(this.getProjectAddress().getStreetNumber());
    copyProject.getProjectAddress().setCity(this.getProjectAddress().getCity());
    copyProject.getProjectAddress().setApartment(this.getProjectAddress().getApartment());
    copyProject.getProjectAddress().setPostalCode(this.getProjectAddress().getPostalCode());
    copyProject.getProjectAddress().setCountry(this.getProjectAddress().getCountry());

    //Copy Promotional Information:
    copyProject.getProjectInformation().setProjectName(this.getProjectInformation().getProjectName());
    copyProject.getProjectInformation().setProjectManagerComments(this.getProjectInformation().getProjectManagerComments());
    copyProject.getProjectInformation().setProjectDescription(this.getProjectInformation().getProjectDescription());
    copyProject.getProjectInformation().addPhotoURL(this.getProjectInformation().getPhotoURL());

    //Copy Human Resources information:
    copyProject.getHumanRessources().setManHoursSpent(this.getHumanRessources().getManHoursSpent());
    copyProject.getHumanRessources().setTotalManHoursNeeded(this.getHumanRessources().getTotalManHoursNeeded());

    //Copy Finances information:
    copyProject.getFinances().setTotalBudget(this.getFinances().getTotalBudget());
    copyProject.getFinances().setMaterialExpences(this.getFinances().getMaterialExpences());

    //Generate progress report:
    copyProject.generateProgressReport();

    return copyProject;
  }

}

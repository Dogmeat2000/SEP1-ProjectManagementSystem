package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class IndustrialProject extends ConstructionProject implements Serializable
{
    private String facilityType; //Used to describe what type of facility is planned.
    private double facilitySize; //Size of building project in m^2.
    private int projectDuration; // How many months the build will take.

    /** Constructs this object the first time it is created/called. */
    public IndustrialProject()
    {
      super();

      //Note: Default values are called and set in the MainModel when project is created, where user changes to these default values are also saved.
      /*If default values successfully loaded, then set them. Else set some hard-coded values!*/
      setFacilitySize(0);
      setFacilityType("");
      setProjectDuration(5);
      setProjectType("Industrial");
    }

    /** Returns a string value containing a description of the planned type of facility */
    public String getFacilityType()
    {
      return facilityType;
    }

    /** Sets a string value containing a description of the planned type of facility */
    public void setFacilityType(String facilityType)
    {
      this.facilityType = facilityType;
    }

    /** Returns the planned facility size in m^2 (Square meters) */
    public double getFacilitySize()
    {
      return facilitySize;
    }

    /** Sets the planned facility size in m^2 (Square meters) */
    public void setFacilitySize(double facilitySize)
    {
      this.facilitySize = facilitySize;
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

  /** Returns a string value containing all project information. Useful for debugging.
   * Author: K. Dashnaw
   * */
  public String toString()
  {
    String returnValue = this.getProjectType() + "\n";
    returnValue += " FacilitySize=" + this.getFacilitySize() + "\n";
    returnValue += " FacilityTYpe=" + this.getFacilityType() + "\n";
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
  public boolean equals(Object project)
  {
    if (!(project instanceof IndustrialProject))
    {
      return false;
    }
    IndustrialProject other = (IndustrialProject) project;

    //Compare local attributes for equality:
    if (!(other.getFacilitySize() == this.getFacilitySize() && other.getFacilityType().equals(this.getFacilityType())
        && other.getProjectDuration() == this.getProjectDuration()))
    {
      return false;
    }
    //Compare Customer and Address info:
    else if(!(other.getCustomer().getCustomerCompany().equals(this.getCustomer().getCustomerCompany()) && other.getProjectAddress().equals(this.getProjectAddress())))
    {
      return false;
    }
    //Compare Finances and Human resources info:
    else if(!(other.getFinances().equals(this.getFinances()) && other.getHumanRessources().equals(this.getHumanRessources()) && other.isProjectConfidential() == this.isProjectConfidential() && other.isProjectFinished() == this.isProjectFinished()))
    {
      return false;
    }
    //Compare remaining info
    else if(!(other.getProjectInformation().equals(this.getProjectInformation()) && other.getProjectStartDate().equals(this.getProjectStartDate()) && other.getProjectEndDate().equals(this.getProjectEndDate())))
    {
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
    IndustrialProject copyProject = new IndustrialProject();

    //Copy attribute values: First project specific values.
    copyProject.setFacilityType(this.getFacilityType());
    copyProject.setFacilitySize(this.getFacilitySize());

    //Copy non-unique project attributes:
    copyProject.setProjectDuration(this.getProjectDuration());
    copyProject.setDashboardProject(this.isDashboardProject());
    copyProject.setProjectConfidentiality(this.isProjectConfidential());
    copyProject.setProjectType("Industrial");
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

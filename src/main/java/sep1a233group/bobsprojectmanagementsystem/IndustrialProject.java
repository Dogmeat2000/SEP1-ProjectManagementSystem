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

    /**
     * Returns the project type as a String (This being an industrial project).
     */
    public String getProjectType()
    {
      return "Industrial";
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
}

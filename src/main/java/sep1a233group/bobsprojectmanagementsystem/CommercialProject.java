package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class CommercialProject extends ConstructionProject implements Serializable
{
  private String intendedBuildingUse; //Used to describe what the building will be used for.
  private int numberOfFloors; //The number of floors this project is planned to have.
  private double buildingSize; //Size of building project in m^2.
  private int projectDuration; // How many months the build will take.

  /** Constructs this object the first time it is created/called. */
  public CommercialProject()
  {
    super();
    //Note: Default values are called and set in the MainModel when project is created, where user changes to these default values are also saved.
    /*If default values successfully loaded, then set them. Else set some hard-coded values!*/
    setBuildingSize(0);
    setIntendedBuildingUse("");
    setNumberOfFloors(0);
    setProjectDuration(5);
  }

  /** Returns a string value containing a description of the planned use of this building */
  public String getIntendedBuildingUse()
  {
    return intendedBuildingUse;
  }

  /** Sets a string value containing a description of the planned use of this building */
  public void setIntendedBuildingUse(String intendedBuildingUse)
  {
    this.intendedBuildingUse = intendedBuildingUse;
  }

  /** Returns the planned number of floors for this project as an integer value. */
  public int getNumberOfFloors()
  {
    return numberOfFloors;
  }

  /** Sets the planned number of floors for this project as an integer value. */
  public void setNumberOfFloors(int numberOfFloors)
  {
    this.numberOfFloors = numberOfFloors;
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

  /** Returns the project type as a String (This being a commercial project).
   */
  public String getProjectType()
  {
    return "Commercial";
  }


  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object project)
  {
    if (!(project instanceof CommercialProject))
    {
      return false;
    }
    CommercialProject other = (CommercialProject) project;

    //Compare local attributes for equality:
    if (!(other.getNumberOfFloors() == this.getNumberOfFloors() && other.getIntendedBuildingUse().equals(this.getIntendedBuildingUse())
        && other.getBuildingSize() == this.getBuildingSize() && other.getProjectDuration() == this.getProjectDuration() && other.getProjectType().equals(this.getProjectType())))
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

  /** Returns a string value containing all project information. Useful for debugging.
   * Author: K. Dashnaw
   * */
  public String toString()
  {
    String returnValue = this.getProjectType() + "\n";
    returnValue += " BuildingSize=" + this.getBuildingSize() + "\n";
    returnValue += " IntendedUse=" + this.getIntendedBuildingUse() + "\n";
    returnValue += " NumberOfFloors=" + this.getNumberOfFloors() + "\n";
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
}
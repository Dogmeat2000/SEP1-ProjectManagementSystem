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

    /**
   * Returns the project type as a String (This being a residential project).
   */
  public String getProjectType()
  {
    return "Residential";
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

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

    //TODO: IMPLEMENT THE DEFAULT VALUES HERE!
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
    //TODO: Implement a calculation based on the set start and finish dates, and use that as the basis for getting the months between!
    this.projectDuration = projectDuration;
  }

  /**
   * Returns the project type as a String (This being a commercial project).
   */
  public String getProjectType()
  {
    return "Commercial";
  }

  /**
   * Loads the default construction project settings, set by the user in the GUI,
   * and sets these as the starting values in this class' constructor!
   */
  public DefaultCommercialSettings loadDefaultSettings()
  {
    //TODO: Missing implementation. Waiting for the Default Settings Class to be created!

    return new DefaultCommercialSettings(0,0);
  }

  public boolean equals(ConstructionProject project)
  {
    //TODO: Missing implementation
    return false;
  }

}
package sep1a233group.bobsprojectmanagementsystem;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class ResidentialProject extends ConstructionProject
{
  private boolean isRenovation; //False = Project is a new construction. True = Project is a renovation.
  private int numberOfKitchens, numberOfBathrooms, numberOfOtherRoomsWithPlumbing; //Project data

  private double buildingSize; //Size of building project in m^2.

  private int projectDuration; // How many months the build will take.

  /** Constructs this object the first time it is created/called. */
  public ResidentialProject()
  {
    super();

    //TODO: IMPLEMENT THE DEFAULT VALUES HERE!
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
    //TODO: Implement a calculation based on the set start and finish dates, and use that as the basis for getting the months between!
    this.projectDuration = projectDuration;
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
   * Returns true if the project is a renovation of an already existing construction.
   * Returns false if it is an entirely new project.
   */
  public boolean getIsRenovation()
  {
    return isRenovation;
  }

  /**
   * Returns the project type as a String (This being a residential project).
   */
  public String getProjectType()
  {
    return "Residential";
  }

  /**
   * Loads the default construction project settings, set by the user in the GUI,
   * and sets these as the starting values in this class' constructor!
   */
  public DefaultResidentialSettings loadDefaultSettings()
  {
    //TODO: Missing implementation. Waiting for the Default Settings Class to be created!

    return new DefaultResidentialSettings(0,0,0,0,false);
  }

  public boolean equals(ConstructionProject project)
  {
    //TODO: Missing implementation
    return false;
  }

}

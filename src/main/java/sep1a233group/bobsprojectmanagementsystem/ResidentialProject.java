package sep1a233group.bobsprojectmanagementsystem;

public class ResidentialProject extends ConstructionProject
{
  private boolean isRenovation; //False = Project is a new construction. True = Project is a renovation.
  private int numberOfKitchens, numberOfBathrooms, numberOfOtherRoomsWithPlumbing; //Project data

  private double buildingSize; //Size of building project in m^2.

  private int projectDuration; // How many months the build will take.

  public ResidentialProject()
  {
    //TODO: IMPLEMENT THE DEFAULT VALUES HERE!
    /*If default values successfully loaded, then set them. Else set some hard-coded values!*/

    setIsRenovation(false);
    setBuildingSize(0);
    setNumberOfKitchens(0);
    setNumberOfBathrooms(0);
    setNumberOfOtherRoomsWithPlumbing(0);
    setProjectDuration(0);
  }

  public int getNumberOfKitchens()
  {
    return numberOfKitchens;
  }

  public void setNumberOfKitchens(int numberOfKitchens)
  {
    this.numberOfKitchens = numberOfKitchens;
  }

  public int getNumberOfBathrooms()
  {
    return numberOfBathrooms;
  }

  public void setNumberOfBathrooms(int numberOfBathrooms)
  {
    this.numberOfBathrooms = numberOfBathrooms;
  }

  public int getNumberOfOtherRoomsWithPlumbing()
  {
    return numberOfOtherRoomsWithPlumbing;
  }

  public void setNumberOfOtherRoomsWithPlumbing(
      int numberOfOtherRoomsWithPlumbing)
  {
    this.numberOfOtherRoomsWithPlumbing = numberOfOtherRoomsWithPlumbing;
  }

  public double getBuildingSize()
  {
    return buildingSize;
  }

  public void setBuildingSize(double buildingSize)
  {
    this.buildingSize = buildingSize;
  }

  public int getProjectDuration()
  {
    return projectDuration;
  }

  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }

  public void setIsRenovation(boolean isRenovation)
  {
    this.isRenovation = isRenovation;
  }

  public boolean getIsRenovation()
  {
    return isRenovation;
  }

  public String getProjectType()
  {
    return "Residential";
  }

  public DefaultResidentialSettings loadDefaultSettings()
  {
    //TODO: Missing implementation. Waiting for the Default Settings Class to be created!

    return new DefaultResidentialSettings();
  }

}

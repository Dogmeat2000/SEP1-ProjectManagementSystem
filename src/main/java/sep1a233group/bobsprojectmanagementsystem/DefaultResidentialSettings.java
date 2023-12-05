package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

//Note: Default values for this class are initialized during MainModel load(). If previous user settings exist in saved files, these are set - else load() defines the hardcoded default values.
public class DefaultResidentialSettings implements Serializable
{

  private int projectDuration, numberOfKitchens, numberOfBathrooms,numberOfOtherRoomsWithPlumbing;

  private boolean isRenovation;

  public DefaultResidentialSettings(int projectDuration, int numberOfKitchens,
      int numberOfBathrooms, int numberOfOtherRoomsWithPlumbing,
      boolean isRenovation)
  {
    setProjectDuration(projectDuration);
    this.numberOfKitchens = numberOfKitchens;
    this.numberOfBathrooms = numberOfBathrooms;
    this.numberOfOtherRoomsWithPlumbing = numberOfOtherRoomsWithPlumbing;
    this.isRenovation = isRenovation;
  }

  public void set(int projectDuration, int numberOfKitchens,
      int numberOfBathrooms, int numberOfOtherRoomsWithPlumbing,
      boolean isRenovation)
  {
    this.projectDuration = projectDuration;
    this.numberOfKitchens = numberOfKitchens;
    this.numberOfBathrooms = numberOfBathrooms;
    this.numberOfOtherRoomsWithPlumbing = numberOfOtherRoomsWithPlumbing;
    this.isRenovation = isRenovation;
  }

  public int getProjectDuration()
  {
    return projectDuration;
  }

  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }

  public int getNumberOfKitchens()
  {
    return numberOfKitchens;
  }

  public int getNumberOfBathrooms()
  {
    return numberOfBathrooms;
  }

  public int getNumberOfOtherRoomsWithPlumbing()
  {
    return numberOfOtherRoomsWithPlumbing;
  }

  public boolean isRenovation()
  {
    return isRenovation;
  }


}

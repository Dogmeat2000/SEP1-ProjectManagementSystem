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


  public int getProjectDuration()
  {
    return projectDuration;
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

  public void setNumberOfKitchens(int numberOfKitchens)
  {
    this.numberOfKitchens = numberOfKitchens;
  }

  public void setNumberOfBathrooms(int numberOfBathrooms)
  {
    this.numberOfBathrooms = numberOfBathrooms;
  }

  public void setNumberOfOtherRoomsWithPlumbing(
      int numberOfOtherRoomsWithPlumbing)
  {
    this.numberOfOtherRoomsWithPlumbing = numberOfOtherRoomsWithPlumbing;
  }

  public void setRenovation(boolean renovation)
  {
    isRenovation = renovation;
  }

  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }

}

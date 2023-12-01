package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

public class DefaultCommercialSettings implements Serializable
{

  private int numberOfFloors, projectDuration;


  public DefaultCommercialSettings(int numberOfFloors, int projectDuration)
  {
    this.numberOfFloors = numberOfFloors;
    this.projectDuration = projectDuration;
  }

  public int getNumberOfFloors()
  {
    return numberOfFloors;
  }

  public int getProjectDuration()
  {
    return projectDuration;
  }

  public void set(int numberOfFloors, int projectDuration)
  {
    this.numberOfFloors = numberOfFloors;
    this.projectDuration = projectDuration;
  }






}

package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

public class DefaultIndustrialSettings implements Serializable
{

  private int projectDuration;

  public DefaultIndustrialSettings(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }

  public int getProjectDuration()
  {
    return projectDuration;
  }

  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }






}

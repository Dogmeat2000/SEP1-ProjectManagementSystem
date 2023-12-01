package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

//Note: Default values for this class are initialized during MainModel load(). If previous user settings exist in saved files, these are set - else load() defines the hardcoded default values.
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

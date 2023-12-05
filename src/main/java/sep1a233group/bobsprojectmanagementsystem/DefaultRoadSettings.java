package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

//Note: Default values for this class are initialized during MainModel load(). If previous user settings exist in saved files, these are set - else load() defines the hardcoded default values.
public class DefaultRoadSettings implements Serializable
{


  private String bridgesOrTunnelDetails, enviromentalOrGeographicalChallenges;

  private int projectDuration;

  public DefaultRoadSettings(String bridgesOrTunnelDetails,
      String enviromentalOrGeographicalChallenges, int projectDuration)
  {
    this.bridgesOrTunnelDetails = bridgesOrTunnelDetails;
    this.enviromentalOrGeographicalChallenges = enviromentalOrGeographicalChallenges;
    this.projectDuration = projectDuration;
  }


  public String getBridgesOrTunnelDetails()
  {
    return bridgesOrTunnelDetails;
  }

  public String getEnviromentalOrGeographicalChallenges()
  {
    return enviromentalOrGeographicalChallenges;
  }

  public int getProjectDuration()
  {
    return projectDuration;
  }

  public void setBridgesOrTunnelDetails(String bridgesOrTunnelDetails)
  {
    this.bridgesOrTunnelDetails = bridgesOrTunnelDetails;
  }

  public void setEnviromentalOrGeographicalChallenges(
      String enviromentalOrGeographicalChallenges)
  {
    this.enviromentalOrGeographicalChallenges = enviromentalOrGeographicalChallenges;
  }

  public void setProjectDuration(int projectDuration)
  {
    this.projectDuration = projectDuration;
  }


}

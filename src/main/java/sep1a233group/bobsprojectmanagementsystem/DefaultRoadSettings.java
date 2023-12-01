package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!
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

  public void set(String bridgesOrTunnelDetails,
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



}

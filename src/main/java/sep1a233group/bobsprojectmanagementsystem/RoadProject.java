package sep1a233group.bobsprojectmanagementsystem;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class RoadProject extends ConstructionProject
{
  private String bridgeOrTunnelDetails; //Used to describe any details relating to possible bridges or tunnels on the construction route.
  private String environmentalOrGeographicalChallenges; //Used to describe any relevant details relating to environmental or geographical challenges on the route.
  private double roadLength; //Describes how long a piece of road the project will encompass.
  private double roadWidth; //Describes the width of the road.
  private int projectDuration; // How many months the build will take.

  /** Constructs this object the first time it is created/called. */
  public RoadProject()
  {
    //TODO: IMPLEMENT THE DEFAULT VALUES HERE!
    /*If default values successfully loaded, then set them. Else set some hard-coded values!*/

    setRoadLength(0);
    setRoadWidth(0);
    setBridgeOrTunnelDetails("N/A");
    setEnvironmentalOrGeographicalChallenges("N/A");
    setProjectDuration(0);
  }

  /** Returns a string value containing any details relating to possible bridges or tunnels on the construction route */
  public String getBridgeOrTunnelDetails()
  {
    return bridgeOrTunnelDetails;
  }

  /** Sets a string value containing any details relating to possible bridges or tunnels on the construction route */
  public void setBridgeOrTunnelDetails(String bridgeOrTunnelDetails)
  {
    this.bridgeOrTunnelDetails = bridgeOrTunnelDetails;
  }

  /** Returns a string value containing any details relating to environmental or geographical challenges on the route */
  public String getEnvironmentalOrGeographicalChallenges()
  {
    return environmentalOrGeographicalChallenges;
  }

  /** Sets a string value containing any details relating to environmental or geographical challenges on the route */
  public void setEnvironmentalOrGeographicalChallenges(String environmentalOrGeographicalChallenges)
  {
    this.environmentalOrGeographicalChallenges = environmentalOrGeographicalChallenges;
  }

  /** Returns the planned length of this road project */
  public double getRoadLength()
  {
    return roadLength;
  }

  /** Sets the planned length of this road project */
  public void setRoadLength(double roadLength)
  {
    this.roadLength = roadLength;
  }

  /** Returns the planned width of this road project */
  public double getRoadWidth()
  {
    return roadWidth;
  }

  /** Sets the planned width of this road project */
  public void setRoadWidth(double roadWidth)
  {
    this.roadWidth = roadWidth;
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
   * Returns the project type as a String (This being an industrial project).
   */
  public String getProjectType()
  {
    return "Road";
  }

  /**
   * Loads the default construction project settings, set by the user in the GUI,
   * and sets these as the starting values in this class' constructor!
   */
  public DefaultRoadSettings loadDefaultSettings()
  {
    //TODO: Missing implementation. Waiting for the Default Settings Class to be created!

    return new DefaultRoadSettings();
  }
}

package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class RoadProject extends ConstructionProject implements Serializable
{
  private String bridgeOrTunnelDetails; //Used to describe any details relating to possible bridges or tunnels on the construction route.
  private String environmentalOrGeographicalChallenges; //Used to describe any relevant details relating to environmental or geographical challenges on the route.
  private double roadLength; //Describes how long a piece of road the project will encompass.
  private double roadWidth; //Describes the width of the road.
  private int projectDuration; // How many months the build will take.

  /** Constructs this object the first time it is created/called. */
  public RoadProject()
  {
    super();

    //Note: Default values are called and set in the MainModel when project is created, where user changes to these default values are also saved.
    /*If default values successfully loaded, then set them. Else set some hard-coded values!*/
    setRoadLength(0);
    setRoadWidth(0);
    setBridgeOrTunnelDetails("");
    setEnvironmentalOrGeographicalChallenges("");
    setProjectDuration(5);
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
    this.projectDuration = projectDuration;
  }

  /** Returns the project type as a String (This being an industrial project).
   * Author: K. Dashnaw
   */
  public String getProjectType()
  {
    return "Road";
  }

  /** Returns a string value containing all project information. Useful for debugging.
   * Author: K. Dashnaw
   * */
  public String toString()
  {
    String returnValue = this.getProjectType();
    returnValue += " RoadLength=" + this.getRoadLength() + "\n";
    returnValue += " RoadWidth=" + this.getRoadWidth() + "\n";
    returnValue += " BridgeOrTunnelsInfo=" + this.getBridgeOrTunnelDetails() + "\n";
    returnValue += " EnvironmentalOrGeoInfo=" + this.getEnvironmentalOrGeographicalChallenges() + "\n";
    returnValue += " displayOnDashboard?=" + this.isDashboardProject() + "\n";
    returnValue += " ProjectStartDate=" + this.getProjectStartDate().toString() + "\n";
    returnValue += " ProjectEndDate=" + this.getProjectEndDate().toString() + "\n";
    returnValue += " ProjectDuration=" + this.getProjectDuration() + "\n";
    returnValue += " Finances=" + this.getFinances() + "\n";
    returnValue += " HumanResources=" + this.getHumanRessources() + "\n";
    returnValue += " IsProjectFinished=" + this.isProjectFinished() + "\n";
    returnValue += " isProjectConfidential=" + this.isProjectConfidential() + "\n";
    returnValue += " Customer=" + this.getCustomer() + "\n";
    returnValue += " Customer=" + this.getProjectAddress() + "\n";
    returnValue += " Customer=" + this.getProjectInformation() + "\n";

    return returnValue;
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object project)
  {
    if (!(project instanceof RoadProject))
    {
      return false;
    }
    RoadProject other = (RoadProject) project;

    //Compare local attributes for equality:
    if (!(other.getRoadWidth() == this.getRoadLength() && other.getProjectDuration() == this.getProjectDuration()
        && other.getRoadLength() == this.getRoadLength() && other.getEnvironmentalOrGeographicalChallenges()
        .equals(this.getEnvironmentalOrGeographicalChallenges()) && other.getBridgeOrTunnelDetails()
        .equals(this.getBridgeOrTunnelDetails())))
    {
      return false;
    }
    //Compare Customer and Address info:
    else if (!(other.getCustomer().getCustomerCompany().equals(this.getCustomer().getCustomerCompany()) && other.getProjectAddress()
        .equals(this.getProjectAddress())))
    {
      return false;
    }
    //Compare Finances and Human resources info:
    else if(!(other.getFinances().equals(this.getFinances()) && other.getHumanRessources().equals(this.getHumanRessources()) && other.isProjectConfidential() == this.isProjectConfidential() && other.isProjectFinished() == this.isProjectFinished()))
    {
      return false;
    }
    //Compare remaining info
    else if (!(other.getProjectInformation().equals(this.getProjectInformation()) && other.getProjectStartDate()
        .equals(this.getProjectStartDate()) && other.getProjectEndDate().equals(this.getProjectEndDate())))
    {
      return false;
    }
    else
    {
      //Objects are identical!
      return true;
    }
  }
}

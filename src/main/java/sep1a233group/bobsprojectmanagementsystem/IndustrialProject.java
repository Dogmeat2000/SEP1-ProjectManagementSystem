package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;

/**
 * This class defines one of the 4 construction project types.
 * Author: K. Dashnaw (All included methods in class, unless otherwise stated)
 */
public class IndustrialProject extends ConstructionProject implements Serializable
{
    private String facilityType; //Used to describe what type of facility is planned.
    private double facilitySize; //Size of building project in m^2.
    private int projectDuration; // How many months the build will take.

    /** Constructs this object the first time it is created/called. */
    public IndustrialProject()
    {
      super();

      //TODO: IMPLEMENT THE DEFAULT VALUES HERE!
      /*If default values successfully loaded, then set them. Else set some hard-coded values!*/
      setFacilitySize(0);
      setFacilityType("");
      setProjectDuration(5);
    }

    /** Returns a string value containing a description of the planned type of facility */
    public String getFacilityType()
    {
      return facilityType;
    }

    /** Sets a string value containing a description of the planned type of facility */
    public void setFacilityType(String facilityType)
    {
      this.facilityType = facilityType;
    }

    /** Returns the planned facility size in m^2 (Square meters) */
    public double getFacilitySize()
    {
      return facilitySize;
    }

    /** Sets the planned facility size in m^2 (Square meters) */
    public void setFacilitySize(double facilitySize)
    {
      this.facilitySize = facilitySize;
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
      return "Industrial";
    }

    /**
     * Loads the default construction project settings, set by the user in the GUI,
     * and sets these as the starting values in this class' constructor!
     */
    public DefaultIndustrialSettings loadDefaultSettings()
    {
      //TODO: Missing implementation. Waiting for the Default Settings Class to be created!

      return new DefaultIndustrialSettings(0);
    }

  public boolean equals(ConstructionProject project)
  {
    //TODO: Missing implementation
    return false;
  }
}

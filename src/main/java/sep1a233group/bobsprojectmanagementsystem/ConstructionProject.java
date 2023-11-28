package sep1a233group.bobsprojectmanagementsystem;

public abstract class ConstructionProject
{
  private Date projectStartDate; //When was the project created/initiated?
  private Date projectEndDate; //When was the project finished (or expected to finish?)

  //TODO: IMPLEMENT CLASS

  public ConstructionProject()
  {
    //TODO: Implement... ALSO CONVERT THESE TEMPORARY DATA SETS TO SETTERS AND GETTERS!
    this.projectStartDate = new Date(/* TODO: MISSING PARAMETERS */);
    this.projectEndDate = new Date(/* TODO: MISSING PARAMETERS */);
  }

  /**
   * This method returns the specific type of construction project this is.
   * They can either be: Residential, Commercial, Industrial or Road projects
   * Author: K. Dashnaw
   */
  public abstract String getProjectType();

}

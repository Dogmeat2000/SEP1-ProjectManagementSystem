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

  /** This method is compares the given ConstructionProject object with the current ConstructionProject object and returns true if they are identical.
   * It is for instance used to validate attempts of adding projects to the system in order to avoid adding duplicates.
   * Author:
   * */
  public boolean equals(ConstructionProject project)
  {
    //TODO: Implement method.
    return false; //Dummy information for now!
  }

    /**
   * This abstract method, once implemented, returns the specific type of construction project this is.
   * They can either be: Residential, Commercial, Industrial or Road projects
   * Author: K. Dashnaw
   */
  public abstract String getProjectType();
}

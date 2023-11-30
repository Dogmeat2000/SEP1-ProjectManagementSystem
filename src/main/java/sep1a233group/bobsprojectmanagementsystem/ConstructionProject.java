package sep1a233group.bobsprojectmanagementsystem;

public abstract class ConstructionProject
{
  private MyDate projectStartDate; //When was the project created/initiated?
  private MyDate projectEndDate; //When was the project finished (or expected to finish?)

  //TODO: IMPLEMENT CLASS

  public ConstructionProject()
  {
    //TODO: Implement... ALSO CONVERT THESE TEMPORARY DATA SETS TO SETTERS AND GETTERS!
    this.projectStartDate = new MyDate(1,1,2000);
    this.projectEndDate = new MyDate(1,1,2000);
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

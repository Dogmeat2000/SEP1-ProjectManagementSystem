package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!

public class DashboardProgressReports implements Serializable
{
  private ProgressReport[] dashboardProgressReports; // Contains the 4 progress reports that are shown on the GUI dashboard.
  //TODO: Implement Class!

  public DashboardProgressReports()
  {
    this.dashboardProgressReports = new ProgressReport[4]; //Max 4! DUMMY DATA FOR NOW! SHOULD BE DELETED AND WRITTEN BETTER!
    //TODO: Implement
  }

  public boolean addProgressReport(ProgressReport progressReport)
  {
    //TODO: Implement
    return true; //Successfully added the progressReport
  }

  public boolean removeProgressReport(ProgressReport progressReport)
  {
    //TODO: Implement
    return true; //Successfully removed the progressReport
  }



}

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
    for (int i = 0; i < dashboardProgressReports.length; i++)
    {
      if (!dashboardProgressReports[i].equals(null))
      {
        dashboardProgressReports[i] = progressReport;
        return true; //Successfully added the progressReport
      }
    }
    //TODO: Implement
    return false; //All dashboard places are full
  }

  public boolean removeProgressReport(ProgressReport progressReport)
  {
    for (ProgressReport findReport:dashboardProgressReports
         )
    {
      if (progressReport.equals(findReport))
      {
        dashboardProgressReports[0] = null;
        return true; //Successfully removed the progressReport
      }
    }
    //TODO: Implement
    return false; // the progressReport was not in the dashboard
  }



}

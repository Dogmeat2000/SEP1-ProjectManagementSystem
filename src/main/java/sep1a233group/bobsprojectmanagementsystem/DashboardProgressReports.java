package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable; //Needed in order to save class object as Binary file!
import java.util.Arrays;

public class DashboardProgressReports implements Serializable
{
  private ProgressReport[] dashboardProgressReports; // Contains the 4 progress reports that are shown on the GUI dashboard.
  private int maxCapacity; //Used to control the maximum number of progress reports to store here.

  public DashboardProgressReports()
  {
    setMaxCapacity(4);
    setProgressReports(new ProgressReport[getMaxCapacity()]);//Max 4! DUMMY DATA FOR NOW! SHOULD BE DELETED AND WRITTEN BETTER!
  }

  public int getMaxCapacity()
  {
    return maxCapacity;
  }

  public void setMaxCapacity(int maxCapacity)
  {
    this.maxCapacity = maxCapacity;
  }

  public ProgressReport[] getProgressReports()
  {
    return dashboardProgressReports;
  }

  public void setProgressReports(ProgressReport[] dashboardProgressReports)
  {
    this.dashboardProgressReports = dashboardProgressReports;
  }

  public boolean addProgressReport(ProgressReport progressReport)
  {
    for (int i = 0; i < getProgressReports().length; i++)
    {
      if (dashboardProgressReports[i] == null)
      {
        dashboardProgressReports[i] = progressReport;
        return true; //Successfully added the progressReport
      }
    }
    return false; //All dashboard places are full
  }

  /** Returns an integer value with the number of free Dashboard slots.*/
  public int getCurrentCapacity()
  {
    int currentCapacity = 0;

    for (int i = 0; i < this.getMaxCapacity(); i++)
    {
      if(this.getProgressReports()[i] == null)
      {
        currentCapacity++;
      }
    }
    return currentCapacity;
  }

  public boolean removeProgressReport(ProgressReport progressReport)
  {
    for (ProgressReport findReport:dashboardProgressReports)
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

  @Override public String toString()
  {
    return "DashboardProgressReports{" + "dashboardProgressReports=" + Arrays.toString(dashboardProgressReports) + '}';
  }
}

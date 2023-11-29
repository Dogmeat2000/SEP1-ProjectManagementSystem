package sep1a233group.bobsprojectmanagementsystem;

import java.util.ArrayList;

/** This is the main controller for the project management system.
 * This controls most of the coding logic and method functionality that is called from the GUI
 * Author: Combined effort from all team members.
 * */
public class MainModel
{
  private DashboardProgressReports dashboardProgressReports; //An object containing all the project progress reports that are displayed on the GUI Dashboard.
  private ArrayList<ConstructionProject> allProjectsList; //Contains an ArrayList with ALL the construction projects in the system.
  private FileIO fileManager; //Is the main file managing class, which handles file in and out operations.
  private DefaultResidentialSettings defaultResidentialSettings; //Handles the default residential project settings used when creating new projects!
  private DefaultCommercialSettings defaultCommercialSettings; //Handles the default commercial project settings used when creating new projects!
  private DefaultIndustrialSettings defaultIndustrialSettings; //Handles the default industrial project settings used when creating new projects!
  private DefaultRoadSettings defaultRoadSettings; //Handles the default road project settings used when creating new projects!

  /** Constructs the MainModel.
 * Author: K. Dashnaw
 * */
  public MainModel()
  {
    //Initialize the file manager.
    setFileManager();






    //TODO: Implement som coding logic for the initialization of the dashboardProgressReports at this stage, that loads this information from a Binary File - if such a file exists
    //The below is simply dummy information for now!
    setDashboardProgressReport(new DashboardProgressReports());

    //TODO: Implement som coding logic for the initialization of the allProjectsList at this stage, that loads this information from a Binary File - if such a file exists
    //The below is simply dummy information for now!
    setAllProjectsList(new ArrayList<>());

    //TODO: Implement som coding logic for all the below, attempting to first load data from a file if such a file exists and data is proper!
    //The below is simply dummy information for now!

    setDefaultResidentialSettings(new DefaultResidentialSettings());
    setDefaultCommercialSettings(new DefaultCommercialSettings());
    setDefaultIndustrialSettings(new DefaultIndustrialSettings());
    setDefaultRoadSettings(new DefaultRoadSettings());
  }

  /** Returns an array with the marked Dashboard projects that the user wants shown on the GUI Dashboard.
   * Author: K. Dashnaw
   * */
  public DashboardProgressReports getDashboardProgressReports()
  {
    return dashboardProgressReports;
  }

/** Sets/Initializes the DashboardProgressReports array
 * Author: K. Dashnaw
 * */
  public void setDashboardProgressReport(DashboardProgressReports progressReports)
  {
    this.dashboardProgressReports = progressReports;
  }

  /** Returns an ArrayList with ALL the projects in the project management system
   * Author: K. Dashnaw
   * */
  public ArrayList<ConstructionProject> getAllProjectsList()
  {
    return allProjectsList;
  }

  /** Sets/Initializes an ArrayList containing all the projects in the project management system
   * Author: K. Dashnaw
   * */
  public void setAllProjectsList(ArrayList<ConstructionProject> allProjectsList)
  {
    this.allProjectsList = allProjectsList;
  }

  /** Gets the file manager responsible for maintaining data persistence across sessions
   * Author: K. Dashnaw
   * */
  public FileIO getFileManager()
  {
    return fileManager;
  }

  /** Sets/Initializes the file manager responsible for maintaining data persistence across sessions
   * Author: K. Dashnaw
   * */
  public void setFileManager()
  {
    this.fileManager = new FileIO();
  }

  /** Gets the default residential project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public DefaultResidentialSettings getDefaultResidentialSettings()
  {
    return defaultResidentialSettings;
  }

  /** Sets/Initializes the default residential project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public void setDefaultResidentialSettings(
      DefaultResidentialSettings defaultResidentialSettings)
  {
    this.defaultResidentialSettings = defaultResidentialSettings;
  }

  /** Gets the default commercial project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public DefaultCommercialSettings getDefaultCommercialSettings()
  {
    return defaultCommercialSettings;
  }

  /** Sets/Initializes the default commercial project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public void setDefaultCommercialSettings(
      DefaultCommercialSettings defaultCommercialSettings)
  {
    this.defaultCommercialSettings = defaultCommercialSettings;
  }

  /** Gets the default industrial project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public DefaultIndustrialSettings getDefaultIndustrialSettings()
  {
    return defaultIndustrialSettings;
  }

  /** Sets/Initializes the default industrial project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public void setDefaultIndustrialSettings(
      DefaultIndustrialSettings defaultIndustrialSettings)
  {
    this.defaultIndustrialSettings = defaultIndustrialSettings;
  }

  /** Gets the default road project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public DefaultRoadSettings getDefaultRoadSettings()
  {
    return defaultRoadSettings;
  }

  /** Sets/Initializes the default road project settings used when creating new projects
   * Author: K. Dashnaw
   * */
  public void setDefaultRoadSettings(DefaultRoadSettings defaultRoadSettings)
  {
    this.defaultRoadSettings = defaultRoadSettings;
  }

/** Adds a single construction project to the project management system.
 * Returns true if operation was successful.
 * Author: K. Dashnaw
 * */
  public boolean addProject(ConstructionProject project)
  {
    //TODO: Implement method.
    //TODO: Perform some validation before adding using the equals method from ConstructionProject Class
    return false;
  }

  /** Edits a single construction project in the project management system.
   * Returns true if operation was successful.
   * Author:
   * */
  public boolean editProject(ConstructionProject project)
  {
    //TODO: Implement method.
    return false;
  }

  /** Removes a single construction project from the project management system.
   * Returns true if operation was successful.
   * Author:
   * */
  public boolean removeProject(ConstructionProject project)
  {
    //TODO: Implement method.
    return false;
  }

  /** Sets the current filtering options that are applied when projects are displayed in the GUI.
   * I.e.: For instance a filtering option could be to only show projects with a budget between 100,000 and 500,000.
   * Returns true if operation was successful.
   * Author:
   * */
  public boolean filterProject()
  {
    //TODO: Implement method.
    return false;
  }

  /** Sets the current default project settings that are applied when new projects are created.
   * Returns true if operation was successful.
   * Author:
   * */
  public boolean editDefaultProjectSettings()
  {
    //TODO: Implement method.
    return false;
  }

  /** Enables data persistence across sessions by saving relevant system information to a file.
   *  Note: Validation of data integrity should be conducted prior to calling this save method,
   *  ideally as early as while adding data to the model field attributes.
   *  Returns true if operation was successful.
   *  Author: K. Dashnaw
   * */
  public boolean save()
  {
    Object[] objectList = new Object[8]; //Pack all the different system Object into a single Object array before saving.

    objectList[0] = this.getAllProjectsList();
    objectList[1] = this.getDashboardProgressReports();
    objectList[2] = this.getDefaultResidentialSettings();
    objectList[3] = this.getDefaultCommercialSettings();
    objectList[4] = this.getDefaultIndustrialSettings();
    objectList[5] = this.getDefaultRoadSettings();
    objectList[7] = this.getFileManager().getLastDataSaveTime();

    if(this.getFileManager().writeToBinary(objectList))
    {
      System.out.println("Data saved successfully");
      return true; //Saving was successful
    }
    else
    {
      return false; //Saving failed.
    }
  }

  /** Enables data persistence across sessions by loading relevant system information from a file.
   * Returns true if operation was successful.
   * Author: K. Dashnaw
   * */
  public boolean load()
  {
    //TODO: Implement method.
    return false;
  }

}

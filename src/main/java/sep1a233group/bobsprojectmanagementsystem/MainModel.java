package sep1a233group.bobsprojectmanagementsystem;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** <p>This is the main controller for the project management system. This controls most of the coding logic and method functionality that is called from the GUI<br><br></p>
 * <p><b>Author:</b> Combined effort from all team members.</p>
 * */
public class MainModel
{
  private DashboardProgressReports dashboardProgressReports; //An object containing all the project progress reports that are displayed on the GUI Dashboard.
  private ArrayList<ConstructionProject> allProjectsList; //Contains an ArrayList with ALL the construction projects in the system.
  private ArrayList<ConstructionProject> filteredProjectsList; //Contains a filtered list of projects in accordance to set filters.
  private FileIO fileManager; //Is the main file managing class, which handles file in and out operations.
  private DefaultResidentialSettings defaultResidentialSettings; //Handles the default residential project settings used when creating new projects!
  private DefaultCommercialSettings defaultCommercialSettings; //Handles the default commercial project settings used when creating new projects!
  private DefaultIndustrialSettings defaultIndustrialSettings; //Handles the default industrial project settings used when creating new projects!
  private DefaultRoadSettings defaultRoadSettings; //Handles the default road project settings used when creating new projects!

  private ConstructionProject selectedProject; //Container used to contain currently selected/active project information.

  private String initializationErrorMessage; //Created so the sceneController on initialization can know if there were any errors while loading the system files.
  private int projectIndexPosition; //Holds a reference to the index of the original project, while in the process of modifying existing project copies (before the original is replaced upon user save).

  /** <p>Constructs the MainModel.
   * Also loads any available project details from system file.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public MainModel()
  {
    //Initialize the file manager.
    setFileManager();

    //Load any system files that might be present:
    if(load())
    {
      //loading was successful
      System.out.println("Debug: Data successfully loaded and validated.");
      setInitializationErrorMessage(""); //Must be initialized!
    }
    else
    {
      //loading failed. The load method re-initialized a clean system file. Any prior data is lost.
      System.out.println("Debug: Data was not loaded successfully. New data has been initialized instead.");
      setInitializationErrorMessage("Data was not loaded successfully. New data has been initialized instead.");
    }
    setFilteredProjectsList(new ArrayList<>());
    filterProject();
    refreshDashboardProjects();
  }

  /** <p>Returns an array with the marked Dashboard projects that the user wants shown on the GUI Dashboard.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public DashboardProgressReports getDashboardProgressReports()
  {
    return dashboardProgressReports;
  }

  /** <p>Sets/Initializes the DashboardProgressReports array</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setDashboardProgressReport(DashboardProgressReports progressReports)
  {
    this.dashboardProgressReports = progressReports;
  }

  /** <p>Returns any potentially set error messages. This is especially used during model initialization by the SceneController,
   * since the SceneController does not know what errors this models internal operations cast.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public String getInitializationErrorMessage()
  {
    return initializationErrorMessage;
  }

  /** Sets/Initialises an error messages. This is especially used during model initialization by the SceneController,
   * since the SceneController does not know what errors this models internal operations cast.
   * Author: K. Dashnaw
   * */
  public void setInitializationErrorMessage(String initializationErrorMessage)
  {
    this.initializationErrorMessage = initializationErrorMessage;
  }

  /** Returns an ArrayList with ALL the projects in the project management system
   * Author: K. Dashnaw
   */
  public ArrayList<ConstructionProject> getAllProjectsList()
  {
    return allProjectsList;
  }

  /** Sets/Initializes an ArrayList containing all the projects in the project management system
   * Author: K. Dashnaw
   */
  public void setAllProjectsList(ArrayList<ConstructionProject> allProjectsList)
  {
    this.allProjectsList = allProjectsList;
  }

  public ArrayList<ConstructionProject> getFilteredProjectsList()
  {
    return filteredProjectsList;
  }

  public void setFilteredProjectsList(ArrayList<ConstructionProject> filteredProjectsList)
  {
    this.filteredProjectsList = filteredProjectsList;
  }

  /** Returns the currently "active" project. Especially used when editing or creating projects.
   * Author: K. Dashnaw
   */
  public ConstructionProject getSelectedProject()
  {
    return selectedProject;
  }

  /** Sets/Initializes an "active project". Especially used when creating new projects of this type.
   * Author: K. Dashnaw
   */
  public void setSelectedProject(ResidentialProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }

  /** Sets/Initializes an "active project". Especially used when creating new projects of this type.
   * Author: K. Dashnaw
   */
  public void setSelectedProject(CommercialProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }

  /** Sets/Initializes an "active project". Especially used when creating new projects of this type.
   * Author: K. Dashnaw
   */
  public void setSelectedProject(IndustrialProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }

  /** Sets/Initializes an "active project". Especially used when creating new projects of this type.
   * Author: K. Dashnaw
   */
  public void setSelectedProject(RoadProject selectedProject)
  {
    this.selectedProject = selectedProject;
  }

  /** Gets the file manager responsible for maintaining data persistence across sessions
   * Author: K. Dashnaw
   */
  public FileIO getFileManager()
  {
    return fileManager;
  }

  /** Sets/Initializes the file manager responsible for maintaining data persistence across sessions
   * Author: K. Dashnaw
   */
  public void setFileManager()
  {
    this.fileManager = new FileIO();
  }

  /** Gets the default residential project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public DefaultResidentialSettings getDefaultResidentialSettings()
  {
    return defaultResidentialSettings;
  }

  /** Sets/Initializes the default residential project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public void setDefaultResidentialSettings(
      DefaultResidentialSettings defaultResidentialSettings)
  {
    this.defaultResidentialSettings = defaultResidentialSettings;
  }

  /** Gets the default commercial project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public DefaultCommercialSettings getDefaultCommercialSettings()
  {
    return defaultCommercialSettings;
  }

  /** Sets/Initializes the default commercial project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public void setDefaultCommercialSettings(
      DefaultCommercialSettings defaultCommercialSettings)
  {
    this.defaultCommercialSettings = defaultCommercialSettings;
  }

  /** Gets the default industrial project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public DefaultIndustrialSettings getDefaultIndustrialSettings()
  {
    return defaultIndustrialSettings;
  }

  /** Sets/Initializes the default industrial project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public void setDefaultIndustrialSettings(
      DefaultIndustrialSettings defaultIndustrialSettings)
  {
    this.defaultIndustrialSettings = defaultIndustrialSettings;
  }

  /** Gets the default road project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public DefaultRoadSettings getDefaultRoadSettings()
  {
    return defaultRoadSettings;
  }

  /** Sets/Initializes the default road project settings used when creating new projects
   * Author: K. Dashnaw
   */
  public void setDefaultRoadSettings(DefaultRoadSettings defaultRoadSettings)
  {
    this.defaultRoadSettings = defaultRoadSettings;
  }

  /** <p>Returns a reference to the index position in the master project list from which a copy of the selected project is currently being modified.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public int getProjectIndexPosition()
  {
    return projectIndexPosition;
  }

  /** <p>Sets a reference to the index position in the master project list from which a copy of the selected project is currently being modified.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void setProjectIndexPosition(int projectIndexPosition)
  {
    this.projectIndexPosition = projectIndexPosition;
  }

  /** <p>Adds a single construction project to the project management system.</p>
   * @return true/false depending on if the operation was successful (true) or not (false).
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public boolean addProject(ConstructionProject project)
  {
    setInitializationErrorMessage("");
    //Check if an identical project already exists in the system files:
    for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      if(project.equals(getAllProjectsList().get(i)))
      {
        //Identical project identified. Do NOT add project.
        //Let user know why.
        setInitializationErrorMessage("ERROR: Duplicate project already exists in system. Unable to add this project.");
        return false;
      }
    }
    //Adds the project to the project list!
    if(this.getAllProjectsList().add(project))
    {
      //Project was added. Save system.
      this.save();
      return true;
    }
    else
    {
      //Project was NOT added.
      return false;
    }
  }

  /** Initializes a new default project of the given type. Available types are:
   * 'ResidentialProjectType', 'CommercialProjectType', 'IndustrialProjectType' or 'RoadProjectType'
   * Parameters are:
   * "String projectType": String value matching one of the above cases.
   * If String does not match, no operation is performed here.
   * Author: K. Dashnaw
   */
  public void newActiveProject(String projectType)
  {
    //Create a new project of the selected type.
    switch (projectType)
    {
      case "ResidentialProjectType":
        ResidentialProject newResProject = new ResidentialProject();
        //Set the default values:
        newResProject.setProjectType("Residential");
        newResProject.setProjectDuration(getDefaultResidentialSettings().getProjectDuration());
        newResProject.setNumberOfKitchens(getDefaultResidentialSettings().getNumberOfKitchens());
        newResProject.setNumberOfBathrooms(getDefaultResidentialSettings().getNumberOfBathrooms());
        newResProject.setNumberOfOtherRoomsWithPlumbing(getDefaultResidentialSettings().getNumberOfOtherRoomsWithPlumbing());
        newResProject.setIsRenovation(getDefaultResidentialSettings().isRenovation());
        this.setSelectedProject(newResProject);
        break;
      case "CommercialProjectType":
        CommercialProject newComProject = new CommercialProject();
        //Set the default values:
        newComProject.setProjectType("Commercial");
        newComProject.setNumberOfFloors(getDefaultCommercialSettings().getNumberOfFloors());
        newComProject.setProjectDuration(getDefaultCommercialSettings().getProjectDuration());
        this.setSelectedProject(newComProject);
        break;
      case "IndustrialProjectType":
        IndustrialProject newIndProject = new IndustrialProject();
        //Set the default values:
        newIndProject.setProjectType("Industrial");
        newIndProject.setProjectDuration(getDefaultIndustrialSettings().getProjectDuration());
        this.setSelectedProject(newIndProject);
        break;
      case "RoadProjectType":
        RoadProject newRoadProject = new RoadProject();
        //Set the default values:
        newRoadProject.setProjectType("Road");
        newRoadProject.setProjectDuration(getDefaultResidentialSettings().getProjectDuration());
        newRoadProject.setProjectDuration(getDefaultRoadSettings().getProjectDuration());
        newRoadProject.setEnvironmentalOrGeographicalChallenges(getDefaultRoadSettings().getEnviromentalOrGeographicalChallenges());
        newRoadProject.setBridgeOrTunnelDetails(getDefaultRoadSettings().getBridgesOrTunnelDetails());
        this.setSelectedProject(newRoadProject);
        break;
    }
  }

  /** Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.
   * Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField bathroomGUIID, TextField kitchenGUIID, TextField otherPlumbingGUIID, TextField durationGUIID, TextField buildingSizeGUIID)
  {
    ResidentialProject currentProject = (ResidentialProject) this.getSelectedProject();

    bathroomGUIID.setText("" + currentProject.getNumberOfBathrooms());
    kitchenGUIID.setText("" + currentProject.getNumberOfKitchens());
    otherPlumbingGUIID.setText("" + currentProject.getNumberOfOtherRoomsWithPlumbing());
    durationGUIID.setText("" + currentProject.getProjectDuration());
    buildingSizeGUIID.setText("" + currentProject.getBuildingSize());
  }

  /** Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.
   * Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField floorsGUIID, TextField durationGUIID, TextField buildingSizeGUIID, TextArea intendedUseGUIID)
  {
    CommercialProject currentProject = (CommercialProject) this.getSelectedProject();

    floorsGUIID.setText("" + currentProject.getNumberOfFloors());
    durationGUIID.setText("" + currentProject.getProjectDuration());
    buildingSizeGUIID.setText("" + currentProject.getBuildingSize());
    intendedUseGUIID.setText(currentProject.getIntendedBuildingUse());
  }

  /** Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.
   * Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField durationGUIID, TextField buildingSizeGUIID, TextArea intendedUseGUIID)
  {
    IndustrialProject currentProject = (IndustrialProject) this.getSelectedProject();

    durationGUIID.setText("" + currentProject.getProjectDuration());
    buildingSizeGUIID.setText("" + currentProject.getFacilitySize());
    intendedUseGUIID.setText(currentProject.getFacilityType());
  }

  /** Method is called from the 'Create Project' GUI.
   * It ensures that the proper default project specific data are loaded into proper .fxml elements in the GUI.
   * Parameters are references to the specific JavaFX elements the default data should be loaded into.
   * Author: K. Dashnaw
   */
  public void initializeCreateProjectGUI(TextField lengthGUIID, TextField widthGUIID, TextField durationGUIID, TextArea bridgeOrTunnelInfoGUIID, TextArea environmentalInfoGUIID)
  {
    RoadProject currentProject = (RoadProject) this.getSelectedProject();

    lengthGUIID.setText("" + currentProject.getRoadLength());
    widthGUIID.setText("" + currentProject.getRoadWidth());
    durationGUIID.setText("" + currentProject.getProjectDuration());
    bridgeOrTunnelInfoGUIID.setText(currentProject.getBridgeOrTunnelDetails());
    environmentalInfoGUIID.setText(currentProject.getEnvironmentalOrGeographicalChallenges());
  }

  /** <p>Edits a single construction project in the project management system.
   * Returns true if operation was successful. See more details about the edit code and sequence in the SubScene_EditProjectView.java
   * This method is mainly here for better understanding with the naming. addProject() is the important one, editProject merely sends
   * the information on to the addProject() method.</p>
   * <p><b>Author:</b>K. Dashnaw</p>
   */
  public boolean editProject(ConstructionProject project)
  {
    if(this.addProject(project))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /** <p>This method is used to refresh the contents of the Dashboard project object,
   * containing references to the projects shown on the Dashboard
   * It is called whenever projects are modified.</p>
   * */
  public void refreshDashboardProjects()
  {
    setDashboardProgressReport(new DashboardProgressReports());
    for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      if(getAllProjectsList().get(i).isDashboardProject() && getDashboardProgressReports().getCurrentCapacity() <= getDashboardProgressReports().getMaxCapacity() && getDashboardProgressReports().getCurrentCapacity() > 0)
      {
        getDashboardProgressReports().addProgressReport(getAllProjectsList().get(i).getProgressReport());
      }
    }
  }

  /** <p>Removes a single construction project from the project management system.
   * Returns true if operation was successful.</p>
   * <p><b>Author:</b> Zakaria</p>
   */
  public boolean removeProject(ConstructionProject project)
  {
    // Checking if the project is not in the list.
    if (!getAllProjectsList().contains(project))
    {
      System.out.println("Project not found in the system!");
      setInitializationErrorMessage("Project not found in the system!");
      return false;
    }

    // if desired project is found, then removing it from the list.
    if (getAllProjectsList().remove(project))
    {
      //The updated list gets saved.
      save();
      System.out.println("Project have been removed: " + project.getProjectInformation().getProjectName());
      setInitializationErrorMessage("");
      return true;
    }
    else
    {
      // In case a removal fails, an error message gets printed.
      System.out.println("Error occurred!");
      setInitializationErrorMessage("Error occurred!");
      return false;
    }
  }

  /** Sets the current filtering options that are applied when projects are displayed in the GUI.
   * I.e.: For instance a filtering option could be to only show projects with a budget between 100,000 and 500,000.
   * Returns true if operation was successful.
   * Author:
   */
  public ArrayList<ConstructionProject> filterProject(/*double minBudget, double maxBudget, int minDuration, int maxDuration*/)
  {
    ArrayList<ConstructionProject> projectListCopy = new ArrayList<>();

    for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      projectListCopy.add(getAllProjectsList().get(i).copy());
    }

    //TODO: Missing final filtering steps.
    //SET THE ACTUAL FILTERS.
    /*for (int i = 0; i < getAllProjectsList().size(); i++)
    {
      if(getAllProjectsList().get(i).getFinances().getTotalBudget() < minBudget || getAllProjectsList().get(i).getFinances().getTotalBudget() > maxBudget)
      {
        projectListCopy.remove(getAllProjectsList().get(i));
      }
      else if()
      {

      }
    }*/

    return projectListCopy;
  }

  /** Sets the current default project settings that are applied when new projects are created.
   * Returns true if operation was successful.
   * Author: Zakaria - Method not needed!
   * */

  public boolean editDefaultProjectSettings()
  {
    return true;
  }

  /** <p>Enables data persistence across sessions by saving relevant system information to a file.
   *  Note: Validation of data integrity should be conducted prior to calling this save method,
   *  ideally as early as while adding data to the model field attributes.</p>
   *  <p><b>Author:</b> K. Dashnaw</p>
   *  @return true if operation was successful, false if unsuccessful.
   * */
  public boolean save ()
  {
    Object[] objectList = new Object[7]; //Pack all the different system Object into a single Object array before saving.

    objectList[0] = this.getAllProjectsList();
    objectList[1] = this.getDashboardProgressReports();
    objectList[2] = this.getDefaultResidentialSettings();
    objectList[3] = this.getDefaultCommercialSettings();
    objectList[4] = this.getDefaultIndustrialSettings();
    objectList[5] = this.getDefaultRoadSettings();
    objectList[6] = this.getFileManager().getLastDataSaveTime();

    if (this.getFileManager().writeToBinary(objectList))
    {
      System.out.println("Data saved successfully");
      return true; //Saving was successful
    }
    else
    {
      return false; //Saving failed.
    }
  }

  /** <p>Enables data persistence across sessions by loading relevant system information from a binary file.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @return true if operation was successful.
   * */
  @SuppressWarnings("unchecked")
  public boolean load ()
  {
    Object[] objectList = new Object[7];
    boolean returnValue = true;

    //Try to load the system binary file:
    try
    {
      objectList = getFileManager().readFromBinary();
    }
    catch(FileNotFoundException error1)
    {
      System.out.println("Unable to load local system file: " + error1);
      returnValue = false;
    }
    catch(IOException error2)
    {
      System.out.println("Unable to load. Unknown exception: " + error2);
      returnValue = false;
    }
    catch(ClassNotFoundException | NullPointerException error3)
    {
      System.out.println("Unable to load. Data is corrupted");
      returnValue = false;
    }

    //If the above operation failed, we create new project files.
    if(!returnValue)
    {
      setAllProjectsList(new ArrayList<>());
      setDashboardProgressReport(new DashboardProgressReports());
      setDefaultResidentialSettings(new DefaultResidentialSettings(9,1,1,1,false));
      setDefaultCommercialSettings(new DefaultCommercialSettings(1,18));
      setDefaultIndustrialSettings(new DefaultIndustrialSettings(30));
      setDefaultRoadSettings(new DefaultRoadSettings("0","None",18));
    }
    else
    {
      //Now we evaluate the data integrity of the loaded classes.
      try
      {
        setAllProjectsList((ArrayList<ConstructionProject>) objectList[0]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate construction project files. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setAllProjectsList(new ArrayList<>());
        returnValue = false;
      }
      try
      {
        setDashboardProgressReport((DashboardProgressReports) objectList[1]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate DashBoard progress report files. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDashboardProgressReport(new DashboardProgressReports());
        returnValue = false;
      }
      try
      {
        setDefaultResidentialSettings((DefaultResidentialSettings) objectList[2]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Residential Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultResidentialSettings(new DefaultResidentialSettings(9,1,1,1,false));
        returnValue = false;
      }
      try
      {
        setDefaultCommercialSettings((DefaultCommercialSettings) objectList[3]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Commercial Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultCommercialSettings(new DefaultCommercialSettings(1,18));
        returnValue = false;
      }
      try
      {
        setDefaultIndustrialSettings((DefaultIndustrialSettings) objectList[4]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Industrial Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultIndustrialSettings(new DefaultIndustrialSettings(30));
        returnValue = false;
      }
      try
      {
        setDefaultRoadSettings((DefaultRoadSettings) objectList[5]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate Default Road Settings. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        setDefaultRoadSettings(new DefaultRoadSettings("0","None",18));
        returnValue = false;
      }
      try
      {
        getFileManager().setLastDataSaveTime((MyDate) objectList[6]);
      }
      catch(Throwable error)
      {
        //Since we are performing an unchecked cast above, I expect that some error might pop up. If so we catch it here.
        System.out.println("Unable to validate last save date. Initializing new files instead!");
        //Data in this class must be corrupted. Re-initialize a clean class!
        getFileManager().setLastDataSaveTime(MyDate.now());
        returnValue = false;
      }
    }
  return returnValue;
  }

  /** <p>Exports progress reports for all projects to JSON files for use on the Company Homepage.<br>
   * File references are defined directly in FileIO.<br>
   * Errors are handled within this method, and confirmation messages relating to the successful or failed exported are written to
   * the 'initializationErrorMessage' attribute in this class.<br> This allows for the scenes calling this method to retrieve
   * these messages and display in the GUI console.</p>
   * <p>Note: This method handles the conversion of ongoing and finished projects relevant information into json compatible strings / json array.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void exportAsJson()
  {
    //First we separate our projects into 2 categories, the finished ones and the ongoing ones.
    //Se filter out any confidential projects in this phase also.
    ArrayList<ConstructionProject> finishedProjectsList = new ArrayList<>();
    ArrayList<ConstructionProject> ongoingProjectsList = new ArrayList<>();
    String finishedProjectsFileName = "exported-FinishedProjects";
    String ongoingProjectsFileName = "exported-OngoingProjects";

    //Run through projects:
    for (int i = 0; i < this.getAllProjectsList().size(); i++)
    {
      if(this.getAllProjectsList().get(i).isProjectFinished() && !(this.getAllProjectsList().get(i).isProjectConfidential()))
      {
        //This is a non-confidential finished project.
        finishedProjectsList.add(this.getAllProjectsList().get(i));
      }
      else
      {
        //This is a non-confidential ongoing project.
        ongoingProjectsList.add(this.getAllProjectsList().get(i));
      }
    }

    //Projects have now been isolated - an unnecessary/confidential projects filtered out. We now extrapolate the data needed for the website.
    //Since json, like xml, is a text-based document, we need to ensure that our data can be represented as a String before writing.
    //Below we format the information as a proper json array with the required information.

    //Add all needed data points from ongoing projects to our String list.
    List<String> ongoingStringList = new ArrayList<>();
    char asciiQuote = '"';
    String stringData;
    for (int i = 0; i < ongoingProjectsList.size(); i++)
    {
      stringData = "{";
      stringData += asciiQuote + "ProjectType" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectType() + asciiQuote + ","; //ProjectType
      stringData += asciiQuote + "ProjectName" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectInformation().getProjectName() + asciiQuote + ","; //Project Name
      stringData += asciiQuote + "ProjectDescription" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectInformation().getProjectDescription() + asciiQuote + ","; //Project Description
      stringData += asciiQuote + "PhotoURL" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectInformation().getPhotoURL() + asciiQuote + ","; //Project photo URL
      stringData += asciiQuote + "ProjectStartDate" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectStartDate() + asciiQuote + ","; //Project start date
      stringData += asciiQuote + "ProjectEndDate" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getProjectEndDate() + asciiQuote + ","; //Project expected finish date
      stringData += asciiQuote + "ManHoursSpent" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getHumanRessources().getManHoursSpent() + asciiQuote + ","; //Project man-hours spent
      stringData += asciiQuote + "TotalManHoursNeeded" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getHumanRessources().getTotalManHoursNeeded() + asciiQuote + ","; //Total man-hours expected to use.
      stringData += asciiQuote + "MaterialExpences" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getFinances().getMaterialExpences() + asciiQuote + ","; //Expenses so far.
      stringData += asciiQuote + "TotalBudget" + asciiQuote + ":" + asciiQuote + ongoingProjectsList.get(i).getFinances().getTotalBudget() + asciiQuote; //Total budget/price.
      stringData += "}";

      if(i != ongoingProjectsList.size()-1)
      {
        stringData += ",";
      }
      ongoingStringList.add(stringData);
    }

    //Add all needed data points from finished projects to our String list.
    List<String> finishedStringList = new ArrayList<>();
    for (int i = 0; i < finishedProjectsList.size(); i++)
    {
      stringData = "{";
      stringData += asciiQuote + "ProjectType" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectType() + asciiQuote + ","; //ProjectType
      stringData += asciiQuote + "ProjectName" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectInformation().getProjectName() + asciiQuote + ","; //Project Name
      stringData += asciiQuote + "ProjectDescription" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectInformation().getProjectDescription() + asciiQuote + ","; //Project Description
      stringData += asciiQuote + "PhotoURL" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectInformation().getPhotoURL() + asciiQuote + ","; //Project photo URL
      stringData += asciiQuote + "ProjectEndDate" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getProjectEndDate() + asciiQuote + ","; //Project finish date
      stringData += asciiQuote + "TotalManHoursNeeded" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getHumanRessources().getTotalManHoursNeeded() + asciiQuote + ","; //Total man-hours expected to use.
      stringData += asciiQuote + "TotalBudget" + asciiQuote + ":" + asciiQuote + finishedProjectsList.get(i).getFinances().getTotalBudget() + asciiQuote; //Total budget/price.
      stringData += "}";

      if(i != finishedProjectsList.size()-1)
      {
        stringData += ",";
      }

      finishedStringList.add(stringData);
    }

    //Convert to single string compatible with the parser:
    String stringWrapperStart = "{" + asciiQuote + "ongoingProjectArray" + asciiQuote + ":[";
    String stringWrapperEnd = "]}";
    String finalOngoingStr = "";
    for (int i = 0; i < ongoingStringList.size(); i++)
    {
      finalOngoingStr += ongoingStringList.get(i);
    }
    finalOngoingStr = stringWrapperStart + finalOngoingStr + stringWrapperEnd;

    stringWrapperStart = "{" + asciiQuote + "finishedProjectArray" + asciiQuote + ":[";
    stringWrapperEnd = "]}";
    String finalFinishedStr = "";
    for (int i = 0; i < finishedStringList.size(); i++)
    {
      finalFinishedStr += finishedStringList.get(i);
    }
    finalFinishedStr = stringWrapperStart + finalFinishedStr + stringWrapperEnd;

    //Check if these are null
    if(finalOngoingStr.length() <= stringWrapperStart.length() + stringWrapperEnd.length())
    {
      finalOngoingStr = "null";
    }

    if(finalFinishedStr.length() <= stringWrapperStart.length() + stringWrapperEnd.length())
    {
      finalFinishedStr = "null";
    }

    //Export data:
    try
    {
      getFileManager().export(finalOngoingStr, ongoingProjectsFileName, ".json"); //Exports ongoing projects
      getFileManager().export(finalFinishedStr, finishedProjectsFileName, ".json"); //Exports finished projects

      //Export was successful
      System.out.println("EXPORT: Successfully exported non-confidential finished and ongoing projects to json.");
      setInitializationErrorMessage("EXPORT: Successfully exported non-confidential finished and ongoing projects to json.");
    }
    catch(FileNotFoundException error)
    {
      System.out.println("ERROR: Unable to export data. Parsing failed." + error);
      setInitializationErrorMessage("ERROR: Failed to export data. Exception occurred. Please contact support for more details.");
    }
    catch(RuntimeException error2)
    {
      System.out.println("ERROR: RUNTIME EXCEPTION OCCURRED WHILE EXPORTING DATA!" + error2);
      setInitializationErrorMessage("ERROR: Failed to export data. Exception occurred. Please contact support for more details.");
    }

  }

}

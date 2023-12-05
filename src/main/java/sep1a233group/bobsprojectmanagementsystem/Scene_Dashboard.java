package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/** This class controls the GUI related view and methods concerning the "Project Dashboard" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class Scene_Dashboard implements Scene_ControllerInterface
{
  private ConstructionProject project1;
  private ConstructionProject project2;
  private ConstructionProject project3;
  private ConstructionProject project4;

  private ProgressReport report1;
  private ProgressReport report2;
  private ProgressReport report3;
  private ProgressReport report4;
  @FXML Text address1, address2, address3, address4;
  @FXML Label projectType1, projectType2, projectType3, projectType4;
  @FXML Label tlfNo1, tlfNo2, tlfNo3, tlfNo4;
  @FXML Label hoursStatus1, hoursStatus2, hoursStatus3, hoursStatus4;
  @FXML GridPane slot2;

  @FXML ProgressBar hoursBar1, hoursBar2, hoursBar3, hoursBar4;
  @FXML Label hoursSpent1, hoursSpent2, hoursSpent3, hoursSpent4;
  @FXML Label expectedHours1, expectedHours2, expectedHours3, expectedHours4;
  @FXML Label budgetStatus1, budgetStatus2, budgetStatus3, budgetStatus4;
  @FXML ProgressBar budgetBar1, budgetBar2, budgetBar3, budgetBar4;
  @FXML Label expenses1, expenses2, expenses3, expenses4;
  @FXML Label budget1, budget2, budget3, budget4;
  @FXML Label timelineStatus1, timelineStatus2, timelineStatus3, timelineStatus4;
  @FXML ProgressBar timelineBar1, timelineBar2, timelineBar3, timelineBar4;
  @FXML Label startDate1, startDate2, startDate3, startDate4;
  @FXML Label expectedDate1, expectedDate2, expectedDate3, expectedDate4;


  @FXML TextField GUI_Console; //textField in the gui, where messages are shown to the user.
  private MainModel activeModel;
  private SceneController sceneController;

  public void hideInactiveSlots()
  {
    slot2.setVisible(false);
  }

  public void resetDataFieldsToDefault()
  {
    address1.setText("#1: Empty");
  }

  public void displayProgressReports()
  {
    budgetBar1.setProgress(.75);

    project1 = null;
    project2 = null;
    project3 = null;
    project4 = null;
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (activeModel.getAllProjectsList().get(i).isDashboardProject())
      {
        if (project1 == null)
        {
          project1 = activeModel.getAllProjectsList().get(i);
          continue;
        }
        if (project2 == null)
        {
          project2 = activeModel.getAllProjectsList().get(i);
          continue;
        }
        if (project3 == null)
        {
          project3 = activeModel.getAllProjectsList().get(i);
          continue;
        }
        if (project4 == null)
        {
          project4 = activeModel.getAllProjectsList().get(i);
          break;
        }
      }
    }
    if (project1 != null){displayReport1();}
    if (project2 != null){displayReport2();}
    if (project3 != null){displayReport3();}
    if (project4 != null){displayReport4();}
  }

  public void displayReport1()
  {
    report1 = project1.generateProgressReport();
    projectType1.setText(project1.getProjectType());
    address1.setText(report1.getProjectAddress().toString());
    tlfNo1.setText(report1.getCustomer().getPhoneNumberPrefix() + " " + Integer.toString(report1.getCustomer().getPhoneNumber()));
    hoursSpent1.setText(Double.toString(report1.getProjectRessources().getManHoursSpent()));
    expectedHours1.setText(Double.toString(report1.getProjectRessources().getTotalManHoursNeeded()));
      if (report1.getProjectRessources().getManHoursSpent() > report1.getProjectRessources().getTotalManHoursNeeded())
      {
        hoursStatus1.setText("Over expected");
      }
      else
      {
        hoursStatus1.setText("Within expected");
      }
    expenses1.setText(Double.toString(report1.getProjectFinances().getMaterialExpences()));
    budget1.setText(Double.toString(report1.getProjectFinances().getTotalBudget()));
      if (report1.getProjectFinances().getMaterialExpences() > report1.getProjectFinances().getTotalBudget())
      {
        budgetStatus1.setText("Over budget");
      }
      else
      {
        budgetStatus1.setText("Within budget");
      }
    startDate1.setText(report1.getProjectStartDate().toString());
    expectedDate1.setText(report1.getProjectEndDate().toString());
      if (report1.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus1.setText("Behind schedule");
      }
      else
      {
        timelineStatus1.setText("On track");
      }
  }
  public void untrackProject1()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project1.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }

  public void displayReport2()
  {
    report2 = project2.generateProgressReport();
    projectType2.setText(project2.getProjectType());
    address2.setText(report2.getProjectAddress().toString());
    tlfNo2.setText(report2.getCustomer().getPhoneNumberPrefix() + " " + Integer.toString(report2.getCustomer().getPhoneNumber()));
    hoursSpent2.setText(Double.toString(report2.getProjectRessources().getManHoursSpent()));
    expectedHours2.setText(Double.toString(report2.getProjectRessources().getTotalManHoursNeeded()));
      if (report2.getProjectRessources().getManHoursSpent() > report2.getProjectRessources().getTotalManHoursNeeded())
      {
        hoursStatus2.setText("Over expected");
      }
      else
      {
        hoursStatus2.setText("Within expected");
      }

    expenses2.setText(Double.toString(report2.getProjectFinances().getMaterialExpences()));
    budget2.setText(Double.toString(report2.getProjectFinances().getTotalBudget()));
      if (report2.getProjectFinances().getMaterialExpences() > report2.getProjectFinances().getTotalBudget())
      {
        budgetStatus2.setText("Over budget");
      }
      else
      {
        budgetStatus2.setText("Within budget");
      }

    startDate2.setText(report2.getProjectStartDate().toString());
    expectedDate2.setText(report2.getProjectEndDate().toString());
      if (report2.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus2.setText("Behind schedule");
      }
      else
      {
        timelineStatus2.setText("On track");
      }
  }

  public void untrackProject2()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project2.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }

  public void displayReport3()
  {
    report3 = project3.generateProgressReport();
    projectType3.setText(project3.getProjectType());
    address3.setText(report3.getProjectAddress().toString());
    tlfNo3.setText(report3.getCustomer().getPhoneNumberPrefix() + " " + Integer.toString(report3.getCustomer().getPhoneNumber()));
    hoursSpent3.setText(Double.toString(report3.getProjectRessources().getManHoursSpent()));
    expectedHours3.setText(Double.toString(report3.getProjectRessources().getTotalManHoursNeeded()));
      if (report3.getProjectRessources().getManHoursSpent() > report3.getProjectRessources().getTotalManHoursNeeded())
      {
      hoursStatus3.setText("Over expected");
      }
      else
      {
        hoursStatus3.setText("Within expected");
      }
    expenses3.setText(Double.toString(report3.getProjectFinances().getMaterialExpences()));
    budget3.setText(Double.toString(report3.getProjectFinances().getTotalBudget()));
      if (report3.getProjectFinances().getMaterialExpences() > report3.getProjectFinances().getTotalBudget())
      {
        budgetStatus3.setText("Over budget");
      }
      else
      {
        budgetStatus3.setText("Within budget");
      }

    startDate3.setText(report3.getProjectStartDate().toString());
    expectedDate3.setText(report3.getProjectEndDate().toString());
      if (report3.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus3.setText("Behind schedule");
      }
      else
      {
        timelineStatus3.setText("On track");
      }
  }

  public void untrackProject3()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project3.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }
  public void displayReport4()
  {
    report4 = project4.generateProgressReport();
    projectType4.setText(project4.getProjectType());
    address4.setText(report4.getProjectAddress().toString());
    tlfNo4.setText(report4.getCustomer().getPhoneNumberPrefix() + " " + Integer.toString(report4.getCustomer().getPhoneNumber()));
    hoursSpent4.setText(Double.toString(report4.getProjectRessources().getManHoursSpent()));
    expectedHours4.setText(Double.toString(report4.getProjectRessources().getTotalManHoursNeeded()));
      if (report4.getProjectRessources().getManHoursSpent() > report4.getProjectRessources().getTotalManHoursNeeded())
      {
        hoursStatus4.setText("Over expected");
      }
      else
      {
        hoursStatus4.setText("Within expected");
      }
    expenses4.setText(Double.toString(report4.getProjectFinances().getMaterialExpences()));
    budget4.setText(Double.toString(report4.getProjectFinances().getTotalBudget()));
      if (report4.getProjectFinances().getMaterialExpences() > report4.getProjectFinances().getTotalBudget())
      {
        budgetStatus4.setText("Over budget");
      }
      else
      {
        budgetStatus4.setText("Within budget");
      }

    startDate4.setText(report4.getProjectStartDate().toString());
    expectedDate4.setText(report4.getProjectEndDate().toString());
      if (report4.getProjectEndDate().isBefore(MyDate.now()))
      {
        timelineStatus4.setText("Behind schedule");
      }
      else
      {
        timelineStatus4.setText("On track");
      }
  }

  public void untrackProject4()
  {
    for (int i = 0; i < activeModel.getAllProjectsList().size(); i++)
    {
      if (project1.equals(activeModel.getAllProjectsList().get(i)))
      {
        activeModel.getAllProjectsList().get(i).setDashboardProject(false);
        break;
      }
    }
    refresh();
  }

  public ProgressReport getReport1()
  {
    return report1;
  }

  public ProgressReport getReport2()
  {
    return report2;
  }

  public ProgressReport getReport3()
  {
    return report3;
  }

  public ProgressReport getReport4()
  {
    return report4;
  }

  public MainModel getActiveModel()
  {
    return activeModel;
  }

  public ConstructionProject getProject1()
  {
    return project1;
  }

  public ConstructionProject getProject2()
  {
    return project2;
  }

  public ConstructionProject getProject3()
  {
    return project3;
  }

  public ConstructionProject getProject4()
  {
    return project4;
  }

  /** <p>Returns a reference to the GUI_Console on this page.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }
  /** <p>Sets/Initializes the GUI_Console on this page.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /** <p>Returns a SceneController object containing a reference to this stages parent controller</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /** <p>Sets/Initializes the SceneController object containing a reference to this stages parent controller</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  /** <p>Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)</p>
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.activeModel = activeModel;
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    resetDataFieldsToDefault();
    displayProgressReports();
    hideInactiveSlots();


    System.out.println("Project Dashboard Scene is now active");
  }

  /** <p>Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)</p>
   * */
  @Override public void refresh()
  {
    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    resetDataFieldsToDefault();
    displayProgressReports();
    hideInactiveSlots();

    this.getActiveModel().refreshDashboardProjects();

    System.out.println("Dashboard now the active stage.");
  }

  /** <p>This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.</p>*/
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    String buttonText = ((Button)actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /** <p>This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.</p>*/
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();

    //Update GUI Console message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** <p>This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.</p>*/
  public void exitApplication()
  {
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  public void updateProject1()
  {
    if(this.getProject1() != null)
    {
      this.QuickUpdate_Project(1);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }

  public void updateProject2()
  {

    if(this.getProject2() != null)
    {
      this.QuickUpdate_Project(2);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }

  public void updateProject3()
  {
    if(this.getProject3() != null)
    {
      this.QuickUpdate_Project(3);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }

  public void updateProject4()
  {
    if(this.getProject4() != null)
    {
      this.QuickUpdate_Project(4);
    }
    else
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! ");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }


  public void QuickUpdate_Project(int reportNumber)
  {
    try
    {
      //Find index position of the selected progress report:
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        if(reportNumber == 1 && this.getProject1().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
        else if(reportNumber == 2 && this.getProject2().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
        else if(reportNumber == 3 && this.getProject3().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
        else if(reportNumber == 4 && this.getProject4().equals(this.getActiveModel().getAllProjectsList().get(i)))
        {
          this.getActiveModel().setProjectIndexPosition(i);
        }
      }

      String projectType = this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).getProjectType();

      //Set active project. Make sure we set COPIES, or else unintended changes might be saved directly to the real project before the user presses the save button!
      if(projectType.equalsIgnoreCase("residential"))
      {
        this.getActiveModel().setSelectedProject((ResidentialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }
      else if(projectType.equalsIgnoreCase("commercial"))
      {
        this.getActiveModel().setSelectedProject((CommercialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }
      else if(projectType.equalsIgnoreCase("industrial"))
      {
        this.getActiveModel().setSelectedProject((IndustrialProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }
      else if(projectType.equalsIgnoreCase("road"))
      {
        this.getActiveModel().setSelectedProject((RoadProject) this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()).copy());
      }


      //Get the x/y position of the active window:
      double x = this.getSceneController().getActiveStage().getX();
      double y = this.getSceneController().getActiveStage().getY();

      //Create the update window:
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Projects_QuickUpdateProjectView.fxml"));
      try
      {
        Stage newStage = new Stage();
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(this.getSceneController().getActiveStage());

        Scene updateScene = new Scene(fxmlLoader.load(), 800, 250);

        Scene_ControllerInterface controller = fxmlLoader.getController();
        controller.init(this.getActiveModel(), this.getSceneController());

        newStage.setScene(updateScene);
        newStage.setResizable(false);
        newStage.setTitle("Update project");

        //Set the x/y position of the window to be close to the calling button. This avoids the confirmation popping up on another screen, if more than 1 screen is connected.
        newStage.setX(x / 2 - 700);
        newStage.setY(y / 2 + 350);

        // show the dialog
        newStage.showAndWait();
        refresh();
      }
      catch(IOException error)
      {
        System.out.println("Unable to display update pop-up. Something went wrong.");
      }
    }
    catch(NullPointerException error)
    {
      this.getSceneController().setGUI_ConsoleMessage("Button does nothing! No project assigned to this dashboard position! " + error);
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }

}


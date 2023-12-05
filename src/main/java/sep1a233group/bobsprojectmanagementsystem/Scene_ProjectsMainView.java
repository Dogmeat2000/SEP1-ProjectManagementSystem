package sep1a233group.bobsprojectmanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

/** This class controls the GUI related view and methods concerning the "View Projects" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class Scene_ProjectsMainView implements Scene_ControllerInterface
{
  //@FXML TableColumn colProjectType;
  @FXML TableView<ConstructionProject> mainTableView;
  @FXML TableColumn<ConstructionProject, String> colProjectType;
  @FXML TableColumn<ConstructionProject, String> colProjectStatus;
  @FXML TableColumn<ConstructionProject, String> colProjectDeadline;
  @FXML TableColumn<ConstructionProject, String> colProjectBudget;
  @FXML TableColumn<ConstructionProject, String> colProjectExpenses;
  @FXML TableColumn<ConstructionProject, String> colProjectManHoursUsed;
  @FXML TableColumn<ConstructionProject, String> colProjectManHoursTotal;
  @FXML TableColumn<ConstructionProject, String> colProjectConfidentiality;
  @FXML TableColumn<ConstructionProject, String> colProjectIsDashboardProject;


  //@FXML TextArea viewArea;
  @FXML TextField GUI_Console;
  private MainModel activeModel;
  private SceneController sceneController;
  private ArrayList<ConstructionProject> projectListCopy;

  /** Returns a reference to the GUI_Console on this page.
   * Author: K. Dashnaw
   * */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }
  /** Sets/Initializes the GUI_Console on this page.
   * Author: K. Dashnaw
   * */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /** Returns a SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   * */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /** Sets/Initializes the SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   * */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  public ArrayList<ConstructionProject> getProjectListCopy()
  {
    return projectListCopy;
  }

  public void setProjectListCopy(ArrayList<ConstructionProject> projectListCopy)
  {
    this.projectListCopy = projectListCopy;
  }

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    //Sets necessary attributes:
    setActiveModel(activeModel);
    setSceneController(sceneController);
    setGUI_Console(this.GUI_Console);
    GUI_Console.setText(this.getSceneController().getGUI_ConsoleMessage());
    getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    refresh();
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  @Override public void refresh()
  {
    setProjectListCopy(this.getActiveModel().filterProject(/* TODO: ADD FILTER PARAMETERS*/));

    mainTableView.getItems().clear();

    if(!getProjectListCopy().isEmpty())
    {
      displayProjects();
    }

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    System.out.println("Project Table View is now the active stage.");
  }

  public MainModel getActiveModel()
  {
    return activeModel;
  }

  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  public void displayProjects()
  {
    mainTableView.setEditable(false);
    this.setProjectListCopy(this.getActiveModel().filterProject(/* INSERT FILTERS filterArray[0], filterArray[1]*/));

    colProjectType.setCellValueFactory(new PropertyValueFactory<>("projectType"));
    colProjectStatus.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isProjectFinished())));
    colProjectDeadline.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProjectEndDate())));
    colProjectBudget.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getFinances().getTotalBudget())));
    colProjectExpenses.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getFinances().getMaterialExpences())));
    colProjectManHoursUsed.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getHumanRessources().getManHoursSpent())));
    colProjectManHoursTotal.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getHumanRessources().getTotalManHoursNeeded())));
    colProjectConfidentiality.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isProjectConfidential())));
    colProjectIsDashboardProject.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isDashboardProject())));

    mainTableView.getItems().addAll(getProjectListCopy());
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

  /**<p>This method initializes the edit sequence allowing the user to modify existing projects in the system.
   * It initializes a temporary copy of the user selected project that collects changes before replacing the
   * originally selected project with the modified one on user confirmation.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void editProjectSelected(ActionEvent actionEvent)
  {

    //Find selected project in project array:
    /*for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
    {
      if(this.getActiveModel().getAllProjectsList().get(i).equals( SOMETHING!!! ))
    }*/

    //Placeholder for now:
    String projectType = this.getActiveModel().getAllProjectsList().get(0).getProjectType();
    this.getActiveModel().setProjectIndexPosition(0);

    //Set active project. Make sure we set COPIES, or else unintended changes might be saved directly to the real project before the user presses the save button!
    if(projectType.equalsIgnoreCase("residential"))
    {
      this.getActiveModel().setSelectedProject((ResidentialProject) this.getActiveModel().getAllProjectsList().get(0).copy());
    }
    else if(projectType.equalsIgnoreCase("commercial"))
    {
      this.getActiveModel().setSelectedProject((CommercialProject) this.getActiveModel().getAllProjectsList().get(0).copy());
    }
    else if(projectType.equalsIgnoreCase("industrial"))
    {
      this.getActiveModel().setSelectedProject((IndustrialProject) this.getActiveModel().getAllProjectsList().get(0).copy());
    }
    else if(projectType.equalsIgnoreCase("road"))
    {
      this.getActiveModel().setSelectedProject((RoadProject) this.getActiveModel().getAllProjectsList().get(0).copy());
    }

    //Open Window
    try
    {
      this.openWindow(actionEvent);
    }
    catch (IOException error)
    {
      this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to edit selected project. Reason unknown.");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }
}

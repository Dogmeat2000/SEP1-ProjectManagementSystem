package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/** <p>This class controls the GUI related view and methods concerning the "quick update project" GUI functionality.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * <p><b>Author:</b> K. Dashnaw</p>
 */
public class SubScene_QuickUpdateProjectView implements Scene_ControllerInterface
{

  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML Button buttonEditProject;
  @FXML Button buttonCancel;
  @FXML CheckBox checkBox_AddToDashBoard;
  @FXML GridPane gridCommonProjectDataContainer;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;

  /** <p>Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)</p>
   */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    //Sets necessary attributes:
    setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    refresh();
  }

  /** <p>Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  @Override public void refresh()
  {
    //Refresh the page, as it is shown on a clean load:

    System.out.println("Quick Update Scene is now the active stage.");

    //Loop though the shared project data gridPane and insert selected project values inside TextFields - while also ensuring fields are reset for previous views of this screen.
    for (Node node : gridCommonProjectDataContainer.getChildren())
    {
     if (node instanceof TextField)
      {
        ((TextField) node).setText(this.loadProjectData_String((TextField) node));
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
     else if(node instanceof CheckBox)
     {
       ((CheckBox) node).setSelected(this.loadProjectData_Checkbox((CheckBox) node));
       ((CheckBox) node).setDisable(false);
     }
    }
    checkBox_AddToDashBoard.setText("Track on Dashboard");

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** <p>Returns a reference to the GUI_Console on this page.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }

  /** <p>Sets/Initializes the GUI_Console on this page.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /** <p>Returns a SceneController object containing a reference to this stages parent controller</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /** <p>Sets/Initializes the SceneController object containing a reference to this stages parent controller</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  /** <p>Returns a reference to the active project model currently providing project related functionality.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public MainModel getActiveModel()
  {
    return activeModel;
  }

  /** <p>Sets/Initializes the reference to the active project model currently providing project related functionality.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  /** <p>This method prompts a warning to the user that unsaved data will be lost on change of view-screen.
   * And then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Prompt the user to confirm they wish to leave this screen.
    if (!(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the edit view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /** <p>Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the edit view,
   * which could otherwise result loss of entered non-saved data.!</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public boolean createPromptWindow()
  {
    String confirmationAction = this.getSceneController().createPromptWindow("WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel aborted.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel aborted.");
        return false; //do not proceed with cancel operation
      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel confirmed. No changes were saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. No changes were saved.");

        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
        return true; //Proceed with cancel operation
      default:
        return false; //do not proceed with cancel operation
    }
  }

  /** <p>This method returns the selected projects Data Field values that correspond with the shared project data fields.
   * This is intended for insertion into the on-screen editable textFields, so that already existing data is pre-entered for the user.</p>
   * <p><b>Author:</b> K. Dashnaw </p>
   * @param node is a reference to the TextField node in which the returned String value shall be inserted.
   * @return A String value intended to be inserted into the above TextField node.
   * */
  public String loadProjectData_String (TextField node)
  {
    switch (node.getPromptText())
    {
      case "Man-Hours in hours":
        return "" + this.getActiveModel().getSelectedProject().getHumanRessources().getManHoursSpent();
      case "Est. total number of hours":
        return "" + this.getActiveModel().getSelectedProject().getHumanRessources().getTotalManHoursNeeded();
      case "Expenses in USD":
        return "" + this.getActiveModel().getSelectedProject().getFinances().getMaterialExpences();
      case "Budget in USD":
        return "" + this.getActiveModel().getSelectedProject().getFinances().getTotalBudget();
      default:
        break;
    }
    return "";
  }

  /** <p>This method returns the selected projects Data Field values that correspond with the shared project data fields.
   * This is intended for insertion into the on-screen clickable CheckBoxes, so that already existing data is pre-selected for the user.</p>
   * <p><b>Author:</b> K. Dashnaw </p>
   * @param node is a reference to the CheckBox node in which the returned boolean value shall be inserted.
   * @return A boolean value intended to be inserted into the above CheckBox node.
   * */
  public boolean loadProjectData_Checkbox (CheckBox node)
  {
    switch (node.getText())
    {
      case "Mark project as completed":
        return this.getActiveModel().getSelectedProject().isProjectFinished();
      case "Mark project as confidential":
        return this.getActiveModel().getSelectedProject().isProjectConfidential();
      case "Track on Dashboard":
        return this.getActiveModel().getSelectedProject().isDashboardProject();
      default:
        break;
    }
    return false;
  }

  /** <p>Returns FALSE if TextField is either empty OR a string OR a negative number/digit, and TRUE is TextField is none of either.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.</p>
   * <p><b>This method MUST be run on a TextField in order to avoid potential crashes/errors.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public boolean validate_NotEmpty_NotString_NotNegative(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty_NotString_NotNegative(keyNode, "Error in data values while creating new project. Please review and correct!"))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }

  /** <p>Method disabled the "create project" button and is used in conjunction with the validation fields to ensure that the
   * "create project" button only is enabled when proper data is ready to be added to the system.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  private void resetValidation()
  {
    buttonEditProject.setDisable(true);

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());

  }

  /** <p>Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.
   * Method adds the received KeyEvent node to the project data.
   * It receives a "KeyEvent node" and parses this to a TextField.</p>
   * <p><b>Warning: KeyEvent node must have a source type of TextField, else errors will occur.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void addTemporaryProjectData(KeyEvent keyNode)
  {
    TextField userInput = (TextField) keyNode.getSource();
    addCommonProjectData(this.getActiveModel().getSelectedProject(), userInput);
  }

  /** <p>Is called from "On Action" EventHandlers in the .fxml scene
   * Method adds the received ActionEvent node to the project data.
   * It receives a "ActionEvent node" parses this as a "CheckBox" and checks if it is selected or not.<br>
   * <b>Warning: ActionEvent node must have a source type of CheckBox, else errors will occur.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    buttonEditProject.setDisable(true);
    CheckBox checkBox = (CheckBox) actionEvent.getSource();
    TextField value = new TextField();
    value.setText(checkBox.getText());

    if (checkBox.isSelected())
    {
      value.setText(value.getText() + "_True");
    }
    else
    {
      value.setText(value.getText() + "_False");
    }
    value.setPromptText(value.getText());
    addCommonProjectData(this.getActiveModel().getSelectedProject(), value);
  }

  /** <p>This method is used in conjunction with the "addTemporaryProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so modifies the in the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the super class that all construction projects are a member of.
   * @param text This is a reference to the node containing the information to add to the project.
   * @return A boolean that is either true or false. True means that data was modified. False means that it was not.
   * */
  public boolean addCommonProjectData(ConstructionProject project, TextField text)
  {
    boolean dataAddedToProject = false;

    //TODO: Implement handling of picture file link!

    switch (text.getPromptText())
    {
      case "Man-Hours in hours":
        project.getHumanRessources().setManHoursSpent(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Est. total number of hours":
        project.getHumanRessources().setTotalManHoursNeeded(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Budget in USD":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.getFinances().setTotalBudget(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Expenses in USD":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.getFinances().setMaterialExpences(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Track on Dashboard_True", "Dashboard is already full._True":
        if(this.getActiveModel().getDashboardProgressReports().getCurrentCapacity() <= this.getActiveModel().getDashboardProgressReports().getMaxCapacity() && this.getActiveModel().getDashboardProgressReports().getCurrentCapacity() > 0)
        {
          project.setDashboardProject(true);
        }
        else
        {
          checkBox_AddToDashBoard.setSelected(false);
          checkBox_AddToDashBoard.setDisable(true);
          checkBox_AddToDashBoard.setText("Dashboard is already full.");
        }
        dataAddedToProject = true;
        break;
      case "Add project to Dashboard?_False", "Dashboard is already full._False":
        project.setDashboardProject(false);
        dataAddedToProject = true;
        break;
      case "Mark project as confidential_True":
        project.setProjectConfidentiality(true);
        dataAddedToProject = true;
        break;
      case "Mark project as confidential_False":
        project.setProjectConfidentiality(false);
        dataAddedToProject = true;
        break;
      case "Mark project as completed_True":
        project.setProjectFinished(true);
        dataAddedToProject = true;
        break;
      case "Mark project as completed_False":
        project.setProjectFinished(false);
        dataAddedToProject = true;
        break;
      default:
        break;
    }

    if(dataAddedToProject)
    {
      activateEditButton();
      return true;
    }
    else
    {
      return false;
    }
  }

   /** <p>This method checks if all required data fields have been filled out before enabling the "create project" button.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void activateEditButton()
  {
    //First check if project has all necessary values for creation:
    boolean dataIsMissing = false;
    ConstructionProject activeProject = this.getActiveModel().getSelectedProject();

    //Human Resources: Attributes checked are; Human Resources.
    if(activeProject.getHumanRessources().getTotalManHoursNeeded() == 0)
    {
      dataIsMissing = true;
    }
    //Finances: Attributes checked are; Total budget.
    else if(activeProject.getFinances().getTotalBudget() == 0)
    {
      dataIsMissing = true;
    }

    //If all required fields are present. Activate the edit button now.
    if(!dataIsMissing)
    {
      buttonEditProject.setDisable(false);
    }
    else
    {
      buttonEditProject.setDisable(true);
    }
  }

  /** <p>This method finalizes the project creation by calling relevant methods from the MainModel. It also asks the user to confirm their creation before finalizing.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void editProject()
  {
    //Prompt user to confirm:
    if(this.getSceneController().createPromptWindow("Save changes?").equalsIgnoreCase("confirmationPressed"))
    {
      //Remove the original project from the project_list, and replace with this modified one.
      this.getActiveModel().removeProject(this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()));

      //Add modified project to system.
      if(this.getActiveModel().editProject(this.getActiveModel().getSelectedProject()))
      {
        //Project successfully added!

        //Now we refresh the dashboard object container:
        this.getActiveModel().refreshDashboardProjects();

        //Update console with message:
        this.getSceneController().setGUI_ConsoleMessage("Project successfully modified. System saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

        Stage stage = (Stage) buttonEditProject.getScene().getWindow();
        stage.close();
      }
      else
      {
        //Project was NOT added successfully.
        //Update console with error message from the model:
        this.getSceneController().setGUI_ConsoleMessage(this.getActiveModel().getInitializationErrorMessage());
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      }
    }
  }
}